package com.d202.koflowa.answer.dto;

import com.d202.koflowa.common.domain.QAType;
import com.d202.koflowa.answer.domain.Comment;
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
        public Comment toEntity(){
            Comment comment = Comment.builder()
                    .seq(commentSeq)
                    .userSeq(userSeq)
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
        private Long userSeq;
        private Long boardSeq;
        private QAType type;
        private String content;

        public Comment toEntity(){
            Comment comment = Comment.builder()
                    .userSeq(userSeq)
                    .boardSeq(boardSeq)
                    .type(type)
                    .content(content)
                    .build();
            return comment;
        }
    }


    @Getter
    public static class Response{
        private Long seq;
        private Long userSeq;
        private Long boardSeq;
        private QAType type;
        private String content;
        private LocalDateTime createdTime;
        private LocalDateTime modifiedTime;

        /* Entity -> Dto*/
        public Response(Comment comment){
            this.seq = comment.getSeq();
            this.userSeq = comment.getUserSeq();
            this.boardSeq = comment.getBoardSeq();
            this.type = comment.getType();
            this.content = comment.getContent();
            this.createdTime = comment.getCreatedTime();
            this.modifiedTime = comment.getModifiedTime();
        }
    }
}
