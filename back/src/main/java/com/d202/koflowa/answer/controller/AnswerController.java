package com.d202.koflowa.answer.controller;

import com.d202.koflowa.answer.dto.AnswerDto;
import com.d202.koflowa.answer.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@io.swagger.v3.oas.annotations.tags.Tag(name = "Answer", description = "")
@Slf4j
@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @Operation(summary = "답글 작성", description = "")
    @PostMapping("/{questionSeq}")
    public ResponseEntity<?> postAnswer(@PathVariable Long questionSeq,
                                        @RequestBody AnswerDto.Request request) {
        return new ResponseEntity<>(answerService.postAnswer(questionSeq, request), HttpStatus.OK);
    }

    @Operation(summary = "답글 수정", description = "")
    @PutMapping("/{questionSeq}")
    public ResponseEntity<?> putAnswer(@PathVariable Long questionSeq,
                                        @RequestBody AnswerDto.Request request) {
        return new ResponseEntity<>(answerService.putAnswer(questionSeq, request), HttpStatus.OK);
    }

    @Operation(summary = "답글 삭제", description = "")
    @DeleteMapping("/{answerSeq}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long answerSeq) {
        return new ResponseEntity<>(answerService.deleteAnswer(answerSeq), HttpStatus.OK);
    }
}
