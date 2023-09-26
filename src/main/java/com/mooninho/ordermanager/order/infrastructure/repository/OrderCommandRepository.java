package com.mooninho.ordermanager.order.infrastructure.repository;

public interface OrderCommandRepository {

    void changeOrderToPreparing(Long orderId);

    void changeOrderToReady(Long orderId);

    void changeOrderToCancel(Long orderId);
}
