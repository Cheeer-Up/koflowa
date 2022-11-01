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


    public boolean saveDataTest(RedisTest redisTest){
        try{
            redisTestRepository.save(redisTest);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public Optional<RedisTest> getDataTest(String id){
        return redisTestRepository.findById(id);

    }
}
