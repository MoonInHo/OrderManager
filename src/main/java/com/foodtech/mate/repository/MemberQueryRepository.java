package com.foodtech.mate.repository;

import com.foodtech.mate.domain.wrapper.Username;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.foodtech.mate.domain.entity.QAccount.account;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public boolean findByUserId(String username) {
        Integer result = queryFactory
                .selectOne()
                .from(account)
                .where(account.username.eq(Username.of(username)))
                .fetchFirst();

        return result != null && result == 1;
    }
}
