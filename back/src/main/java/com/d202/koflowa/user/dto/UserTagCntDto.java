package com.d202.koflowa.user.dto;

import lombok.Getter;

public class UserTagCntDto {
    @Getter
    public static class Response{
        private String tagName;
        private Long cnt;

        public Response(String tagName, Long cnt){
            this.tagName = tagName;
            this.cnt = cnt;
        }
    }
}
