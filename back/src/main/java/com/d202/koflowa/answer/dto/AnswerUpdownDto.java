package com.d202.koflowa.answer.dto;

import com.d202.koflowa.common.domain.UDType;
import com.d202.koflowa.answer.domain.AnswerUpdown;
import lombok.*;

public class AnswerUpdownDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long userSeq;
        private Long answerSeq;
        private UDType type;

        /* Dto -> Entity */
        public AnswerUpdown toEntity(){
            AnswerUpdown answerUpdown = AnswerUpdown.builder()
//                    .user()
//                    .answer()
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
