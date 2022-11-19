package com.d202.koflowa.answer.dto;


import com.d202.koflowa.answer.domain.Answer;
import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.user.domain.User;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AnswerDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
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
//        private User user;
        private String userNickname;
        private String profile;
        private Boolean accept;
        private String createdTime;
        private String modifiedTime;
        private Long up;
        private Long down;

        /* Entity -> Dto*/
        public Response(Answer answer){
            this.seq = answer.getSeq();
//            this.user = answer.getUser();
            this.questionSeq = answer.getQuestion().getSeq();
            this.content = answer.getContent();
            this.userSeq = answer.getUser().getSeq();
            this.userNickname = answer.getUser().getNickname();
            this.profile  = answer.getUser().getProfile();
            this.accept = answer.getAccept();
            this.createdTime = answer.getCreatedTime().format(DateTimeFormatter.ISO_DATE_TIME);
            this.modifiedTime = answer.getModifiedTime().format(DateTimeFormatter.ISO_DATE_TIME);
            this.createdTime = answer.getCreatedTime().format(DateTimeFormatter.ISO_DATE_TIME);;
            this.modifiedTime = answer.getModifiedTime().format(DateTimeFormatter.ISO_DATE_TIME);;
            this.up = answer.getUp();
            this.down = answer.getDown();
        }

        public Response(Long seq, Long questionSeq, String content, LocalDateTime createdTime){
            this.seq = seq;
            this.questionSeq = questionSeq;
            this.content = content;
            this.createdTime = createdTime.format(DateTimeFormatter.ISO_DATE_TIME);
        }
    }
}
