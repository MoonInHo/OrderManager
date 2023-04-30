package com.foodtech.mate.domain.wrapper;

import com.foodtech.mate.exception.exception.InvalidPasswordException;
import com.foodtech.mate.exception.exception.NullAndBlankPasswordException;
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
            throw new NullAndBlankPasswordException("! 비밀번호를 입력해 주세요.");
        }

        if (password.isBlank()) {
            throw new NullAndBlankPasswordException("! 공백을 사용할 수 없습니다.");
        }

        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%&*?])[A-Za-z\\d!@#$%&*?]{10,20}$")) {
            throw new InvalidPasswordException("! 올바른 형식으로 입력해주세요.");
        }

        return new Password(password);
    }

    public static Password encodedPassword(String password) {
        return new Password(password);
    }
}
