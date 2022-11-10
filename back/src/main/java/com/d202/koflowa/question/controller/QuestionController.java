package com.d202.koflowa.question.controller;

import com.d202.koflowa.answer.domain.Comment;
import com.d202.koflowa.answer.dto.CommentDto;
import com.d202.koflowa.common.response.Response;
import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.question.dto.QuestionDto;
import com.d202.koflowa.question.dto.QuestionUpdownDto;
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

    /* 질문 전체 조회 */
    @GetMapping("/{page}/{size}")
    public Response getAllQuestion(@PathVariable int page, @PathVariable int size) {
        return Response.success(questionService.getAllQuestion(page, size));
    }

    /* keyword가 포함된 질문 조회 */
    @GetMapping("/{keyword}/{page}/{size}")
    public Response searchQuestionByKeyword(@PathVariable String keyword, @PathVariable int page, @PathVariable int size) {
        return Response.success(questionService.searchQuestionByKeyword(keyword, page, size));
    }

    /* 특정 유저가 작성한 질문 조회 */
    @GetMapping("/{user_seq}/{page}/{size}")
    public Response searchQuestionByUser(@PathVariable Long user_seq, @PathVariable int page, @PathVariable int size) {
        return Response.success(questionService.searchQuestionByUserSeq(user_seq, page, size));
    }

    /* 질문 작성 */
    @PostMapping
    public Response createQuestion(@RequestBody QuestionDto.RequestCreate questionDto) {
        return Response.success(questionService.createQuestion(questionDto));
    }

    /* 질문 상세 조회 */
    @GetMapping("/{question_seq}")
    public Response getQuestionDetail(@PathVariable Long question_seq) {
        return Response.success(questionService.getQuestionDetail(question_seq));
    }

    /* 질문 수정 */
    @PutMapping
    public Response updateQuestion(QuestionDto.Request questionDto) {
        return Response.success(questionService.updateQuestion(questionDto));
    }

    /* 질문 삭제 */
    @DeleteMapping("/{question_seq}")
    public Response deleteQuestion(@PathVariable Long question_seq) {
        questionService.deleteQuestion(question_seq);
        return Response.success();
    }

    /* 질문 추천 및 비추천 */
    @PostMapping("/updown")
    public Response upDownQuestion(@RequestBody QuestionUpdownDto.Request questionUpdownDto) {
        Long user_seq = 0L; // 헤더에서 토큰 분리 후 같이 조회하기
        questionService.setQuestionUpDown(questionUpdownDto);
        return Response.success();
    }

    /* 질문 코멘트 작성 */
    @PostMapping("/comment")
    public Response createComment(@RequestBody CommentDto.Request commentDto) {
        return Response.success(questionService.createComment(commentDto));
    }

    /* 질문 코멘트 수정 */
    @PutMapping("/comment")
    public Response updateComment(@RequestBody CommentDto.Request commentDto) {
        return Response.success(questionService.updateComment(commentDto));
    }

    /* 질문 코멘트 삭제 */
    @DeleteMapping ("/comment")
    public Response deleteComment(@RequestBody CommentDto.Request commentDto) {
        questionService.deleteComment(commentDto);
        return Response.success();
    }

    /* 질문 코멘트 조회 */
    @GetMapping ("/comment/{question_seq}")
    public Response getQuestionComment(@PathVariable Long question_seq) {
        return Response.success(questionService.getQuestionComment(question_seq));
    }
}
