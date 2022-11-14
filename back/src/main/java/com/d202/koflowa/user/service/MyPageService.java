package com.d202.koflowa.user.service;

import com.d202.koflowa.S_J_O.advice.assertThat.DefaultAssert;
import com.d202.koflowa.S_J_O.payload.response.ApiResponse;
import com.d202.koflowa.S_J_O.security.token.UserPrincipal;
import com.d202.koflowa.answer.domain.Answer;
import com.d202.koflowa.answer.dto.AnswerDto;
import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.question.domain.QuestionTag;
import com.d202.koflowa.question.dto.QuestionDto;
import com.d202.koflowa.question.repository.QuestionTagRepository;
import com.d202.koflowa.user.domain.ReputationLog;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.dto.ReputationLogDto;
import com.d202.koflowa.user.dto.UserDto;
import com.d202.koflowa.user.dto.UserTagCntDto;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {
    private final UserRepository userRepository;
    private final UserTagRepository userTagRepository;
    private final ReputationLogRepository reputationLogRepository;
    private final QuestionRepository questionRepository;
    private final QuestionTagRepository questionTagRepository;
    private final AnswerRepository answerRepository;

    public Page<UserDto.Response> getAllProfile(Pageable pageable){
        Page<User> userList = userRepository.findAll(pageable);
        List<UserDto.Response> dtoList = new ArrayList<>();
        for (User user :userList){
            dtoList.add(new UserDto.Response(user));
        }
        return new PageImpl<>(dtoList, pageable, userList.getTotalElements());
    }

    public UserDto.Response getProfile(long id){
        return new UserDto.Response(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }
    public User getProfileByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new IllegalStateException("not found user"));
    }

    @Transactional
    public UserDto.Response putProfile(long id, UserDto.Request newUser){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException();

        user.get().putUser(newUser);
        return new UserDto.Response(userRepository.save(user.get()));
    }

    public List<UserTagCntDto.Response> getTags(long id){
        List<Question> userQuestion = questionRepository.findAllByUserSeq(id);
        List<UserTagCntDto.Response> res = new ArrayList<>();
        if (!userQuestion.isEmpty()){
            HashMap<String, Long> tagCnt = new HashMap<>();
            for (Question question : userQuestion){
                Long seq = question.getSeq();
                List<QuestionTag> questionTag = questionTagRepository.findAllByQuestion_Seq(seq);
                for (QuestionTag tag : questionTag) {
                    String tagName = tag.getTag().getName();
                    if (tagCnt.containsKey(tagName)){
                        Long cnt = tagCnt.get(tagName);
                        tagCnt.put(tagName, cnt + 1);
                    }else{
                        tagCnt.put(tagName, 1L);
                    }
                }
            }
            for (String tagName : tagCnt.keySet()) {
                res.add(new UserTagCntDto.Response(tagName, tagCnt.get(tagName)));
            }
        }

        return res;
    }

    public Page<ReputationLogDto.Response> getReputation(long id, Pageable pageable){
        Page<ReputationLog> reputationLogList = reputationLogRepository.findByUser_Seq(id, pageable);
        List<ReputationLogDto.Response> dtoList = new ArrayList<>();
        for (ReputationLog reputationLog : reputationLogList) {
            dtoList.add(new ReputationLogDto.Response(reputationLog));
        }
        return new PageImpl<ReputationLogDto.Response>(dtoList, pageable, reputationLogList.getTotalElements());
    }

    public Page<QuestionDto.Response> getQuestion(long id, Pageable pageable){
        Page<Question> questionList = questionRepository.findByUserSeq(id, pageable);
        List<QuestionDto.Response> dtoList = new ArrayList<>();
        for (Question question : questionList) {
            dtoList.add(new QuestionDto.Response(question.getTitle(), question.getSeq(), question.getCreatedTime()));
        }
        return new PageImpl<QuestionDto.Response>(dtoList, pageable, questionList.getTotalElements());
    }

    public Page<AnswerDto.Response> getAnswer(long id, Pageable pageable){
        Page<Answer> answerList = answerRepository.findByUserSeq(id, pageable);
        List<AnswerDto.Response> dtoList = new ArrayList<>();
        for (Answer answer : answerList) {
            dtoList.add(new AnswerDto.Response(answer.getSeq(), answer.getQuestion().getSeq(), answer.getContent(), answer.getCreatedTime()));
        }
        return new PageImpl<AnswerDto.Response>(dtoList, pageable, answerList.getTotalElements());
    }

    public ResponseEntity<?> readByUser(UserPrincipal userPrincipal){
        Optional<User> user = userRepository.findById(userPrincipal.getId());
        DefaultAssert.isOptionalPresent(user);
        ApiResponse apiResponse = ApiResponse.builder().check(true).information(user.get()).build();
        return ResponseEntity.ok(apiResponse);
    }
}
