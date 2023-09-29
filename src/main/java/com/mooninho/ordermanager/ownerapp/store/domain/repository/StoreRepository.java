package com.mooninho.ordermanager.ownerapp.store.domain.repository;

import com.mooninho.ordermanager.ownerapp.store.infrastructure.repository.StoreQueryRepository;
import com.mooninho.ordermanager.ownerapp.store.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreQueryRepository {
}
