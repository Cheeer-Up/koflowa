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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    /* 질문 전체 조회 */
    @Operation(summary = "전체 질문 조회", description = "전체 질문 조회 api 입니다. 오류가 발생하면 page와 size만 입력하시오.")
    @GetMapping("/all")
    public Response getAllQuestion(Pageable pageable) {
        return Response.success(questionService.getAllQuestion(pageable));
    }

    /* keyword가 포함된 질문 조회 */
    @Operation(summary = "keyword 포함 질문 조회", description = "keyword로 제목과 내용을 검색하여 질문들을 조회하는 api 입니다. 오류가 발생하면 page와 size만 입력하시오.")
    @GetMapping("/keyword/{keyword}")
    public Response searchQuestionByKeyword(@PathVariable String keyword, Pageable pageable) {
        return Response.success(questionService.searchQuestionByKeyword(keyword, pageable));
    }

    /* 특정 유저가 작성한 질문 조회 */
    @Operation(summary = "유저 질문 조회", description = "특정 유저 ID값으로 질문을 검색하는 api 입니다. 오류가 발생하면 page와 size만 입력하시오.")
    @GetMapping("/user/{user_seq}")
    public Response searchQuestionByUser(@PathVariable Long user_seq, Pageable pageable) {
        return Response.success(questionService.searchQuestionByUserSeq(user_seq, pageable));
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
    public Response updateQuestion(@RequestBody QuestionDto.Request questionDto) {
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
        return Response.success(questionService.setQuestionUpDown(questionUpdownDto));
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
    public Response updateComment(@RequestBody CommentDto.RequestUpdate commentDto) {
        return Response.success(questionService.updateComment(commentDto));
    }

    /* 질문 코멘트 삭제 */
    @Operation(summary = "코멘트 삭제", description = "특정 코멘트를 삭제하는 api 입니다.")
    @DeleteMapping ("/comment/{comment_seq}")
    public Response deleteComment(@PathVariable("comment_seq") Long commentSeq) {
        questionService.deleteComment(commentSeq);
        return Response.success();
    }

    /* 질문 코멘트 조회 */
    @Operation(summary = "코멘트 조회", description = "질문에 대한 코멘트들을 조회하는 api 입니다.")
    @GetMapping ("/comment/{question_seq}")
    public Response getQuestionComment(@PathVariable Long question_seq) {
        return Response.success(questionService.getQuestionComment(question_seq));
    }
}
