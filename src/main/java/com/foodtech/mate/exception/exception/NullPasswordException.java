package com.foodtech.mate.exception.exception;

import org.springframework.security.core.AuthenticationException;

public class NullPasswordException extends AuthenticationException {

    public NullPasswordException(String message) {
        super(message);
    }
}
