package com.foodtech.mate.repository;

import com.foodtech.mate.domain.wrapper.store.BusinessNumber;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.foodtech.mate.domain.entity.QStore.store;

@Repository
@RequiredArgsConstructor
public class StoreQueryRepository {

    private final JPAQueryFactory queryFactory;

    public boolean isBusinessNumberExist(BusinessNumber businessNumber) {
        Integer result = queryFactory
                .selectOne()
                .from(store)
                .where(store.businessNumber.eq(businessNumber))
                .fetchFirst();

        return result != null && result == 1;
    }
}
