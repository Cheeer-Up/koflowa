package com.d202.koflowa.controller;

import com.d202.koflowa.domain.Tag;
import com.d202.koflowa.dto.ResponseDto;
import com.d202.koflowa.dto.TagDto;
import com.d202.koflowa.repository.tag.TagRepository;
import com.d202.koflowa.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@io.swagger.v3.oas.annotations.tags.Tag(name = "Tag", description = "Tag api 입니다.")
@Slf4j
@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor // 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해준다.
public class TagController {

    private final TagService tagService;

    @Operation(summary = "전체 태그 조회", description = "전체 태그 조회 api 입니다.")
    @GetMapping("/")
    public ResponseEntity<?> getTagList() {
        List<Tag> tagList = tagService.findAll();
        return ResponseEntity.ok(tagList);
    }

    @Operation(summary = "태그 생성", description = "태그 생성 api 입니다.")
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

    @Operation(summary = "태그 상세 조회", description = "태그 상세 조회 api 입니다.")
    @GetMapping("/{tagSeq}")
    public ResponseEntity<?> getDetailTag(@PathVariable Long tagSeq) {
        Optional<Tag> tag = tagService.findBySeq(tagSeq);
        if (tag.isPresent()){
            return ResponseEntity.ok(new TagDto.Response(tag.get()));
        } else {
            return ResponseEntity.badRequest().body(new ResponseDto("존재하지 않는 태그 id 입니다."));
        }
    }

    @Operation(summary = "태그 수정", description = "태그 수정 api 입니다.")
    @PutMapping("/{tagSeq}")
    public ResponseEntity<?> putTag(@PathVariable Long tagSeq,
                                    @RequestBody TagDto.Request request) {
        Tag req = request.toEntity();
        Optional<Tag> tag = tagService.findBySeq(tagSeq);
        if (tag.isPresent()){
            // 태그 정보 수정
            tag.get().setName(req.getName());
            tag.get().setDiscription(req.getDiscription());

            // 수정된 태그 저장
            tagService.saveTag(tag.get());

            // 수정된 태그 내용 다시 리턴
            TagDto.Response res = new TagDto.Response(tag.get());
            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.badRequest().body(new ResponseDto("존재하지 않는 태그 id 입니다."));
        }
    }
}
