package com.foodtech.mate.security.service;

import com.foodtech.mate.domain.entity.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AccountContext extends User {

    private final Account account;

    public AccountContext(Account account, Collection<? extends GrantedAuthority> authorities) {
        super(account.userIdOf(), account.passwordOf(), authorities);
        this.account = account;
    }
}
