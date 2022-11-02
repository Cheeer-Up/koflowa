package com.d202.koflowa.exception;

public class SessionNotFoundException extends RuntimeException{
    public SessionNotFoundException(String msg){
        super(msg);
    }
}
