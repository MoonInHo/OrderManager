package com.mooninho.ordermanager.ownerapp.member.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.member.domain.entity.Member;
import com.mooninho.ordermanager.ownerapp.member.domain.vo.Phone;
import com.mooninho.ordermanager.ownerapp.member.domain.vo.Username;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.mooninho.ordermanager.ownerapp.member.domain.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean isUsernameExist(Username username) {
        return queryFactory
                .selectOne()
                .from(member)
                .where(member.username.eq(username))
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
    public Optional<Member> findByUsername(Username username) {
        Member owner = queryFactory
                .selectFrom(member)
                .where(member.username.eq(username))
                .fetchOne();

        return Optional.ofNullable(owner);
    }

    @Override
    public Long getMemberId(Username username) {
        return queryFactory
                .select(member.id)
                .from(member)
                .where(member.username.eq(username))
                .fetchOne();
    }
}
