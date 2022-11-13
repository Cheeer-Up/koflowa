package com.d202.koflowa.tag.controller;

import com.d202.koflowa.tag.domain.Tag;
import com.d202.koflowa.common.domain.TagStatus;
import com.d202.koflowa.tag.dto.TagDto;
import com.d202.koflowa.tag.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@io.swagger.v3.oas.annotations.tags.Tag(name = "Tag", description = "Tag api 입니다.")
@Slf4j
@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor // 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해준다.
public class TagController {

    private final TagService tagService;

    @Operation(summary = "전체 태그 조회", description = "전체 태그 조회 api 입니다.")
    @GetMapping("")
    public ResponseEntity<Page<TagDto.Response>> getTagList(Pageable pageable) {
        return new ResponseEntity<>(tagService.getTagList(pageable), HttpStatus.OK);
    }

    @Operation(summary = "태그 생성", description = "태그 생성 api 입니다.")
    @PostMapping("/regist")
    public ResponseEntity<Tag> saveTag(@RequestBody TagDto.Request request) {
        return new ResponseEntity<>(tagService.saveTag(request), HttpStatus.OK);
    }

    @Operation(summary = "태그 상세 조회", description = "태그 상세 조회 api 입니다.")
    @GetMapping("/{tagName}")
    public ResponseEntity<TagDto.DetailResponse> getDetailTag(@PathVariable String tagName) {
        return new ResponseEntity<>(tagService.getDetailTag(tagName), HttpStatus.OK);
    }

    @Operation(summary = "태그 수정", description = "태그 수정 api 입니다.")
    @PutMapping("/{tagName}")
    public ResponseEntity<Tag> putTag(@PathVariable String tagName,
                                    @RequestBody TagDto.Request request) {
        return new ResponseEntity<>(tagService.putTag(tagName, request), HttpStatus.OK);
    }

    @Operation(summary = "주시 태그 추가", description = "주시 태그 추가 api 입니다.")
    @PostMapping("/watch/{tagSeq}")
    public ResponseEntity<?> postWatchedTag(@PathVariable Long tagSeq,
                                            @RequestParam Long userSeq) {
        return new ResponseEntity<>(tagService.postUserTag(tagSeq, userSeq, TagStatus.WATCHED), HttpStatus.OK);
    }

    @Operation(summary = "주시 태그 삭제", description = "주시 태그 삭제 api 입니다.")
    @DeleteMapping("/watch/{tagSeq}")
    public ResponseEntity<?> deleteWatchedTag(@PathVariable Long tagSeq,
                                              @RequestParam Long userSeq) {
        return new ResponseEntity<>(tagService.deleteUserTag(tagSeq, userSeq, TagStatus.WATCHED), HttpStatus.OK);
    }

    @Operation(summary = "숨김 태그 추가", description = "숨김 태그 추가 api 입니다.")
    @PostMapping("/ignore/{tagSeq}")
    public ResponseEntity<?> postIgnoredTag(@PathVariable Long tagSeq,
                                            @RequestParam Long userSeq) {
        return new ResponseEntity<>(tagService.postUserTag(tagSeq, userSeq, TagStatus.IGNORED), HttpStatus.OK);
    }

    @Operation(summary = "숨김 태그 삭제", description = "숨김 태그 삭제 api 입니다.")
    @DeleteMapping("/ignore/{tagSeq}")
    public ResponseEntity<?> deleteIgnoredTag(@PathVariable Long tagSeq,
                                              @RequestParam Long userSeq) {
        return new ResponseEntity<>(tagService.deleteUserTag(tagSeq, userSeq, TagStatus.IGNORED), HttpStatus.OK);
    }
}
