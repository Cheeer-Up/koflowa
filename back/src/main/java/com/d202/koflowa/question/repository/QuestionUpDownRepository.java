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
    QuestionUpdown findByQuestionSeqAndUserSeq(@Param("qs") Long questionSeq, @Param("us") Long userSeq);

    @Query("SELECT COUNT(qd) FROM  QuestionUpdown qd "
            + "WHERE qd.user.seq = :us AND qd.question.seq = :qs " +
            "AND qd.type = com.d202.koflowa.common.domain.UDType.UP")
    Long getGood(@Param("qs") Long questionSeq, @Param("us") Long userSeq);

    @Query("SELECT COUNT(qd) FROM  QuestionUpdown qd "
            + "WHERE qd.user.seq = :us AND qd.question.seq = :qs " +
            "AND qd.type = com.d202.koflowa.common.domain.UDType.DOWN")
    Long getBad(@Param("qs") Long questionSeq, @Param("us") Long userSeq);
}
