package com.d202.koflowa.repository.question;

import com.d202.koflowa.domain.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
