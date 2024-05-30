package com.mooninho.ordermanager.ownerapp.orderhistory.application.service;

import com.mooninho.ordermanager.ownerapp.orderhistory.application.dto.request.CreateOrderCancelHistoryRequestDto;
import com.mooninho.ordermanager.ownerapp.orderhistory.domain.repository.OrderCancelHistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("[유닛 테스트] - 주문 히스토리 도메인")
class OrderHistoryServiceTest {

    @Mock
    private OrderCancelHistoryRepository orderCancelHistoryRepository;

    @InjectMocks
    private OrderHistoryService orderHistoryService;

    @Test
    @DisplayName("주문 히스토리 생성 - 히스토리 생성 시 주문 히스토리 저장")
    void properInfo_createOrderCancelHistory_saveOrderCancelHistory() {
        //given
        CreateOrderCancelHistoryRequestDto createOrderCancelHistoryRequestDto =
                new CreateOrderCancelHistoryRequestDto("고객 변심으로 인한 주문 취소");

        //when
        orderHistoryService.createCancelHistory(1L, createOrderCancelHistoryRequestDto);

        //then
        verify(orderCancelHistoryRepository, times(1)).save(any());
    }
}