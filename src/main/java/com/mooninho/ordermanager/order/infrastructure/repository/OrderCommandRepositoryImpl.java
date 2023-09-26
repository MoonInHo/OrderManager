package com.mooninho.ordermanager.order.infrastructure.repository;

import com.mooninho.ordermanager.order.domain.enums.OrderStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.mooninho.ordermanager.order.domain.entity.QOrder.order;

@Repository
@RequiredArgsConstructor
public class OrderCommandRepositoryImpl implements OrderCommandRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public void changeOrderToPreparing(Long orderId) {
        queryFactory
                .update(order)
                .set(order.orderStatus, OrderStatus.PREPARING)
                .where(order.id.eq(orderId))
                .execute();
    }

    @Override
    public void changeOrderToReady(Long orderId) {
        queryFactory
                .update(order)
                .set(order.orderStatus, OrderStatus.READY)
                .where(order.id.eq(orderId))
                .execute();
    }

    @Override
    public void changeOrderToCancel(Long orderId) {
        queryFactory
                .update(order)
                .set(order.orderStatus, OrderStatus.CANCEL)
                .where(order.id.eq(orderId))
                .execute();
    }
}
