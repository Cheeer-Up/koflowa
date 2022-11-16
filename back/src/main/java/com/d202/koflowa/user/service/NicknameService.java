package com.d202.koflowa.user.service;

import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NicknameService {
    private final UserRepository userRepository;
    public User setMyNickname(String nickname) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(nickname != null || nickname.equals("") == false) {
            user.setNickname(nickname);
            userRepository.save(user);
        }

        return user;
    }
}
