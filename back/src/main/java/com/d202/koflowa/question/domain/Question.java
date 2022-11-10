package com.d202.koflowa.question.domain;

import com.d202.koflowa.common.domain.BaseTimeEntity;
import com.d202.koflowa.question.dto.QuestionDto;
import com.d202.koflowa.user.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Builder
//모든 필드값을 파라미터로 받는 생성자 자동 생성
@AllArgsConstructor
//final, not null 등만 받는 생성자 생성
//@RequiredArgsConstructor
@Entity
@Getter
@Setter
//파라미터가 없는 기본 생성자 자동 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@RequiredArgsConstructor
@Table(name = "question")
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_seq", columnDefinition = "bigint unsigned")
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "question_updown")
    @JsonBackReference
    private QuestionUpdown questionUpdown;

    @Column(name = "user_seq", columnDefinition = "bigint unsigned")
    private Long userSeq;

    @Column(name = "question_title", columnDefinition = "varchar(100)")
    private String title;

    @Column(name = "question_content", columnDefinition = "")
    private String content;

    @Column(name = "up", columnDefinition = "bigint unsigned")
    private Long up;

    @Column(name = "down", columnDefinition = "bigint unsigned")
    private Long down;
}
