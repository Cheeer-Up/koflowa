package com.d202.koflowa.talk.domain;

import com.d202.koflowa.common.domain.BaseTimeEntity;
import com.d202.koflowa.common.domain.CreateTimeEntity;
import com.d202.koflowa.user.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "message")
public class Message extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_seq", columnDefinition = "bigint unsigned")
    private Long messageSeq;

    @ManyToOne
    @JoinColumn(name = "room_seq")
    @JsonBackReference
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    @JsonBackReference
    private User user;

    @Column(name = "content")
    private String content;

    @Column(name = "session_code")
    private int sessionCode = -1;
}