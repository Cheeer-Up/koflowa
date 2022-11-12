package com.d202.koflowa.common.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthProvider {
    local,
    facebook,
    GOOGLE,
    google,
    github,
    kakao,
    naver
}