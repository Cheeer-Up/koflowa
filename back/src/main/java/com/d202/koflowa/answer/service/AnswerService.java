package com.d202.koflowa.answer.service;

import com.d202.koflowa.answer.domain.Answer;
import com.d202.koflowa.answer.dto.AnswerDto;
import com.d202.koflowa.answer.repository.AnswerRepository;
import com.d202.koflowa.exception.QuestionNotFoundException;
import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AnswerService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public Object postAnswer(Long questionSeq, AnswerDto.Request request) {
        Optional<Question> question = questionRepository.findById(questionSeq);
        if (question.isEmpty()) {
            throw new QuestionNotFoundException("존재하지 않는 게시글입니다.");
        }

        // TODO: 유저 정보 저장 필요

        // 답변 저장
        Answer answer = request.toEntity(question.get());
        answerRepository.save(answer);

        //return
        return new AnswerDto.Response(answer);
    }

    public Object putAnswer(Long questionSeq, AnswerDto.Request request) {
        Optional<Answer> answer = answerRepository.findById(request.getAnswerSeq());
        if (answer.isEmpty()) {
            // throw new
        }

        // TODO: content, accept, modifiedTime 수정
        return null;
    }

    public Object deleteAnswer(Long answerSeq) {
        Optional<Answer> answer = answerRepository.findById(answerSeq);
        if (answer.isEmpty()) {
            // throw new
        }
        answerRepository.delete(answer.get());
        return null;
    }
}
