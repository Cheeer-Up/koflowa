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
import com.d202.koflowa.exception.AnswerUpdownExistException;
import com.d202.koflowa.exception.QuestionNotFoundException;
import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.question.dto.QuestionDto;
import com.d202.koflowa.question.exception.QuestionCommentNotFoundException;
import com.d202.koflowa.question.exception.SpecificQuestionNotFound;
import com.d202.koflowa.question.repository.QuestionRepository;
import com.d202.koflowa.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public AnswerDto.Response createAnswer(Long questionSeq, AnswerDto.Request request) {
        Optional<Question> question = questionRepository.findById(questionSeq);
        if (question.isEmpty()) {
            throw new QuestionNotFoundException("존재하지 않는 게시글입니다.");
        }

        // TODO: 유저 정보 저장 필요
        // TODO: 유저 명성 +1, 명성 log 등록
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Answer answer = request.toEntity(question.get());
//        answer.setUserSeq( user.getSeq());

//        answerRepository.save(answer);
//
//        //return
//        return new AnswerDto.Response(answer);
        return new AnswerDto.Response(answerRepository.save(answer));
    }

    public AnswerDto.Response updateAnswer(Long answerSeq, AnswerDto.Request request) {
        Optional<Answer> answer = answerRepository.findById(answerSeq);
        if (answer.isEmpty()) {
            // throw new
            throw new AnswerNotFoundException("존재하지 않는 답변입니다.");
        }

        // TODO: content, accept, modifiedTime 수정
        answer.get().updateAnswerContent(request);
        return new AnswerDto.Response(answer.get());
//        return new AnswerDto.Response(answerRepository.save(answer.get()));
    }

    public void deleteAnswer(Long answerSeq) {
        Optional<Answer> answer = answerRepository.findById(answerSeq);
        if (answer.isEmpty()) {
            // throw new
            throw new AnswerNotFoundException("존재하지 않는 답변입니다.");
        }
        // 채택 답변일 시 질문의 채택 답변 seq를 null로 만들어줘야 하는가
        // 아니면 방치
        answer.get().getQuestion().setAcceptAnswerSeq(null);
        answerRepository.delete(answer.get());
    }

//    public void upDownAnswer(Long answerSeq, AnswerUpdownDto.Request request){
//        Optional<Answer> answer = answerRepository.findById(answerSeq);
//        if (answer.isEmpty()) {
//            // throw new
//            throw new AnswerNotFoundException("존재하지 않는 답변입니다.");
//        }
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long seq = user.getSeq();
//        Optional<AnswerUpdown> answerUpdown = answerUpDownRepository.findByUser_SeqAndAnswer_Seq(user.getSeq(), answerSeq);
//        if (answerUpdown.isEmpty()){
//            // 비어있다면 생성
//            AnswerUpdown response = answerUpDownRepository.save(request.toEntity(user, answer.get()));
//        } else if (answerUpdown.get().getType() == request.getType()) {
//            // 타입이 같으면 삭제
//            answerUpDownRepository.delete(answerUpdown.get());
//            if(request.getType()== UDType.UP){
//                answer.get().updateAnswerUp(-1);
//            } else if (request.getType()== UDType.DOWN) {
//                answer.get().updateAnswerDown(-1);
//            }
//        }else {
//            // 타입이 다르면 수정
//            answerUpdown.get().updateUPType(request.getType());
//            if(request.getType()== UDType.UP){
//                answer.get().updateAnswerUp(1);
//                answer.get().updateAnswerDown(-1);
//            } else if (request.getType()== UDType.DOWN) {
//                answer.get().updateAnswerUp(-1);
//                answer.get().updateAnswerDown(1);
//            }
//        }
//
//        // answerUpDown에 있는지 보고 있으면 패스 없으면 save
//        // 그후 answer에 up을 1 올려주고
//
//    }

    public void postBestAnswer(Long answerSeq){
        Optional<Answer> answer = answerRepository.findById(answerSeq);
        if (answer.isEmpty()) {
            // throw new
            throw new AnswerNotFoundException("존재하지 않는 답변입니다.");
        }
        Optional<Question> question = questionRepository.findById(answer.get().getQuestion().getSeq());
        if (question.isEmpty()) {
            throw new QuestionNotFoundException("존재하지 않는 게시글입니다.");
        }
        // 답변에 채택 등록 후 질문에 원래 답변 있는지 조회 후 없으면 등록,
        // 있으면 원래 답변 채택 해제 후 질문 accept 수정
        if(question.get().getAcceptAnswerSeq()!=null){
            Optional<Answer> prevAcceptedAnswer = answerRepository.findById(question.get().getAcceptAnswerSeq());
            if (prevAcceptedAnswer.isEmpty()) {
                // throw new
                throw new AnswerNotFoundException("존재하지 않는 답변입니다.");
            }
            prevAcceptedAnswer.get().updateAnswerAccept(false);
        }
        // entity에 setter? setter로 수정하면 db에 update되나? 따로 save?
        question.get().setAcceptAnswerSeq(answerSeq);
        answer.get().updateAnswerAccept(true);
    }

    public CommentDto.Response createComment(CommentDto.RequestCreate commentDto) {
        commentDto.setType(QAType.ANSWER);
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
                .orElseThrow(() -> new AnswerNotFoundException("존재하지 않는 답변입니다."));
        return  new AnswerDto.Response(answer);
    }

    public Page<Answer> searchAnswerByQuestionSeq(Long questionSeq, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return answerRepository.findAllByQuestion_Seq(questionSeq, pageRequest);
    }
}
