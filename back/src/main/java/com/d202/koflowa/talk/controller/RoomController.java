package com.d202.koflowa.talk.controller;

import com.d202.koflowa.common.response.Response;
import com.d202.koflowa.talk.dto.RoomDto;
import com.d202.koflowa.talk.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/talk/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @Operation(summary = "방 생성하기", description = "상대방과의 채팅방을 생성하는 api 입니다.")
    @PostMapping
    public Response createRoom(@RequestBody RoomDto.RequestCreate roomDto) {
        return Response.success(roomService.createRoom(roomDto));
    }

    //token 도입시 파라미터를 삭제하고 토큰 값에서 조회할 것
    @Operation(summary = "채팅방 조회", description = "나의 현재 채팅방을 조회하는 api 입니다.")
    @GetMapping
    public Response getMyRoomList() {
        return Response.success(roomService.getMyRoomList());
    }

    @Operation(summary = "채팅방 삭제", description = "해당 채팅방의 삭제하는 api 입니다.")
    @DeleteMapping
    public Response deleteRoom(@RequestBody RoomDto.Request roomDto) {
        roomService.deleteRoom(roomDto);
        return Response.success();
    }
}
