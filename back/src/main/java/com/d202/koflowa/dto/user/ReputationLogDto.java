package com.d202.koflowa.dto.user;

import com.d202.koflowa.domain.user.ReputationLog;
import com.d202.koflowa.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;

public class ReputationLogDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long seq;
        private User user;
        private int score;
        private String situation;
        private String createdTime, modifiedTime;

        public ReputationLog toEntity(){
            return ReputationLog.builder()
                    .user(user)
                    .score(score)
                    .situation(situation)
                    .build();
        }
    }

    public static class Response{
        private Long seq;
        private User user;
        private int score;
        private String situation;
        private LocalDateTime createdTime, modifiedTime;

        public Response(ReputationLog reputationLog){
            this.seq = reputationLog.getSeq();
            this.user = reputationLog.getUser();
            this.score = reputationLog.getScore();
            this.situation = reputationLog.getSituation();
            this.createdTime = reputationLog.getCreatedTime();
            this.modifiedTime = reputationLog.getModifiedTime();
        }
    }
}
