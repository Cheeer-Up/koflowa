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
    Optional<List<Message>> findAllByRoomOrderByCreatedTimeDesc(Room room);
    Optional<Message> findByMessageSeqAndUser_Seq(Long messageSeq, Long userSeq);

    @Query("UPDATE MessageLog ml SET ml.cdType = com.d202.koflowa.common.domain.CDType.CHECKED "
            + "WHERE ml.room.roomSeq = :roomSeq AND ml.userSeq = :userSeq")
    List<MessageLog> updateAllToReadByRoomSeqAndUserSeq(@Param("roomSeq") Long roomSeq, @Param("userSeq") Long userSeq);
}