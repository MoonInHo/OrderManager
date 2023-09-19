package com.mooninho.ordermanager.member.domain.vo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Password {

    private final String password;

    private Password(String password) {
        this.password = password;
    }

    public static Password of(String password) {

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("비밀번호를 입력하세요.");
        }
        if (password.contains(" ")) {
            throw new IllegalArgumentException("비밀번호엔 공백을 포함할 수 없습니다.");
        }
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%&*?])[A-Za-z\\d!@#$%&*?]{10,20}$")) {
            throw new IllegalArgumentException("비밀번호 형식이 올바르지 않습니다.");
        }

        return new Password(password);
    }

    public static Password encodedPassword(String password) {
        return new Password(password);
    }

    public String password() {
        return password;
    }
}
