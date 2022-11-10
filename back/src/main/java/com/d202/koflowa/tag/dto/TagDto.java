package com.d202.koflowa.tag.dto;

import com.d202.koflowa.tag.domain.Tag;
import lombok.*;
import java.time.format.DateTimeFormatter;


public class TagDto {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor()
    public static class Request {
        private String name;
        private String description;
        public Tag toEntity() {
            return Tag.builder()
                    .name(name)
                    .description(description)
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private Long seq;
        private String name;
        private String description;
        private String createdTime;
        private String modifiedTime;

        public Response(Tag tag) {
            this.seq = tag.getSeq();
            this.name = tag.getName();
            this.description = tag.getDescription();
            this.createdTime = tag.getCreatedTime().format(DateTimeFormatter.ISO_DATE_TIME);
            this.modifiedTime = tag.getModifiedTime().format(DateTimeFormatter.ISO_DATE_TIME);
        }
    }
}
