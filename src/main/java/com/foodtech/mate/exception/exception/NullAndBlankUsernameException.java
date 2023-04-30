package com.foodtech.mate.exception.exception;

import org.springframework.security.core.AuthenticationException;

public class NullAndBlankUsernameException extends AuthenticationException {

    public NullAndBlankUsernameException(String message) {
        super(message);
    }
}
