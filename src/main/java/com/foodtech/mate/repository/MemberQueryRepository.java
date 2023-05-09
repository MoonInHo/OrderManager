package com.foodtech.mate.repository;

import com.foodtech.mate.domain.entity.Account;
import com.foodtech.mate.domain.wrapper.account.Phone;
import com.foodtech.mate.domain.wrapper.account.UserId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.foodtech.mate.domain.entity.QAccount.account;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public boolean isUserIdExist(UserId userId) {
        Integer result = queryFactory
                .selectOne()
                .from(account)
                .where(account.userId.eq(userId))
                .fetchFirst();

        return result != null && result == 1;
    }

    public Account findAccountByUserId(String userId) {
        return queryFactory
                .selectFrom(account)
                .from(account)
                .where(account.userId.eq(UserId.of(userId)))
                .fetchOne();
    }

    public UserId findUserIdByPhone(String phone) {
        return queryFactory
                .select(account.userId)
                .from(account)
                .where(account.phone.eq(Phone.of(phone)))
                .fetchOne();
    }

    public Long changePassword(Account foundAccount) {
        return queryFactory
                .update(account)
                .set(account.password, foundAccount.getPassword())
                .where(account.id.eq(foundAccount.getId()))
                .execute();
    }
}
