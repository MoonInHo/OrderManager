package com.order.manager.dto.account;

import com.order.manager.domain.entity.Account;
import com.order.manager.domain.wrapper.account.Name;
import com.order.manager.domain.wrapper.account.Password;
import com.order.manager.domain.wrapper.account.Phone;
import com.order.manager.domain.wrapper.account.UserId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AccountDto {

    private String userId;
    private String password;
    private String name;
    private String phone;

    public static AccountDto createAccountDto(String userId, String password, String name, String phone) {
        return new AccountDto(userId, password, name, phone);
    }

    public void passwordEncrypt(String password) {
        this.password = password;
    }

    public Account toEntity() {
        return Account.createMember(
                UserId.of(userId),
                Password.encodedPassword(password),
                Name.of(name),
                Phone.of(phone)
        );
    }
}
