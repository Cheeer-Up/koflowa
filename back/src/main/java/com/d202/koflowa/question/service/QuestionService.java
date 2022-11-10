package com.d202.koflowa.question.service;

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
import com.d202.koflowa.talk.exception.RoomNotFoundException;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionUpDownRepository questionUpDownRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    public Page<Question> getAllQuestion(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return questionRepository.findAll(pageRequest);
    }

    public Page<Question> searchQuestionByKeyword(String keyword, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return questionRepository.findAllByKeyword(keyword, pageRequest);
    }

    public Page<Question> searchQuestionByUserSeq(Long userSeq, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return questionRepository.findAllByUserSeq(userSeq, pageRequest);
    }

    public QuestionDto.Response createQuestion(QuestionDto.RequestCreate questionDto) {
        return new QuestionDto.Response(questionRepository.save(questionDto.toEntity()));
    }

    public QuestionDto.Response getQuestionDetail(Long question_seq) {
        Question question = questionRepository.findBySeq(question_seq)
                .orElseThrow(() -> new SpecificQuestionNotFound());
        return  new QuestionDto.Response(question);
    }
    public QuestionDto.Response updateQuestion(QuestionDto.Request questionDto) {
        Question question = questionRepository.findBySeq(questionDto.getQuestionSeq())
                .orElseThrow(() -> new SpecificQuestionNotFound());
        question.setTitle(questionDto.getQuestionTitle());
        question.setContent(questionDto.getQuestionContent());

        return  new QuestionDto.Response(questionRepository.save(question));
    }

    public void deleteQuestion(Long question_seq) {
        questionRepository.delete(questionRepository.findBySeq(question_seq)
                .orElseThrow(() -> new SpecificQuestionNotFound()));
    }

    //질문 테이블에 숫자 기록하는거 하기
    public QuestionUpdownDto.Response setQuestionUpDown(QuestionUpdownDto.Request questionUpdownDto) {
        System.out.println(questionUpdownDto);
        /* 중복 검색 방지를 위한 객체 생성 */

        //User user = userRepository.findBySeq(questionUpdownDto.getUserSeq()).get();

        /* QuestionUpdown 조회 */
        QuestionUpdown questionUpdown = questionUpDownRepository
                .findByQuestionSeqAndUserSeq( questionUpdownDto.getQuestionSeq(), questionUpdownDto.getUserSeq() )
                .orElseThrow(() -> new QuestionUpException());

        if(questionUpdown == null){ // 비어있다면 생성
            questionUpdown = questionUpDownRepository.save(
                    questionUpdownDto.toEntity(
                            userRepository.findBySeq(questionUpdownDto.getUserSeq()).get(),
                            questionRepository.findBySeq(questionUpdownDto.getQuestionSeq()).get())
            );
        } else if(questionUpdown.getType() == questionUpdownDto.getQuestionUpdownType()) { // 타입이 같으면 삭제

            // 삭제
            questionUpDownRepository.delete(questionUpdown);

            Question question =  questionRepository.findBySeq(questionUpdownDto.getQuestionSeq()).get();

            // 삭제 완료 후 변경
            if(questionUpdownDto.getQuestionUpdownType() == UDType.UP){
                question.setUp(question.getUp() -1);
            }else if(questionUpdownDto.getQuestionUpdownType() == UDType.DOWN){
                question.setUp(question.getDown() -1);
            }

            // 변경 내역 저장
            questionRepository.save(question);

        } else { // 타입이 다르면 수정
            questionUpdown.setType(questionUpdownDto.getQuestionUpdownType());

            // 저장
            questionUpDownRepository.save(questionUpdown);

            Question question =  questionRepository.findBySeq(questionUpdownDto.getQuestionSeq()).get();

            if(questionUpdownDto.getQuestionUpdownType() == UDType.UP){
                question.setUp(question.getUp() + 1);
                question.setDown(question.getDown() -1);
            }else if(questionUpdownDto.getQuestionUpdownType() == UDType.DOWN) {
                question.setUp(question.getUp() - 1);
                question.setDown(question.getDown() + 1);
            }

            questionRepository.save(question);
        }

        return new QuestionUpdownDto.Response(questionUpdown);
    }

    public CommentDto.Response createComment(CommentDto.RequestCreate commentDto) {
        return new CommentDto.Response(commentRepository.save(commentDto.toEntity()));
    }

    public CommentDto.Response updateComment(CommentDto.Request commentDto) {
        Comment comment = commentRepository.findBySeq(commentDto.getCommentSeq())
                .orElseThrow(() -> new CommentNotFoundException());
        comment.setContent(commentDto.getContent());
        return new CommentDto.Response(commentRepository.save(comment));
    }

    public void deleteComment(CommentDto.Request commentDto) {
        Comment comment = commentRepository.findBySeqAndUserSeq(commentDto.getCommentSeq(), commentDto.getUserSeq())
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
