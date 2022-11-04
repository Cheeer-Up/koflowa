package com.d202.koflowa.controller;

import com.d202.koflowa.domain.question.Answer;
import com.d202.koflowa.domain.user.ReputationLog;
import com.d202.koflowa.domain.user.User;
import com.d202.koflowa.dto.tag.TagDto;
import com.d202.koflowa.dto.user.UserDto;
import com.d202.koflowa.dto.user.UserTagDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.d202.koflowa.domain.question.Question;
import com.d202.koflowa.service.MyPageService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/my-pages")
@RequiredArgsConstructor
@Tag(name = "MyPage", description = "MyPage API")
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/profile")
    @Operation(summary = "사용자 프로필 조회", description = "")
    public ResponseEntity<User> getProfile(){
        return new ResponseEntity<>(myPageService.getProfile(1), HttpStatus.OK);
    }

    @PutMapping("/profile")
    @Operation(summary = "사용자 프로필 수정", description = "")
    public ResponseEntity<User> putProfile(@RequestBody UserDto.Request user){
        return new ResponseEntity<>(myPageService.putProfile(1, user), HttpStatus.OK);
    }

    @PutMapping("/profile/image")
    @Operation(summary = "사용자 프로필 이미지 수정", description = "")
    public void putProfileImg(@RequestParam("data")MultipartFile multipartFile){

    }

    @GetMapping("/tags")
    @Operation(summary = "사용자 관심, 무시 태그 조회", description = "")
    public ResponseEntity<List<UserTagDto.Response>> getTags(){
        return new ResponseEntity<>(myPageService.getTags(1), HttpStatus.OK);
    }

    @GetMapping("/reputation")
    @Operation(summary = "사용자 명성 로그 조회", description = "")
    public ResponseEntity<List<ReputationLog>> getReputation(Pageable pageable){
        return new ResponseEntity<>(myPageService.getReputation(1, pageable), HttpStatus.OK);
    }

    @GetMapping("/question")
    @Operation(summary = "사용자 작성 질문 조회", description = "")
    public ResponseEntity<List<Question>> getQuestion(Pageable pageable){
        return new ResponseEntity<>(myPageService.getQuestion(1, pageable), HttpStatus.OK);
    }

    @GetMapping("/answer")
    @Operation(summary = "사용자 작성 답변 조회", description = "")
    public ResponseEntity<List<Answer>> getAnswer(Pageable pageable){
        return new ResponseEntity<>(myPageService.getAnswer(1, pageable), HttpStatus.OK);
    }
}
