//package com.d202.koflowa.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//
//    // 인증되지 않은 사용자 접근에 대한 handler
//    @Autowired
//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    // JWT 요청 처리 필터
//    @Autowired
//    private JwtRequestFilter jwtRequestFilter;
//
//    /*
//     * 스프링 시큐리티 룰을 무시할 URL 규칙 설정
//     * 정적 자원에 대해서는 Security 설정을 적용하지 않음
//     */
//    public void configure(WebSecurity web) throws Exception{
//        web.ignoring()
//                .antMatchers("/resources/**")
//                .antMatchers("/css/**")
//                .antMatchers("/vendor/**")
//                .antMatchers("/js/**")
//                .antMatchers("/js/**")
//                .antMatchers("favicon/**")
//                .antMatchers("/img/**");
//    }
//
//    // 스프링 시큐리티 규칙
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable() // csrf 보안 설정 비활성화
//                .antMatcher("/**").authorizeRequests() // 보호된 리소스 URI에 접근할 수 있는 권한 설정
//
//                .antMatchers("/index").permitAll() // 전체 접근 허용
//                .antMatchers("/main").authenticated() // 인증된 사용자만 접근 허용
//                .antMatchers("/regist").anonymous() // 인증되지 않은 사용자만 접근 허용
//                .antMatchers("/mypage").hasRole("ADMIN") // ROLE_ADMIN 권한을 가진 사용자만 접근 허용
//                .antMatchers("/check").hasAnyRole("ADMIN", "USER") // ROLE_ADMIN 혹은 ROLE_USER 권한을 가진 사용자만 접근 허용
//
//                // 그 외 항목 전부 인증 적용
//                .anyRequest()
//                .authenticated()
//
//                // exception 처리
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 인증되지 않은 사용자 접근 시
//
//                // Spring Security에서 session을 생성하거나 사용하지 않도록 설정
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                // JWT filter 적용
//                .and()
//                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//    }
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
////        http
////                .authorizeHttpRequests((requests) -> requests
////                        .antMatchers("/","/home")
////                        .permitAll()
////                        .anyRequest()
////                        .authenticated()).formLogin((form)-> form
////                        .loginPage("/login")
////                        .permitAll())
////                .logout((logout)->logout.permitAll());
////
////        return http.build();
//
////        @Bean
////        public UserDetailsService userDetailsService() {
////            UserDetails user =
////                    User.withDefaultPasswordEncoder()
////                            .username("user")
////                            .password("password")
////                            .roles("USER")
////                            .build();
////
////            return new InMemoryUserDetailsManager(user);
////        }
//
//}
