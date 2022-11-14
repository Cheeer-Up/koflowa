package com.d202.koflowa.answer.controller;

import com.d202.koflowa.answer.dto.AnswerDto;
import com.d202.koflowa.answer.dto.AnswerUpdownDto;
import com.d202.koflowa.answer.dto.CommentDto;
import com.d202.koflowa.answer.service.AnswerService;
import com.d202.koflowa.common.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@io.swagger.v3.oas.annotations.tags.Tag(name = "Answer", description = "")
@Slf4j
@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @Operation(summary = "답글 작성", description = "")
    @PostMapping("/{question-seq}")
    public Response crateAnswer(@PathVariable("question-seq") Long questionSeq,
                               @RequestBody AnswerDto.Request request) {
        return Response.success(answerService.createAnswer(questionSeq, request));
    }

    @Operation(summary = "답글 수정", description = "")
    @PutMapping("/{answer-seq}")
    public Response updateAnswer(@PathVariable("answer-seq") Long answerSeq,
                                        @RequestBody AnswerDto.Request request) {
        return Response.success(answerService.updateAnswer(answerSeq, request));
    }

    @Operation(summary = "답글 삭제", description = "")
    @DeleteMapping("/{answer-seq}")
    public Response deleteAnswer(@PathVariable("answer-seq") Long answerSeq) {
        answerService.deleteAnswer(answerSeq);
        return Response.success();
    }

    @Operation(summary = "답글 추천,비추천", description = "")
    @PostMapping("/updown/{answer-seq}")
    public Response postAnswerUp(@PathVariable("answer-seq") Long answerSeq, @RequestBody AnswerUpdownDto.Request request) {
        answerService.upDownAnswer(answerSeq, request);
        return Response.success();
    }

    @Operation(summary = "답글 채택", description = "")
    @PostMapping("/accept/{answer-seq}")
    public Response postBestAnswer(@PathVariable("answer-seq") Long answerSeq) {
        answerService.postBestAnswer(answerSeq);
        return Response.success();
    }


    /* 답변 코멘트 작성 */
    @Operation(summary = "코멘트 작성", description = "질문에 대한 코멘트를 작성하는 api 입니다.")
    @PostMapping("/comment")
    public Response createComment(@RequestBody CommentDto.RequestCreate commentDto) {
        return Response.success(answerService.createComment(commentDto));
    }

    /* 답변 코멘트 수정 */
    @PutMapping("/comment")
    @Operation(summary = "코멘트 수정", description = "특정 코멘트를 수정하는 api 입니다.")
    public Response updateComment(@RequestBody CommentDto.Request commentDto) {
        return Response.success(answerService.updateComment(commentDto));
    }

    /* 답변 코멘트 삭제 */
    @Operation(summary = "코멘트 삭제", description = "특정 코멘트를 삭제하는 api 입니다.")
    @DeleteMapping ("/comment")
    public Response deleteComment(@RequestBody CommentDto.Request commentDto) {
        answerService.deleteComment(commentDto);
        return Response.success();
    }

    /* 답변 코멘트 조회 */
    @Operation(summary = "코멘트 조회", description = "답변에 대한 코멘트들을 조회하는 api 입니다.")
    @GetMapping ("/comment/{answer-seq}")
    public Response getQuestionComment(@PathVariable("answer-seq") Long answerSeq) {
        return Response.success(answerService.getAnswerComment(answerSeq));
    }

    /* 답변 상세 조회 */
    @Operation(summary = "답변 상세 조회", description = "답변 seq로 답변 상세 조회하는 api 입니다.")
    @GetMapping("/detail/{answer-seq}")
    public Response getAnswerDetail(@PathVariable("answer-seq") Long answerSeq) {
        return Response.success(answerService.getAnswerDetail(answerSeq));
    }

    /* 질문에 대한 답변 리스트 조회 */
    @Operation(summary = "질문에 대한 답변 리스트 조회", description = "질문 seq로 질문에 대한 대답 리스트를 조회하는 api 입니다.")
    @GetMapping("/{question-seq}/{page}/{size}")
    public Response searchQuestionByKeyword(@PathVariable("question-seq") Long questionSeq, @PathVariable int page, @PathVariable int size) {
        return Response.success(answerService.searchAnswerByQuestionSeq(questionSeq, page, size));
    }
}
