package com.mooninho.ordermanager.order.infrastructure.repository;

import com.mooninho.ordermanager.order.infrastructure.dto.response.GetCompleteOrderResponseDto;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetPreparingOrderResponseDto;
import com.mooninho.ordermanager.order.infrastructure.dto.response.GetWaitingOrderResponseDto;

import java.util.List;

public interface OrderQueryRepository {

    List<GetWaitingOrderResponseDto> getWaitingOrders(Long storeId);

    List<GetPreparingOrderResponseDto> getPreparingOrders(Long storeId);

    List<GetCompleteOrderResponseDto> getCompleteOrders(Long storeId);
}
