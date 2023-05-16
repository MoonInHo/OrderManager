package com.foodtech.mate.controller.verifier;

import com.foodtech.mate.domain.entity.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class fetch {

    public static Long fetchLoggedInUserKey() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        Account account = (Account) principal;

        return account.getId();
    }
}
