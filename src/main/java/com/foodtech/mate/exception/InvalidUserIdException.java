package com.foodtech.mate.exception;

public class InvalidUserIdException extends RuntimeException {

    public InvalidUserIdException(String message) {
        super(message);
    }
}
