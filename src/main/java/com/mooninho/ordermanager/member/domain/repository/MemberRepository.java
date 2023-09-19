package com.mooninho.ordermanager.member.domain.repository;

import com.mooninho.ordermanager.member.domain.entity.Member;
import com.mooninho.ordermanager.member.infrastructure.repository.MemberCommandRepository;
import com.mooninho.ordermanager.member.infrastructure.repository.MemberQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository, MemberCommandRepository {
}
