package com.d202.koflowa.talk.dto;

import com.d202.koflowa.talk.domain.Message;
import com.d202.koflowa.talk.domain.Room;
import lombok.*;

public class MessageDto {
    @Data
    @RequiredArgsConstructor
    public static class Request{
        private Long messageSeq;
        private Long roomSeq;
        private boolean checked;
        private String content;
        private int type;
        private int sessionCode;

        public Message toEntity(Room room) {
            return Message.builder()
                    .messageSeq(messageSeq)
                    .room(room)
                    .checked(checked)
                    .content(content)
                    .type(type)
                    .sessionCode(sessionCode)
                    .build();
        }
    }

    @Getter
    public static class Response{
        private Long messageSeq;
        private Room roomSeq;
        private boolean checked;
        private String content;
        private String created_time;
        private int type;
        private int sessionCode;           ;

        // boolean -> Boolean 이어야 인식되었다.
        public Response(Message message) {
            this.messageSeq = message.getMessageSeq();
            this.roomSeq = message.getRoom();
            this.checked = message.getChecked();
            this.content = message.getContent();
            this.created_time = message.getCreatedTime().toString();
            this.type = message.getType();
            this.sessionCode = message.getSessionCode();
        }
    }
}
