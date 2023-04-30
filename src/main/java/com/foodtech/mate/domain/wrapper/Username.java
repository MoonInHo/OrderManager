package com.foodtech.mate.domain.wrapper;

import com.foodtech.mate.exception.exception.InvalidUsernameException;
import com.foodtech.mate.exception.exception.NullAndBlankUsernameException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Username {

    @Column(unique = true)
    private final String username;

    private Username(String username) {
        this.username = username;
    }

    public static Username of(String username) {

        if (username == null) {
            throw new NullAndBlankUsernameException("! 아이디를 입력해 주세요.");
        }

        if (username.isBlank()) {
            throw new NullAndBlankUsernameException("! 공백을 사용할 수 없습니다.");
        }

        if (!username.matches("^[a-zA-Z0-9]{5,}$")) {
            throw new InvalidUsernameException("! 올바른 형식으로 입력해주세요.");
        }

        return new Username(username);
    }
}
