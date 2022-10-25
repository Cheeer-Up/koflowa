package com.d202.koflowa.domain.talktalk;

import com.d202.koflowa.domain.common.AuthProvider;
import com.d202.koflowa.domain.common.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "talk_talk_meeting")
public class TalkTalkMeeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "talk_talk_meeting_seq", columnDefinition = "bigint unsigned")
    private Long seq;

    @OneToOne
    @JoinColumn(name = "talk_talk_chat_seq")
    private TalkTalkChat talkTalkChat;

    @Column(name = "chatroom_seq")
    private int chatroomSeq;

    @Column(name = "session_code")
    private int sessionCode;
}
