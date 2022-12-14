package com.d202.koflowa.talk.dto;

import com.d202.koflowa.talk.domain.Room;
import com.d202.koflowa.user.domain.User;
import lombok.*;

import java.time.format.DateTimeFormatter;

public class RoomDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{
        private Long roomSeq;

        public Room toEntity() {
            Room room = Room.builder()
                    .roomSeq(roomSeq)
                    .build();
            return room;
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestCreate{
        private Long receiver_seq;

        public Room toEntity(Long user1) {
            Room room = Room.builder()
                    .user1Seq(user1)
                    .user2Seq(receiver_seq)
                    .user1Delete(false)
                    .user2Delete(false)
                    .build();
            return room;
        }
    }

    @Getter
    public static class Response{
        private Long roomSeq;
        private String createdTime;
        private Long waitingMessageNumber;
        private User user;
        public Response(Room room) {
            this.roomSeq = room.getRoomSeq();
            this.createdTime = room.getCreatedTime().format(DateTimeFormatter.ISO_DATE_TIME);;
        }

        public void setWaitingMessageNumber(Long waitingMessageNumber) {
            this.waitingMessageNumber = waitingMessageNumber;
        }
    }

    @Getter
    public static class ResponseWithUser{
        private Long roomSeq;
        private String createdTime;
        private Long waitingMessageNumber;
        private User user;
        public ResponseWithUser(Room room, User user) {
            this.roomSeq = room.getRoomSeq();
            this.user = user;
            this.createdTime = room.getCreatedTime().format(DateTimeFormatter.ISO_DATE_TIME);;
        }

        public void setWaitingMessageNumber(Long waitingMessageNumber) {
            this.waitingMessageNumber = waitingMessageNumber;
        }
    }
}
