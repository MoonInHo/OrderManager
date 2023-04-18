package com.foodtech.mate.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountDto {

    private String username;
    private String password;

    private AccountDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static AccountDto saveAccountInfo(String username, String password) {
        return new AccountDto(username, password);
    }
}
