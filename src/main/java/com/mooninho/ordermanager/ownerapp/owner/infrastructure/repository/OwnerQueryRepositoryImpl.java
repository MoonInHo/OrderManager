package com.mooninho.ordermanager.ownerapp.owner.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.owner.domain.entity.Owner;
import com.mooninho.ordermanager.ownerapp.owner.domain.entity.QOwner;
import com.mooninho.ordermanager.ownerapp.owner.domain.vo.Phone;
import com.mooninho.ordermanager.ownerapp.owner.domain.vo.Username;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.mooninho.ordermanager.ownerapp.owner.domain.entity.QOwner.owner;

@Repository
@RequiredArgsConstructor
public class OwnerQueryRepositoryImpl implements OwnerQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean isUsernameExist(Username username) {
        return queryFactory
                .selectOne()
                .from(owner)
                .where(owner.username.eq(username))
                .fetchFirst() != null;
    }

    @Override
    public boolean isPhoneExist(Phone phone) {
        return queryFactory
                .selectOne()
                .from(owner)
                .where(owner.phone.eq(phone))
                .fetchFirst() != null;
    }

    @Override
    public Optional<Owner> findByUsername(Username username) {
        Owner owner = queryFactory
                .selectFrom(QOwner.owner)
                .where(QOwner.owner.username.eq(username))
                .fetchOne();

        return Optional.ofNullable(owner);
    }

    @Override
    public Optional<Long> getOwnerId(Username username) {
        Long ownerId = queryFactory
                .select(owner.id)
                .from(owner)
                .where(owner.username.eq(username))
                .fetchOne();

        return Optional.ofNullable(ownerId);
    }
}
