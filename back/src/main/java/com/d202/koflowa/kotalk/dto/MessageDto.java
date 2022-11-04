package com.d202.koflowa.kotalk.dto;

import lombok.Data;

@Data
public class MessageDto {
    private Long user_seq; // 유저의 아이디가 곧 방이름
    private Long sender; // 보낸 사람의 ID값
    private String sender_nickname; // 보낸 사람의 닉네임
    private String chat_content;
}
