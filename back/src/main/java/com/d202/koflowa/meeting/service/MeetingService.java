package com.d202.koflowa.meeting.service;

import com.d202.koflowa.meeting.dto.TokenDto;
import com.d202.koflowa.exception.SessionNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import io.openvidu.java.client.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class MeetingService {
    private final String url = "https://k7d202.p.ssafy.io:8443";
    private final OpenVidu openvidu = new OpenVidu(url, "koflowa202");


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
//                .data("user_data")//?
                .build();
        Connection connection = session.createConnection(connectionProperties);
        TokenDto tokenDto = TokenDto.builder()
                .token(connection.getToken())
                .build();
        return tokenDto;
    }

    public TokenDto requestToOpenviduJoin(String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        Session session = openvidu.getActiveSession(sessionId);
        if (session == null)
            throw new SessionNotFoundException("Session Not Found");
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                .type(ConnectionType.WEBRTC)
                .role(OpenViduRole.PUBLISHER)
//                .data("user_data")
                .build();
        Connection connection = session.createConnection(connectionProperties);
        TokenDto tokenDto = TokenDto.builder()
                .token(connection.getToken())
                .build();
        return tokenDto;
    }

    public Map<String, Object> requestToOpenViduDelete(String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        Map<String, Object> response = new HashMap<>();
        try {
            Session session = openvidu.getActiveSession(sessionId);
            session.close();
            response.put("result", "SUCCESS");
        }catch (Exception e){
            response.put("result", "FAIL");
            response.put("reason", "세션 삭제 실패");
        }
        return response;
    }
}
