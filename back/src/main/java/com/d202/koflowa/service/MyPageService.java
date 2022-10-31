package com.d202.koflowa.service;

import com.d202.koflowa.domain.user.User;
import com.d202.koflowa.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;
    public Optional<User> getProfile(long id){
        return userRepository.findById(id);
    }

    public void putProfile(long id){
        Optional<User> user = userRepository.findById(id);
    }

    public void putProfileImg(long id, String img){

    }

    public void getTags(){

    }

    public void getReputation(@PathVariable long page, @PathVariable long size){

    }

    public void getQuestion(@PathVariable long page, @PathVariable long size){

    }

    public void getAnswer(@PathVariable long page, @PathVariable long size){

    }
}
