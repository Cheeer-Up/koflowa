package com.d202.koflowa.repository.question;

import com.d202.koflowa.domain.question.QuestionUpdown;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionUpDownRepository extends JpaRepository<QuestionUpdown, Long> {
}
