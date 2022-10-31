<<<<<<< HEAD
//package com.d202.koflowa.jwt;
=======
//package com.d202.koflowa.filter;
>>>>>>> 2175f3dd0d4d348db9275e26fa76411fd7af9344
//
//import com.d202.koflowa.dto.user.UserDto;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.UnsupportedJwtException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.security.SignatureException;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//// 클라이언트 요청 시 JWT를 검증하는 Filter
//// 요청당 한 번의 filter 수행을 위해 OncePerRequestFilter를 상속받는다.
//@Component
//public class JwtRequestFilter extends OncePerRequestFilter {
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//
//    // 실제 JWT 검증을 실행하는 Provider
//    @Autowired private JwtTokenProvider jwtTokenProvider;
//
//    // 인증에서 제외할 url
//    private static final List<String> EXCLUDE_URL =
//            Collections.unmodifiableList(
//                    Arrays.asList(
//                            "/static/**",
//                            "/favicon.ico",
//                            "/admin",
//                            "/admin/authentication",
//                            "/admin/refresh",
//                            "/admin/join",
//                            "/admin/join/**",
//                            "/admin/loginView",
//                            "/admin/login"
//                    ));
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        // jwt local storage 사용 시 해당 코드를 사용하여 header에서 토큰을 받아오도록 함
//        // final String token = request.getHeader("Authorization");
//
//        // jwt cookie 사용 시 해당 코드를 사용하여 쿠키에서 토큰을 받아오도록 함
//        String token = Arrays.stream(request.getCookies())
//                .filter(c -> c.getName().equals("jdhToken"))
//                .findFirst() .map(Cookie::getValue)
//                .orElse(null);
//
//        String adminId = null;
//        String jwtToken = null;
//
//        // Bearer token인 경우 JWT 토큰 유효성 검사 진행
//        if (token != null && token.startsWith("Bearer ")) {
//            jwtToken = token.substring(7);
//            try {
//                adminId = jwtTokenProvider.getUsernameFromToken(jwtToken);
//            } catch (SignatureException e) {
//                log.error("Invalid JWT signature: {}", e.getMessage());
//            } catch (MalformedJwtException e) {
//                log.error("Invalid JWT token: {}", e.getMessage());
//            } catch (ExpiredJwtException e) {
//                log.error("JWT token is expired: {}", e.getMessage());
//            } catch (UnsupportedJwtException e) {
//                log.error("JWT token is unsupported: {}", e.getMessage());
//            } catch (IllegalArgumentException e) {
//                log.error("JWT claims string is empty: {}", e.getMessage());
//            }
//        } else {
//            logger.warn("JWT Token does not begin with Bearer String");
//        }
<<<<<<< HEAD
////
=======
//
>>>>>>> 2175f3dd0d4d348db9275e26fa76411fd7af9344
//        // token 검증이 되고 인증 정보가 존재하지 않는 경우 spring security 인증 정보 저장
//        if(adminId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDto userDto = new UserDto();
//            // DB에서 관련 정보 조회
//            // ...
//
//            if(jwtTokenProvider.validateToken(jwtToken, userDto)) {
//                UsernamePasswordAuthenticationToken authenticationToken =
//                        new UsernamePasswordAuthenticationToken(userDto, null ,userDto.getAuthorities());
//
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            }
//        }
//
//        // accessToken 인증이 되었다면 refreshToken 재발급이 필요한 경우 재발급
//        try {
//            if(adminId != null) {
//                jwtTokenProvider.reGenerateRefreshToken(adminId);
//            }
//        }catch (Exception e) {
//            log.error("[JwtRequestFilter] refreshToken 재발급 체크 중 문제 발생 : {}", e.getMessage());
//        }
//
//        filterChain.doFilter(request,response);
//    }
//
//    // Filter에서 제외할 URL 설정
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        return EXCLUDE_URL.stream().anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));
//    }
//}
