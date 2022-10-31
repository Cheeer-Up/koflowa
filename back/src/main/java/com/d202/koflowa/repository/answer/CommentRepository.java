package com.d202.koflowa.repository.answer;

import com.d202.koflowa.domain.question.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
