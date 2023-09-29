package com.mooninho.ordermanager.ownerapp.orderhistory.application.service;

import com.mooninho.ordermanager.ownerapp.orderhistory.application.dto.request.CreateOrderCancelHistoryRequestDto;
import com.mooninho.ordermanager.ownerapp.orderhistory.domain.repository.OrderCancelHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderHistoryService {

    private final OrderCancelHistoryRepository orderCancelHistoryRepository;

    @Transactional
    public void createCancelHistory(
            Long orderId,
            CreateOrderCancelHistoryRequestDto createOrderCancelHistoryRequestDto
    ) {
        orderCancelHistoryRepository.save(createOrderCancelHistoryRequestDto.toEntity(orderId));
    }
}
