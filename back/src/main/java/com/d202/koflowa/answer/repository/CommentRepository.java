package com.d202.koflowa.answer.repository;

import com.d202.koflowa.answer.domain.Comment;
import com.d202.koflowa.common.domain.QAType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findBySeq(Long commentSeq);
    Optional<Comment> findBySeqAndUserSeq(Long seq, Long userSeq);
    Optional<List<Comment>> findAllByBoardSeqAndTypeOrderByCreatedTime(Long boardSeq, QAType qaType);
}
