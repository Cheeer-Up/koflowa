package com.d202.koflowa.talk.dto;

import com.d202.koflowa.talk.domain.Room;
import lombok.*;

public class RoomDto {

    @Getter
    @Builder
    public static class Request{
        private Long roomSeq;
        private Long user1;
        private Long user2;
        private String user1Nickname;
        private String user2Nickname;

        public Room toEntity() {
            Room room = Room.builder()
                    .roomSeq(roomSeq)
                    .user1(user1)
                    .user2(user2)
                    .user1Nickname(user1Nickname)
                    .user2Nickname(user2Nickname)
                    .build();
            return room;
        }

        /* 유저1과 유저2의 값의 위치를 바꾸어 저장한다 */
        public Room toEntity2() {
            Room room = Room.builder()
                    .roomSeq(roomSeq)
                    .user1(user2)
                    .user2(user1)
                    .user1Nickname(user2Nickname)
                    .user2Nickname(user1Nickname)
                    .build();
            return room;
        }
    }

    @Getter
    public static class Response{
        private Long roomSeq;
        private Long user1;
        private Long user2;
        private String user1Nickname;
        private String user2Nickname;

        public Response(Room room) {
            this.roomSeq = room.getRoomSeq();
            this.user1 = room.getUser1();
            this.user2 = room.getUser2();
            this.user1Nickname = room.getUser1Nickname();
            this.user2Nickname = room.getUser2Nickname();
        }
    }
}
