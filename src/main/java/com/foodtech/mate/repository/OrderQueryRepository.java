package com.foodtech.mate.repository;

import com.foodtech.mate.domain.dto.order.CompletedOrderDto;
import com.foodtech.mate.domain.dto.order.FindOrderDto;
import com.foodtech.mate.domain.entity.Order;
import com.foodtech.mate.domain.state.OrderState;
import com.foodtech.mate.domain.state.OrderType;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
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

    public Order findOrderByOrderId(Long orderId) {
        return queryFactory
                .selectFrom(order)
                .where(order.id.eq(orderId))
                .fetchFirst();
    }

    public List<FindOrderDto> findOrderByStoreId(Long storeId, OrderState orderStateCode) {

        BooleanExpression condition = null;
        if (OrderState.WAITING.equals(orderStateCode)) {
            condition = order.orderState.eq(OrderState.WAITING);
        }
        if (OrderState.PREPARING.equals(orderStateCode)) {
            condition = order.orderState.eq(OrderState.PREPARING);
        }

        Expression<String> customerInfo = getCustomerInfo();
        Expression<String> menuName = getMenuName();

        return queryFactory
                .select(
                        Projections.constructor(
                                FindOrderDto.class,
                                order.id.as("orderId"),
                                order.orderTimestamp.orderTimestamp,
                                menuName,
                                order.totalPrice,
                                customerInfo,
                                order.customerRequest,
                                //TODO 고객요청, 주문상세를 주문상태에 따라 다르게 조회
                                order.orderState.stringValue().as("orderState"),
                                order.orderType.stringValue().as("orderType"),
                                order.paymentType.stringValue().as("paymentType")
                        )
                )
                .from(order)
                .join(order.customer, customer)
                .where(order.store.id.eq(storeId), condition)
                .orderBy(order.orderTimestamp.orderTimestamp.asc())
                .fetch();
    }

    public OrderState findOrderStateByOrderId(Long orderId) {
        return queryFactory
                .select(order.orderState)
                .from(order)
                .where(order.id.eq(orderId))
                .fetchFirst();
    }

    public Long updateOrderState(Long orderId, OrderState orderStateCode) {
        return queryFactory
                .update(order)
                .set(order.orderState, orderStateCode)
                .where(order.id.eq(orderId))
                .execute();
    }

    public OrderType findOrderTypeByOrderId(Long orderId) {
        return queryFactory
                .select(order.orderType)
                .from(order)
                .where(order.orderType.eq(OrderType.TOGO), order.id.eq(orderId))
                .fetchFirst();
    }

    public List<CompletedOrderDto> findCompleteOrder() {

        Expression<String> customerInfo = getCustomerInfo();
        Expression<String> menuName = getMenuName();

        return queryFactory
                .select(
                        Projections.constructor(
                                CompletedOrderDto.class,
                                order.orderTimestamp.orderTimestamp,
                                order.orderType,
                                order.paymentType,
                                menuName,
                                customerInfo,
                                order.orderState
                        )
                )
                .from(order)
                .join(order.customer, customer)
                .where(order.orderState.eq(OrderState.COMPLETE))
                .fetch();
    }

    private static Expression<String> getMenuName() {
        Expression<String> menuName =
                ExpressionUtils.as(
                        JPAExpressions
                                .select(Expressions.stringTemplate("group_concat({0})", menu.menuName))
                                .from(orderDetail)
                                .join(orderDetail.menu, menu)
                                .where(orderDetail.order.id.eq(order.id))
                                .groupBy(orderDetail.order.id),
                        "menuNames"
                );
        return menuName;
    }

    private static Expression<String> getCustomerInfo() {
        Expression<String> contact = customer.contact.contact;
        Expression<String> address = customer.address.address
                .concat(" ")
                .concat(customer.address.addressDetail);

        Expression<String> customerInfo = Expressions.cases()
                .when(order.orderType.eq(OrderType.DELIVERY))
                .then(address)
                .otherwise(contact)
                .as("customerInfo");

        return customerInfo;
    }
}
