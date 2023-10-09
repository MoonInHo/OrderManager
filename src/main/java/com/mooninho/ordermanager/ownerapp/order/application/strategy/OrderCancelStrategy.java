package com.mooninho.ordermanager.ownerapp.order.application.strategy;

public interface OrderCancelStrategy {

    boolean checkOrderCancellationEligibility(Long deliveryId);
}
