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
public class Phone {

    @Column(unique = true)
    private final String phone;

    private Phone(String phone) {
        this.phone = phone;
    }

    public static Phone of(String phone) {

        if (phone == null) {
            throw new NullValueException();
        }

        if (phone.isBlank()) {
            throw new EmptyValueException();
        }

        if (!phone.matches("^010-(?:\\d{3}|\\d{4})-\\d{4}$")) {
            throw new InvalidFormatException();
        }

        return new Phone(phone);
    }
}
