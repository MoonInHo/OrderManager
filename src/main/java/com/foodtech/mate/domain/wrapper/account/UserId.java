package com.foodtech.mate.domain.wrapper.account;

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
            throw new IllegalArgumentException("! 아이디를 입력해 주세요.");
        }

        if (userId.isBlank()) {
            throw new IllegalArgumentException("! 공백을 사용할 수 없습니다.");
        }

        if (!userId.matches("^[a-zA-Z0-9]{5,}$")) {
            throw new IllegalArgumentException("! 올바른 형식으로 입력해주세요.");
        }

        return new UserId(userId);
    }
}
