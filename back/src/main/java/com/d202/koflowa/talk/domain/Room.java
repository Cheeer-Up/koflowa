package com.d202.koflowa.talk.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_seq", columnDefinition = "bigint unsigned")
    private Long roomSeq;

    @Column(name = "user1")
    private Long user1;

    @Column(name = "user2")
    private Long user2;

    @Column(name = "user1_nickname")
    private String user1Nickname;

    @Column(name = "user2_nickname")
    private String user2Nickname;
}

