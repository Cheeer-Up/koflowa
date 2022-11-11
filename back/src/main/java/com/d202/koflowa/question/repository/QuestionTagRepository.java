package com.d202.koflowa.question.repository;

import com.d202.koflowa.question.domain.QuestionTag;
import com.d202.koflowa.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionTagRepository extends JpaRepository<QuestionTag, Long> {
    Optional<List<QuestionTag>> findByTag(Tag tag);
}
