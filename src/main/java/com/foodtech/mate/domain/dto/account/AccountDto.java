package com.foodtech.mate.domain.dto.account;

import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.domain.wrapper.account.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountDto {

    private String userId;
    private String password;
    private String name;
    private String phone;

    public static AccountDto createAccountDto(String userId, String password, String name, String phone) {
        return new AccountDto(userId, password, name, phone);
    }

    public static Account toEntity(AccountDto accountDto) {
        return Account.createMember(
                UserId.of(accountDto.getUserId()),
                Password.of(accountDto.getPassword()),
                Name.of(accountDto.getName()),
                Phone.of(accountDto.getPhone())
        );
    }
}
