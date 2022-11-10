package com.d202.koflowa.question.repository;

import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.question.domain.QuestionUpdown;
import com.d202.koflowa.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface QuestionUpDownRepository extends JpaRepository<QuestionUpdown, Long> {
    @Query("SELECT qd FROM QuestionUpdown qd "
            + "WHERE qd.question.seq = :qs AND qd.user.seq = :us")
    Optional<QuestionUpdown> findByQuestionSeqAndUserSeq(@Param("qs") Long questionSeq, @Param("us") Long userSeq);

    @Query("SELECT COUNT(qd) FROM  QuestionUpdown qd "
            + "WHERE qd.user.seq = :user_seq AND qd.question.seq = :question_seq " +
            "AND qd.type = 'UP'")
    Long getGood(@Param("question_seq") Long questionSeq, @Param("user_seq") Long userSeq);

    @Query("SELECT COUNT(qd) FROM  QuestionUpdown qd "
            + "WHERE qd.user.seq = :user_seq AND qd.question.seq = :question_seq " +
            "AND qd.type = 'DOWN'")
    Long getBad(@Param("question_seq") Long questionSeq, @Param("user_seq") Long userSeq);
}
