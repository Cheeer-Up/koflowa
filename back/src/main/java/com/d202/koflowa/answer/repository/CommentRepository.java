package com.d202.koflowa.answer.repository;

import com.d202.koflowa.answer.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
