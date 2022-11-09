package com.d202.koflowa.question.repository;

import com.d202.koflowa.question.domain.QuestionUpdown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuestionUpDownRepository extends JpaRepository<QuestionUpdown, Long> {
    @Query("SELECT question_updown FROM QuestionUpdown question_updown "
            + "WHERE question_updown.user.seq = :user_seq AND question_updown.question.seq = :question_seq")
    Optional<QuestionUpdown> findBySeqAndUserSeq(Long question_seq, Long user_seq);

    @Query("SELECT COUNT(question_updown) FROM  QuestionUpdown question_updown "
            + "WHERE question_updown.user.seq = :user_seq AND question_updown.question.seq = :question_seq " +
            "AND question_updown.type = :UP")
    Long getGood(Long question_seq, Long user_seq);

    @Query("SELECT COUNT(question_updown) FROM  QuestionUpdown question_updown "
            + "WHERE question_updown.user.seq = :user_seq AND question_updown.question.seq = :question_seq " +
            "AND question_updown.type = :DOWN")
    Long getBad(Long question_seq, Long user_seq);
}
