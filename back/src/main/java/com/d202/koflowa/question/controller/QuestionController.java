package com.d202.koflowa.question.controller;

import com.d202.koflowa.common.response.Response;
import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.question.dto.QuestionDto;
import com.d202.koflowa.question.service.QuestionService;
import com.d202.koflowa.talk.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{page}/{size}")
    public Response getAllQuestion(@PathVariable int page, @PathVariable int size) {
        return Response.success(questionService.getAllQuestion(page, size));
    }

    @GetMapping("/{keyword}/{page}/{size}")
    public Response searchQuestionByKeyword(@PathVariable String keyword, @PathVariable int page, @PathVariable int size) {
        return Response.success(questionService.searchQuestionByKeyword(keyword, page, size));
    }

    @GetMapping("/{user_seq}/{page}/{size}")
    public Response searchQuestionByUser(@PathVariable Long user_seq, @PathVariable int page, @PathVariable int size) {
        return Response.success(questionService.searchQuestionByUserSeq(user_seq, page, size));
    }


    @PostMapping
    public Response createQuestion(@RequestBody QuestionDto.RequestCreate questionDto) {
        return Response.success(questionService.createQuestion(questionDto));
    }
}
