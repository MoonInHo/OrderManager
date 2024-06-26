package com.mooninho.ordermanager.ownerapp.store.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.store.domain.vo.StoreName;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.mooninho.ordermanager.ownerapp.member.domain.entity.QMember.member;
import static com.mooninho.ordermanager.ownerapp.store.domain.entity.QStore.store;

@Repository
@RequiredArgsConstructor
public class StoreQueryRepositoryImpl implements StoreQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean isExistStoreName(StoreName storeName) {
        return queryFactory
                .selectOne()
                .from(store)
                .where(store.storeName.eq(storeName))
                .fetchFirst() != null;
    }

    @Override
    public boolean isNotOwner(Long storeId, Long memberId) {
        return queryFactory
                .selectOne()
                .from(store)
                .where(store.id.eq(storeId))
                .fetchFirst() == null;
    }

    @Override
    public boolean isOwner(Long storeId, Long ownerId) {
        return queryFactory
                .selectOne()
                .from(store)
                .where(
                        store.id.eq(storeId),
                        member.id.eq(ownerId)
                )
                .fetchFirst() != null;
    }
}
