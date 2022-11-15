package com.d202.koflowa.talk.domain;

import com.d202.koflowa.common.domain.CreateTimeEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "room")
public class Room extends CreateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_seq", columnDefinition = "bigint unsigned")
    private Long roomSeq;

    @Column(name = "user1_seq")
    private Long user1Seq;

    @Column(name = "user2_seq")
    private Long user2Seq;

    @Column(name = "user1_deleted")
    private boolean user1Delete = false;

    @Column(name = "user2_deleted")
    private boolean user2Delete = false;
}

