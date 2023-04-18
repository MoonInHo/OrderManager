package com.foodtech.mate.repository;

import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.domain.entity.QAccount;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.foodtech.mate.domain.entity.QAccount.account;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public boolean findByUserId(String userId) {
        Integer result = queryFactory
                .selectOne()
                .from(account)
                .where(account.userId.eq(userId))
                .fetchFirst();

        return result != null && result == 1;
    }
}
