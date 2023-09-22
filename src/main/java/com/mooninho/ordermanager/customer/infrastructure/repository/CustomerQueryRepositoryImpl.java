package com.mooninho.ordermanager.customer.infrastructure.repository;

import com.mooninho.ordermanager.customer.domain.vo.Contact;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.mooninho.ordermanager.임시.domain.entity.QCustomer.customer;

@Repository
@RequiredArgsConstructor
public class CustomerQueryRepositoryImpl implements CustomerQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean isExistContact(Contact contact) {
        return queryFactory
                .selectOne()
                .from(customer)
                .where(customer.contact.eq(contact))
                .fetchFirst() != null;
    }
}
