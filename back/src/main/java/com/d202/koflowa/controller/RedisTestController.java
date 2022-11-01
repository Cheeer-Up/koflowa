package com.d202.koflowa.controller;

import com.d202.koflowa.dto.redisTest.RedisTest;
import com.d202.koflowa.service.RedisTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisTestController {

    @Autowired
    private RedisTestService redisTestService;

    @PostMapping("/save")
    public ResponseEntity saveTest(@RequestBody RedisTest redisTest) {

        if(redisTestService.saveDataTest(redisTest)){
            return new ResponseEntity(HttpStatus.OK);
        }
        else
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/get")
    public RedisTest getTest(String id){
        Optional<RedisTest> t = redisTestService.getDataTest(id);
        RedisTest redisTest = t.get();
        return redisTest;
    }
}
