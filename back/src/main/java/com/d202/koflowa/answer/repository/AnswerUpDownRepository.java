package com.d202.koflowa.answer.repository;

import com.d202.koflowa.answer.domain.AnswerUpdown;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerUpDownRepository extends JpaRepository<AnswerUpdown, Long> {
}
