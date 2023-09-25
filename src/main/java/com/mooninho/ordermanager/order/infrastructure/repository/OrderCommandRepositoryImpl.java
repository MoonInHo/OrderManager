package com.mooninho.ordermanager.order.infrastructure.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderCommandRepositoryImpl implements OrderCommandRepository {

    private final JPAQueryFactory queryFactory;
}
