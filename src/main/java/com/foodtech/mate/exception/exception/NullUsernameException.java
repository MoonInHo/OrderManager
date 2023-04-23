package com.foodtech.mate.exception.exception;

import org.springframework.security.core.AuthenticationException;

public class NullUsernameException extends AuthenticationException {

    public NullUsernameException(String message) {
        super(message);
    }
}
