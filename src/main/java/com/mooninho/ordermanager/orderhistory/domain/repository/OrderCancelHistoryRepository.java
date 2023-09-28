package com.mooninho.ordermanager.orderhistory.domain.repository;

import com.mooninho.ordermanager.orderhistory.domain.entity.OrderCancelHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCancelHistoryRepository extends JpaRepository<OrderCancelHistory, Long> {
}
