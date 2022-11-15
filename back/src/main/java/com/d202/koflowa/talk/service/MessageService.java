package com.d202.koflowa.talk.service;

import com.d202.koflowa.common.domain.CDType;
import com.d202.koflowa.talk.domain.Message;
import com.d202.koflowa.talk.domain.MessageLog;
import com.d202.koflowa.talk.domain.Room;
import com.d202.koflowa.talk.dto.MessageDto;
import com.d202.koflowa.talk.dto.MessageLogDto;
import com.d202.koflowa.talk.dto.RoomDto;
import com.d202.koflowa.talk.exception.MessageListLoadException;
import com.d202.koflowa.talk.exception.MessageNotFoundException;
import com.d202.koflowa.talk.exception.MessageNotSavedException;
import com.d202.koflowa.talk.exception.RoomNotFoundException;
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
                roomRepository.findByRoomSeqAuthWithUserSeq(roomSeq, user.getSeq())
                        .orElseThrow(() -> new RoomNotFoundException()))
                .orElseThrow(() -> new MessageListLoadException());

        List<MessageDto.Response> messageDtoList = new ArrayList<>();


        for(int i = 0; i < messageList.size(); i++){
            MessageDto.Response talkTalkChatDto = new MessageDto.Response(messageList.get(i));
            messageDtoList.add(talkTalkChatDto);
        }

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
        MessageLogDto.Request messageLogDto = new MessageLogDto(Long userSeq, Message message, Room room, cdType);
        MessageLog messageLog = messageLogRepository.save(messageLogDto.toEntityAll());


        return new MessageDto.Response(message);
    }

    public void deleteMessage(Long messageSeq){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        /* 양방향 매핑하지 않고 프론트 단에서 두 번 호출한다 */
        messageRepository.delete(messageRepository.findByMessageSeqAndUser_Seq(messageSeq, user.getSeq())
                .orElseThrow(() -> new MessageNotFoundException()));
    }

    public void checkMessageRead(Long roomSeq) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        messageRepository.updateAllToReadByRoomSeqAndUserSeq(roomSeq, user.getSeq());
    }
}
