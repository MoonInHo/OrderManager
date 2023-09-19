package com.mooninho.ordermanager.member.infrastructure.repository;

import com.mooninho.ordermanager.member.domain.entity.Member;
import com.mooninho.ordermanager.member.domain.vo.Phone;
import com.mooninho.ordermanager.member.domain.vo.UserId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.mooninho.ordermanager.member.domain.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean isUserIdExist(UserId userId) {
        return queryFactory
                .selectOne()
                .from(member)
                .where(member.userId.eq(userId))
                .fetchFirst() != null;
    }

    @Override
    public boolean isPhoneExist(Phone phone) {
        return queryFactory
                .selectOne()
                .from(member)
                .where(member.phone.eq(phone))
                .fetchFirst() != null;
    }

    @Override
    public Optional<Member> findByUserId(UserId userId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(member)
                .where(member.userId.eq(userId))
                .fetchOne()
        );
    }
}
