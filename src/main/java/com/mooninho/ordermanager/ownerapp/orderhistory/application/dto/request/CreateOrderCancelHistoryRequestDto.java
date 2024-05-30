package com.mooninho.ordermanager.ownerapp.orderhistory.application.dto.request;

import com.mooninho.ordermanager.ownerapp.order.domain.entity.Order;
import com.mooninho.ordermanager.ownerapp.orderhistory.domain.entity.OrderCancelHistory;
import com.mooninho.ordermanager.ownerapp.orderhistory.domain.vo.CancelReason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderCancelHistoryRequestDto {

    private String cancelReason;

    public OrderCancelHistory toEntity(Long orderId) {
        return OrderCancelHistory.createOrderCancelHistory(
                CancelReason.of(cancelReason),
                Order.createKeyObject(orderId)
        );
    }
}
