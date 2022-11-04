package com.d202.koflowa.repository.answer;

import com.d202.koflowa.domain.question.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Page<Answer> findByUserSeq(long userSeq, Pageable pageable);
}
