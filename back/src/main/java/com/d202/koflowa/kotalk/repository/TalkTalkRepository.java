package com.d202.koflowa.kotalk.repository;

import com.d202.koflowa.kotalk.domain.TalkTalk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TalkTalkRepository extends JpaRepository<TalkTalk, Long> {

    TalkTalk findByTalkTalkSeq(Long talkTalkSeq);

    /* 나의 채팅방 목록 전체 조회*/
    @Query(value = "select talkTalk " + "from TalkTalk talkTalk " +
            "where talkTalk.talkTalkUser1 = :userSeq")
    List<TalkTalk> findMyChatRoom(Long userSeq);

    /* 나와 특정 상대의 채팅방 목록 조회 */
    @Query(value = "select talkTalk " + "from TalkTalk talkTalk " +
            "where ( talkTalk.talkTalkUser1 = :talkTalkUser1 and talkTalk.talkTalkUser2 = :talkTalkUser2)")
    TalkTalk findSpecificChatRoom(Long talkTalkUser1, Long talkTalkUser2);
}
