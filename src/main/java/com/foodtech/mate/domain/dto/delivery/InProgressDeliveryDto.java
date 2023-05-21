package com.foodtech.mate.domain.dto.delivery;

import com.foodtech.mate.domain.state.DeliveryState;
import com.foodtech.mate.domain.state.OrderType;
import com.foodtech.mate.domain.state.PaymentType;
import com.foodtech.mate.domain.wrapper.delivery.Company;
import com.foodtech.mate.domain.wrapper.order.TotalPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InProgressDeliveryDto {

    private Long deliveryId;
    private LocalDateTime orderTimeStamp;
    private OrderType orderType;
    private Company company;
    private PaymentType paymentType;
    private TotalPrice totalPrice;
    private DeliveryState deliveryState;
}
