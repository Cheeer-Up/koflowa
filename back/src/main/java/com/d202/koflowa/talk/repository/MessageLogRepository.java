package com.d202.koflowa.talk.repository;

import com.d202.koflowa.common.domain.CDType;
import com.d202.koflowa.talk.domain.Message;
import com.d202.koflowa.talk.domain.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MessageLogRepository extends JpaRepository<MessageLog, Long> {
    Optional<MessageLog> findByMessage_MessageSeqAndUserSeq(Long messageSeq, Long userSeq);
    Long countAllByUserSeqAndCdType(Long userSeq, CDType cdType);
    Long countAllByUserSeqAndRoom_RoomSeqAndCdType(Long userSeq, Long roomSeq, CDType cdType);
}
