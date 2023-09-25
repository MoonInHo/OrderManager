package com.mooninho.ordermanager.owner.domain.repository;

import com.mooninho.ordermanager.owner.domain.entity.Owner;
import com.mooninho.ordermanager.owner.infrastructure.repository.OwnerQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long>, OwnerQueryRepository {
}
