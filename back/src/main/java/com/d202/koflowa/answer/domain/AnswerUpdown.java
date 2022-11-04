package com.d202.koflowa.answer.domain;

import com.d202.koflowa.common.domain.UDType;
import com.d202.koflowa.user.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "answer_updown")
public class AnswerUpdown {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_updown_seq", columnDefinition = "bigint unsigned")
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_seq")
    @JsonBackReference
    private Answer answer;

    @Column(name = "answer_updown_type")
    @Enumerated(EnumType.STRING)
    private UDType type;

}
