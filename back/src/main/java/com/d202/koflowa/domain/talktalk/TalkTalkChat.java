package com.d202.koflowa.domain.talktalk;

import com.d202.koflowa.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "talk_talk_chat")
public class TalkTalkChat extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "talk_talk_chat_seq", columnDefinition = "bigint unsigned")
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "talk_talk_seq")
    @JsonBackReference
    private TalkTalk talkTalk;

    @Column(name = "talk_talk_user")
    private Long user;

    @Column(name = "talk_talk_user1_deleted")
    private boolean user1Deleted;

    @Column(name = "talk_talk_user2_deleted")
    private boolean user2Deleted;

    @Column(name = "chat_content")
    private String content;

    @Column(name = "talk_talk_chat_type")
    private int type;

    @Column(name = "chatroom_seq")
    private int chatroomSeq;

    @Column(name = "session_code")
    private int sessionCode;
}