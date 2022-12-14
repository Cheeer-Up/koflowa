package com.d202.koflowa.S_J_O.service.auth;

import com.d202.koflowa.S_J_O.TokenMapping;
import com.d202.koflowa.S_J_O.advice.assertThat.DefaultAssert;
import com.d202.koflowa.S_J_O.payload.request.auth.RefreshTokenRequest;
import com.d202.koflowa.S_J_O.payload.response.ApiResponse;
import com.d202.koflowa.S_J_O.payload.response.AuthResponse;
import com.d202.koflowa.S_J_O.payload.response.Message;
//import com.d202.koflowa.S_J_O.security.token.UserPrincipal;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final CustomTokenProviderService customTokenProviderService;
    
    private final UserRepository userRepository;
//    private final TokenRepository tokenRepository;
    

    public ResponseEntity<?> whoAmI(User userPrincipal){
        Optional<User> user = userRepository.findById(userPrincipal.getSeq());
        DefaultAssert.isOptionalPresent(user);
        ApiResponse apiResponse = ApiResponse.builder().check(true).information(user.get()).build();

        return ResponseEntity.ok(apiResponse);
    }

    public ResponseEntity<?> delete(User userPrincipal){
        Optional<User> user = userRepository.findById(userPrincipal.getSeq());
        DefaultAssert.isTrue(user.isPresent(), "유저가 올바르지 않습니다.");

//        Optional<Token> token = tokenRepository.findByUserEmail(user.get().getEmail());
//        DefaultAssert.isTrue(token.isPresent(), "토큰이 유효하지 않습니다.");

        userRepository.delete(user.get());
//        tokenRepository.delete(token.get());

        ApiResponse apiResponse = ApiResponse.builder().check(true).information(Message.builder().message("회원 탈퇴하셨습니다.").build()).build();

        return ResponseEntity.ok(apiResponse);
    }

//    public ResponseEntity<?> modify(UserPrincipal userPrincipal, ChangePasswordRequest passwordChangeRequest){
//        Optional<User> user = userRepository.findById(userPrincipal.getId());
//        boolean passwordCheck = passwordEncoder.matches(passwordChangeRequest.getOldPassword(),user.get().getPassword());
//        DefaultAssert.isTrue(passwordCheck, "잘못된 비밀번호 입니다.");
//
//        boolean newPasswordCheck = passwordChangeRequest.getNewPassword().equals(passwordChangeRequest.getReNewPassword());
//        DefaultAssert.isTrue(newPasswordCheck, "신규 등록 비밀번호 값이 일치하지 않습니다.");
//
//
//        passwordEncoder.encode(passwordChangeRequest.getNewPassword());
//
//        return ResponseEntity.ok(true);
//    }

//    public ResponseEntity<?> signin(SignInRequest signInRequest){
//        Authentication authentication = authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(
//                signInRequest.getEmail(),
//                signInRequest.getPassword()
//            )
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        TokenMapping tokenMapping = customTokenProviderService.createToken(authentication);
//        Token token = Token.builder()
//                            .refreshToken(tokenMapping.getRefreshToken())
//                            .userEmail(tokenMapping.getUserEmail())
//                            .build();
//        tokenRepository.save(token);
//        AuthResponse authResponse = AuthResponse.builder().accessToken(tokenMapping.getAccessToken()).refreshToken(token.getRefreshToken()).build();
//
//        return ResponseEntity.ok(authResponse);
//    }

//    public ResponseEntity<?> signup(SignUpRequest signUpRequest){
//        DefaultAssert.isTrue(!userRepository.existsByEmail(signUpRequest.getEmail()), "해당 이메일이 존재하지 않습니다.");
//
//        User user = User.builder()
//                        .name(signUpRequest.getName())
//                        .email(signUpRequest.getEmail())
//                        .password(passwordEncoder.encode(signUpRequest.getPassword()))
//                        .provider(Provider.local)
//                        .role(Role.ADMIN)
//                        .build();
//
//        userRepository.save(user);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/auth/")
//                .buildAndExpand(user.getId()).toUri();
//        ApiResponse apiResponse = ApiResponse.builder().check(true).information(Message.builder().message("회원가입에 성공하였습니다.").build()).build();
//
//        return ResponseEntity.created(location).body(apiResponse);
//    }

    public ResponseEntity<?> refresh(RefreshTokenRequest tokenRefreshRequest){
        //1차 검증
        boolean checkValid = valid(tokenRefreshRequest.getRefreshToken());
        DefaultAssert.isAuthentication(checkValid);

        Optional<User> user = userRepository.findByRefreshToken(tokenRefreshRequest.getRefreshToken());

//        Optional<Token> token = tokenRepository.findByRefreshToken(tokenRefreshRequest.getRefreshToken());
        Authentication authentication = customTokenProviderService.getAuthenticationByEmail(user.get().getEmail());

        //4. refresh token 정보 값을 업데이트 한다.
        //시간 유효성 확인
        TokenMapping tokenMapping;

        Long expirationTime = customTokenProviderService.getExpiration(tokenRefreshRequest.getRefreshToken());
        // 만기까지 시간 남았을 때
        if(expirationTime > 0){
            tokenMapping = customTokenProviderService.refreshToken(authentication, user.get().getRefreshToken());
        }else{
            tokenMapping = customTokenProviderService.createToken(authentication);
        }
        User updateUser = user.get();
        updateUser.putUpdateToken(tokenMapping.getRefreshToken());

        userRepository.save(updateUser);

        AuthResponse authResponse = AuthResponse.builder().accessToken(tokenMapping.getAccessToken()).refreshToken(updateUser.getRefreshToken()).build();

        return ResponseEntity.ok(authResponse);
    }

    public ResponseEntity<?> signout(RefreshTokenRequest tokenRefreshRequest){
        boolean checkValid = valid(tokenRefreshRequest.getRefreshToken());
        DefaultAssert.isAuthentication(checkValid);

        Optional<User> user = userRepository.findByRefreshToken(tokenRefreshRequest.getRefreshToken());

        userRepository.delete(user.get());
        ApiResponse apiResponse = ApiResponse.builder().check(true).information(Message.builder().message("로그아웃 하였습니다.").build()).build();

        return ResponseEntity.ok(apiResponse);
    }

    private boolean valid(String refreshToken){

        //1. 토큰 형식 물리적 검증
        boolean validateCheck = customTokenProviderService.validateToken(refreshToken);
        DefaultAssert.isTrue(validateCheck, "Token 검증에 실패하였습니다.");

        //2. refresh token 값을 불러온다.
        Optional<User> user = userRepository.findByRefreshToken(refreshToken);

        DefaultAssert.isTrue(user.isPresent(), "탈퇴 처리된 회원입니다.");

        //3. email 값을 통해 인증값을 불러온다
        Authentication authentication = customTokenProviderService.getAuthenticationByEmail(user.get().getEmail());
        DefaultAssert.isTrue(user.get().getEmail().equals(authentication.getName()), "사용자 인증에 실패하였습니다.");

        return true;
    }


}
