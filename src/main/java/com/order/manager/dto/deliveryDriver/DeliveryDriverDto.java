package com.order.manager.dto.deliveryDriver;

import com.order.manager.domain.entity.DeliveryCompany;
import com.order.manager.domain.entity.DeliveryDriver;
import com.order.manager.domain.wrapper.delivery.DriverName;
import com.order.manager.domain.wrapper.delivery.DriverPhone;
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
