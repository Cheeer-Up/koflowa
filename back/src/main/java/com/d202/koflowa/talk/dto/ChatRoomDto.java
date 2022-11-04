package com.d202.koflowa.talk.dto;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class ChatRoomDto {
    private String roomId;
    private Set<WebSocketSession> sessions = new HashSet<>();

    public static ChatRoomDto create(){
        ChatRoomDto room = new ChatRoomDto();
        room.roomId = UUID.randomUUID().toString();
        return room;
    }
}
