package com.d202.koflowa.S_J_O.security.auth;

import com.d202.koflowa.S_J_O.advice.assertThat.DefaultAssert;
import com.d202.koflowa.S_J_O.security.auth.company.Google;
import com.d202.koflowa.common.domain.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new Google(attributes);
        } else {
            DefaultAssert.isAuthentication("해당 oauth2 기능은 지원하지 않습니다.");
        }
        return null;
    }
}
