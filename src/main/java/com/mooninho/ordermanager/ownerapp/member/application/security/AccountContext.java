package com.mooninho.ordermanager.ownerapp.member.application.security;

import com.mooninho.ordermanager.ownerapp.member.domain.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AccountContext extends User {

    private final Member member;

    public AccountContext(Member owner, Collection<? extends GrantedAuthority> authorities) {
        super(owner.username(), owner.password(), authorities);
        this.member = owner;
    }
}
