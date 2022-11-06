package com.d202.koflowa.talk.service;

import com.d202.koflowa.talk.domain.Message;
import com.d202.koflowa.talk.dto.MessageDto;
import com.d202.koflowa.talk.dto.RoomDto;
import com.d202.koflowa.talk.exception.MessageListLoadException;
import com.d202.koflowa.talk.exception.MessageNotFoundException;
import com.d202.koflowa.talk.exception.MessageNotSavedException;
import com.d202.koflowa.talk.exception.RoomNotFoundException;
import com.d202.koflowa.talk.repository.MessageRepository;
import com.d202.koflowa.talk.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;

    public List<MessageDto.Response> getMessageList(RoomDto.Request roomDto){
        System.out.println("들어왔나?" + roomDto.getRoomSeq());
        List<Message> messageList = messageRepository.findAllByRoomOrderByCreatedTime(
                roomRepository.findByRoomSeq(roomDto.getRoomSeq())
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
        /* 양방향 매핑하지 않고 프론트 단에서 두 번 호출한다 */
        Message message = messageRepository.save(messageDto.toEntity(roomRepository.findByRoomSeq(messageDto.getMessageSeq())
                .orElseThrow(() -> new RoomNotFoundException())));

        if(message == null){
            throw new MessageNotSavedException();
        }

        return new MessageDto.Response(message);
    }

    public void deleteMessage(MessageDto.Request messageDto){
        /* 양방향 매핑하지 않고 프론트 단에서 두 번 호출한다 */
        messageRepository.delete(messageRepository.findByMessageSeq(messageDto.getMessageSeq())
                .orElseThrow(() -> new MessageNotFoundException()));
    }
}
