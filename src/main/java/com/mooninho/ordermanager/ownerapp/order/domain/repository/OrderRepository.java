package com.mooninho.ordermanager.ownerapp.order.domain.repository;

import com.mooninho.ordermanager.ownerapp.order.domain.entity.Order;
import com.mooninho.ordermanager.ownerapp.order.infrastructure.repository.OrderCommandRepository;
import com.mooninho.ordermanager.ownerapp.order.infrastructure.repository.OrderQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderQueryRepository, OrderCommandRepository {
}
