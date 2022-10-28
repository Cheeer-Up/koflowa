package com.d202.koflowa.dto;

import com.d202.koflowa.domain.Tag;
import lombok.*;

import java.time.LocalDateTime;


public class TagDto {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private Long seq;
        private String name;
        private String discription;
        public Tag toEntity() {
            return Tag.builder()
                    .seq(seq)
                    .name(name)
                    .discription(discription)
                    .build();
        }
    }

    public static class Response {
        private Long seq;
        private String name;
        private String discription;
        private LocalDateTime createdTime, modifiedTime;

        public Response(Tag tag) {
            this.seq = tag.getSeq();
            this.name = tag.getName();
            this.discription = tag.getDiscription();
            this.createdTime = tag.getCreatedTime();
            this.modifiedTime = tag.getModifiedTime();
        }
    }
}
