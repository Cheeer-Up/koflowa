package com.d202.koflowa.exception;

public class UserTagExistException extends RuntimeException{
    public UserTagExistException(String message) {
        super(message);
    }
}
