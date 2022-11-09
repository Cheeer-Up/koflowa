package com.d202.koflowa.meeting.controller;

import com.d202.koflowa.common.response.Response;
import com.d202.koflowa.meeting.dto.SessionDto;
import com.d202.koflowa.meeting.dto.TokenDto;
import com.d202.koflowa.meeting.service.MeetingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/meeting")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MeetingController {
    private final MeetingService meetingService;

    @PostMapping("")
    public Response openSession(@RequestBody SessionDto sessionDto) throws OpenViduJavaClientException, OpenViduHttpException, JsonProcessingException {
        return Response.success(meetingService.requestToOpenviduCreate(sessionDto.getCustomSessionId()));
    }

    @DeleteMapping("/{session-id}")
    public Response deleteSession(@PathVariable("session-id") String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        return meetingService.requestToOpenViduDelete(sessionId);
    }

    @GetMapping("/{session-id}")
    public Response joinSession(@PathVariable("session-id") String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        return Response.success(meetingService.requestToOpenviduJoin(sessionId));
    }
}
