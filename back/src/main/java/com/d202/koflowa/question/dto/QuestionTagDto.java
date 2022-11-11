package com.d202.koflowa.question.dto;

import com.d202.koflowa.question.domain.QuestionTag;

public class QuestionTagDto {
    private Long tagSeq;
    private Long questionSeq;
    public QuestionTagDto(QuestionTag questionTag) {
        this.tagSeq = questionTag.getTag().getSeq();
        this.questionSeq = questionTag.getQuestion().getSeq();
    }
}
