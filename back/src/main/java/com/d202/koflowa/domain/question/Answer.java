package com.d202.koflowa.domain.question;

import com.d202.koflowa.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "answer")
public class Answer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_seq", columnDefinition = "bigint unsigned")
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_seq")
    @JsonBackReference
    private Question question;

    @Column(name = "answer_content")
    private String content;

    @Column(name = "user_seq", columnDefinition = "bigint unsigned")
    private Long userSeq;

    @Column(name = "answer_accept")
    private Boolean accept;

}
