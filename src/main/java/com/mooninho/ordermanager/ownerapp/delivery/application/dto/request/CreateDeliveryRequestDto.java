package com.mooninho.ordermanager.ownerapp.delivery.application.dto.request;

import com.mooninho.ordermanager.ownerapp.delivery.domain.entity.Delivery;
import com.mooninho.ordermanager.ownerapp.delivery.domain.vo.DeliveryTips;
import com.mooninho.ordermanager.ownerapp.deliverycompany.domain.entity.DeliveryCompany;
import com.mooninho.ordermanager.ownerapp.order.domain.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDeliveryRequestDto {

    private Long deliveryCompanyId;
    private Integer deliveryTips;

    public Delivery toEntity(Long orderId) {
        return Delivery.createDelivery(
                Order.createKeyObject(orderId),
                DeliveryCompany.createKeyObject(deliveryCompanyId),
                DeliveryTips.of(deliveryTips)
        );
    }
}
