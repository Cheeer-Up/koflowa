package com.d202.koflowa.talk.controller;

import com.d202.koflowa.common.response.Response;
import com.d202.koflowa.talk.dto.MessageDto;
import com.d202.koflowa.talk.dto.RoomDto;
import com.d202.koflowa.talk.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/talk/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    /* 메시지 송신 */
    @Operation(summary = "메시지 보내기", description = "채팅방에서 상대에게 메시지 보내는 api 입니다.")
    @PostMapping
    public Response sendChatMessage(@RequestBody MessageDto.Request messageDto) {
        return Response.success(messageService.createMessage(messageDto));
    }

    @Operation(summary = "채팅방 메시지들을 조회", description = "상대방과의 채팅방의 메시지들을 조회하는 api 입니다.")
    @GetMapping("/{roomSeq}")
    public Response getChatMessageList(@PathVariable("roomSeq") Long roomSeq) {
        return Response.success(messageService.getMessageList(roomSeq));
    }

    /* 메시지 삭제 */
    @Operation(summary = "메시지 삭제", description = "메시지를 삭제하는 api 입니다.")
    @DeleteMapping
    public Response deleteMessage(Long messageSeq) {
        messageService.deleteMessage(messageSeq);
        return Response.success();
    }

    /* 메시지 로깅 : 읽음 표시 */
    @Operation(summary = "채팅방 메시지 전체 읽음 표시", description = "메시지를 전체 읽음 표시하는 api 입니다.")
    @GetMapping("/read")
    public Response checkMessageRead(Long roomSeq) {
        messageService.checkMessageRead(roomSeq);
        return Response.success();
    }

    /* 현재 유저의 미확인 메시지 개수 조회 */
    @Operation(summary = "유저의 미확인 메시지 전체 개수 표시", description = "현재 접속한 유저의 메시지 개수를 반환하는 api 입니다.")
    @GetMapping("/check")
    public Response countWaitingMessage() {
        return Response.success(messageService.countWaitingMessage());
    }
}
