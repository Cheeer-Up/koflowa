package com.d202.koflowa.answer.dto;

import com.d202.koflowa.answer.domain.Answer;
import com.d202.koflowa.common.domain.UDType;
import com.d202.koflowa.answer.domain.AnswerUpdown;
import com.d202.koflowa.user.domain.User;
import lombok.*;

public class AnswerUpdownDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private UDType type;

        /* Dto -> Entity */
        public AnswerUpdown toEntity(User user, Answer answer, UDType type){
            AnswerUpdown answerUpdown = AnswerUpdown.builder()
                    .user(user)
                    .answer(answer)
                    .type(type)
                    .build();
            return answerUpdown;
        }
    }

    @Getter
    public static class Response{
        private Long seq;
        private Long userSeq;
        private Long answerSeq;
        private UDType type;

        /* Entity -> Dto*/
        public Response(AnswerUpdown answerUpdown){
            this.seq = answerUpdown.getSeq();
            this.userSeq = answerUpdown.getUser().getSeq();
            this.answerSeq = answerUpdown.getAnswer().getSeq();
            this.type = answerUpdown.getType();
        }
    }
}
