package com.mooninho.ordermanager.임시.dto.deliveryDriver;

import com.mooninho.ordermanager.임시.domain.entity.DeliveryCompany;
import com.mooninho.ordermanager.임시.domain.entity.DeliveryDriver;
import com.mooninho.ordermanager.임시.domain.wrapper.delivery.DriverName;
import com.mooninho.ordermanager.임시.domain.wrapper.delivery.DriverPhone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDriverDto {

    private String driverName;
    private String driverPhone;
    private Long deliveryCompanyId;

    public DeliveryDriver toEntity() {
        return DeliveryDriver.createDeilveryDriver(
                DriverName.of(driverName),
                DriverPhone.of(driverPhone),
                DeliveryCompany.createKeyValue(deliveryCompanyId)
        );
    }
}
