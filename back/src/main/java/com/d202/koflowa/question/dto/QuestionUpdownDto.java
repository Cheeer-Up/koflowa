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
        private Long questionSeq;
        private UDType questionUpdownType;

        public QuestionUpdown toEntity(User user, Question question){
            return QuestionUpdown
                    .builder()
                    .user(user)
                    .question(question)
                    .type(questionUpdownType)
                    .build();
        }
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        private Long questionUpdownSeq;
        private User user;
        private Question question;
        private UDType questionUpdownType;

        public Response(QuestionUpdown questionUpdown){
            this.questionUpdownSeq = questionUpdown.getSeq();
            this.user = questionUpdown.getUser();
            this.question = questionUpdown.getQuestion();
            this.questionUpdownType = questionUpdown.getType();
        }
    }
}
