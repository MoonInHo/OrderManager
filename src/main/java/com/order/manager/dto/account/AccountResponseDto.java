package com.order.manager.dto.account;

import com.order.manager.domain.wrapper.account.Name;
import com.order.manager.domain.wrapper.account.Password;
import com.order.manager.domain.wrapper.account.Phone;
import com.order.manager.domain.wrapper.account.UserId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {

    private UserId userId;
    private Password password;
    private Name name;
    private Phone phone;

    public String isPassword() {
        return password.getPassword();
    }
}
