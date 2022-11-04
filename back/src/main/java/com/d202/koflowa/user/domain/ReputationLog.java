package com.d202.koflowa.user.domain;

import com.d202.koflowa.common.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reputation_log")
public class ReputationLog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reputation_log_seq", columnDefinition = "bigint unsigned")
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    @JsonBackReference
    private User user;

    @Column(name = "reputation_log_score")
    private int score;

    @Column(name = "reputation_log_situation", length = 15)
    private String situation;
}
