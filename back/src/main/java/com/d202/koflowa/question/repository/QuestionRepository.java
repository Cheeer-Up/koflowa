package com.d202.koflowa.question.repository;

import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;

import java.util.List;
import java.util.Optional;


public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findByUserSeq(Long userSeq, @PageableDefault(size = 15) Pageable pageable);

    @Query(value = "SELECT question FROM Question question " +
            "WHERE (question.content LIKE %:keyword%) OR " +
            "(question.title LIKE %:keyword%) " +"ORDER BY question.createdTime DESC")
    Page<Question> findAllByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "SELECT question FROM Question question " + "ORDER BY question.createdTime DESC")
    Page<Question> findAll(Pageable pageable);

    Page<Question> findAllByUserSeqOrderByCreatedTimeDesc(Long userSeq, Pageable pageable);

    List<Question> findAllByUserSeq(Long userSeq);

    @Query(value = "SELECT question, question.user FROM Question question WHERE question.seq = :questionSeq")
    Optional<Question> findBySeq(@Param("questionSeq") Long questionSeq);

    @Query(value = "SELECT question, question.user FROM Question question WHERE question.seq = :questionSeq AND question.user.seq = :userSeq")
    Optional<Question> findBySeqAndUser(@Param("questionSeq") Long questionSeq, @Param("userSeq") Long userSeq);
}
