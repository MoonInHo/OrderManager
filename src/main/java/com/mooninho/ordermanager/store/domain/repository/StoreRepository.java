package com.mooninho.ordermanager.store.domain.repository;

import com.mooninho.ordermanager.store.domain.entity.Store;
import com.mooninho.ordermanager.store.infrastructure.repository.StoreQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreQueryRepository {
}
