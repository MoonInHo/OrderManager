package com.mooninho.ordermanager.ownerapp.menu.domain.repository;

import com.mooninho.ordermanager.ownerapp.menu.domain.entity.Menu;
import com.mooninho.ordermanager.ownerapp.menu.infrastructure.repository.MenuQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long>, MenuQueryRepository {
}
