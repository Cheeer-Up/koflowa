package com.d202.koflowa.talk.controller.socket;

import com.d202.koflowa.talk.dto.socket.ChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate template; //특정 Broker로 메세지를 전달

    //Client가 SEND할 수 있는 경로
    //stompConfig에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
    //"/pub/chat/enter"
    @MessageMapping(value = "/chat/enter")
    public void enter(ChatDto chatDto){
        template.convertAndSend("/sub/chat/room/" + chatDto.getUserSeq(), chatDto);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatDto chatDto){
        template.convertAndSend("/sub/chat/room/" + chatDto.getUserSeq(), chatDto);
    }

}
