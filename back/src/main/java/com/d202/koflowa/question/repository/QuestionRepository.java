package com.d202.koflowa.question.repository;

import com.d202.koflowa.question.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;


public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findByUser_Seq(Long userSeq, @PageableDefault(size = 15) Pageable pageable);
}
