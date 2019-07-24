package com.mp.todo.domain.exception;

public class InvalidTaskNameException extends Exception {
    public InvalidTaskNameException(String message) {
        super(message);
    }
}
