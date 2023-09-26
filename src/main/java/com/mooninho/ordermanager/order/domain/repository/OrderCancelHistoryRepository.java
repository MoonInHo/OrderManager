package com.mooninho.ordermanager.order.domain.repository;

import com.mooninho.ordermanager.order.domain.entity.OrderCancelHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCancelHistoryRepository extends JpaRepository<OrderCancelHistory, Long> {
}
