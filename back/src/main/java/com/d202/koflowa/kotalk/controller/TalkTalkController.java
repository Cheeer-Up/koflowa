package com.d202.koflowa.kotalk.controller;

import com.d202.koflowa.kotalk.dto.TalkTalkDto;
import com.d202.koflowa.kotalk.service.TalkTalkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/talktalk")
@RequiredArgsConstructor
public class TalkTalkController {

    private final TalkTalkService talkTalkService;

    @PostMapping
    public Map<String, Object> createChatRoom(@RequestBody TalkTalkDto.RequestTalkTalkDto talkTalkDto) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("값 들어옴 " + talkTalkDto.toString());

        try {
            // 송신자와 수신자의 Room Id 값을 반환
            response.put("response", talkTalkService.createTalkTalk(talkTalkDto));
        }
        catch (Exception e){
            response.put("result", "FAIL");
            response.put("reason", "방 생성 실패");
        }

        return response;
    }

    //token 도입시 파라미터를 삭제하고 토큰 값에서 조회할 것
    @PostMapping("/history")
    public Map<String, Object> getMyChatRoom(@RequestBody TalkTalkDto.RequestTalkTalkDto talkTalkDto) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("아이디값 들어옴 " + talkTalkDto.getTalk_talk_user1());

        try {
            response.put("result", talkTalkService.getTalkTalkList(talkTalkDto.getTalk_talk_user1()));
        }
        catch (Exception e){
            response.put("result", "FAIL");
            response.put("reason", "방 조회 실패");
        }

        return response;
    }

    @DeleteMapping
    public Map<String, Object> deleteChatRoom(@RequestBody TalkTalkDto.RequestTalkTalkDto talkTalkDto) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("값 들어옴 " + talkTalkDto);

        try {
            talkTalkService.deleteTalkTalk(talkTalkDto);
            response.put("response", "SUCCESS");
        }
        catch (Exception e){
            response.put("result", "FAIL");
            response.put("reason", "삭제 실패");
        }

        return response;
    }
}
