package com.mooninho.ordermanager.ownerapp.order.application.strategy;

import org.springframework.stereotype.Component;

@Component
public class BaeminOrderCancellationStrategy implements OrderCancelStrategy {

    /**
     * 배달 취소 정책 및 환불 정책 추가 가능
     */
    @Override
    public boolean checkOrderCancellationEligibility(Long deliveryId) {
        return deliveryId == null;
    }
}
