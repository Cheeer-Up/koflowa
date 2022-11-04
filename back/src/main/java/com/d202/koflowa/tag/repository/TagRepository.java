package com.d202.koflowa.tag.repository;

import com.d202.koflowa.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    public List<Tag> findAll();

    public Optional<Tag> findByName(String name);
    public Optional<Tag> findBySeq(Long seq);
}
