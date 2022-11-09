package com.d202.koflowa.user.controller;

import com.d202.koflowa.answer.domain.Answer;
import com.d202.koflowa.common.response.Response;
import com.d202.koflowa.user.domain.ReputationLog;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.dto.UserDto;
import com.d202.koflowa.user.dto.UserTagDto;
import com.d202.koflowa.user.service.UploadImgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.user.service.MyPageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/my-pages")
@RequiredArgsConstructor
@Tag(name = "MyPage", description = "MyPage API")
public class MyPageController {

    private final MyPageService myPageService;
    private final UploadImgService uploadImgService;


    @GetMapping("/profile")
    @Operation(summary = "전체 사용자 프로필 조회", description = "")
    public Response getAllProfile(Pageable pageable){
        return Response.success(myPageService.getAllProfile(pageable));
    }


    @GetMapping("/profile/{seq}")
    @Operation(summary = "특정 사용자 프로필 조회", description = "")
    public Response getProfile(@PathVariable long seq){
        return Response.success(myPageService.getProfile(seq));
    }

    @PutMapping("/profile")
    @Operation(summary = "사용자 프로필 수정", description = "")
    public Response putProfile(@RequestBody UserDto.Request user){
        return Response.success(myPageService.putProfile(1, user));
    }

    @PutMapping("/profile/image")
    @Operation(summary = "사용자 프로필 이미지 수정", description = "")
    public Response putProfileImg(@RequestParam("data")MultipartFile multipartFile) throws IOException {
       return Response.success(uploadImgService.upload(multipartFile, "static",1));
    }

    @GetMapping("/tags")
    @Operation(summary = "사용자 관심, 무시 태그 조회", description = "")
    public Response getTags(){
        return Response.success(myPageService.getTags(1));
    }

    @GetMapping("/reputation")
    @Operation(summary = "사용자 명성 로그 조회", description = "")
    public Response getReputation(Pageable pageable){
        return Response.success(myPageService.getReputation(1, pageable));
    }

    @GetMapping("/question")
    @Operation(summary = "사용자 작성 질문 조회", description = "")
    public Response getQuestion(Pageable pageable){
        return Response.success(myPageService.getQuestion(1, pageable));
    }

    @GetMapping("/answer")
    @Operation(summary = "사용자 작성 답변 조회", description = "")
    public Response getAnswer(Pageable pageable){
        return Response.success(myPageService.getAnswer(1, pageable));
    }
}
