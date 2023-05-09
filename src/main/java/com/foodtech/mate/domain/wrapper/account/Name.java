package com.foodtech.mate.domain.wrapper.account;

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
            throw new IllegalArgumentException("! 이름을 입력해 주세요.");
        }

        if (name.isBlank()) {
            throw new IllegalArgumentException("! 공백을 사용할 수 없습니다.");
        }

        if (!name.matches("^[가-힣]{2,5}$")) {
            throw new IllegalArgumentException("! 올바른 형식으로 입력해주세요.");
        }

        return new Name(name);
    }
}
