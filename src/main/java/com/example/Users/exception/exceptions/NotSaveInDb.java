package com.example.Users.exception.exceptions;

public class NotSaveInDb extends RuntimeException {
    public NotSaveInDb(String message) {
        super(message);
    }
}
