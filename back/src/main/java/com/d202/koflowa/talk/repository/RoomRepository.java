package com.d202.koflowa.talk.repository;

import com.d202.koflowa.talk.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByRoomSeq(Long roomSeq);

    /* 나의 채팅방 목록 전체 조회*/
    @Query(value = "select room " + "from Room room " +
            "where room.user1 = :user1")
    List<Room> findMyChatRoom(Long user1);

    /* 나와 특정 상대의 채팅방 목록 조회 */
    @Query(value = "select room " + "from Room room " +
            "where ( room.user1 = :user1 and room.user2 = :user2)")
    Room findSpecificChatRoom(Long user1, Long user2);
}
