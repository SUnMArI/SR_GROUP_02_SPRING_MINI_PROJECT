package com.example.springminiproject.exception;

public class BadRequestHandlerException extends RuntimeException{
    public BadRequestHandlerException(String message) {
        super(message);
    }
}
