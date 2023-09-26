package com.mooninho.ordermanager.order.infrastructure.repository;

import com.mooninho.ordermanager.order.domain.enums.OrderStatus;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetCompleteOrderResponseDto;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetPreparingOrderResponseDto;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetWaitingOrderResponseDto;

import java.util.List;
import java.util.Optional;

public interface OrderQueryRepository {

    List<GetWaitingOrderResponseDto> getWaitingOrders(Long storeId);

    List<GetPreparingOrderResponseDto> getPreparingOrders(Long storeId);

    List<GetCompleteOrderResponseDto> getCompleteOrders(Long storeId);

    Optional<OrderStatus> getOrderStatus(Long orderId);
}
