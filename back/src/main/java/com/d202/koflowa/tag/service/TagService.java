package com.d202.koflowa.tag.service;

import com.d202.koflowa.question.domain.QuestionTag;
import com.d202.koflowa.question.dto.QuestionDto;
import com.d202.koflowa.question.repository.QuestionTagRepository;
import com.d202.koflowa.tag.domain.Tag;
import com.d202.koflowa.common.domain.TagStatus;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.domain.UserTag;
import com.d202.koflowa.common.dto.ResponseDto;
import com.d202.koflowa.tag.dto.TagDto;
import com.d202.koflowa.exception.*;
import com.d202.koflowa.tag.repository.TagRepository;
import com.d202.koflowa.user.repository.UserRepository;
import com.d202.koflowa.user.repository.UserTagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final QuestionTagRepository questionTagRepository;

    @Transactional(readOnly = true)
    public Page<TagDto.Response> getTagList(Pageable pageable) {
        Page<Tag> tags = tagRepository.findAll(pageable);
        List<TagDto.Response> tagDtoList = new ArrayList<>();
        for(Tag tag:tags) {
            TagDto.Response tagResponse = new TagDto.Response(tag);
            tagDtoList.add(tagResponse);
        }
        return new PageImpl<TagDto.Response>(tagDtoList, pageable, tags.getTotalElements());
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
    public TagDto.DetailResponse getDetailTag(String tagName) {
        Optional<Tag> tag = tagRepository.findByName(tagName);
        if (tag.isEmpty()) {
            throw new TagNotFoundException("존재하지 않는 태그 입니다.");
        }

        // 관련 게시글 정보 추가
        Optional<List<QuestionTag>> qList = questionTagRepository.findByTag(tag.get());
        List<QuestionDto.Response> questions = new ArrayList<>();
        for (QuestionTag q : qList.get()) {
            questions.add(new QuestionDto.Response(q.getQuestion()));
        }

        return new TagDto.DetailResponse(tag.get(), questions.size(), questions);
    }


    public Tag putTag(String tagName, TagDto.Request request) {
        Tag req = request.toEntity();
        Optional<Tag> tag = tagRepository.findByName(tagName);
        if (tag.isEmpty()) {
            throw new TagNotFoundException("존재하지 않는 태그 입니다.");
        }

        // 태그 정보 수정
        tag.get().setName(req.getName());
        tag.get().setDescription(req.getDescription());

        // 수정된 태그 저장 및 리턴
        return tagRepository.save(tag.get());
    }


    public ResponseDto postUserTag(Long tagSeq, Long userSeq, TagStatus tagStatus) {
        Optional<UserTag> userTag = userTagRepository.findByUserSeqAndTagSeqAndTagStatus(userSeq, tagSeq, tagStatus);
        if (userTag.isPresent()) {
            throw new UserTagExistException("이미 등록된 태그입니다.");
        }

        Optional<Tag> tag = tagRepository.findBySeq(tagSeq);
        if (tag.isEmpty()) {
            throw new TagNotFoundException("존재하지 않는 태그 id 입니다.");
        }

        Optional<User> user = userRepository.findBySeq(userSeq);
        if (user.isEmpty()) {
            throw new UserNotFoundException("존재하지 않는 유저 seq 입니다.");
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
        Optional<UserTag> userTag = userTagRepository.findByUserSeqAndTagSeqAndTagStatus(userSeq, tagSeq, tagStatus);
        if (userTag.isEmpty()) {
            throw new UserTagNotFoundException("태그가 존재하지 않습니다.");
        }
        userTagRepository.delete(userTag.get());
        return new ResponseDto("태그가 삭제되었습니다.");
    }
}
