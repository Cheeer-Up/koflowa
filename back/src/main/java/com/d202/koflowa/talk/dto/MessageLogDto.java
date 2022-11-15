package com.d202.koflowa.talk.dto;

import com.d202.koflowa.common.domain.CDType;
import com.d202.koflowa.talk.domain.Message;
import com.d202.koflowa.talk.domain.MessageLog;
import com.d202.koflowa.talk.domain.Room;
import com.d202.koflowa.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

public class MessageLogDto {

    @Getter
    @Builder
    public static class Request{
        private Long messageSeq;
        private Long userSeq;
        private CDType cdType;

        public MessageLog toEntity(Message message, Room room) {
            MessageLog messageLog = MessageLog.builder()
                    .userSeq(userSeq)
                    .room(room)
                    .message(message)
                    .cdType(cdType)
                    .build();
            return messageLog;
        }

        public MessageLog toEntityAll(Long userSeq, Message message, Room room, CDType cdType) {
            MessageLog messageLog = MessageLog.builder()
                    .userSeq(userSeq)
                    .room(room)
                    .message(message)
                    .cdType(cdType)
                    .build();
            return messageLog;
        }
    }

    @Getter
    public static class Response{
        private Long seq;
        private Long userSeq;
        private Message message;
        private String createdTime;
        private CDType cdType;

        public Response(MessageLog messageLog) {
            this.seq = messageLog.getSeq();
            this.userSeq = messageLog.getUserSeq();
            this.message = messageLog.getMessage();
            this.cdType = messageLog.getCdType();
            this.createdTime = messageLog.getCreatedTime().format(DateTimeFormatter.ISO_DATE_TIME);;
        }
    }
}
