package com.foodtech.mate.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.foodtech.mate.domain.entity.QStore.store;

@Repository
@RequiredArgsConstructor
public class StoreQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Long findStoreIdByUserCode(Long userCode) {
        return queryFactory
                .select(store.account.id)
                .from(store)
                .where(store.account.id.eq(userCode))
                .fetchFirst();
    }
}
