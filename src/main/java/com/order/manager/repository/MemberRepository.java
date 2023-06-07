package com.order.manager.repository;

import com.order.manager.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Account, Long> {
}
