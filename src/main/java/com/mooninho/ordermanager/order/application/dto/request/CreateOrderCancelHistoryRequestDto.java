package com.mooninho.ordermanager.order.application.dto.request;

import com.mooninho.ordermanager.order.domain.entity.Order;
import com.mooninho.ordermanager.order.domain.entity.OrderCancelHistory;
import com.mooninho.ordermanager.order.domain.vo.CancelReason;
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
