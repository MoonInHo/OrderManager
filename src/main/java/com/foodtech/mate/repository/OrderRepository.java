package com.foodtech.mate.repository;

import com.foodtech.mate.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Store, Long> {
}
