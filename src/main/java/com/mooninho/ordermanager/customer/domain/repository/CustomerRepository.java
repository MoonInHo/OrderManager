package com.mooninho.ordermanager.customer.domain.repository;

import com.mooninho.ordermanager.customer.domain.entity.Customer;
import com.mooninho.ordermanager.customer.infrastructure.repository.CustomerQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerQueryRepository {
}
