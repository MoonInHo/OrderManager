package com.mooninho.ordermanager.ownerapp.order.infrastructure.repository;

public interface OrderCommandRepository {

    void changeOrderToPreparing(Long orderId);

    void changeOrderToReady(Long orderId);

    void changeOrderToCancel(Long orderId);

    void changeOrderToComplete(Long orderId);
}
