package com.d202.koflowa.question.service;

import com.d202.koflowa.exception.UserNotFoundException;
import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.question.dto.QuestionDto;
import com.d202.koflowa.question.exception.QuestionUserNotFoundException;
import com.d202.koflowa.question.repository.QuestionRepository;
import com.d202.koflowa.talk.exception.RoomNotFoundException;
import com.d202.koflowa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    public Page<Question> getAllQuestion(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return questionRepository.findAll(pageRequest);
    }

    public Page<Question> searchQuestionByKeyword(String keyword, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return questionRepository.findAllByKeyword(keyword, pageRequest);
    }

    public Page<Question> searchQuestionByUserSeq(Long user_seq, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return questionRepository.findAllByUser(userRepository.findBySeq(user_seq).get(), pageRequest);
    }

    public QuestionDto.Response createQuestion(QuestionDto.RequestCreate questionDto) {
        Question question = questionRepository.save(questionDto.toEntity(userRepository.findBySeq(questionDto.getUser_seq())
                .orElseThrow(() -> new QuestionUserNotFoundException())));
        return new QuestionDto.Response(question);
    }
}
