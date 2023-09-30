package com.mooninho.ordermanager.ownerapp.deliverycompany.domain.repository;

import com.mooninho.ordermanager.ownerapp.deliverycompany.domain.entity.DeliveryCompany;
import com.mooninho.ordermanager.ownerapp.deliverycompany.infrastructure.repository.DeliveryCompanyQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryCompanyRepository extends JpaRepository<DeliveryCompany, Long>, DeliveryCompanyQueryRepository {
}
