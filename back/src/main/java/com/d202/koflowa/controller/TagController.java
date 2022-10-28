package com.d202.koflowa.controller;

import com.d202.koflowa.domain.Tag;
import com.d202.koflowa.dto.ResponseDto;
import com.d202.koflowa.dto.TagDto;
import com.d202.koflowa.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor // 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해준다.
public class TagController {

    private final TagService tagService;

    @GetMapping("/")
    public ResponseEntity<?> getTagList() {
        List<Tag> tagList = tagService.findAll();
        return ResponseEntity.ok(tagList);
    }

    @PostMapping("/regist")
    public ResponseEntity<?> saveTag(@RequestBody TagDto.Request request) {
        Optional<Tag> tag = tagService.findByName(request.getName());
        if (!tag.isPresent()) {
            Tag newTag = request.toEntity();
            return ResponseEntity.ok(tagService.saveTag(newTag));
        } else {
            return ResponseEntity.badRequest().body(new ResponseDto("이미 존재하는 태그입니다."));
        }
    }
}
