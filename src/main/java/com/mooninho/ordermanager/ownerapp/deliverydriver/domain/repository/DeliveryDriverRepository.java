package com.mooninho.ordermanager.ownerapp.deliverydriver.domain.repository;

import com.mooninho.ordermanager.ownerapp.deliverydriver.domain.entity.DeliveryDriver;
import com.mooninho.ordermanager.ownerapp.deliverydriver.infrastructure.repository.DeliveryDriverQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryDriverRepository extends JpaRepository<DeliveryDriver, Long>, DeliveryDriverQueryRepository {
}
