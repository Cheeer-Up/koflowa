package com.d202.koflowa.tag.dto;

import com.d202.koflowa.question.dto.QuestionDto;
import com.d202.koflowa.tag.domain.Tag;
import lombok.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class TagDto {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class DetailResponse {
        private Long seq;
        private String name;
        private String description;
        private String createdTime;
        private String modifiedTime;
        private int questionCount;
        private List<QuestionDto.Response> questions = new ArrayList<>();

        public DetailResponse(Tag tag, int questionCount, List<QuestionDto.Response> questionTagDtos) {
            this.seq = tag.getSeq();
            this.seq = tag.getSeq();
            this.name = tag.getName();
            this.description = tag.getDescription();
            this.createdTime = tag.getCreatedTime().format(DateTimeFormatter.ISO_DATE_TIME);
            this.modifiedTime = tag.getModifiedTime().format(DateTimeFormatter.ISO_DATE_TIME);
            this.questionCount = questionCount;
            this.questions = questionTagDtos;
        }
    }
}
