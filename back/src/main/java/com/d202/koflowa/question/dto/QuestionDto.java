package com.d202.koflowa.question.dto;

import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.question.domain.QuestionUpdown;
import com.d202.koflowa.user.domain.User;
import lombok.*;
import java.time.LocalDateTime;


public class QuestionDto {
    // 이너 클래스로 관리
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request{
        private Long questionSeq;
        private Long userSeq;
        private String questionTitle;
        private String questionContent;
        private LocalDateTime createdTime;
        private LocalDateTime updatedTime;

        public Question toEntity(){
            return Question.builder()
                    .seq(questionSeq)
                    .userSeq(userSeq)
                    .title(questionTitle)
                    .content(questionContent)
                    .build();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RequestCreate{
        private Long userSeq;
        private String questionTitle;
        private String questionContent;

        public Question toEntity(){
            return Question.builder()
                    .userSeq(userSeq)
                    .title(questionTitle)
                    .content(questionContent)
                    .build();
        }
    }

    @Getter
    public static class Response{
        private Long questionSeq;
        private Long userSeq;
        private String questionTitle;
        private String questionContent;

        private Long up;
        private Long down;
        private LocalDateTime createdTime;
        private LocalDateTime updatedTime;

        public Response(Question question){
            this.questionSeq = question.getSeq();
            this.userSeq = question.getUserSeq();
            this.questionTitle = question.getTitle();
            this.questionContent = question.getContent();
            this.up = question.getUp();
            this.down = question.getDown();
            this.createdTime = question.getCreatedTime();
            this.updatedTime = question.getModifiedTime();
        }
    }
}
