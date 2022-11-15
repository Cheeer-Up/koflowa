package com.d202.koflowa.talk.repository;

import com.d202.koflowa.talk.domain.Message;
import com.d202.koflowa.talk.domain.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageLogRepository extends JpaRepository<MessageLog, Long> {

}
