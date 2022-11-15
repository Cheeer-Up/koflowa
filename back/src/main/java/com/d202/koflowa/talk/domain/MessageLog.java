package com.d202.koflowa.talk.domain;

import com.d202.koflowa.common.domain.CDType;
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
@Table(name = "message_log")
public class MessageLog extends CreateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_log_seq", columnDefinition = "bigint unsigned")
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "message_seq")
    @JsonBackReference
    private Message message;

    @ManyToOne
    @JoinColumn(name = "room_seq")
    @JsonBackReference
    private Room room;

    @Column(name = "user_seq")
    private Long userSeq;

    @Column(name = "status", length = 7)
    @Enumerated(EnumType.STRING)
    private CDType cdType;
}
