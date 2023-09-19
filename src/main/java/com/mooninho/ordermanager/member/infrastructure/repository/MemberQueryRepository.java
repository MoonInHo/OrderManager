package com.mooninho.ordermanager.member.infrastructure.repository;

import com.mooninho.ordermanager.member.domain.entity.Member;
import com.mooninho.ordermanager.member.domain.vo.Phone;
import com.mooninho.ordermanager.member.domain.vo.UserId;

import java.util.Optional;

public interface MemberQueryRepository {

    boolean isUserIdExist(UserId userId);

    boolean isPhoneExist(Phone phone);

    Optional<Member> findByUserId(UserId userId);
}
