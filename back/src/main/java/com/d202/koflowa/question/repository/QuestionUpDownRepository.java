package com.d202.koflowa.question.repository;

import com.d202.koflowa.question.domain.QuestionUpdown;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionUpDownRepository extends JpaRepository<QuestionUpdown, Long> {
}
