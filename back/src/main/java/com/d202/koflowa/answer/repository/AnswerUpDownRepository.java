package com.d202.koflowa.answer.repository;

import com.d202.koflowa.answer.domain.AnswerUpdown;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerUpDownRepository extends JpaRepository<AnswerUpdown, Long> {

    Optional<AnswerUpdown> findByUser_SeqAndAnswer_Seq(Long userSeq, Long answerSeq);
}
