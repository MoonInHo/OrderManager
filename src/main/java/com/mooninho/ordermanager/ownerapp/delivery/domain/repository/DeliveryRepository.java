package com.mooninho.ordermanager.ownerapp.delivery.domain.repository;

import com.mooninho.ordermanager.ownerapp.delivery.domain.entity.Delivery;
import com.mooninho.ordermanager.ownerapp.delivery.infrastructure.repository.DeliveryCommandRepository;
import com.mooninho.ordermanager.ownerapp.delivery.infrastructure.repository.DeliveryQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>, DeliveryQueryRepository, DeliveryCommandRepository {
}
