package com.d202.koflowa.kotalk.dto;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class RoomDto {
    private String roomId;
    private Set<WebSocketSession> sessions = new HashSet<>();

    public static RoomDto create(){
        RoomDto room = new RoomDto();
        room.roomId = UUID.randomUUID().toString();
        return room;
    }
}
