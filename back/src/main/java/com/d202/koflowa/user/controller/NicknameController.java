package com.d202.koflowa.user.controller;

import com.d202.koflowa.common.response.Response;
import com.d202.koflowa.user.service.NicknameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nickname")
@RequiredArgsConstructor
@Tag(name = "Nickname", description = "Nickname API")
public class NicknameController {
    private final NicknameService nicknameService;

    @GetMapping("/{nickname}")
    @Operation(summary = "OAuth2.0 회원의 닉네임 설정", description = "")
    public Response nicknameSetting(@PathVariable("nickname") String nickname){
        return Response.success(nicknameService.setMyNickname(nickname));
    }
}
