package com.d202.koflowa.tag.controller;

import com.d202.koflowa.common.response.Response;
import com.d202.koflowa.common.domain.TagStatus;
import com.d202.koflowa.tag.dto.TagDto;
import com.d202.koflowa.tag.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Tag", description = "Tag API")
@RequiredArgsConstructor // 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해준다.
public class TagController {

    private final TagService tagService;

    @Operation(summary = "전체 태그 조회", description = "전체 태그 조회 api 입니다.")
    @GetMapping("")
    public Response getTagList(Pageable pageable) {
        return Response.success(tagService.getTagList(pageable));
    }

    @Operation(summary = "태그 생성", description = "태그 생성 api 입니다.")
    @PostMapping("/regist")
    public Response saveTag(@RequestBody TagDto.Request request) {
        return Response.success(tagService.saveTag(request));
    }

    @Operation(summary = "태그 상세 조회", description = "태그 상세 조회 api 입니다.")
    @GetMapping("/{tagName}")
    public Response getDetailTag(@PathVariable String tagName) {
        return Response.success(tagService.getDetailTag(tagName));
    }

    @Operation(summary = "태그 수정", description = "태그 수정 api 입니다.")
    @PutMapping("/{tagName}")
    public Response putTag(@PathVariable String tagName,
                                    @RequestBody TagDto.Request request) {
        return Response.success(tagService.putTag(tagName, request));
    }


    @Operation(summary = "주시 태그 목록 가져오기", description = "사용자가 저장한 주시태그 목록을 가져옵니다.")
    @GetMapping("/watch")
    public Response getWatchedTag() {
        return Response.success(tagService.getWatchedTag());
    }


    @Operation(summary = "주시 태그 추가", description = "주시 태그 추가 api 입니다.")
    @PostMapping("/watch/{tagName}")
    public Response postWatchedTag(@PathVariable String tagName) {
        return Response.success(tagService.postUserTag(tagName, TagStatus.WATCHED));
    }

    @Operation(summary = "주시 태그 삭제", description = "주시 태그 삭제 api 입니다.")
    @DeleteMapping("/watch/{tagName}")
    public Response deleteWatchedTag(@PathVariable String tagName) {
        return Response.success(tagService.deleteUserTag(tagName, TagStatus.WATCHED));
    }

    @Operation(summary = "숨김 태그 목록 가져오기", description = "사용자가 저장한 숨김 태그 목록을 가져옵니다.")
    @GetMapping("/ignore")
    public Response getIgnoreTag() {
        return Response.success(tagService.getIgnoreTag());
    }

    @Operation(summary = "숨김 태그 추가", description = "숨김 태그 추가 api 입니다.")
    @PostMapping("/ignore/{tagName}")
    public Response postIgnoredTag(@PathVariable String tagName) {
        return Response.success(tagService.postUserTag(tagName, TagStatus.IGNORED));
    }

    @Operation(summary = "숨김 태그 삭제", description = "숨김 태그 삭제 api 입니다.")
    @DeleteMapping("/ignore/{tagName}")
    public Response deleteIgnoredTag(@PathVariable String tagName) {
        return Response.success(tagService.deleteUserTag(tagName, TagStatus.IGNORED));
    }

    @Operation(summary = "태그 목록 가져오기", description = "모든 태그의 이름을 리스트로 반환합니다.")
    @GetMapping("/list")
    public Response getTagStrList() {
        return Response.success(tagService.getTagStrList());
    }
}
