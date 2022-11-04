package com.d202.koflowa.talk.controller;

import com.d202.koflowa.talk.dto.MessageDto;
import com.d202.koflowa.talk.dto.RoomDto;
import com.d202.koflowa.talk.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    /* 메시지 송신 */
    @PostMapping
    public Map<String, Object> sendChatMessage(@RequestBody MessageDto.Request messageDto) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("talkTalkSeq " + messageDto);

        try {
            response.put("result", messageService.createMessage(messageDto));
        }
        catch (Exception e){
            response.put("result", "FAIL");
            response.put("reason", "메시지 보내기 실패");
        }

        return response;
    }

    /* 채팅방의 메시지들을 조회 : 나중에 최적화 필요 DTO를 다시 만들던가 하기 : Spring Security로 ID값 받고 요청 SEQ값 조회 */
    @PostMapping("/log")
    public Map<String, Object> getChatMessageList(@RequestBody RoomDto.Request roomDto) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("talkTalkSeq " + roomDto);

        try {
            response.put("result", messageService.getMessageList(roomDto));
        }
        catch (Exception e){
            response.put("result", "FAIL");
            response.put("reason", "방 조회 실패");
        }

        return response;
    }

    /* 메시지 삭제 */
    @DeleteMapping
    public Map<String, Object> deleteMessage(@RequestBody MessageDto.Request messageDto) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("talkTalkSeq " + messageDto);

        try {
            messageService.deleteMessage(messageDto);
            response.put("result", "SUCCESS");
        }
        catch (Exception e){
            response.put("result", "FAIL");
            response.put("reason", "메시지 삭제 실패");
        }

        return response;
    }
}
