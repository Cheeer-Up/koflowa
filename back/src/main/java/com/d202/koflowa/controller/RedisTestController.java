package com.d202.koflowa.controller;

import com.d202.koflowa.dto.redisTest.RedisTest;
import com.d202.koflowa.service.RedisTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisTestController {

    @Autowired
    private RedisTestService redisTestService;

    @PostMapping("/save")
    public void saveTest() {
        redisTestService.saveDataTest();
    }

    @GetMapping("/get")
    public RedisTest getTest(){
        Optional<RedisTest> t = redisTestService.getDataTest();
        RedisTest redisTest = t.get();
        return redisTest;
    }
}
