package com.d202.koflowa.talk.repository;

import com.d202.koflowa.talk.domain.Message;
import com.d202.koflowa.talk.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    /* 방 id를 이용해 메시지들을 조회 */
    Optional<List<Message>> findAllByRoomOrderByCreatedTime(Room room);
    Optional<Message> findByMessageSeq(Long messageSeq);
}