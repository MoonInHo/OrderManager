package com.mooninho.ordermanager.order.infrastructure.repository;

import com.mooninho.ordermanager.order.infrastructure.dto.response.GetWaitingOrderResponseDto;

import java.util.List;

public interface OrderQueryRepository {

    List<GetWaitingOrderResponseDto> getWaitingOrders(Long storeId);
}
