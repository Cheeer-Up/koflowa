package com.d202.koflowa.kotalk.repository;

import com.d202.koflowa.kotalk.domain.TalkTalk;
import com.d202.koflowa.kotalk.domain.TalkTalkChat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkTalkChatRepository extends JpaRepository<TalkTalkChat, Long> {
    /* 방 id를 이용해 메시지들을 조회 */
    List<TalkTalkChat> findAllByTalkTalkOrderByCreatedTime(TalkTalk talkTalk);
    TalkTalkChat findByTalkTalkChatSeq(Long talkTalkChatSeq);
}