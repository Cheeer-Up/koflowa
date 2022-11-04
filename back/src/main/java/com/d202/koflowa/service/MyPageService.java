package com.d202.koflowa.service;

import com.d202.koflowa.domain.BaseTimeEntity;
import com.d202.koflowa.domain.question.Answer;
import com.d202.koflowa.domain.question.Question;
import com.d202.koflowa.domain.user.ReputationLog;
import com.d202.koflowa.domain.user.User;
import com.d202.koflowa.domain.user.UserTag;
import com.d202.koflowa.dto.user.UserDto;
import com.d202.koflowa.dto.user.UserTagDto;
import com.d202.koflowa.exception.UserNotFoundException;
import com.d202.koflowa.repository.answer.AnswerRepository;
import com.d202.koflowa.repository.question.QuestionRepository;
import com.d202.koflowa.repository.user.ReputationLogRepository;
import com.d202.koflowa.repository.user.UserRepository;
import com.d202.koflowa.repository.user.UserTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {
    private final UserRepository userRepository;
    private final UserTagRepository userTagRepository;
    private final ReputationLogRepository reputationLogRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public User getProfile(long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));
    }

    @Transactional
    public User putProfile(long id, UserDto.Request newUser){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("사용자를 찾을 수 없습니다.");

        user.get().putUser(newUser);
        userRepository.save(user.get());

        return  userRepository.findById(id).get();
    }

    @Transactional
    public void putProfileImg(long id, String img){
        // S3 저장 필요
    }

    public List<UserTagDto.Response> getTags(long id){
        List<UserTag> userTagList = userTagRepository.findByUser_Seq(id);
        List<UserTagDto.Response> userTagRes = new ArrayList<>();
        for (UserTag userTag : userTagList) {
            userTagRes.add(new UserTagDto.Response(userTag));
        }
        return userTagRes;
    }

    public List<ReputationLog> getReputation(long id, Pageable pageable){
        return reputationLogRepository.findByUser_Seq(id, pageable).getContent();
    }

    public List<Question> getQuestion(long id, Pageable pageable){
        return questionRepository.findByUser_Seq(id, pageable).getContent();
    }

    public List<Answer> getAnswer(long id, Pageable pageable){
        return answerRepository.findByUserSeq(id, pageable).getContent();
    }
}
