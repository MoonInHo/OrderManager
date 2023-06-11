package com.order.manager.repository;

import com.order.manager.domain.wrapper.delivery.Company;
import com.order.manager.dto.delivery.DeliveryDetailResponseDto;
import com.order.manager.dto.delivery.DeliveryTrackingResponseDto;
import com.order.manager.dto.order.CompletedOrderResponseDto;
import com.order.manager.dto.order.OrderTypeResponseDto;
import com.order.manager.dto.order.PreparingOrderResponseDto;
import com.order.manager.dto.order.WaitingOrderResponseDto;
import com.order.manager.enums.state.DeliveryState;
import com.order.manager.enums.state.OrderState;
import com.order.manager.enums.type.OrderType;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.order.manager.domain.entity.QCustomer.customer;
import static com.order.manager.domain.entity.QDelivery.delivery;
import static com.order.manager.domain.entity.QDeliveryCompany.deliveryCompany;
import static com.order.manager.domain.entity.QDeliveryDriver.deliveryDriver;
import static com.order.manager.domain.entity.QMenu.menu;
import static com.order.manager.domain.entity.QOrder.order;
import static com.order.manager.domain.entity.QOrderDetail.orderDetail;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<WaitingOrderResponseDto> findWaitingOrder(Long storeId) {

        Expression<String> customerInfo = getCustomerInfo();
        Expression<String> menuName = getMenuName();

        return queryFactory
                .select(
                        Projections.constructor(
                                WaitingOrderResponseDto.class,
                                order.id,
                                order.orderTimestamp.orderTimestamp,
                                menuName,
                                order.totalPrice,
                                customerInfo,
                                order.customerRequest,
                                order.orderState,
                                order.orderType,
                                order.paymentType
                        )
                )
                .from(order)
                .join(order.customer, customer)
                .where(order.store.id.eq(storeId), order.orderState.eq(OrderState.WAITING))
                .orderBy(order.orderTimestamp.orderTimestamp.asc())
                .fetch();
    }

    public List<PreparingOrderResponseDto> findPreparingOrder(Long storeId) {

        Expression<String> customerInfo = getCustomerInfo();
        Expression<String> menuName = getMenuName();

        return queryFactory
                .select(
                        Projections.constructor(
                                PreparingOrderResponseDto.class,
                                order.id,
                                order.orderTimestamp.orderTimestamp,
                                menuName,
                                order.totalPrice,
                                customerInfo,
                                //TODO List 로 출력하는 방법
//                                order.orderDetail,
                                order.orderState,
                                order.orderType,
                                order.paymentType
                        )
                )
                .from(order)
                .join(order.customer, customer)
                .where(order.store.id.eq(storeId), order.orderState.eq(OrderState.PREPARING))
                .orderBy(order.orderTimestamp.orderTimestamp.asc())
                .fetch();
    }

    public List<CompletedOrderResponseDto> findCompleteOrder(Long storeId) {

        Expression<String> customerInfo = getCustomerInfo();
        Expression<String> menuName = getMenuName();

        return queryFactory
                .select(
                        Projections.constructor(
                                CompletedOrderResponseDto.class,
                                order.orderTimestamp.orderTimestamp,
                                order.orderType,
                                order.paymentType,
                                menuName,
                                order.totalPrice,
                                customerInfo,
                                order.orderState
                        )
                )
                .from(order)
                .join(order.customer, customer)
                .where(order.store.id.eq(storeId), order.orderState.eq(OrderState.COMPLETE))
                .fetch();
    }

    public OrderTypeResponseDto findOrderTypeByOrderIdAndStoreId(Long storeId, Long orderId) {
        return queryFactory
                .select(
                        Projections.constructor(
                                OrderTypeResponseDto.class,
                                order.orderType,
                                order.orderState
                        )
                )
                .from(order)
                .where(order.store.id.eq(storeId), order.id.eq(orderId))
                .fetchOne();
    }

    public OrderState findOrderStateByOrderId(Long storeId, Long orderId) {
        return queryFactory
                .select(order.orderState)
                .from(order)
                .where(order.store.id.eq(storeId), order.id.eq(orderId))
                .fetchOne();
    }

    public Long updateOrderStateToPreparing(Long storeId, Long orderId) {
        return queryFactory
                .update(order)
                .set(order.orderState, OrderState.PREPARING)
                .where(order.store.id.eq(storeId), order.id.eq(orderId))
                .execute();
    }

    public Long updateOrderStateToReady(Long storeId, Long orderId) {
        return queryFactory
                .update(order)
                .set(order.orderState, OrderState.READY)
                .where(order.store.id.eq(storeId), order.id.eq(orderId))
                .execute();
    }

    public Long updateOrderStateToCancel(Long storeId, Long orderId) {
        return queryFactory
                .update(order)
                .set(order.orderState, OrderState.CANCEL)
                .where(order.store.id.eq(storeId), order.id.eq(orderId))
                .execute();
    }

    public Long updateOrderStateToComplete(Long storeId, Long orderId) {
        return queryFactory
                .update(order)
                .set(order.orderState, OrderState.COMPLETE)
                .where(order.store.id.eq(storeId), order.id.eq(orderId))
                .execute();
    }

    public List<DeliveryTrackingResponseDto> findDeliveryByDeliveryState(Long storeId) {
        return queryFactory
                .select(
                        Projections.constructor(
                                DeliveryTrackingResponseDto.class,
                                delivery.id,
                                order.orderTimestamp.orderTimestamp,
                                order.orderType,
                                delivery.deliveryCompany.company,
                                order.paymentType,
                                order.totalPrice,
                                delivery.deliveryState
                        )
                )
                .from(order)
                .join(order.delivery, delivery)
                .where(
                        order.store.id.eq(storeId),
                        order.orderType.eq(OrderType.DELIVERY),
                        delivery.deliveryState.ne(DeliveryState.COMPLETE)
                )
                .fetch();
    }

    //TODO fetch join 사용 고민하기
    public List<DeliveryDetailResponseDto> findDeliveryDetail(Long storeId, Long deliveryId) {
        return queryFactory
                .select(
                        Projections.constructor(
                                DeliveryDetailResponseDto.class,
                                delivery.deliveryState,
                                deliveryCompany.company,
                                delivery.deliveryTips,
                                deliveryDriver.driverName,
                                deliveryDriver.driverPhone,
                                customer.contact,
                                customer.address,
                                order.orderTimestamp.orderTimestamp,
                                order.orderType
                        )
                )
                .from(delivery)
                .join(delivery.deliveryDriver, deliveryDriver)
                .join(delivery.deliveryCompany, deliveryCompany)
                .join(delivery.order, order)
                .join(order.customer, customer)
                .where(order.store.id.eq(storeId), delivery.id.eq(deliveryId))
                .fetch();
    }

    private static Expression<String> getMenuName() {
        return ExpressionUtils.as(
                        JPAExpressions
                                .select(Expressions.stringTemplate("group_concat({0})", menu.menuName))
                                .from(orderDetail)
                                .join(orderDetail.menu, menu)
                                .where(orderDetail.order.id.eq(order.id))
                                .groupBy(orderDetail.order.id),
                        "menuNames"
        );
    }

    private static Expression<String> getCustomerInfo() {
        Expression<String> customerContact = customer.contact.contact;
        Expression<String> address = customer.address.address
                .concat(" ")
                .concat(customer.address.addressDetail);

        return Expressions.cases()
                .when(order.orderType.eq(OrderType.DELIVERY))
                .then(address)
                .otherwise(customerContact)
                .as("customerInfo");
    }

    public Long findDeliveryCompanyIdByCompanyName(Company companyName) {
        return queryFactory
                .select(deliveryCompany.id)
                .from(deliveryCompany)
                .where(deliveryCompany.company.eq(companyName))
                .fetchOne();
    }
}
