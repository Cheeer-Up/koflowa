package com.d202.koflowa.talk.controller;

import com.d202.koflowa.common.response.Response;
import com.d202.koflowa.talk.dto.RoomDto;
import com.d202.koflowa.talk.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public Response createRoom(@RequestBody RoomDto.Request roomDto) {
        return Response.success(roomService.createRoom(roomDto));
    }

    //token 도입시 파라미터를 삭제하고 토큰 값에서 조회할 것
    @PostMapping("/history")
    public Response getMyRoomList(@RequestBody RoomDto.Request roomDto) {
        return Response.success(roomService.getMyRoomList(roomDto.getUser1()));
    }

    @DeleteMapping
    public Response deleteRoom(@RequestBody RoomDto.Request roomDto) {
        return Response.success();
    }
}
