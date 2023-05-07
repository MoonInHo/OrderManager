package com.foodtech.mate.domain.wrapper.account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Email {

    private final String email;

    private Email(String password) {
        this.email = password;
    }

    public static Email of(String email) {

        if (email == null) {
            throw new IllegalArgumentException("! 이메일을 입력해 주세요.");
        }

        if (email.isBlank()) {
            throw new IllegalArgumentException("! 공백을 사용할 수 없습니다.");
        }

        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("! 올바른 형식으로 입력해주세요.");
        }

        return new Email(email);
    }
}
