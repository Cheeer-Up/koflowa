package com.d202.koflowa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my-pages")
@RequiredArgsConstructor
public class MyPageController {

    @GetMapping("/profile")
    public void getProfile(){

    }

    @PutMapping("/profile")
    public void putProfile(){

    }

    @PutMapping("/profile/image")
    public void putProfileImg(){

    }

    @GetMapping("/tags")
    public void getTags(){

    }

    @GetMapping("/reputation/{page}/{size}")
    public void getReputation(@PathVariable long page, @PathVariable long size){

    }

    @GetMapping("/question/{page}/{size}")
    public void getQuestion(@PathVariable long page, @PathVariable long size){

    }

    @GetMapping("/answer/{page}/{size}")
    public void getAnswer(@PathVariable long page, @PathVariable long size){

    }
}
