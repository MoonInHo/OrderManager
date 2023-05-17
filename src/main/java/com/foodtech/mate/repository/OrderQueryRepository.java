package com.foodtech.mate.repository;

import com.foodtech.mate.domain.dto.order.PendingOrderDto;
import com.foodtech.mate.domain.state.OrderState;
import com.foodtech.mate.domain.state.OrderType;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.foodtech.mate.domain.entity.QCustomer.customer;
import static com.foodtech.mate.domain.entity.QMenu.menu;
import static com.foodtech.mate.domain.entity.QOrder.order;
import static com.foodtech.mate.domain.entity.QOrderDetail.orderDetail;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<PendingOrderDto> findPendingOrderByStoreId(Long storeId) {

        Expression<String> customerInfo = Expressions.cases()
                .when(order.orderType.eq(OrderType.DELIVERY))
                .then(order.customer.address.address.concat(" ").concat(order.customer.address.addressDetail))
                .otherwise(order.customer.contact.contact)
                .as("customerInfo");

        Expression<String> menuName =
                ExpressionUtils.as(
                        JPAExpressions
                                .select(Expressions.stringTemplate("group_concat({0})", menu.menuName.menuName))
                                .from(orderDetail)
                                .join(orderDetail.menu, menu)
                                .where(orderDetail.order.id.eq(order.id))
                                .groupBy(orderDetail.order.id),
                        "menuName"
                );

        List<PendingOrderDto> orders = queryFactory
                .select(
                        Projections.fields(
                                PendingOrderDto.class,
                                order.id.as("orderId"),
                                order.orderTimestamp.orderTimestamp,
                                menuName,
                                order.totalPrice,
                                customerInfo,
                                order.customerRequest,
                                customer.address.as("address"),
                                order.orderState.stringValue().as("orderState"),
                                order.orderType.stringValue().as("orderType"),
                                order.paymentType.stringValue().as("paymentType")
                        )
                )
                .from(order)
                .join(order.customer, customer)
                .where(order.store.id.eq(storeId), order.orderState.eq(OrderState.WAITING))
                .orderBy(order.orderTimestamp.orderTimestamp.asc())
                .fetch();

        return orders;
    }

    public OrderState findOrderByOrderId(Long orderId) {
        return queryFactory
                .select(order.orderState)
                .from(order)
                .where(order.id.eq(orderId))
                .fetchFirst();
    }

    public Long changeOrderState(Long orderId, OrderState orderState) {

        //TODO 주문상태 전달받은 파라미터값으로 변경하는 방법 찾아보기
        return queryFactory
                .update(order)
                .set(order.orderState, orderState)
                .where(order.id.eq(orderId))
                .execute();
    }
}
