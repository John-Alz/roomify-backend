package com.roomify.roomifybackend.services.exception;

public class NoExistException extends RuntimeException {
    public NoExistException(String message) {
        super(message);
    }
}
