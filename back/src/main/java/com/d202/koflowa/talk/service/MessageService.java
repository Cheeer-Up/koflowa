package com.d202.koflowa.talk.service;

import com.d202.koflowa.talk.domain.Message;
import com.d202.koflowa.talk.dto.MessageDto;
import com.d202.koflowa.talk.dto.RoomDto;
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
        List<Message> talkChatMessageList = messageRepository.findAllByRoomOrderByCreatedTime(roomRepository.findByRoomSeq(roomDto.getRoomSeq()));
        List<MessageDto.Response> talkTalkChatDtoList = new ArrayList<>();


        for(int i = 0; i < talkChatMessageList.size(); i++){
            MessageDto.Response talkTalkChatDto = new MessageDto.Response(talkChatMessageList.get(i));
            talkTalkChatDtoList.add(talkTalkChatDto);
        }

        return talkTalkChatDtoList;
    }

    public MessageDto.Response createMessage(MessageDto.Request messageDto){
        /* 양방향 매핑하지 않고 프론트 단에서 두 번 호출한다 */
        Message message = messageRepository.save(messageDto.toEntity(roomRepository.findByRoomSeq(messageDto.getMessageSeq())));

        return new MessageDto.Response(message);
    }

    public void deleteMessage(MessageDto.Request talkTalkChatDto){
        /* 양방향 매핑하지 않고 프론트 단에서 두 번 호출한다 */
        messageRepository.delete(messageRepository.findByMessageSeq(talkTalkChatDto.getMessageSeq()));
    }
}
