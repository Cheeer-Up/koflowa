package com.d202.koflowa.user.domain;

import com.d202.koflowa.common.domain.BaseTimeEntity;
import com.d202.koflowa.common.domain.AuthProvider;
import com.d202.koflowa.common.domain.Role;
import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.user.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@ToString
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User extends BaseTimeEntity implements OAuth2User, UserDetails {
    
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

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @Builder.Default
//    private List<Question> questions = new ArrayList<>();
//
//    // Sale Like Method
//    public void addQuestion(Question question) {
//        this.questions.add(question);
//        question.setUser(this);
//    }
//
//    public void removeQuestion(Long questionSeq) {
//        questions.removeIf(question ->
//                question.getSeq()==questionSeq);
//    }

    public void putUser(UserDto.Request user){
        this.nickname = user.getNickname();
        this.about = user.getAbout();
    }

    public void putProfile(String profile){
        this.profile = profile;
    }

    public void putUpdateToken(String updateToken){
        this.refreshToken = updateToken;
    }

    public void putReputationScore(int reputationScore){
        this.reputationScore = reputationScore;
    }

    public void updateName(String updateName){
        this.name = updateName;
    }
    public void updateImageUrl(String profile){
        this.profile = profile;
    }
    public void putName(String name){
        this.name = name;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
