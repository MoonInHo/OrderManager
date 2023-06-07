package com.order.manager.domain.wrapper.account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Role {

    private final String role;

    private Role(String role) {
        this.role = role;
    }

    public static Role of(String role) {
        return new Role(role);
    }
}
