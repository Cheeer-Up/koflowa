package com.d202.koflowa.question.service;

import com.d202.koflowa.S_J_O.service.auth.CustomUserDetailsService;
import com.d202.koflowa.answer.domain.Answer;
import com.d202.koflowa.answer.domain.Comment;
import com.d202.koflowa.answer.dto.CommentDto;
import com.d202.koflowa.answer.repository.CommentRepository;
import com.d202.koflowa.common.domain.QAType;
import com.d202.koflowa.common.domain.UDType;
import com.d202.koflowa.common.exception.CommentNotFoundException;
import com.d202.koflowa.exception.QuestionNotFoundException;
import com.d202.koflowa.exception.UserNotFoundException;
import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.question.domain.QuestionUpdown;
import com.d202.koflowa.question.dto.QuestionDto;
import com.d202.koflowa.question.dto.QuestionUpdownDto;
import com.d202.koflowa.question.exception.QuestionCommentNotFoundException;
import com.d202.koflowa.question.exception.QuestionUpException;
import com.d202.koflowa.question.exception.QuestionUserNotFoundException;
import com.d202.koflowa.question.exception.SpecificQuestionNotFound;
import com.d202.koflowa.question.repository.QuestionRepository;
import com.d202.koflowa.question.repository.QuestionUpDownRepository;
import com.d202.koflowa.tag.domain.Tag;
import com.d202.koflowa.tag.dto.TagDto;
import com.d202.koflowa.talk.exception.RoomNotFoundException;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.repository.UserRepository;
import com.d202.koflowa.user.service.ReputationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionUpDownRepository questionUpDownRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ReputationService reputationService;

    public Page<QuestionDto.Response> getAllQuestion(Pageable pageable) {
        Page<Question> questions = questionRepository.findAll(pageable);

        List<QuestionDto.Response> pageDtoList = new ArrayList<>();

        for(Question question : questions) {
            QuestionDto.Response questionResponse = new QuestionDto.Response(question);
            pageDtoList.add(questionResponse);
        }

        return new PageImpl<QuestionDto.Response>(pageDtoList, pageable, questions.getTotalElements());
    }

    public Page<QuestionDto.Response> searchQuestionByKeyword(String keyword, Pageable pageable) {
        Page<Question> questions = questionRepository.findAllByKeyword(keyword, pageable);

        List<QuestionDto.Response> pageDtoList = new ArrayList<>();

        for(Question question : questions) {
            QuestionDto.Response questionResponse = new QuestionDto.Response(question);
            pageDtoList.add(questionResponse);
        }

        return new PageImpl<QuestionDto.Response>(pageDtoList, pageable, questions.getTotalElements());
    }

    public Page<QuestionDto.Response> searchQuestionByUserSeq(Long userSeq, Pageable pageable) {
        Page<Question> questions = questionRepository.findAllByUserSeqOrderByCreatedTimeDesc(userSeq, pageable);

        List<QuestionDto.Response> pageDtoList = new ArrayList<>();

        for(Question question : questions) {
            QuestionDto.Response questionResponse = new QuestionDto.Response(question);
            pageDtoList.add(questionResponse);
        }

        return new PageImpl<QuestionDto.Response>(pageDtoList, pageable, questions.getTotalElements());
    }

    public QuestionDto.Response createQuestion(QuestionDto.RequestCreate questionDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("질문생성 "+user);
        Question question = questionRepository.save(questionDto.toEntity(user));
        reputationService.saveLog(user,"질문 작성", 15, question.getSeq());
        return new QuestionDto.Response(question);
    }

    public QuestionDto.Response getQuestionDetail(Long question_seq) {
        Question question = questionRepository.findBySeq(question_seq)
                .orElseThrow(() -> new SpecificQuestionNotFound());
        return  new QuestionDto.Response(question);
    }
    public QuestionDto.Response updateQuestion(QuestionDto.Request questionDto) {
        System.out.println(questionDto);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        Question question = questionRepository.findBySeqAndUser(questionDto.getQuestionSeq(), user.getSeq())
                .orElseThrow(() -> new SpecificQuestionNotFound());
        System.out.println(question);
        question.setTitle(questionDto.getQuestionTitle());
        question.setContent(questionDto.getQuestionContent());
        System.out.println(question);

        return new QuestionDto.Response(questionRepository.save(question));
    }

    public void deleteQuestion(Long question_seq) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        questionRepository.delete(questionRepository.findBySeqAndUser(question_seq, user.getSeq())
                .orElseThrow(() -> new SpecificQuestionNotFound()));
    }

    //질문 테이블에 숫자 기록하는거 하기
    public QuestionDto.Response setQuestionUpDown(QuestionUpdownDto.Request questionUpdownDto) {
        System.out.println(questionUpdownDto);

        /* 중복 검색 방지를 위한 객체 생성 */
        Question question =  questionRepository.findBySeq(questionUpdownDto.getQuestionSeq()).get();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        /* QuestionUpdown 조회 */
        QuestionUpdown questionUpdown = questionUpDownRepository
                .findByQuestionSeqAndUserSeq( questionUpdownDto.getQuestionSeq(), user.getSeq() );
                //.orElseThrow(() -> new QuestionUpException());

        if(questionUpdown == null){ // 비어있다면 생성
             questionUpDownRepository.save(
                    questionUpdownDto.toEntity(
                            user,
                            question)
            );

            // 생성 완료 후 변경
            if(questionUpdownDto.getQuestionUpdownType() == UDType.UP){
                question.setUp(question.getUp() + 1);
            }else if(questionUpdownDto.getQuestionUpdownType() == UDType.DOWN){
                question.setDown(question.getDown() + 1);
            }

            // 변경 내역 저장
            //questionRepository.save(question);

        } else if(questionUpdown.getType() == questionUpdownDto.getQuestionUpdownType()) { // 타입이 같으면 삭제

            // 삭제
            questionUpDownRepository.delete(questionUpdown);

            // 삭제 완료 후 변경
            if(questionUpdownDto.getQuestionUpdownType() == UDType.UP){
                question.setUp(question.getUp() -1);
            }else if(questionUpdownDto.getQuestionUpdownType() == UDType.DOWN){
                question.setDown(question.getDown() -1);
            }

            // 변경 내역 저장
            //questionRepository.save(question);

        } else { // 타입이 다르면 수정
            questionUpdown.setType(questionUpdownDto.getQuestionUpdownType());

            // 저장
            questionUpDownRepository.save(questionUpdown);

            if(questionUpdownDto.getQuestionUpdownType() == UDType.UP){
                question.setUp(question.getUp() + 1);
                question.setDown(question.getDown() -1);
            }else if(questionUpdownDto.getQuestionUpdownType() == UDType.DOWN) {
                question.setUp(question.getUp() - 1);
                question.setDown(question.getDown() + 1);
            }

            //questionRepository.save(question);
        }

        // 명성 로그 등록
        reputationService.saveLog(question.getUser(),"질문 추천", 3, question.getSeq());

        return new QuestionDto.Response(question);
    }

    public CommentDto.Response createComment(CommentDto.RequestCreate commentDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        Question question = questionRepository.findBySeq(commentDto.getBoardSeq())
                .orElseThrow(() -> new SpecificQuestionNotFound());

        reputationService.saveLog(user,"댓글 작성", 5, question.getSeq());
        return new CommentDto.Response(commentRepository.save(commentDto.toEntity(user)));
    }

    public CommentDto.Response updateComment(CommentDto.RequestUpdate commentDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = commentRepository.findBySeqAndUser_Seq(commentDto.getCommentSeq(), user.getSeq())
                .orElseThrow(() -> new CommentNotFoundException());
        comment.setContent(commentDto.getContent());
        return new CommentDto.Response(comment);
    }

    public void deleteComment(Long commentSeq) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = commentRepository.findBySeqAndUser_Seq(commentSeq, user.getSeq())
                .orElseThrow(() -> new CommentNotFoundException());
        commentRepository.delete(comment);
    }

    public List<CommentDto.Response> getQuestionComment(Long question_seq) {
        List<Comment> commentList = commentRepository.findAllByBoardSeqAndTypeOrderByCreatedTime(question_seq, QAType.QUESTION)
                .orElseThrow(() -> new QuestionCommentNotFoundException());
        List<CommentDto.Response> commentResponseList = new ArrayList<>();

        for(int i=0; i<commentList.size(); i++){
            commentResponseList.add(new CommentDto.Response(commentList.get(i)));
        }

        return commentResponseList;
    }
}
