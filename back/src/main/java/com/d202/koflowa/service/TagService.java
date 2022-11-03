package com.d202.koflowa.service;

import com.d202.koflowa.domain.Tag;
import com.d202.koflowa.domain.common.TagStatus;
import com.d202.koflowa.domain.user.User;
import com.d202.koflowa.domain.user.UserTag;
import com.d202.koflowa.dto.ResponseDto;
import com.d202.koflowa.dto.TagDto;
import com.d202.koflowa.exception.*;
import com.d202.koflowa.repository.tag.TagRepository;
import com.d202.koflowa.repository.user.UserRepository;
import com.d202.koflowa.repository.user.UserTagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.tokens.TagToken;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TagService {

    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final UserTagRepository userTagRepository;

    @Transactional(readOnly = true)
    public List<TagDto.Response> getTagList() {
        List<Tag> tags = tagRepository.findAll();
        List<TagDto.Response> tagDtoList = new ArrayList<>();
        for(Tag tag:tags) {
            TagDto.Response tagResponse = new TagDto.Response(tag);
            tagDtoList.add(tagResponse);
        }
        return tagDtoList;
    }


    public Tag saveTag(TagDto.Request request) {
        Optional<Tag> tag = tagRepository.findByName(request.getName());
        if (tag.isPresent()) {
            throw new TagExistException("이미 존재하는 태그입니다.");
        }

        Tag newTag = request.toEntity();
        return tagRepository.save(newTag);
    }

    @Transactional(readOnly = true)
    public TagDto.Response getDetailTag(Long tagSeq) {
        Optional<Tag> tag = tagRepository.findBySeq(tagSeq);
        if (tag.isEmpty()) {
            throw new TagNotFoundException("존재하지 않는 태그 id 입니다.");
        }

        // TODO: 관련 게시글 정보 추가 필요
        return new TagDto.Response(tag.get());
    }


    public Tag putTag(Long tagSeq, TagDto.Request request) {
        Tag req = request.toEntity();
        Optional<Tag> tag = tagRepository.findBySeq(tagSeq);
        if (tag.isEmpty()) {
            throw new TagNotFoundException("존재하지 않는 태그 id 입니다.");
        }

        // 태그 정보 수정
        tag.get().setName(req.getName());
        tag.get().setDiscription(req.getDiscription());

        // 수정된 태그 저장 및 리턴
        return tagRepository.save(tag.get());
    }


    public ResponseDto postUserTag(Long tagSeq, Long userSeq, TagStatus tagStatus) {
        Optional<Tag> tag = tagRepository.findBySeq(tagSeq);
        if (tag.isEmpty()) {
            throw new TagNotFoundException("존재하지 않는 태그 id 입니다.");
        }

        Optional<User> user = userRepository.findBySeq(userSeq);
        if (user.isEmpty()) {
            throw new UserNotFoundException("존재하지 않는 유저 seq 입니다.");
        }

        Optional<UserTag> userTag = userTagRepository.findByUserAndTagAndTagStatus(user.get(), tag.get(), tagStatus);
        if (userTag.isPresent()) {
            throw new UserTagExistException("이미 등록된 태그입니다.");
        }

        // 주시 태그 저장
        userTagRepository.save(UserTag.builder()
                        .tag(tag.get())
                        .user(user.get())
                        .tagStatus(tagStatus)
                        .build());

        // 결과 리턴
        return new ResponseDto(String.format("[%s] 태그가 %s(%d) 유저의 태그로 등록되었습니다.",
                tag.get().getName(),
                user.get().getName(),
                user.get().getSeq()));
    }


    public ResponseDto deleteUserTag(Long tagSeq, Long userSeq, TagStatus tagStatus) {
        Optional<Tag> tag = tagRepository.findBySeq(tagSeq);
        if (tag.isEmpty()) {
            throw new TagNotFoundException("존재하지 않는 태그 id 입니다.");
        }

        Optional<User> user = userRepository.findBySeq(userSeq);
        if (user.isEmpty()) {
            throw new UserNotFoundException("존재하지 않는 유저 seq 입니다.");
        }

        Optional<UserTag> userTag = userTagRepository.findByUserAndTagAndTagStatus(user.get(), tag.get(), tagStatus);
        if (userTag.isEmpty()) {
            throw new UserTagNotFoundException("태그가 존재하지 않습니다.");
        }
        userTagRepository.delete(userTag.get());
        return new ResponseDto("태그가 삭제되었습니다.");
    }
}
