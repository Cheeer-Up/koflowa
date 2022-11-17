package com.d202.koflowa.answer.repository;

import com.d202.koflowa.answer.domain.Answer;
import com.d202.koflowa.question.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Page<Answer> findByUserSeq(long userSeq, Pageable pageable);
    List<Answer> findAllByQuestion_Seq(Long questionSeq);
}
