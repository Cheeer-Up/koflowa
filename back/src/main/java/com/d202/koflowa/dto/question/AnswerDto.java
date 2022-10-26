package com.d202.koflowa.dto.question;


import com.d202.koflowa.domain.question.Answer;
import lombok.*;

public class AnswerDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class Request{
        private Long questionSeq;
        private String content;

        /* Dto -> Entity */
        public Answer toEntity() {
            Answer answer = Answer.builder()
//                    .question()   // question
                    .content(content)
                    .build();
            return answer;
        }
    }

    @Getter
    private static class Response{
        private Long seq;
        private Long questionSeq;
        private String content;
        private Long userSeq;
        private Boolean accept;

        /* Entity -> Dto*/
        public Response(Answer answer){
            this.seq = answer.getSeq();
            this.questionSeq = answer.getQuestion().getSeq();
            this.content = answer.getContent();
            this.userSeq = answer.getUserSeq();
            this.accept = answer.getAccept();
        }
    }
}
