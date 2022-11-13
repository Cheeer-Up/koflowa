package com.d202.koflowa.talk.dto;

import lombok.Data;

@Data
public class ChatDto {
    private Long userSeq; // 유저의 아이디가 곧 방이름
    private Long sender; // 보낸 사람의 ID값
    private String senderNickname; // 보낸 사람의 닉네임
    private String chatContent;
}
