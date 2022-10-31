package com.d202.koflowa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("")
    public Map<String, Object> test(){
        Map<String, Object> response = new HashMap<>();
        response.put("result", "Success");
        return response;
    }
}
