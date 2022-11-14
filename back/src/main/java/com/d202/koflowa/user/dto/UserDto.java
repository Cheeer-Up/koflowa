package com.d202.koflowa.user.dto;

import com.d202.koflowa.common.domain.AuthProvider;
import com.d202.koflowa.common.domain.Role;
import com.d202.koflowa.user.domain.User;
import lombok.*;

import java.time.LocalDateTime;


public class UserDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class Request{
        private String nickname;
        private String profile;
        private String about;

        public User toEntity(){
            return User.builder()
                    .nickname(nickname)
                    .profile(profile)
                    .about(about)
                    .build();
        }
    }

    @Getter
    public static class Response{
        private Long seq;
        private String email;
        private String nickname;
        private String profile;
        private String about;
        private Role role;
        private AuthProvider authProvider;
        private String refreshToken;
        private int reputationScore;
        private LocalDateTime createdTime, modifiedTime;

        public Response(User user){
            this.seq = user.getSeq();
            this.email = user.getEmail();
            this.nickname = user.getNickname();
            this.profile = user.getProfile();
            this.about = user.getAbout();
            this.role = user.getRole();
            this.authProvider = user.getAuthProvider();
            this.refreshToken = user.getRefreshToken();
            this.reputationScore = user.getReputationScore();
            this.createdTime = user.getCreatedTime();
            this.modifiedTime = user.getModifiedTime();
        }
    }

    @Getter
    public static class ResponseForQuestion{
        private Long seq;
        private String nickname;
        private String profile;

        public ResponseForQuestion(User user){
            this.seq = user.getSeq();
            this.nickname = user.getNickname();
            this.profile = user.getProfile();
        }
    }
}
