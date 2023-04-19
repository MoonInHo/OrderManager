package com.foodtech.mate.domain.wrapper;

import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
public class Username {

    @Column(unique = true)
    @NotBlank(message = "! 공백을 사용할 수 없습니다.")
    private final String username;

    private Username() {
        this.username = "";
    }

    private Username(String username) {
        this.username = username;
    }

    public static Username of(String username) {
        return new Username(username);
    }
}
