package com.mooninho.ordermanager.order.infrastructure.repository;

import com.mooninho.ordermanager.delivery.domain.enums.DeliveryStatus;
import com.mooninho.ordermanager.order.domain.enums.OrderStatus;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetCompleteOrderResponseDto;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetOrderDetailResponseDto;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetPreparingOrderResponseDto;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetWaitingOrderResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
                                order.timestamp.timestamp,
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
                                order.timestamp.timestamp,
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
                                order.timestamp.timestamp,
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

    @Override
    public Optional<GetOrderDetailResponseDto> getOrderDetail(Long orderId) {

        return Optional.ofNullable(queryFactory
                .select(
                        Projections.fields(
                                GetOrderDetailResponseDto.class,
                                Expressions.asNumber(orderId).as("orderId"),
                                order.timestamp.timestamp,
                                //orderDetails 를 List 형식으로 표현
                                order.totalPrice.totalPrice,
                                customer.address.address,
                                customer.address.addressDetail,
                                customer.contact.contact,
                                order.customerRequest.customerRequest,
                                order.orderType,
                                order.paymentType,
                                order.orderStatus // 배달 도메인 조회 로직도 추가
                        )
                )
                .from(order)
                .join(order.customer, customer)
                .where(order.id.eq(orderId))
                .fetchOne()
        );
    }

    @Override
    public Optional<OrderStatus> getOrderStatus(Long orderId) {
        return Optional.ofNullable(queryFactory
                .select(order.orderStatus)
                .from(order)
                .where(order.id.eq(orderId))
                .fetchOne()
        );
    }

    public DeliveryStatus findDeliveryState(Long storeId, Long orderId) {
//        return queryFactory
//                .select(order.delivery.deliveryState)
//                .from(order)
//                .join(order.delivery, delivery)
//                .where(order.store.id.eq(storeId), order.id.eq(orderId))
//                .fetchOne();
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

//    public List<DeliveryTrackingResponseDto> findDeliveryByDeliveryState(Long storeId) {
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
//        return null;
//    }

    //TODO fetch join 사용 고민하기
//    public List<DeliveryDetailResponseDto> findDeliveryDetail(Long storeId, Long deliveryId) {
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
//        return null;
//    }

//    public Long findDeliveryCompanyIdByCompanyName(Company companyName) {
//        return queryFactory
//                .select(deliveryCompany.id)
//                .from(deliveryCompany)
//                .where(deliveryCompany.company.eq(companyName))
//                .fetchOne();
//        return null;
//    }
}
