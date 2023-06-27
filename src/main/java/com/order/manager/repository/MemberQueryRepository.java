package com.order.manager.repository;

import com.order.manager.domain.entity.Account;
import com.order.manager.domain.wrapper.account.*;
import com.order.manager.dto.account.AccountDto;
import com.order.manager.dto.account.AccountResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.order.manager.domain.entity.QAccount.account;

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

        return result != null;
    }

    public boolean isPhoneExist(Phone phone) {
        Integer result = queryFactory
                .selectOne()
                .from(account)
                .where(account.phone.eq(phone))
                .fetchFirst();

        return result != null;
    }

    public Account getByUserId(UserId userId) {
        return queryFactory
                .selectFrom(account)
                .where(account.userId.eq(userId))
                .fetchOne();
    }

    public AccountResponseDto findByUserId(UserId userId) {
        return queryFactory
                .select(
                        Projections.constructor(
                                AccountResponseDto.class,
                                account.userId,
                                account.password,
                                account.name,
                                account.phone
                        )
                )
                .from(account)
                .where(account.userId.eq(userId))
                .fetchOne();
    }

    public UserId findUserIdByPhone(Phone phone) {
        return queryFactory
                .select(account.userId)
                .from(account)
                .where(account.phone.eq(phone))
                .fetchOne();
    }

    public Long changePassword(UserId userId, Password newPassword) {
        return queryFactory
                .update(account)
                .set(account.password, newPassword)
                .where(account.userId.eq(userId))
                .execute();
    }

    public boolean isAccountNotExist(Phone phone) {
        Integer result = queryFactory
                .selectOne()
                .from(account)
                .where(account.phone.eq(phone))
                .fetchFirst();

        return result == null;
    }

    public void updateVerificationFailureCount() {
        queryFactory
                .update(account)
                .set(account.verificationFailureCount, account.verificationFailureCount.add(1))
                .execute();
    }

    public Integer findVerificationFailureCountByPhone(Phone phone) {
        return queryFactory
                .select(account.verificationFailureCount)
                .from(account)
                .where(account.phone.eq(phone))
                .fetchOne();
    }
}
