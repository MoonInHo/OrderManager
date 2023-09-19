package com.mooninho.ordermanager.member.domain.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserId {

    private final String userId;

    private UserId(String userId) {
        this.userId = userId;
    }

    public static UserId of(String userId) {

        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("아이디를 입력하세요.");
        }
        if (userId.contains(" ")) {
            throw new IllegalArgumentException("아이디엔 공백을 포함할 수 없습니다.");
        }
        if (!userId.matches("^[a-zA-Z0-9]{5,}$")) {
            throw new IllegalArgumentException("아이디 형식이 올바르지 않습니다.");
        }

        return new UserId(userId);
    }

    public String userId() {
        return userId;
    }
}
