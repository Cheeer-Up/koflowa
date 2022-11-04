package com.d202.koflowa.user.dto;

import com.d202.koflowa.tag.domain.Tag;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.domain.UserTag;
import lombok.Getter;


public class UserTagDto {
    @Getter
    public static class Response{
        private Long seq;
        private User user;
        private Tag tag;

        public Response(UserTag userTag){
            this.seq = userTag.getSeq();
            this.user = userTag.getUser();
            this.tag = userTag.getTag();
        }
    }
}
