package com.d202.koflowa.user.domain;

import com.d202.koflowa.common.domain.BaseTimeEntity;
import com.d202.koflowa.common.domain.AuthProvider;
import com.d202.koflowa.common.domain.Role;
import com.d202.koflowa.user.dto.UserDto;
import lombok.*;

import javax.persistence.*;

@ToString
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_seq", columnDefinition = "bigint unsigned")
    private Long seq;

    @Column(name = "user_email", length = 100)
    private String email;

    @Column(name = "user_name", length = 30)
    private String name;

    @Column(name = "user_nickname", length = 15)
    private String nickname;

    @Column(name = "user_profile", length = 255)
    private String profile;

    @Column(name = "user_about", columnDefinition = "text")
    private String about;

    @Column(name = "user_role", length = 10)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "user_auth_provider", length = 20)
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Column(name = "user_refresh_token", length = 255)
    private String refreshToken;

    @Column(name = "reputation_score", columnDefinition = "int unsigned")
    private int reputationScore;

    public void putUser(UserDto.Request user){
        this.nickname = user.getNickname();
        this.about = user.getAbout();
    }

    public void putProfile(String profile){
        this.profile = profile;
    }

    public void putUpdateToken(String updateToken){this.refreshToken = updateToken;}

    public void putReputationScore(int reputationScore){
        this.reputationScore = reputationScore;
    }

    public void updateName(String updateName){this.name = updateName;}
    public void updateImageUrl(String profile){this.profile = profile;}
    public void putName(String name){
        this.name = name;
    }

}
