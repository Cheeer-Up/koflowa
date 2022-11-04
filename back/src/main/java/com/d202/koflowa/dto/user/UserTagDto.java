package com.d202.koflowa.dto.user;

import com.d202.koflowa.domain.Tag;
import com.d202.koflowa.domain.common.AuthProvider;
import com.d202.koflowa.domain.common.Role;
import com.d202.koflowa.domain.user.User;
import com.d202.koflowa.domain.user.UserTag;
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
