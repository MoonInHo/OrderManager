package com.foodtech.mate.repository;

import com.foodtech.mate.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Account, Long> {
}
