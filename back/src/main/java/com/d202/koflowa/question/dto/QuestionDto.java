package com.d202.koflowa.question.dto;

import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.question.domain.QuestionUpdown;
import com.d202.koflowa.user.domain.User;
import lombok.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class QuestionDto {
    // 이너 클래스로 관리
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request{
        private Long questionSeq;
        private String questionTitle;
        private String questionContent;

        public Question toEntity(User user){
            return Question.builder()
                    .seq(questionSeq)
                    .title(questionTitle)
                    .content(questionContent)
                    .user(user)
                    .build();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RequestCreate{
        private String questionTitle;
        private String questionContent;

        public Question toEntity(User user){
            return Question.builder()
                    .title(questionTitle)
                    .content(questionContent)
                    .user(user)
                    .up(0L)
                    .down(0L)
                    .answerCount(0L)
                    .build();
        }
    }

    @Getter
    public static class Response{
        private Long questionSeq;
        private User user;

        private String questionTitle;
        private String questionContent;

        private Long up;
        private Long down;
        private String createdTime;
        private String updatedTime;

        private Long answerCount;

        private Long acceptAnswerSeq;

        public Response(Question question){
            this.questionSeq = question.getSeq();
            this.user = question.getUser();
            this.questionTitle = question.getTitle();
            this.questionContent = question.getContent();
            this.up = question.getUp();
            this.down = question.getDown();
            this.answerCount = question.getAnswerCount();
            this.acceptAnswerSeq = question.getAcceptAnswerSeq();
            this.createdTime = question.getCreatedTime().format(DateTimeFormatter.ISO_DATE_TIME);
            this.updatedTime = question.getModifiedTime().format(DateTimeFormatter.ISO_DATE_TIME);
        }

        public Response(String questionTitle, Long questionSeq, LocalDateTime createdTime){
            this.questionSeq = questionSeq;
            this.questionTitle = questionTitle;
            this.createdTime = createdTime.format(DateTimeFormatter.ISO_DATE_TIME);;
        }
    }
}
