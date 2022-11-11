package com.d202.koflowa.user.dto;

import com.d202.koflowa.user.domain.ReputationLog;
import com.d202.koflowa.user.domain.User;
import lombok.*;

import java.time.LocalDateTime;

public class ReputationLogDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private User user;
        private int score;
        private String message;
        private String createdTime, modifiedTime;
        private Long questionSeq;

        public ReputationLog toEntity(){
            return ReputationLog.builder()
                    .user(user)
                    .score(score)
                    .message(message)
                    .questionSeq(questionSeq)
                    .build();
        }
    }

    public static class Response{
        private Long seq;
        private User user;
        private int score;
        private String message;
        private Long questionSeq;
        private LocalDateTime createdTime, modifiedTime;

        public Response(ReputationLog reputationLog){
            this.seq = reputationLog.getSeq();
            this.user = reputationLog.getUser();
            this.score = reputationLog.getScore();
            this.message = reputationLog.getMessage();
            this.questionSeq = reputationLog.getQuestionSeq();
            this.createdTime = reputationLog.getCreatedTime();
            this.modifiedTime = reputationLog.getModifiedTime();
        }
    }
}
