package com.d202.koflowa.service;

import com.d202.koflowa.dto.redisTest.RedisTest;
import com.d202.koflowa.repository.redisTest.RedisTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RedisTestService {

    @Autowired
    private RedisTestRepository redisTestRepository;

    public void saveDataTest(){
        redisTestRepository.save(new RedisTest("010","chung",3, LocalDateTime.now()));
    }
    public Optional<RedisTest> getDataTest(){
        return redisTestRepository.findById("010");

    }
}
