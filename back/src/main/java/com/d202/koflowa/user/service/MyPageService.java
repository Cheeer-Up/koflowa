package com.d202.koflowa.user.service;

import com.d202.koflowa.answer.domain.Answer;
import com.d202.koflowa.answer.dto.AnswerDto;
import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.question.dto.QuestionDto;
import com.d202.koflowa.user.domain.ReputationLog;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.domain.UserTag;
import com.d202.koflowa.user.dto.ReputationLogDto;
import com.d202.koflowa.user.dto.UserDto;
import com.d202.koflowa.user.dto.UserTagDto;
import com.d202.koflowa.answer.repository.AnswerRepository;
import com.d202.koflowa.question.repository.QuestionRepository;
import com.d202.koflowa.user.exception.UserNotFoundException;
import com.d202.koflowa.user.repository.ReputationLogRepository;
import com.d202.koflowa.user.repository.UserRepository;
import com.d202.koflowa.user.repository.UserTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

    public List<UserDto.Response> getAllProfile(Pageable pageable){
        List<User> userList = userRepository.findAll(pageable).getContent();
        List<UserDto.Response> res = new ArrayList<>(userList.size());
        for (User user :userList){
            res.add(new UserDto.Response(user));
        }
        return res;
    }

    public UserDto.Response getProfile(long id){
        return new UserDto.Response(userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new));
    }

    @Transactional
    public UserDto.Response putProfile(long id, UserDto.Request newUser){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException();

        user.get().putUser(newUser);
        return new UserDto.Response(userRepository.save(user.get()));
    }

    public List<UserTagDto.Response> getTags(long id){
        List<UserTag> userTagList = userTagRepository.findByUser_Seq(id);
        List<UserTagDto.Response> res = new ArrayList<>();
        for (UserTag userTag : userTagList) {
            res.add(new UserTagDto.Response(userTag));
        }
        return res;
    }

    public List<ReputationLogDto.Response> getReputation(long id, Pageable pageable){
        List<ReputationLog> reputationLogList = reputationLogRepository.findByUser_Seq(id, pageable).getContent();
        List<ReputationLogDto.Response> res = new ArrayList<>(reputationLogList.size());
        for (ReputationLog reputationLog : reputationLogList) {
            res.add(new ReputationLogDto.Response(reputationLog));
        }
        return res;
    }

    public List<QuestionDto.Response> getQuestion(long id, Pageable pageable){
        List<Question> questionList = questionRepository.findByUserSeq(id, pageable).getContent();
        List<QuestionDto.Response> res = new ArrayList<>(questionList.size());
        for (Question question : questionList) {
            res.add(new QuestionDto.Response(question));
        }
        return res;
    }

    public List<AnswerDto.Response> getAnswer(long id, Pageable pageable){
        List<Answer> answerList = answerRepository.findByUserSeq(id, pageable).getContent();
        List<AnswerDto.Response> res = new ArrayList<>(answerList.size());
        for (Answer answer : answerList) {
            res.add(new AnswerDto.Response(answer));
        }
        return res;
    }
}
