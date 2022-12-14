package com.d202.koflowa.talk.controller;

import com.d202.koflowa.common.response.Response;
import com.d202.koflowa.talk.dto.RoomDto;
import com.d202.koflowa.talk.service.MessageService;
import com.d202.koflowa.talk.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/talk/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final MessageService messageService;

    @Operation(summary = "방 생성/조회하기", description = "상대방과의 채팅방을 생성/조회하는 api 입니다.")
    @PostMapping
    public Response createRoom(@RequestBody RoomDto.RequestCreate roomDto) {
        return Response.success(roomService.createRoom(roomDto));
    }

    @Operation(summary = "채팅방 조회", description = "나의 현재 채팅방을 조회하는 api 입니다.")
    @GetMapping
    public Response getMyRoomList() {
        return Response.success(roomService.getMyRoomList());
    }

    @Operation(summary = "채팅방 삭제", description = "해당 채팅방의 삭제하는 api 입니다.")
    @DeleteMapping
    public Response deleteRoom(@RequestBody RoomDto.Request roomDto) {
        /* 해당 채팅방 논리 삭제 */
        roomService.deleteRoom(roomDto);
        /* 해당 메시지 로그 논리 삭제 */
        messageService.checkMessageDeleted(roomDto.getRoomSeq());

        return Response.success();
    }
}
