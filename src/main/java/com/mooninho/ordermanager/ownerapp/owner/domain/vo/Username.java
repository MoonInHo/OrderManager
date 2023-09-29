package com.mooninho.ordermanager.ownerapp.owner.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Username {

    private final String username;

    private Username(String username) {
        this.username = username;
    }

    public static Username of(String username) {

        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("아이디를 입력하세요.");
        }
        if (username.contains(" ")) {
            throw new IllegalArgumentException("아이디엔 공백을 포함할 수 없습니다.");
        }
        if (!username.matches("^[a-zA-Z0-9]{5,}$")) {
            throw new IllegalArgumentException("아이디 형식이 올바르지 않습니다.");
        }

        return new Username(username);
    }

    public String username() {
        return username;
    }
}
