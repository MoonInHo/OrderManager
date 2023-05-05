package com.foodtech.mate.domain.dto;

import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.domain.wrapper.Password;
import com.foodtech.mate.domain.wrapper.Phone;
import com.foodtech.mate.domain.wrapper.Username;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountDto {

    private String username;
    private String password;
    private String phone;

    private AccountDto(String username, String password, String phone) {
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public static AccountDto createAccountDto(String username, String password, String phone) {
        return new AccountDto(username, password, phone);
    }

    public static Account createAccount(AccountDto accountDto) {
        return Account.createMember(
                Username.of(accountDto.getUsername()),
                Password.of(accountDto.getPassword()),
                Phone.of(accountDto.getPhone())
        );
    }
}
