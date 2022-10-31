package com.d202.koflowa.controller;

import com.d202.koflowa.service.MeetingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/meeting")
@RequiredArgsConstructor
public class MeetingController {
    private final MeetingService meetingService;

    @PostMapping("")
    public Map<String, Object> openSession(@RequestParam Long oppSeq) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long seq = user.getSeq();
        Map<String, Object> response = new HashMap<>();
        Long seq = 123l;
        try {
            String token = meetingService.requestToOpenviduCreate(seq, oppSeq);
            response.put("token", token);
        }catch (JsonProcessingException e){
            response.put("result", "FAIL");
            response.put("reason", "세션 생성 실패");
        } catch (OpenViduJavaClientException e) {
            throw new RuntimeException(e);
        } catch (OpenViduHttpException e) {
            throw new RuntimeException(e);
        }

        return response;
    }

    @DeleteMapping("/{session-id}")
    public Map<String, Object> deleteSession(@PathVariable("session-id") String sessionId){
        Map<String, Object> response = new HashMap<>();
        try {
            meetingService.requestToOpenViduDelete(sessionId);
            response.put("result", "SUCCESS");
        }catch (Exception e){
            response.put("result", "FAIL");
            response.put("reason", "세션 삭제 실패");
        }
        return response;
    }

    @GetMapping("/{session-id}")
    public Map<String, Object> joinSession(@PathVariable("session-id") String sessionId){
        Map<String, Object> response = new HashMap<>();
        try {
            String token = meetingService.requestToOpenviduJoin(sessionId);
            response.put("result", token);
        }catch (Exception e){
            response.put("result", "FAIL");
            response.put("reason", "세션 참가 실패");
        }
        return response;

    }
}
