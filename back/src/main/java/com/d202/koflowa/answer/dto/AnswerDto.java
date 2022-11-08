package com.d202.koflowa.answer.dto;


import com.d202.koflowa.answer.domain.Answer;
import com.d202.koflowa.question.domain.Question;
import lombok.*;

import java.time.LocalDateTime;

public class AnswerDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long answerSeq;
        private String content;

        /* Dto -> Entity */
        public Answer toEntity(Question question) {
            Answer answer = Answer.builder()
                    .question(question)
                    .content(content)
                    .build();
            return answer;
        }
    }

    @Getter
    public static class Response{
        private Long seq;
        private Long questionSeq;
        private String content;
        private Long userSeq;
        private Boolean accept;
        private LocalDateTime createdTime;
        private LocalDateTime modifiedTime;

        /* Entity -> Dto*/
        public Response(Answer answer){
            this.seq = answer.getSeq();
            this.questionSeq = answer.getQuestion().getSeq();
            this.content = answer.getContent();
            this.userSeq = answer.getUserSeq();
            this.accept = answer.getAccept();
            this.createdTime = answer.getCreatedTime();
            this.modifiedTime = answer.getModifiedTime();
        }
    }
}
