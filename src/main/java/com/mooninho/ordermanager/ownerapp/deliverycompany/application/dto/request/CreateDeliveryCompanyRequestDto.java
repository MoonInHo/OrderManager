package com.mooninho.ordermanager.ownerapp.deliverycompany.application.dto.request;

import com.mooninho.ordermanager.ownerapp.deliverycompany.domain.entity.DeliveryCompany;
import com.mooninho.ordermanager.ownerapp.deliverycompany.domain.vo.CompanyName;
import com.mooninho.ordermanager.ownerapp.store.domain.entity.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateDeliveryCompanyRequestDto {

    private String companyName;

    public DeliveryCompany toEntity(Long storeId) {
        return DeliveryCompany.createDeliveryCompany(
                CompanyName.of(companyName),
                Store.createKeyObject(storeId)
        );
    }
}
