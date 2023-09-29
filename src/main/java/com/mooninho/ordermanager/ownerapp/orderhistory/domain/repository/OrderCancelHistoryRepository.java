package com.mooninho.ordermanager.ownerapp.orderhistory.domain.repository;

import com.mooninho.ordermanager.ownerapp.orderhistory.domain.entity.OrderCancelHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCancelHistoryRepository extends JpaRepository<OrderCancelHistory, Long> {
}
