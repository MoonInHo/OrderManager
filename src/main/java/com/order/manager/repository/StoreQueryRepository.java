package com.order.manager.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.order.manager.domain.entity.QAccount.account;
import static com.order.manager.domain.entity.QStore.store;

@Repository
@RequiredArgsConstructor
public class StoreQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Long findStoreIdByUserKey(Long userKey) {
        return queryFactory
                .select(store.id)
                .from(store)
                .where(account.id.eq(userKey))
                .fetchOne();
    }
}
