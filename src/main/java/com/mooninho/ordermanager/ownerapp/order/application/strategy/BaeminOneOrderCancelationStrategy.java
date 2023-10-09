package com.mooninho.ordermanager.ownerapp.order.application.strategy;

import org.springframework.stereotype.Component;

@Component
public class BaeminOneOrderCancelationStrategy implements OrderCancelStrategy {

    @Override
    public boolean checkOrderCancellationEligibility(Long deliveryId) {
        return false;
    }
}
