package com.order.manager.util.account;

import com.order.manager.domain.entity.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggedInUserinfoProvider {

    public static Long loggedInUserKeyFetcher() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        Account account = (Account) principal;

        return account.isUserKey();
    }
}
