package com.d202.koflowa.domain.talktalk;

import com.d202.koflowa.domain.common.AuthProvider;
import com.d202.koflowa.domain.common.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "talk_talk")
public class TalkTalk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "talk_talk_seq", columnDefinition = "bigint unsigned")
    private Long seq;

    @Column(name = "talk_talk_user1")
    private Long user1;

    @Column(name = "talk_talk_user2")
    private Long user2;
}

