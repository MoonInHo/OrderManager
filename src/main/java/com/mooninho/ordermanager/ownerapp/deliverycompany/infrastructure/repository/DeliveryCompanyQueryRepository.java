package com.mooninho.ordermanager.ownerapp.deliverycompany.infrastructure.repository;

import com.mooninho.ordermanager.ownerapp.deliverycompany.domain.vo.CompanyName;

public interface DeliveryCompanyQueryRepository {

    boolean isCompanyNameExist(CompanyName companyName);
}
