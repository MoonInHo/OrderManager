package com.mooninho.ordermanager.ownerapp.order.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.delivery.infrastructure.dto.response.GetInProgressDeliveryOrdersResponseDto;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.DeliveryAppType;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.OrderStatus;
import com.mooninho.ordermanager.ownerapp.order.infrastructure.dto.response.*;

import java.util.List;
import java.util.Optional;

public interface OrderQueryRepository {

    List<GetWaitingOrderResponseDto> getWaitingOrders(Long storeId);

    List<GetPreparingOrderResponseDto> getPreparingOrders(Long storeId);

    List<GetCompletedOrderResponseDto> getCompleteOrders(Long storeId);

    Optional<GetOrderDetailResponseDto> getOrderDetail(Long orderId);

    Optional<OrderStatus> getOrderStatus(Long orderId);

    List<GetInProgressDeliveryOrdersResponseDto> getInProgressDeliveryOrders(Long storeId);

    Optional<GetDeliveryOrderResponseDto> getDeliveryOrder(Long storeId, Long deliveryId);

    DeliveryAppType getDeliveryAppType(Long orderId);
}
