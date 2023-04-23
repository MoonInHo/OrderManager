package com.foodtech.mate.repository;

import com.foodtech.mate.domain.dto.AccountDto;
import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.domain.wrapper.Username;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.foodtech.mate.domain.entity.QAccount.account;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public boolean isUsernameExist(String username) {
        Integer result = queryFactory
                .selectOne()
                .from(account)
                .where(account.username.eq(Username.of(username)))
                .fetchFirst();

        return result != null && result == 1;
    }

    public Account findByUsername(String username) {
        return queryFactory
                .selectFrom(account)
                .from(account)
                .where(account.username.eq(Username.of(username)))
                .fetchFirst();
    }
}
