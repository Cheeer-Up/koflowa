package com.d202.koflowa.S_J_O.service.auth;

import com.d202.koflowa.S_J_O.advice.assertThat.DefaultAssert;
//import com.d202.koflowa.S_J_O.security.token.UserPrincipal;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("유저 정보를 찾을 수 없습니다.")
        );

        return user;
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        DefaultAssert.isOptionalPresent(user);

        return user.get();
    }
    
}
