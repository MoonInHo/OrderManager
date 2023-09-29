package com.mooninho.ordermanager.ownerapp.customer.domain.repository;

import com.mooninho.ordermanager.ownerapp.customer.domain.entity.Customer;
import com.mooninho.ordermanager.ownerapp.customer.infrastructure.repository.CustomerQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerQueryRepository {
}
