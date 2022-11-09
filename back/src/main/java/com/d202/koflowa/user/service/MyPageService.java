package com.d202.koflowa.user.service;

import com.d202.koflowa.answer.domain.Answer;
import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.user.domain.ReputationLog;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.domain.UserTag;
import com.d202.koflowa.user.dto.UserDto;
import com.d202.koflowa.user.dto.UserTagDto;
import com.d202.koflowa.exception.UserNotFoundException;
import com.d202.koflowa.answer.repository.AnswerRepository;
import com.d202.koflowa.question.repository.QuestionRepository;
import com.d202.koflowa.user.repository.ReputationLogRepository;
import com.d202.koflowa.user.repository.UserRepository;
import com.d202.koflowa.user.repository.UserTagRepository;
import lombok.RequiredArgsConstructor;
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
        return questionRepository.findByUserSeq(id, pageable).getContent();
    }

    public List<Answer> getAnswer(long id, Pageable pageable){
        return answerRepository.findByUserSeq(id, pageable).getContent();
    }
}
