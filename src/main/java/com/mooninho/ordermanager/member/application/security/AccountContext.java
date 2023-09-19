package com.mooninho.ordermanager.member.application.security;

import com.mooninho.ordermanager.member.domain.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AccountContext extends User {

    private final Member member;

    public AccountContext(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.userId(), member.password(), authorities);
        this.member = member;
    }
}
