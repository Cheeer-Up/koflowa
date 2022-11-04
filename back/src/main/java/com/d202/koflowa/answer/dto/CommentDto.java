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
        private Long userSeq;
        private Long boardSeq;
        private QAType type;
        private String content;

        /* Dto -> Entity */
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
        public Response(Response response){
            this.seq = response.getSeq();
            this.userSeq = response.getUserSeq();
            this.boardSeq = response.getBoardSeq();
            this.type = response.getType();
            this.content = response.getContent();
            this.createdTime = response.getCreatedTime();
            this.modifiedTime = response.getModifiedTime();
        }
    }
}
