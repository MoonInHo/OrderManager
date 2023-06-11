package com.order.manager.domain.wrapper.account;

import com.order.manager.exception.exception.EmptyValueException;
import com.order.manager.exception.exception.InvalidFormatException;
import com.order.manager.exception.exception.member.NullValueException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserId {

    @Column(unique = true)
    private final String userId;

    private UserId(String userId) {
        this.userId = userId;
    }

    public static UserId of(String userId) {

        if (userId == null) {
            throw new NullValueException();
        }

        if (userId.isBlank()) {
            throw new EmptyValueException();
        }

        if (!userId.matches("^[a-zA-Z0-9]{5,}$")) {
            throw new InvalidFormatException();
        }

        return new UserId(userId);
    }
}
