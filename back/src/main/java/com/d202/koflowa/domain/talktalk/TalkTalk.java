package com.d202.koflowa.domain.talktalk;

import com.d202.koflowa.domain.common.AuthProvider;
import com.d202.koflowa.domain.common.Role;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "talk_talk")
public class TalkTalk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "talk_talk_seq", columnDefinition = "bigint unsigned")
    private Long talkTalkSeq;

    @Column(name = "talk_talk_user1")
    private Long talkTalkUser1;

    @Column(name = "talk_talk_user2")
    private Long talkTalkUser2;

    @Column(name = "talk_talk_user1_nickname")
    private String talkTalkUser1Nickname;

    @Column(name = "talk_talk_user2_nickname")
    private String talkTalkUser2Nickname;
}

