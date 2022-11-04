package com.d202.koflowa.kotalk.controller;


import com.d202.koflowa.kotalk.dto.MessageDto;
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
    public void enter(MessageDto message){
        template.convertAndSend("/sub/chat/room/" + message.getUser_seq(), message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(MessageDto message){
        template.convertAndSend("/sub/chat/room/" + message.getUser_seq(), message);
    }

}