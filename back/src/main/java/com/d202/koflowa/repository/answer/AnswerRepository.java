package com.d202.koflowa.repository.answer;

import com.d202.koflowa.domain.question.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
