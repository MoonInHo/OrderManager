package com.foodtech.mate.dto.account;

import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.domain.wrapper.account.Name;
import com.foodtech.mate.domain.wrapper.account.Password;
import com.foodtech.mate.domain.wrapper.account.Phone;
import com.foodtech.mate.domain.wrapper.account.UserId;
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

    public Account toEntity() {
        return Account.createMember(
                UserId.of(userId),
                Password.of(password),
                Name.of(name),
                Phone.of(phone)
        );
    }
}
