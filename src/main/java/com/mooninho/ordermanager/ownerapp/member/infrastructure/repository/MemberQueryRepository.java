package com.mooninho.ordermanager.ownerapp.member.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.member.domain.entity.Member;
import com.mooninho.ordermanager.ownerapp.member.domain.vo.Phone;
import com.mooninho.ordermanager.ownerapp.member.domain.vo.Username;

import java.util.Optional;

public interface MemberQueryRepository {

    boolean isUsernameExist(Username username);

    boolean isPhoneExist(Phone phone);

    Optional<Member> findByUsername(Username username);

    Long getMemberId(Username username);
}
