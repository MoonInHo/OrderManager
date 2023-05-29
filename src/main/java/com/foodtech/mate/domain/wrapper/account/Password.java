package com.foodtech.mate.domain.wrapper.account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

import static com.foodtech.mate.util.validation.PatternMatcher.isInvalidPasswordFormat;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Password {

    private final String password;

    private Password(String password) {
        this.password = password;
    }

    public static Password of(String password) {

        if (password == null) {
            throw new IllegalArgumentException("! 비밀번호를 입력해 주세요.");
        }

        if (password.isBlank()) {
            throw new IllegalArgumentException("! 공백을 사용할 수 없습니다.");
        }

        if (isInvalidPasswordFormat(password)) {
            throw new IllegalArgumentException("! 올바른 형식으로 입력해주세요.");
        }

        return new Password(password);
    }

    public static Password encodedPassword(String password) {
        return new Password(password);
    }
}
