package com.d202.koflowa.answer.domain;

import com.d202.koflowa.answer.dto.AnswerDto;
import com.d202.koflowa.common.domain.BaseTimeEntity;
import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.user.dto.UserDto;
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

    @Column(name = "up", columnDefinition = "bigint unsigned")
    private Long up;

    @Column(name = "down", columnDefinition = "bigint unsigned")
    private Long down;

    public void updateAnswerContent(AnswerDto.Request answerDto){
        this.content = answerDto.getContent();
    }

    public void updateAnswerUp(Integer num){
        this.up += num;
    }

    public void updateAnswerDown(Integer num){
        this.down += num;
    }

    public void updateAnswerAccept(Boolean accept){
        this.accept = accept;
    }

    public void setUserSeq(Long userSeq){
        this.userSeq = userSeq;
    }
}
