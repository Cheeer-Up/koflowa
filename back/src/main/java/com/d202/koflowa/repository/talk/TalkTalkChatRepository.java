package com.d202.koflowa.repository.talk;

import com.d202.koflowa.domain.talktalk.TalkTalkChat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkTalkChatRepository extends JpaRepository<TalkTalkChat, Long> {
    /* 방 id를 이용해 메시지들을 조회 */
    List<TalkTalkChat> findAllByTalkTalkSeqOrderByCreatedTime(Long talk_talk_seq);
}