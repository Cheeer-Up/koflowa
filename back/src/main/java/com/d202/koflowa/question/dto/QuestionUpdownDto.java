package com.d202.koflowa.question.dto;

import com.d202.koflowa.common.domain.UDType;
import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.question.domain.QuestionUpdown;
import com.d202.koflowa.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class QuestionUpdownDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request{
        private Long question_updown_seq;
        private User user_seq;
        private Question question_seq;
        private UDType question_updown_type;

        public QuestionUpdown toEntity(){
            return QuestionUpdown
                    .builder()
                    .seq(question_updown_seq)
                    .user(user_seq)
                    .question(question_seq)
                    .type(question_updown_type)
                    .build();
        }
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        private Long question_updown_seq;
        private User user_seq;
        private Question question_seq;
        private UDType question_updown_type;

        public Response(QuestionUpdown questionUpdown){
            this.question_updown_seq = questionUpdown.getSeq();
            this.user_seq = questionUpdown.getUser();
            this.question_seq = questionUpdown.getQuestion();
            this.question_updown_type = questionUpdown.getType();
        }
    }
}