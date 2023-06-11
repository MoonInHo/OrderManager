package com.order.manager.domain.wrapper.account;

import com.order.manager.exception.exception.EmptyValueException;
import com.order.manager.exception.exception.InvalidFormatException;
import com.order.manager.exception.exception.member.NullValueException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Name {

    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name of(String name) {

        if (name == null) {
            throw new NullValueException();
        }

        if (name.isBlank()) {
            throw new EmptyValueException();
        }

        if (!name.matches("^[가-힣]{2,5}$")) {
            throw new InvalidFormatException();
        }

        return new Name(name);
    }
}
