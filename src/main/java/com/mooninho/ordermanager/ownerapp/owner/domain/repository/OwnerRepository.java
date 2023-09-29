package com.mooninho.ordermanager.ownerapp.owner.domain.repository;

import com.mooninho.ordermanager.ownerapp.owner.domain.entity.Owner;
import com.mooninho.ordermanager.ownerapp.owner.infrastructure.repository.OwnerQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long>, OwnerQueryRepository {
}
