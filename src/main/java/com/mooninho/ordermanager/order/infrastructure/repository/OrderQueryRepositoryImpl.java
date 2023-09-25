package com.mooninho.ordermanager.order.infrastructure.repository;

import com.mooninho.ordermanager.order.domain.enums.OrderStatus;
import com.mooninho.ordermanager.order.domain.enums.OrderType;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetPreparingOrderResponseDto;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetWaitingOrderResponseDto;
import com.mooninho.ordermanager.임시.domain.wrapper.delivery.Company;
import com.mooninho.ordermanager.임시.dto.delivery.DeliveryDetailResponseDto;
import com.mooninho.ordermanager.임시.dto.delivery.DeliveryTrackingResponseDto;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetCompleteOrderResponseDto;
import com.mooninho.ordermanager.임시.dto.order.OrderTypeResponseDto;
import com.mooninho.ordermanager.임시.enums.state.DeliveryState;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mooninho.ordermanager.customer.domain.entity.QCustomer.customer;
import static com.mooninho.ordermanager.order.domain.entity.QOrder.order;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepositoryImpl implements OrderQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<GetWaitingOrderResponseDto> getWaitingOrders(Long storeId) {

        return queryFactory
                .select(
                        Projections.fields(
                                GetWaitingOrderResponseDto.class,
                                order.id,
                                order.orderTimestamp.orderTimestamp,
//                                ExpressionUtils.list(String.class, menuNames), // TODO List 방식으로 조회
//                                Expressions.constant(menuNames),
                                order.totalPrice.totalPrice,
                                customer.address.address,
                                customer.contact.contact,
                                order.customerRequest.customerRequest, //TODO 매장요청, 라이더 요청으로 분할 & 처리 예정시간 추가
                                order.orderType,
                                order.paymentType
                        )
                )
                .from(order)
                .join(order.customer, customer)
                .where(
                        order.store.id.eq(storeId),
                        order.orderStatus.eq(OrderStatus.WAITING)
                )
                .orderBy(order.id.asc())
                .fetch();
    }

    @Override
    public List<GetPreparingOrderResponseDto> getPreparingOrders(Long storeId) {

        return queryFactory
                .select(
                        Projections.fields(
                                GetPreparingOrderResponseDto.class,
                                order.id,
                                order.orderTimestamp.orderTimestamp,
                                //menuNames
                                order.totalPrice.totalPrice,
                                customer.address.address,
                                customer.address.addressDetail,
                                customer.contact.contact,
                                order.customerRequest.customerRequest,
                                order.orderType,
                                order.paymentType
                        )
                )
                .from(order)
                .join(order.customer, customer)
                .where(
                        order.store.id.eq(storeId),
                        order.orderStatus.eq(OrderStatus.PREPARING)
                )
                .orderBy(order.id.asc())
                .fetch();
    }

    @Override
    public List<GetCompleteOrderResponseDto> getCompleteOrders(Long storeId) {

        return queryFactory
                .select(
                        Projections.fields(
                                GetCompleteOrderResponseDto.class,
                                order.id,
                                order.orderTimestamp.orderTimestamp,
                                //menuNames
                                order.totalPrice.totalPrice,
                                customer.address.address,
                                order.orderType,
                                order.paymentType
                        )
                )
                .from(order)
                .join(order.customer, customer)
                .where(
                        order.store.id.eq(storeId),
                        order.orderStatus.eq(OrderStatus.COMPLETE)
                )
                .orderBy(order.id.desc())
                .fetch();
    }

    public OrderTypeResponseDto findOrderTypes(Long storeId, Long orderId) {
//        return queryFactory
//                .select(
//                        Projections.constructor(
//                                OrderTypeResponseDto.class,
//                                Expressions.asNumber(orderId).as("orderId"),
//                                order.orderType,
//                                order.orderState
//                        )
//                )
//                .from(order)
//                .where(order.store.id.eq(storeId), order.id.eq(orderId))
//                .fetchOne();
        return null;
    }

    public OrderStatus findOrderStateByOrderId(Long storeId, Long orderId) {
//        return queryFactory
//                .select(order.orderState)
//                .from(order)
//                .where(order.store.id.eq(storeId), order.id.eq(orderId))
//                .fetchOne();
        return null;
    }

    public OrderType findOrderTypeByOrderId(Long storeId, Long orderId) {
//        return queryFactory
//                .select(order.orderType)
//                .from(order)
//                .where(order.store.id.eq(storeId), order.id.eq(orderId))
//                .fetchOne();
        return null;
    }

    public DeliveryState findDeliveryState(Long storeId, Long orderId) {
//        return queryFactory
//                .select(order.delivery.deliveryState)
//                .from(order)
//                .join(order.delivery, delivery)
//                .where(order.store.id.eq(storeId), order.id.eq(orderId))
//                .fetchOne();
        return null;
    }

    public Long updateOrderStateToPreparing(Long storeId, Long orderId) {
//        return queryFactory
//                .update(order)
//                .set(order.orderState, OrderState.PREPARING)
//                .where(order.store.id.eq(storeId), order.id.eq(orderId))
//                .execute();
        return null;
    }

    public Long updateOrderStateToReady(Long storeId, Long orderId) {
//        return queryFactory
//                .update(order)
//                .set(order.orderState, OrderState.READY)
//                .where(order.store.id.eq(storeId), order.id.eq(orderId))
//                .execute();
        return null;
    }

    public Long updateOrderStateToCancel(Long storeId, Long orderId) {
//        return queryFactory
//                .update(order)
//                .set(order.orderState, OrderState.CANCEL)
//                .where(order.store.id.eq(storeId), order.id.eq(orderId))
//                .execute();
        return null;
    }

    public Long updateOrderStateToComplete(Long storeId, Long orderId) {
//        return queryFactory
//                .update(order)
//                .set(order.orderState, OrderState.COMPLETE)
//                .where(order.store.id.eq(storeId), order.id.eq(orderId))
//                .execute();
        return null;
    }

    public List<DeliveryTrackingResponseDto> findDeliveryByDeliveryState(Long storeId) {
//        return queryFactory
//                .select(
//                        Projections.constructor(
//                                DeliveryTrackingResponseDto.class,
//                                delivery.id,
//                                order.orderTimestamp.orderTimestamp,
//                                order.orderType,
//                                delivery.deliveryCompany.company,
//                                order.paymentType,
//                                order.totalPrice,
//                                delivery.deliveryState
//                        )
//                )
//                .from(order)
//                .join(order.delivery, delivery)
//                .where(
//                        order.store.id.eq(storeId),
//                        order.orderType.eq(OrderType.DELIVERY),
//                        delivery.deliveryState.ne(DeliveryState.COMPLETE)
//                )
//                .fetch();
        return null;
    }

    //TODO fetch join 사용 고민하기
    public List<DeliveryDetailResponseDto> findDeliveryDetail(Long storeId, Long deliveryId) {
//        return queryFactory
//                .select(
//                        Projections.constructor(
//                                DeliveryDetailResponseDto.class,
//                                delivery.deliveryState,
//                                deliveryCompany.company,
//                                delivery.deliveryTips,
//                                deliveryDriver.driverName,
//                                deliveryDriver.driverPhone,
//                                customer.contact,
//                                customer.address,
//                                order.orderTimestamp.orderTimestamp,
//                                order.orderType
//                        )
//                )
//                .from(delivery)
//                .join(delivery.deliveryDriver, deliveryDriver)
//                .join(delivery.deliveryCompany, deliveryCompany)
//                .join(delivery.order, order)
//                .join(order.customer, customer)
//                .where(order.store.id.eq(storeId), delivery.id.eq(deliveryId))
//                .fetch();
        return null;
    }

    public Long findDeliveryCompanyIdByCompanyName(Company companyName) {
//        return queryFactory
//                .select(deliveryCompany.id)
//                .from(deliveryCompany)
//                .where(deliveryCompany.company.eq(companyName))
//                .fetchOne();
        return null;
    }
}
