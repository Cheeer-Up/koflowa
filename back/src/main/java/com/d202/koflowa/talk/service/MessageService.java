package com.d202.koflowa.talk.service;

import com.d202.koflowa.common.domain.CDType;
import com.d202.koflowa.talk.domain.Message;
import com.d202.koflowa.talk.domain.MessageLog;
import com.d202.koflowa.talk.domain.Room;
import com.d202.koflowa.talk.dto.MessageDto;
import com.d202.koflowa.talk.dto.MessageLogDto;
import com.d202.koflowa.talk.dto.RoomDto;
import com.d202.koflowa.talk.exception.*;
import com.d202.koflowa.talk.repository.MessageLogRepository;
import com.d202.koflowa.talk.repository.MessageRepository;
import com.d202.koflowa.talk.repository.RoomRepository;
import com.d202.koflowa.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageLogRepository messageLogRepository;
    private final RoomRepository roomRepository;

    public List<MessageDto.Response> getMessageList(Long roomSeq){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Message> messageList = messageRepository.findAllByRoomOrderByCreatedTimeDesc(
                roomRepository.findByRoomSeqAuthWithUserSeq(roomSeq, user.getSeq()).orElseThrow(() -> new RoomNotFoundException()), user.getSeq())
                .orElseThrow(() -> new MessageListLoadException());

        List<MessageDto.Response> messageDtoList = new ArrayList<>();

        for(int i = 0; i < messageList.size(); i++){
            MessageDto.Response talkTalkChatDto = new MessageDto.Response(messageList.get(i));
            messageDtoList.add(talkTalkChatDto);
        }

        /* 메시지 읽음 처리 완료 */
        checkMessageRead(roomSeq);

        return messageDtoList;
    }

    public MessageDto.Response createMessage(MessageDto.Request messageDto){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Room room = roomRepository.findByRoomSeq(messageDto.getRoomSeq()).orElseThrow(() -> new RoomNotFoundException());
        /* 메시지 생성 후 추가 */
        Message message = messageRepository.save(
                messageDto.toEntity(user, roomRepository.findByRoomSeq(messageDto.getRoomSeq()).get())
        );

        if(message == null){
            throw new MessageNotSavedException();
        }

        Long receiver = -1L;

        if(room.getUser1Seq() == user.getSeq()){
            receiver = room.getUser2Seq();
            if(room.getUser2Delete()){
                room.setUser2Delete(false); //상대편 채팅방 삭제시 켜주기
            }
        }else if(room.getUser2Seq() == user.getSeq()){
            receiver = room.getUser1Seq();
            if(room.getUser1Delete()){
                room.setUser1Delete(false); //상대편 채팅방 삭제시 켜주기
            }
        }

        if(receiver == -1L){
            throw new User1NotFoundException();
        }

        /* 읽음 체크를 위해 메시지 로그를 남기는 부분 */
        MessageLogDto.RequestForMessageLog messageLogDto1 = new MessageLogDto.RequestForMessageLog(message, room, user.getSeq(), CDType.CHECKED);
        MessageLogDto.RequestForMessageLog messageLogDto2 = new MessageLogDto.RequestForMessageLog(message, room, receiver, CDType.WAIT);


        messageLogRepository.save(messageLogDto1.toEntity());
        messageLogRepository.save(messageLogDto2.toEntity());

        return new MessageDto.Response(message);
    }

    public void deleteMessage(Long messageSeq){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MessageLog messageLog = messageLogRepository.findByMessage_MessageSeqAndUserSeq(messageSeq, user.getSeq())
                .orElseThrow(() -> new MessageLogNotFoundException());

        messageLog.setCdType(CDType.DELETED);
    }

    public void checkMessageRead(Long roomSeq) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<MessageLog> messageLogList = messageRepository.updateAllToReadByRoomSeqAndUserSeq(roomSeq, user.getSeq());

        for(MessageLog messageLog : messageLogList){
            messageLog.setCdType(CDType.CHECKED);
        }
    }

    public void checkMessageDeleted(Long roomSeq) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<MessageLog> messageLogList = messageRepository.updateAllToDeletedByRoomSeqAndUserSeq(roomSeq, user.getSeq());

        for(MessageLog messageLog : messageLogList){
            messageLog.setCdType(CDType.DELETED);
        }
    }

    public Long countWaitingMessage() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return messageLogRepository.countAllByUserSeqAndCdType(user.getSeq(), CDType.WAIT);
    }

    public Long countWaitingMessageByRoom(Long roomSeq, Long userSeq) {
        System.out.println("=========카운팅======= : " + messageLogRepository.countAllByUserSeqAndRoom_RoomSeqAndCdType(userSeq, roomSeq, CDType.WAIT));
        return messageLogRepository.countAllByUserSeqAndRoom_RoomSeqAndCdType(userSeq, roomSeq, CDType.WAIT);
    }
}
