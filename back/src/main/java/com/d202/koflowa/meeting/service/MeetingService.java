package com.d202.koflowa.meeting.service;

import com.d202.koflowa.S_J_O.service.auth.CustomUserDetailsService;
import com.d202.koflowa.common.response.Response;
import com.d202.koflowa.common.response.Result;
import com.d202.koflowa.meeting.dto.TokenDto;
import com.d202.koflowa.exception.SessionNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import io.openvidu.java.client.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MeetingService {
//    private final String url = "http://localhost:4443";
    private final String url = "https://k7d202.p.ssafy.io:8443";
//    private final String url = "https://k7d202.p.ssafy.io/openvidu";
    private OpenVidu openvidu = new OpenVidu(url, "koflowa202");
//    private OpenVidu openvidu = new OpenVidu(url, "MY_SECRET");



    public TokenDto requestToOpenviduCreate(String sessionId) throws JsonProcessingException, OpenViduJavaClientException, OpenViduHttpException {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long seq = user.getSeq();

        SessionProperties properties = new SessionProperties.Builder()
                .customSessionId(sessionId)
                .build();
        Session session = openvidu.createSession(properties);
        //연결까지 한번에
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                .type(ConnectionType.WEBRTC)
                .role(OpenViduRole.PUBLISHER)
                .build();
        Connection connection = session.createConnection(connectionProperties);

        return TokenDto.builder()
                .token(connection.getToken())
                .build();
    }

    public TokenDto requestToOpenviduJoin(String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        Session session = openvidu.getActiveSession(sessionId);
        if (session == null)
            throw new SessionNotFoundException("Session Not Found");
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                .type(ConnectionType.WEBRTC)
                .role(OpenViduRole.PUBLISHER)
                .build();
        Connection connection = session.createConnection(connectionProperties);

        return TokenDto.builder()
                .token(connection.getToken())
                .build();
    }

    public Response requestToOpenViduDelete(String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
//        log.info(sessionId);
        Session session = openvidu.getActiveSession(sessionId);
//        List<Session> sessionList = openvidu.getActiveSessions();
//        log.info("session : " + session);
//        log.info("sessionList : " + sessionList);
        if (session == null)
            throw new SessionNotFoundException("Session Not Found");
        session.close();
        return Response.success();
    }
}
