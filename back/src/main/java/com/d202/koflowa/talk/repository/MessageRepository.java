package com.d202.koflowa.talk.repository;

import com.d202.koflowa.talk.domain.Message;
import com.d202.koflowa.talk.domain.MessageLog;
import com.d202.koflowa.talk.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    /* 방 id를 이용해 메시지들을 조회 */
    @Query("SELECT m FROM Message m " +
            "LEFT OUTER JOIN MessageLog ml ON m.messageSeq = ml.message.messageSeq " +
            "WHERE m.room = :room AND ml.userSeq = :userSeq And ml.cdType <> com.d202.koflowa.common.domain.CDType.DELETED")
    Optional<List<Message>> findAllByRoomOrderByCreatedTimeDesc(@Param("room") Room room, @Param("userSeq") Long userSeq);
    Optional<Message> findByMessageSeqAndUser_Seq(Long messageSeq, Long userSeq);

    @Query("SELECT ml FROM MessageLog ml " +
            "WHERE ml.room.roomSeq = :roomSeq AND ml.userSeq = :userSeq AND ml.cdType <> com.d202.koflowa.common.domain.CDType.DELETED")
    List<MessageLog> updateAllToReadByRoomSeqAndUserSeq(@Param("roomSeq") Long roomSeq, @Param("userSeq") Long userSeq);

    @Query("SELECT ml FROM MessageLog ml " +
            "WHERE ml.room.roomSeq = :roomSeq AND ml.userSeq = :userSeq AND ml.cdType <> com.d202.koflowa.common.domain.CDType.DELETED")
    List<MessageLog> updateAllToDeletedByRoomSeqAndUserSeq(@Param("roomSeq") Long roomSeq, @Param("userSeq") Long userSeq);
}