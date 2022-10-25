package com.d202.koflowa.dto.question;


import com.d202.koflowa.domain.question.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AnswerDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class Request{
        private Long questionSeq;
        private String content;

//        public Answer toEntity() {
//            Answer answer = Answer.builder()
////                    .question()   // question
//        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class Response{
        private Long seq;
        private Long questionSeq;
        private String content;
        private Long userSeq;
        private Boolean accept;
    }
}
