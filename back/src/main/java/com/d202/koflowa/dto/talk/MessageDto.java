package com.d202.koflowa.dto.talk;

import lombok.Data;

@Data
public class MessageDto {
    private String roomId;
    private String sender;
    private String message;
}
