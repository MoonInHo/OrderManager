package com.mooninho.ordermanager.ownerapp.deliverydriver.infrastructure.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryDriverQueryRepositoryImpl implements DeliveryDriverQueryRepository {

    private final JPAQueryFactory queryFactory;
}
