package com.mooninho.ordermanager.ownerapp.owner.application.security;

import com.mooninho.ordermanager.ownerapp.owner.domain.entity.Owner;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AccountContext extends User {

    private final Owner owner;

    public AccountContext(Owner owner, Collection<? extends GrantedAuthority> authorities) {
        super(owner.username(), owner.password(), authorities);
        this.owner = owner;
    }
}
