package com.foodtech.mate.exception.exception;

import org.springframework.security.core.AuthenticationException;

public class NullAndBlankPasswordException extends AuthenticationException {

    public NullAndBlankPasswordException(String message) {
        super(message);
    }
}
