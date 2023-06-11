package com.order.manager.domain.wrapper.account;

import com.order.manager.exception.exception.EmptyValueException;
import com.order.manager.exception.exception.InvalidFormatException;
import com.order.manager.exception.exception.member.NullValueException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Password {

    private final String password;

    private Password(String password) {
        this.password = password;
    }

    public static Password of(String password) {

        if (password == null) {
            throw new NullValueException();
        }

        if (password.isBlank()) {
            throw new EmptyValueException();
        }

        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%&*?])[A-Za-z\\d!@#$%&*?]{10,20}$")) {
            throw new InvalidFormatException();
        }

        return new Password(password);
    }

    public static Password encodedPassword(String password) {
        return new Password(password);
    }
}
