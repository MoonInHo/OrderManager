package com.mooninho.ordermanager.ownerapp.member.domain.repository;

import com.mooninho.ordermanager.ownerapp.member.domain.entity.Member;
import com.mooninho.ordermanager.ownerapp.member.infrastructure.repository.MemberQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository {
}
