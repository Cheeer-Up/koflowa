package com.d202.koflowa.controller;

import com.d202.koflowa.dto.talktalk.TalkTalkChatDto;
import com.d202.koflowa.dto.talktalk.TalkTalkDto;
import com.d202.koflowa.service.talk.TalkTalkChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/talktalk/chat")
@RequiredArgsConstructor
public class TalkTalkChatController {
    private final TalkTalkChatService talkTalkChatService;

    /* 메시지 송신 */
    @PostMapping
    public Map<String, Object> sendChatMessage(@RequestBody TalkTalkChatDto.RequestMessage talkTalkChatDto) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("talkTalkSeq " + talkTalkChatDto);

        try {
            response.put("result", talkTalkChatService.createTalkTalkChat(talkTalkChatDto));
        }
        catch (Exception e){
            response.put("result", "FAIL");
            response.put("reason", "메시지 보내기 실패");
        }

        return response;
    }

    /* 채팅방의 메시지들을 조회 */
    @PostMapping("/log")
    public Map<String, Object> getChatMessageList(@RequestBody TalkTalkDto.Request talkTalkDto) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("talkTalkSeq " + talkTalkDto);

        try {
            response.put("result", talkTalkChatService.getChatMessageList(talkTalkDto));
        }
        catch (Exception e){
            response.put("result", "FAIL");
            response.put("reason", "방 조회 실패");
        }

        return response;
    }

    /* 메시지 삭제 */
    @DeleteMapping ("")
    public Map<String, Object> deleteChatMessage(@RequestBody TalkTalkChatDto.RequestMessage talkTalkChatDto) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("talkTalkSeq " + talkTalkChatDto);

        try {
            talkTalkChatService.deleteTalkTalkChat(talkTalkChatDto);
            response.put("result", "SUCCESS");
        }
        catch (Exception e){
            response.put("result", "FAIL");
            response.put("reason", "메시지 삭제 실패");
        }

        return response;
    }
}
