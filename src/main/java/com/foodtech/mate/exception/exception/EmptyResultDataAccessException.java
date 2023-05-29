package com.foodtech.mate.exception.exception;

public class EmptyResultDataAccessException extends RuntimeException {

    public EmptyResultDataAccessException() {
        super("상태 업데이트 실패.");
    }
}
