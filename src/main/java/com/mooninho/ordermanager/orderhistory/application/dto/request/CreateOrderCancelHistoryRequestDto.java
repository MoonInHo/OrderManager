package com.mooninho.ordermanager.orderhistory.application.dto.request;

import com.mooninho.ordermanager.order.domain.entity.Order;
import com.mooninho.ordermanager.orderhistory.domain.entity.OrderCancelHistory;
import com.mooninho.ordermanager.orderhistory.domain.vo.CancelReason;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateOrderCancelHistoryRequestDto {

    private String cancelReason;

    public OrderCancelHistory toEntity(Long orderId) {
        return OrderCancelHistory.createOrderCancelHistory(
                CancelReason.of(cancelReason),
                Order.createKeyObject(orderId)
        );
    }
}
