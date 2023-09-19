package com.mooninho.ordermanager.임시.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Long findStoreIdByUserKey(Long userKey) {
//        return queryFactory
//                .select(store.id)
//                .from(store)
//                .where(account.id.eq(userKey))
//                .fetchOne();
        return null;
    }
}
