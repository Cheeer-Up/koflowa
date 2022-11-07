package com.d202.koflowa.exception;

import com.d202.koflowa.common.response.Response;
import com.d202.koflowa.talk.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    /* Talk 기능 Exception */
    @ExceptionHandler(User1NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response User1NotFoundException() {
        return Response.failure(404, "해당 유저의 채팅방은 존재하지 않습니다.");
    }

    @ExceptionHandler(RoomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response RoomNotFoundException() {
        return Response.failure(404, "채팅방 검색에 실패했습니다.");
    }

    @ExceptionHandler(Room1NoFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response Room1NoFoundException() {
        return Response.failure(404, "유저가 방을 생성할 때 room1이 생성되지 않았습니다.");
    }

    @ExceptionHandler(Room2NoFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response Room2NoFoundException() {
        return Response.failure(404, "유저가 방을 생성할 때 room2가 생성되지 않았습니다.");
    }

    @ExceptionHandler(RoomDeleteFailureException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response RoomDeleteFailureException() {
        return Response.failure(404, "방을 삭제하는데 실패했습니다.");
    }

    @ExceptionHandler(MessageListLoadException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response MessageListLoadException() {
        return Response.failure(404, "유저의 채팅방의 메시지들을 불러오는데 실패했습니다.");
    }

    @ExceptionHandler(MessageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response MessageNotFoundException() {
        return Response.failure(404, "메시지 검색에 실패했습니다.");
    }

    @ExceptionHandler(MessageNotSavedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response MessageNotSavedException() {
        return Response.failure(404, "메시지 저장에 실패했습니다.");
    }
}