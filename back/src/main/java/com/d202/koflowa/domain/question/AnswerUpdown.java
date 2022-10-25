package com.d202.koflowa.domain.question;

import com.d202.koflowa.domain.common.UDType;
import com.d202.koflowa.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
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
    private User userSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_seq")
    @JsonBackReference
    private Answer answerSeq;

    @Column(name = "answer_updown_type")
    private UDType type;

}
