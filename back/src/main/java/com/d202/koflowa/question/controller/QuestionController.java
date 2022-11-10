package com.d202.koflowa.question.controller;

import com.d202.koflowa.answer.domain.Comment;
import com.d202.koflowa.answer.dto.CommentDto;
import com.d202.koflowa.common.response.Response;
import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.question.dto.QuestionDto;
import com.d202.koflowa.question.dto.QuestionUpdownDto;
import com.d202.koflowa.question.service.QuestionService;
import com.d202.koflowa.talk.dto.MessageDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    /* 질문 전체 조회 */
    @Operation(summary = "전체 질문 조회", description = "전체 질문 조회 api 입니다.")
    @GetMapping("/{page}/{size}")
    public Response getAllQuestion(@PathVariable int page, @PathVariable int size) {
        return Response.success(questionService.getAllQuestion(page, size));
    }

    /* keyword가 포함된 질문 조회 */
    @Operation(summary = "keyword 포함 질문 조회", description = "keyword로 제목과 내용을 검색하여 질문들을 조회하는 api 입니다.")
    @GetMapping("/keyword/{keyword}/{page}/{size}")
    public Response searchQuestionByKeyword(@PathVariable String keyword, @PathVariable int page, @PathVariable int size) {
        return Response.success(questionService.searchQuestionByKeyword(keyword, page, size));
    }

    /* 특정 유저가 작성한 질문 조회 */
    @Operation(summary = "유저 질문 조회", description = "특정 유저 ID값으로 질문을 검색하는 api 입니다.")
    @GetMapping("/user/{user_seq}/{page}/{size}")
    public Response searchQuestionByUser(@PathVariable Long user_seq, @PathVariable int page, @PathVariable int size) {
        return Response.success(questionService.searchQuestionByUserSeq(user_seq, page, size));
    }

    /* 질문 작성 */
    @Operation(summary = "질문 작성", description = "질문 작성 api 입니다.")
    @PostMapping
    public Response createQuestion(@RequestBody QuestionDto.RequestCreate questionDto) {
        return Response.success(questionService.createQuestion(questionDto));
    }

    /* 질문 상세 조회 */
    @Operation(summary = "질문 상세 조회", description = "질문 상세 조회 api 입니다.")
    @GetMapping("/{question_seq}")
    public Response getQuestionDetail(@PathVariable Long question_seq) {
        return Response.success(questionService.getQuestionDetail(question_seq));
    }

    /* 질문 수정 */
    @Operation(summary = "질문 수정", description = "질문 수정 api 입니다.")
    @PutMapping
    public Response updateQuestion(QuestionDto.Request questionDto) {
        return Response.success(questionService.updateQuestion(questionDto));
    }

    /* 질문 삭제 */
    @Operation(summary = "질문 삭제", description = "질문을 삭제하는 api 입니다.")
    @DeleteMapping("/{question_seq}")
    public Response deleteQuestion(@PathVariable Long question_seq) {
        questionService.deleteQuestion(question_seq);
        return Response.success();
    }

    /* 질문 추천 및 비추천 */
    @Operation(summary = "질문 추천/비추천", description = "질문을 추천하거나 비추천하는 api 입니다.")
    @PostMapping("/updown")
    public Response upDownQuestion(@RequestBody QuestionUpdownDto.Request questionUpdownDto) {
        Long user_seq = 0L; // 헤더에서 토큰 분리 후 같이 조회하기
        questionService.setQuestionUpDown(questionUpdownDto);
        return Response.success();
    }

    /* 질문 코멘트 작성 */
    @Operation(summary = "코멘트 작성", description = "질문에 대한 코멘트를 작성하는 api 입니다.")
    @PostMapping("/comment")
    public Response createComment(@RequestBody CommentDto.RequestCreate commentDto) {
        return Response.success(questionService.createComment(commentDto));
    }

    /* 질문 코멘트 수정 */
    @PutMapping("/comment")
    @Operation(summary = "코멘트 수정", description = "특정 코멘트를 수정하는 api 입니다.")
    public Response updateComment(@RequestBody CommentDto.Request commentDto) {
        return Response.success(questionService.updateComment(commentDto));
    }

    /* 질문 코멘트 삭제 */
    @Operation(summary = "코멘트 삭제", description = "특정 코멘트를 삭제하는 api 입니다.")
    @DeleteMapping ("/comment")
    public Response deleteComment(@RequestBody CommentDto.Request commentDto) {
        questionService.deleteComment(commentDto);
        return Response.success();
    }

    /* 질문 코멘트 조회 */
    @Operation(summary = "코멘트 조회", description = "질문에 대한 코멘트들을 조회하는 api 입니다.")
    @GetMapping ("/comment/{question_seq}")
    public Response getQuestionComment(@PathVariable Long question_seq) {
        return Response.success(questionService.getQuestionComment(question_seq));
    }
}
