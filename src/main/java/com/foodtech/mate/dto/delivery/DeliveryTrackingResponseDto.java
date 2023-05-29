package com.foodtech.mate.dto.delivery;

import com.foodtech.mate.enums.state.DeliveryState;
import com.foodtech.mate.enums.type.OrderType;
import com.foodtech.mate.enums.type.PaymentType;
import com.foodtech.mate.domain.wrapper.delivery.Company;
import com.foodtech.mate.domain.wrapper.order.TotalPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryTrackingResponseDto {

    private Long deliveryId;
    private LocalDateTime orderTimeStamp;
    private OrderType orderType;
    private Company company;
    private PaymentType paymentType;
    private TotalPrice totalPrice;
    private DeliveryState deliveryState;
}
