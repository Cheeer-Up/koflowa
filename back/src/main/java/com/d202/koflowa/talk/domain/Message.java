package com.d202.koflowa.talk.domain;

import com.d202.koflowa.common.domain.BaseTimeEntity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_seq")
    @JsonBackReference
    private Room room;

    @Column(name = "checked")
    private Boolean checked;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private int type;

    @Column(name = "session_code")
    private int sessionCode;
}