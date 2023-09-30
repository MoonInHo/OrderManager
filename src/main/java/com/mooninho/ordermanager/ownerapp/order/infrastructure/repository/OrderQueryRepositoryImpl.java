package com.mooninho.ordermanager.ownerapp.order.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.delivery.domain.enums.DeliveryStatus;
import com.mooninho.ordermanager.ownerapp.delivery.infrastructure.dto.response.GetInProgressDeliveryOrdersResponseDto;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.OrderStatus;
import com.mooninho.ordermanager.ownerapp.order.infrastructure.dto.response.GetCompleteOrderResponseDto;
import com.mooninho.ordermanager.ownerapp.order.infrastructure.dto.response.GetOrderDetailResponseDto;
import com.mooninho.ordermanager.ownerapp.order.infrastructure.dto.response.GetPreparingOrderResponseDto;
import com.mooninho.ordermanager.ownerapp.order.infrastructure.dto.response.GetWaitingOrderResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.mooninho.ordermanager.ownerapp.customer.domain.entity.QCustomer.customer;
import static com.mooninho.ordermanager.ownerapp.delivery.domain.entity.QDelivery.delivery;
import static com.mooninho.ordermanager.ownerapp.deliverycompany.domain.entity.QDeliveryCompany.deliveryCompany;
import static com.mooninho.ordermanager.ownerapp.order.domain.entity.QOrder.order;
import static com.mooninho.ordermanager.ownerapp.store.domain.entity.QStore.store;

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
                                // 주문 경과시간 추가
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
                                Expressions.asNumber(orderId).as("id"),
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

    @Override
    public List<GetInProgressDeliveryOrdersResponseDto> getInProgressDeliveryOrders(Long storeId) {

        return queryFactory
                .select(
                        Projections.fields(
                                GetInProgressDeliveryOrdersResponseDto.class,
                                order.id,
                                order.timestamp.timestamp,
                                order.orderType,
                                // 배달 경과시간 추가 -> 배달 시작 시점에 타임스탬프를 찍고 조회 시점에 배달 시점과 현재 시점의 시간 차를 계산
                                delivery.deliveryCompany.companyName.companyName,
                                order.paymentType,
                                order.totalPrice.totalPrice,
                                delivery.deliveryStatus
                        )
                )
                .from(order)
                .join(order.delivery, delivery)
                .join(delivery.deliveryCompany, deliveryCompany)
                .where(
                        store.id.eq(storeId),
                        delivery.deliveryStatus.ne(DeliveryStatus.COMPLETE)
                )
                .fetch();
    }
}
