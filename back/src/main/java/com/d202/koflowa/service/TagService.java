package com.d202.koflowa.service;

import com.d202.koflowa.domain.Tag;
import com.d202.koflowa.dto.TagDto;
import com.d202.koflowa.repository.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {

    private final TagRepository tagRepository;

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Optional<Tag> findByName(String name) {
        return tagRepository.findByName(name);
    }

    @Transactional
    public String saveTag(Tag tag) {
        return tagRepository.save(tag).getName();
    }
}
