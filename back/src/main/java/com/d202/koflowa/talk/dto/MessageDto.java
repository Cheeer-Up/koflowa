package com.d202.koflowa.talk.dto;

import com.d202.koflowa.talk.domain.Message;
import com.d202.koflowa.talk.domain.Room;
import com.d202.koflowa.user.domain.User;
import lombok.*;

import java.time.format.DateTimeFormatter;

public class MessageDto {
    @Data
    @RequiredArgsConstructor
    public static class Request{
        private Long roomSeq;
        private String content;
        private int sessionCode;

        public Message toEntity(User user, Room room) {
            return Message.builder()
                    .user(user)
                    .room(room)
                    .content(content)
                    .sessionCode(sessionCode)
                    .build();
        }
    }

    @Data
    @RequiredArgsConstructor
    public static class RequestCreate{
        private Long roomSeq;
        private String content;
        private int sessionCode;
        public Message toEntity(User user, Room room) {
            return Message.builder()
                    .user(user)
                    .room(room)
                    .content(content)
                    .sessionCode(sessionCode)
                    .build();
        }
    }

    @Getter
    public static class Response{
        private Long messageSeq;
        private Room room;
        private User user;
        private String content;
        private String createdTime;
        private String modifiedTime;
        private int sessionCode;

        // boolean -> Boolean 이어야 인식되었다.
        public Response(Message message) {
            this.messageSeq = message.getMessageSeq();
            this.room = message.getRoom();
            this.user = message.getUser();
            this.content = message.getContent();
            this.createdTime = message.getCreatedTime().format(DateTimeFormatter.ISO_DATE_TIME);
            this.modifiedTime = message.getModifiedTime().format(DateTimeFormatter.ISO_DATE_TIME);;
            this.sessionCode = message.getSessionCode();
        }
    }
}
