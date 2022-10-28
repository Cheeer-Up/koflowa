package com.d202.koflowa.repository.talk;

import com.d202.koflowa.domain.talktalk.TalkTalk;
import com.d202.koflowa.dto.talktalk.TalkTalkDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TalkTalkRepository extends JpaRepository<TalkTalk, Long> {
    /* 두 명의 유저 중 하나라도 포함된 방 조회 */
    List<TalkTalk> findAllByTalkTalkUser1OrTalkTalkUser2(Long userSeq);
    @Query(value = "select talkTalk " + "from TalkTalk talkTalk " +
            "where ( talkTalk.talkTalkUser1 = :talkTalkUser1 and talkTalk.talkTalkUser2 = :talkTalkUser2) or " +
            "( talkTalk.talkTalkUser2 = :talkTalkUser1 and talkTalk.talkTalkUser1 = :talkTalkUser2)")
    TalkTalk findAllByTalkTalkUser1AndTalkTalkUser2(Long talkTalkUser1, Long talkTalkUser2);
}
