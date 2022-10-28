package com.d202.koflowa.dto.question;

import com.d202.koflowa.domain.question.Question;
import com.d202.koflowa.domain.user.User;
import lombok.*;
import java.time.LocalDateTime;


public class QuestionDto {
    // 이너 클래스로 관리
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request{
        private Long question_seq;
        private User user_seq;
        private String question_title;
        private String question_content;
        private LocalDateTime created_time;
        private LocalDateTime updated_time;

        public Question toEntity(){
            return Question.builder()
                    .seq(question_seq)
                    .user(user_seq)
                    .title(question_title)
                    .content(question_title)
                    .build();
        }
    }
    @Getter
    public static class Response{
        private Long question_seq;
        private User user_seq;
        private String question_title;
        private String question_content;
        private LocalDateTime created_time;
        private LocalDateTime updated_time;

        public Response(Question question){
            this.question_seq = question.getSeq();
            this.user_seq = question.getUser();
            this.question_title = question.getTitle();
            this.question_content = question.getContent();
            this.created_time = question.getCreatedTime();
            this.updated_time = question.getModifiedTime();
        }
    }
}
