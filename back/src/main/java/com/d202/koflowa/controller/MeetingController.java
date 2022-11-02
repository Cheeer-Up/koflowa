package com.d202.koflowa.controller;

import com.d202.koflowa.dto.meeting.SessionDto;
import com.d202.koflowa.dto.meeting.TokenDto;
import com.d202.koflowa.service.MeetingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import lombok.RequiredArgsConstructor;
import org.apache.http.Header;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/meeting")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MeetingController {
    private final MeetingService meetingService;

    @PostMapping("")
    public TokenDto openSession(@RequestBody SessionDto sessionDto) throws OpenViduJavaClientException, OpenViduHttpException, JsonProcessingException {
        return meetingService.requestToOpenviduCreate(sessionDto.getCustomSessionId());
    }

    @DeleteMapping("/{session-id}")
    public ResponseEntity<Map<String, Object>> deleteSession(@PathVariable("session-id") String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        return new ResponseEntity<>(meetingService.requestToOpenViduDelete(sessionId), HttpStatus.OK);
    }

    @GetMapping("/{session-id}")
    public TokenDto joinSession(@PathVariable("session-id") String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        return meetingService.requestToOpenviduJoin(sessionId);
    }
}
