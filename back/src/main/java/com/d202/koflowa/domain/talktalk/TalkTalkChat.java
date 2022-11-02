package com.d202.koflowa.domain.talktalk;

import com.d202.koflowa.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "talk_talk_chat")
public class TalkTalkChat extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "talk_talk_chat_seq", columnDefinition = "bigint unsigned")
    private Long talkTalkChatSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "talk_talk_seq")
    @JsonBackReference
    private TalkTalk talkTalk;

    @Column(name = "checked")
    private Boolean checked;

    @Column(name = "chat_content")
    private String chatContent;

    @Column(name = "talk_talk_chat_type")
    private int talkTalkChatType;

    @Column(name = "session_code")
    private int sessionCode;
}