package com.d202.koflowa.answer.service;

import com.d202.koflowa.answer.domain.Answer;
import com.d202.koflowa.answer.domain.AnswerUpdown;
import com.d202.koflowa.answer.domain.Comment;
import com.d202.koflowa.answer.dto.AnswerDto;
import com.d202.koflowa.answer.dto.AnswerUpdownDto;
import com.d202.koflowa.answer.dto.CommentDto;
import com.d202.koflowa.answer.repository.AnswerRepository;
import com.d202.koflowa.answer.repository.AnswerUpDownRepository;
import com.d202.koflowa.answer.repository.CommentRepository;
import com.d202.koflowa.common.domain.QAType;
import com.d202.koflowa.common.domain.UDType;
import com.d202.koflowa.common.exception.CommentNotFoundException;
import com.d202.koflowa.exception.AnswerNotFoundException;
import com.d202.koflowa.exception.QuestionNotFoundException;
import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.question.exception.QuestionCommentNotFoundException;
import com.d202.koflowa.question.repository.QuestionRepository;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.repository.UserRepository;
import com.d202.koflowa.user.service.ReputationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AnswerService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final AnswerUpDownRepository answerUpDownRepository;
    private final CommentRepository commentRepository;
    private final ReputationService reputationService;
    private final UserRepository userRepository;

    public AnswerDto.Response createAnswer(Long questionSeq, AnswerDto.Request request) {
        Optional<Question> question = questionRepository.findById(questionSeq);
        if (question.isEmpty()) {
            throw new QuestionNotFoundException("???????????? ?????? ??????????????????.");
        }

        // TODO: ?????? ?????? ?????? ??????
        // TODO: ?????? ?????? +1, ?????? log ??????
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Answer answer = request.toEntity(question.get());
        answer.setUser(user);
        reputationService.saveLog(user,"?????? ??????", 10, questionSeq);

        // question??? answerCnt +1
        question.get().setAnswerCount(question.get().getAnswerCount()+1);
        return new AnswerDto.Response(answerRepository.save(answer));
    }

    public AnswerDto.Response updateAnswer(Long answerSeq, AnswerDto.Request request) {
        Optional<Answer> answer = answerRepository.findById(answerSeq);
        if (answer.isEmpty()) {
            // throw new
            throw new AnswerNotFoundException("???????????? ?????? ???????????????.");
        }

        // TODO: content, accept, modifiedTime ??????
        answer.get().updateAnswerContent(request);
        return new AnswerDto.Response(answer.get());
//        return new AnswerDto.Response(answerRepository.save(answer.get()));
    }

    public void deleteAnswer(Long answerSeq) {
        Optional<Answer> answer = answerRepository.findById(answerSeq);
        if (answer.isEmpty()) {
            // throw new
            throw new AnswerNotFoundException("???????????? ?????? ???????????????.");
        }
        Optional<Question> question = questionRepository.findById(answer.get().getQuestion().getSeq());
        if (question.isEmpty()) {
            throw new QuestionNotFoundException("???????????? ?????? ??????????????????.");
        }

        // ?????? ????????? ??? ????????? ?????? ?????? seq??? null??? ??????????????? ?????????
        // ????????? ??????
        question.get().setAcceptAnswerSeq(null);
        answerRepository.delete(answer.get());
        question.get().setAnswerCount(question.get().getAnswerCount()-1);
    }

    public void upDownAnswer(Long answerSeq, AnswerUpdownDto.Request request){
        Optional<Answer> answer = answerRepository.findById(answerSeq);
        if (answer.isEmpty()) {
            // throw new
            throw new AnswerNotFoundException("???????????? ?????? ???????????????.");
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<AnswerUpdown> answerUpdown = answerUpDownRepository.findByUser_SeqAndAnswer_Seq(user.getSeq(), answerSeq);
        if (answerUpdown.isEmpty()){
            // ??????????????? ??????
            AnswerUpdown response = answerUpDownRepository.save(request.toEntity(user, answer.get(), request.getType()));
            if(request.getType()== UDType.UP){
                answer.get().updateAnswerUp(1);
            } else if (request.getType()== UDType.DOWN) {
                answer.get().updateAnswerDown(1);
            }
        } else if (answerUpdown.get().getType() == request.getType()) {
            // ????????? ????????? ??????
            answerUpDownRepository.delete(answerUpdown.get());
            if(request.getType()== UDType.UP){
                answer.get().updateAnswerUp(-1);
            } else if (request.getType()== UDType.DOWN) {
                answer.get().updateAnswerDown(-1);
            }
        }else {
            // ????????? ????????? ??????
            answerUpdown.get().updateUPType(request.getType());
            if(request.getType()== UDType.UP){
                answer.get().updateAnswerUp(1);
                answer.get().updateAnswerDown(-1);
            } else if (request.getType()== UDType.DOWN) {
                answer.get().updateAnswerUp(-1);
                answer.get().updateAnswerDown(1);
            }
        }

        // ?????? ??? ?????? ??????
        reputationService.saveLog(answer.get().getUser(),"?????? ??????", 3, answer.get().getQuestion().getSeq());

    }

    public void postBestAnswer(Long answerSeq){
        Optional<Answer> answer = answerRepository.findById(answerSeq);
        if (answer.isEmpty()) {
            // throw new
            throw new AnswerNotFoundException("???????????? ?????? ???????????????.");
        }
        Optional<Question> question = questionRepository.findById(answer.get().getQuestion().getSeq());
        if (question.isEmpty()) {
            throw new QuestionNotFoundException("???????????? ?????? ??????????????????.");
        }
        // ????????? ?????? ?????? ??? ????????? ?????? ?????? ????????? ?????? ??? ????????? ??????,
        // ????????? ?????? ?????? ?????? ?????? ??? ?????? accept ??????
        if(question.get().getAcceptAnswerSeq()!=null){
            Optional<Answer> prevAcceptedAnswer = answerRepository.findById(question.get().getAcceptAnswerSeq());
            if (prevAcceptedAnswer.isEmpty()) {
                // throw new
                throw new AnswerNotFoundException("???????????? ?????? ???????????????.");
            }
            prevAcceptedAnswer.get().updateAnswerAccept(false);
        }
        // entity??? setter? setter??? ???????????? db??? update??????? ?????? save?
        question.get().setAcceptAnswerSeq(answerSeq);
        answer.get().updateAnswerAccept(true);

        // ?????? ??? ?????? ??????
        reputationService.saveLog(answer.get().getUser(),"?????? ??????", 20, question.get().getSeq());
    }

    public CommentDto.Response createComment(CommentDto.RequestCreate commentDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Answer> answer = answerRepository.findById(commentDto.getBoardSeq());
        if (answer.isEmpty()) {
            // throw new
            throw new AnswerNotFoundException("???????????? ?????? ???????????????.");
        }
        reputationService.saveLog(user,"?????? ??????", 5, answer.get().getQuestion().getSeq());


        commentDto.setType(QAType.ANSWER);
        return new CommentDto.Response(commentRepository.save(commentDto.toEntity(user)));
    }

    public CommentDto.Response updateComment(CommentDto.RequestUpdate commentDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = commentRepository.findBySeqAndUser_Seq(commentDto.getCommentSeq(), user.getSeq())
                .orElseThrow(() -> new CommentNotFoundException());
        comment.setContent(commentDto.getContent());
        return new CommentDto.Response(comment);
    }

    public void deleteComment(CommentDto.RequestDelete commentDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = commentRepository.findBySeqAndUserSeq(commentDto.getCommentSeq(), user.getSeq())
                .orElseThrow(() -> new CommentNotFoundException());
        commentRepository.delete(comment);
    }

    public List<CommentDto.Response> getAnswerComment(Long answerSeq) {
        List<Comment> commentList = commentRepository.findAllByBoardSeqAndTypeOrderByCreatedTime(answerSeq, QAType.ANSWER)
                .orElseThrow(() -> new QuestionCommentNotFoundException());
        List<CommentDto.Response> commentResponseList = new ArrayList<>();

        for(int i=0; i<commentList.size(); i++){
            commentResponseList.add(new CommentDto.Response(commentList.get(i)));
        }

        return commentResponseList;
    }


    public AnswerDto.Response getAnswerDetail(Long answerSeq) {
        Answer answer = answerRepository.findById(answerSeq)
                .orElseThrow(() -> new AnswerNotFoundException("???????????? ?????? ???????????????."));
        return  new AnswerDto.Response(answer);
    }

    public List<AnswerDto.Response> searchAnswerByQuestionSeq(Long questionSeq) {
        List<Answer> answers = answerRepository.findAllByQuestion_Seq(questionSeq);
        List<AnswerDto.Response> answerDtoList = new ArrayList<>();
        for (Answer answer : answers){
            AnswerDto.Response answerResponse = new AnswerDto.Response(answer);
            answerDtoList.add(answerResponse);
        }
//        return new PageImpl<AnswerDto.Response>(pageDtoList, pageable, answers.getTotalElements());
        return answerDtoList;
    }

    public AnswerUpdownDto.Response searchAnswerUpdown(Long answerSeq){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<AnswerUpdown> answerUpdown = answerUpDownRepository.findByUser_SeqAndAnswer_Seq(user.getSeq(), answerSeq);
        if(answerUpdown.isPresent()) {
            return new AnswerUpdownDto.Response(answerUpdown.get());
        }else{
            return null;
        }
    }
}
