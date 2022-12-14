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
import org.springframework.security.core.context.SecurityContextHolder;
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
            throw new TagExistException("?????? ???????????? ???????????????.");
        }

        Tag newTag = request.toEntity();
        return tagRepository.save(newTag);
    }

    @Transactional(readOnly = true)
    public TagDto.DetailResponse getDetailTag(String tagName) {
        Optional<Tag> tag = tagRepository.findByName(tagName);
        if (tag.isEmpty()) {
            throw new TagNotFoundException("???????????? ?????? ?????? ?????????.");
        }

        // ?????? ????????? ?????? ??????
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
            throw new TagNotFoundException("???????????? ?????? ?????? ?????????.");
        }

        // ?????? ?????? ??????
        tag.get().setName(req.getName());
        tag.get().setDescription(req.getDescription());

        // ????????? ?????? ?????? ??? ??????
        return tagRepository.save(tag.get());
    }


    public ResponseDto postUserTag(String tagName, TagStatus tagStatus) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserTag> userTag = userTagRepository.findByUserSeqAndTagNameAndTagStatus(user.getSeq(), tagName, tagStatus);
        if (userTag.isPresent()) {
            throw new UserTagExistException("?????? ????????? ???????????????.");
        }

        Optional<Tag> tag = tagRepository.findByName(tagName);
        if (tag.isEmpty()) {
            throw new TagNotFoundException("???????????? ?????? ?????? ?????????.");
        }

        // ?????? ?????? ??????
        userTagRepository.save(UserTag.builder()
                        .tag(tag.get())
                        .user(user)
                        .tagStatus(tagStatus)
                        .build());

        // ?????? ??????
        return new ResponseDto(String.format("[%s] ????????? %s(%d) ????????? ????????? ?????????????????????.",
                tag.get().getName(),
                user.getName(),
                user.getSeq()));
    }


    public ResponseDto deleteUserTag(String tagName, TagStatus tagStatus) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserTag> userTag = userTagRepository.findByUserSeqAndTagNameAndTagStatus(user.getSeq(), tagName, tagStatus);
        if (userTag.isEmpty()) {
            throw new UserTagNotFoundException("????????? ???????????? ????????????.");
        }
        userTagRepository.delete(userTag.get());
        return new ResponseDto("????????? ?????????????????????.");
    }

    public List<String> getTagStrList() {
        List<Tag> tags = tagRepository.findAll();
        List<String> stringList = new ArrayList<>();
        for (Tag tag: tags) {
            stringList.add(tag.getName());
        }
        return stringList;
    }

    public List<String> getWatchedTag() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UserTag> userTagList = userTagRepository.findByUserAndTagStatus(user, TagStatus.WATCHED);
        List<String> watchedList = new ArrayList<>();
        for(UserTag userTag: userTagList) {
            watchedList.add(userTag.getTag().getName());
        }
        return watchedList;
    }

    public Object getIgnoreTag() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UserTag> userTagList = userTagRepository.findByUserAndTagStatus(user, TagStatus.IGNORED);
        List<String> IgnoreList = new ArrayList<>();
        for(UserTag userTag: userTagList) {
            IgnoreList.add(userTag.getTag().getName());
        }
        return IgnoreList;
    }
}
