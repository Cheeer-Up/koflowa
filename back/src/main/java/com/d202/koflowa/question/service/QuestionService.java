package com.d202.koflowa.question.service;

import com.d202.koflowa.answer.domain.Comment;
import com.d202.koflowa.answer.dto.CommentDto;
import com.d202.koflowa.answer.repository.CommentRepository;
import com.d202.koflowa.common.domain.QAType;
import com.d202.koflowa.common.domain.TagStatus;
import com.d202.koflowa.common.domain.UDType;
import com.d202.koflowa.common.exception.CommentNotFoundException;
import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.question.domain.QuestionTag;
import com.d202.koflowa.question.domain.QuestionUpdown;
import com.d202.koflowa.question.dto.QuestionDto;
import com.d202.koflowa.question.dto.QuestionUpdownDto;
import com.d202.koflowa.question.exception.QuestionCommentNotFoundException;
import com.d202.koflowa.question.exception.SpecificQuestionNotFound;
import com.d202.koflowa.question.repository.QuestionRepository;
import com.d202.koflowa.question.repository.QuestionTagRepository;
import com.d202.koflowa.question.repository.QuestionUpDownRepository;
import com.d202.koflowa.tag.repository.TagRepository;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.repository.UserTagRepository;
import com.d202.koflowa.user.service.ReputationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
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
    private final TagRepository tagRepository;
    private final QuestionTagRepository questionTagRepository;
    private final CommentRepository commentRepository;
    private final ReputationService reputationService;

    public Page<QuestionDto.Response> getAllQuestion(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Page<Question> questions;
        if (authentication.getPrincipal() != "anonymousUser")
        {
            // ????????? ??? ??????
            User user = (User) authentication.getPrincipal();
            // WATCHED ??? ?????????
            questions = questionTagRepository.findQuestionByUser(user, pageable);
            if (questions.isEmpty()) {
                questions = questionRepository.findAll(pageable);
            }
        } else {
            questions = questionRepository.findAll(pageable);
        }

        List<QuestionDto.Response> pageDtoList = new ArrayList<>();
        for(Question question : questions) {
            List<String> tagList = questionTagRepository.findTagNameByQuestionSeq(question.getSeq());
            QuestionDto.Response questionResponse = new QuestionDto.Response(question, tagList);
            pageDtoList.add(questionResponse);
        }

        return new PageImpl<QuestionDto.Response>(pageDtoList, pageable, questions.getTotalElements());
    }

    public Page<QuestionDto.Response> searchQuestionByKeyword(String keyword, Pageable pageable) {
        Page<Question> questions = questionRepository.findAllByKeyword(keyword, pageable);

        List<QuestionDto.Response> pageDtoList = new ArrayList<>();

        for(Question question : questions) {
            List<String> tagList = questionTagRepository.findTagNameByQuestionSeq(question.getSeq());
            QuestionDto.Response questionResponse = new QuestionDto.Response(question, tagList);
            pageDtoList.add(questionResponse);
        }

        return new PageImpl<QuestionDto.Response>(pageDtoList, pageable, questions.getTotalElements());
    }

    public Page<QuestionDto.Response> searchQuestionByTagName(String tagName, Pageable pageable) {
        Page<Question> questions = questionTagRepository.findQuestionByTagName(tagName, pageable);
        List<QuestionDto.Response> pageDtoList = new ArrayList<>();

        for(Question question : questions) {
            List<String> tagList = questionTagRepository.findTagNameByQuestionSeq(question.getSeq());
            QuestionDto.Response questionResponse = new QuestionDto.Response(question, tagList);
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
        System.out.println("???????????? "+user);
        Question question = questionRepository.save(questionDto.toEntity(user));

        //QuestionTag ??????
        List<String> tagList = questionDto.getTagList();
        for (String tagName : tagList) {
            questionTagRepository.save(QuestionTag.builder()
                    .tag(tagRepository.findByName(tagName).get())
                    .question(question)
                    .build());
        }

        reputationService.saveLog(user,"?????? ??????", 15, question.getSeq());
        return new QuestionDto.Response(question);
    }

    public QuestionDto.Response getQuestionDetail(Long question_seq) {
        Question question = questionRepository.findBySeq(question_seq)
                .orElseThrow(() -> new SpecificQuestionNotFound());
        List<String> tagList = questionTagRepository.findTagNameByQuestionSeq(question.getSeq());
        return new QuestionDto.Response(question, tagList);
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

    //?????? ???????????? ?????? ??????????????? ??????
    public QuestionDto.Response setQuestionUpDown(QuestionUpdownDto.Request questionUpdownDto) {
        System.out.println(questionUpdownDto);

        /* ?????? ?????? ????????? ?????? ?????? ?????? */
        Question question =  questionRepository.findBySeq(questionUpdownDto.getQuestionSeq()).get();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        /* QuestionUpdown ?????? */
        QuestionUpdown questionUpdown = questionUpDownRepository
                .findByQuestionSeqAndUserSeq( questionUpdownDto.getQuestionSeq(), user.getSeq() );
                //.orElseThrow(() -> new QuestionUpException());

        if(questionUpdown == null){ // ??????????????? ??????
             questionUpDownRepository.save(
                    questionUpdownDto.toEntity(
                            user,
                            question)
            );

            // ?????? ?????? ??? ??????
            if(questionUpdownDto.getQuestionUpdownType() == UDType.UP){
                question.setUp(question.getUp() + 1);
            }else if(questionUpdownDto.getQuestionUpdownType() == UDType.DOWN){
                question.setDown(question.getDown() + 1);
            }

            // ?????? ?????? ??????
            //questionRepository.save(question);

        } else if(questionUpdown.getType() == questionUpdownDto.getQuestionUpdownType()) { // ????????? ????????? ??????

            // ??????
            questionUpDownRepository.delete(questionUpdown);

            // ?????? ?????? ??? ??????
            if(questionUpdownDto.getQuestionUpdownType() == UDType.UP){
                question.setUp(question.getUp() -1);
            }else if(questionUpdownDto.getQuestionUpdownType() == UDType.DOWN){
                question.setDown(question.getDown() -1);
            }

            // ?????? ?????? ??????
            //questionRepository.save(question);

        } else { // ????????? ????????? ??????
            questionUpdown.setType(questionUpdownDto.getQuestionUpdownType());

            // ??????
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

        // ?????? ?????? ??????
        reputationService.saveLog(question.getUser(),"?????? ??????", 3, question.getSeq());

        return new QuestionDto.Response(question);
    }

    public CommentDto.Response createComment(CommentDto.RequestCreate commentDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user);
        Question question = questionRepository.findBySeq(commentDto.getBoardSeq())
                .orElseThrow(() -> new SpecificQuestionNotFound());

        reputationService.saveLog(user,"?????? ??????", 5, question.getSeq());
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

    public QuestionUpdownDto.Response searchupDownQuestion(Long questionSeq){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        QuestionUpdown questionUpdownOptional = questionUpDownRepository.findByQuestionSeqAndUserSeq(questionSeq, user.getSeq());
        if(questionUpdownOptional!=null) {
            return new QuestionUpdownDto.Response(questionUpdownOptional);
        }else{
            return null;
        }
    }
}
