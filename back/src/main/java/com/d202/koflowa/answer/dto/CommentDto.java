package com.d202.koflowa.answer.dto;

import com.d202.koflowa.common.domain.QAType;
import com.d202.koflowa.answer.domain.Comment;
import com.d202.koflowa.user.domain.User;
import lombok.*;

import java.time.LocalDateTime;

public class CommentDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{

        private Long commentSeq;
        private Long userSeq;
        private Long boardSeq;
        private QAType type;
        private String content;

        /* Dto -> Entity */
        public Comment toEntity(User user){
            Comment comment = Comment.builder()
                    .seq(commentSeq)
                    .user(user)
                    .boardSeq(boardSeq)
                    .type(type)
                    .content(content)
                    .build();
            return comment;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RequestCreate{

        private Long boardSeq;
        private QAType type;
        private String content;

        public Comment toEntity(User user){
            Comment comment = Comment.builder()
                    .user(user)
                    .boardSeq(boardSeq)
                    .type(type)
                    .content(content)
                    .build();
            return comment;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RequestUpdate{

        private Long commentSeq;
        private String content;

        public Comment toEntity(User user){
            Comment comment = Comment.builder()
                    .user(user)
                    .content(content)
                    .build();
            return comment;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RequestDelete{
        private Long commentSeq;
    }

    @Getter
    public static class Response{
        private Long seq;
        private User user;
        private Long boardSeq;
        private QAType type;
        private String content;
        private LocalDateTime createdTime;
        private LocalDateTime modifiedTime;

        /* Entity -> Dto*/
        public Response(Comment comment){
            this.seq = comment.getSeq();
            this.user = comment.getUser();
            this.boardSeq = comment.getBoardSeq();
            this.type = comment.getType();
            this.content = comment.getContent();
            this.createdTime = comment.getCreatedTime();
            this.modifiedTime = comment.getModifiedTime();
        }
    }
}
