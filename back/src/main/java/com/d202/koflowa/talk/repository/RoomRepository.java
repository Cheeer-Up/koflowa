package com.d202.koflowa.talk.repository;

import com.d202.koflowa.talk.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByRoomSeq(Long roomSeq);

    @Query("SELECT room FROM Room room " +
            "WHERE (room.roomSeq = :roomSeq AND room.user1Seq = :userSeq AND room.user1Delete = false) " +
            "OR (room.roomSeq = :roomSeq AND room.user2Seq = :userSeq AND room.user2Delete = false)")
    Optional<Room> findByRoomSeqAuthWithUserSeq(@Param("roomSeq") Long roomSeq, @Param("userSeq") Long userSeq);

    /* 나의 채팅방 목록 전체 조회*/
    @Query(value = "SELECT room FROM Room room " +
            "WHERE (room.user1Seq = :userSeq AND room.user1Delete = false) " +
            "OR (room.user2Seq = :userSeq AND room.user2Delete = false)")
    List<Room> findMyChatRoomNotDeleted(@Param("userSeq") Long userSeq);

    /* 나와 특정 상대의 채팅방 목록 조회 */
    @Query(value = "SELECT room FROM Room room " +
            "WHERE (room.user1Seq = :user1 AND room.user2Seq = :user2) " +
            "OR (room.user1Seq = :user2 AND room.user2Seq = :user1)")
    Room findSpecificChatRoom(@Param("user1") Long user1, @Param("user2") Long user2);

    @Query(value = "SELECT room FROM Room room " +
            "WHERE (room.user1Seq = :userSeq AND room.roomSeq = :roomSeq AND room.user1Delete = false) " +
            "OR (room.user2Seq = :userSeq AND room.roomSeq = :roomSeq AND room.user2Delete = false)")
    Optional<Room> findRoomForDelete(@Param("roomSeq") Long roomSeq, @Param("userSeq") Long userSeq);
}
