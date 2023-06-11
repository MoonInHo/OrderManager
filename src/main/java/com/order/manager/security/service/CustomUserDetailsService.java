package com.order.manager.security.service;

import com.order.manager.domain.entity.Account;
import com.order.manager.domain.wrapper.account.UserId;
import com.order.manager.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberQueryRepository memberQueryRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Account account = memberQueryRepository.getByUserId(UserId.of(userId));
        if (account == null) {
            throw new UsernameNotFoundException("사용자 정보를 다시 확인해 주세요.");
        }

        return new AccountContext(account, account.createRole());
    }
}
