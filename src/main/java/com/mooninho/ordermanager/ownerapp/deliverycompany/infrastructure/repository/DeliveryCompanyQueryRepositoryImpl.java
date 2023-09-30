package com.mooninho.ordermanager.ownerapp.deliverycompany.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.deliverycompany.domain.entity.QDeliveryCompany;
import com.mooninho.ordermanager.ownerapp.deliverycompany.domain.vo.CompanyName;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.mooninho.ordermanager.ownerapp.deliverycompany.domain.entity.QDeliveryCompany.deliveryCompany;

@Repository
@RequiredArgsConstructor
public class DeliveryCompanyQueryRepositoryImpl implements DeliveryCompanyQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean isCompanyNameExist(CompanyName companyName) {
        return queryFactory
                .selectOne()
                .from(deliveryCompany)
                .where(deliveryCompany.companyName.eq(companyName))
                .fetchFirst() != null;
    }
}
