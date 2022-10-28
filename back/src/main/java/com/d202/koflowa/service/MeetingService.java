package com.d202.koflowa.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import io.openvidu.java.client.*;

@Service
public class MeetingService {
    private final String url = "https://k7d202.p.ssafy.io:8443";
    private final OpenVidu openvidu = new OpenVidu(url, "koflowa202");


    public String requestToOpenviduCreate(Long seq, Long oppSeq) throws JsonProcessingException, OpenViduJavaClientException, OpenViduHttpException {
//        String url = "https://k7d202.p.ssafy.io/openvidu/api/sessions";
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Header set
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        // auth 값 숨김 필요
//        httpHeaders.setBasicAuth("OPENVIDUAPP","koflowa202");
//
//        // Body set
//        RequestOpenViduBody requestOpenviduBody = RequestOpenViduBody.builder()
//                .customSessionId(seq.toString()+'_'+oppSeq.toString())
//                .build();
//
//        // Message
//        HttpEntity<?> requestMessage = new HttpEntity<>(requestOpenviduBody, httpHeaders);
//
//        // Request
//        HttpEntity<String> response = restTemplate.postForEntity(url, requestMessage, String.class);
//
//        // Response 파싱
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
//        SessionDto sessionDto = objectMapper.readValue(response.getBody(), SessionDto.class);
//
//        return sessionDto;


        SessionProperties properties = new SessionProperties.Builder()
                .customSessionId(seq.toString()+'_'+oppSeq.toString())
                .build();
        Session session = openvidu.createSession(properties);
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                .type(ConnectionType.WEBRTC)
                .role(OpenViduRole.PUBLISHER)
                .data("user_data")//?
                .build();
        Connection connection = session.createConnection(connectionProperties);
        String token = connection.getToken();
        return token;
    }

    public String requestToOpenviduJoin(String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        Session session = openvidu.getActiveSession(sessionId);
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                .type(ConnectionType.WEBRTC)
                .role(OpenViduRole.PUBLISHER)
                .data("user_data")
                .build();
        Connection connection = session.createConnection(connectionProperties);
        String token = connection.getToken();
        return token;
    }

    public void requestToOpenViduDelete(String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Header set
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        // auth 값 숨김 필요
//        httpHeaders.setBasicAuth("OPENVIDUAPP","koflowa202");
//
//
//        // Message
//        HttpEntity<?> requestMessage = new HttpEntity<>(httpHeaders);
//
//        // Request
////        HttpEntity<String> response = restTemplate.postForEntity(url, requestMessage, String.class);
//        restTemplate.delete(url, requestMessage, String.class);
        Session session = openvidu.getActiveSession(sessionId);
        session.close();
    }
}
