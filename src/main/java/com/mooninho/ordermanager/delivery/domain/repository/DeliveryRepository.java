package com.mooninho.ordermanager.delivery.domain.repository;

import com.mooninho.ordermanager.delivery.domain.entity.Delivery;
import com.mooninho.ordermanager.delivery.infrastructure.repository.DeliveryQueryRepository;
import com.mooninho.ordermanager.delivery.infrastructure.repository.DeliveryQueryRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>, DeliveryQueryRepository {
}
