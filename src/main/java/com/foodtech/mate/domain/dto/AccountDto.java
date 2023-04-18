package com.foodtech.mate.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountDto {

    private String userId;
    private String password;

    private AccountDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public static AccountDto saveAccountInfo(String userId, String password) {
        return new AccountDto(userId, password);
    }
}
