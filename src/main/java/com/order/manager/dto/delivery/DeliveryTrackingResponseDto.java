package com.order.manager.dto.delivery;

import com.order.manager.enums.state.DeliveryState;
import com.order.manager.enums.type.OrderType;
import com.order.manager.enums.type.PaymentType;
import com.order.manager.domain.wrapper.delivery.Company;
import com.order.manager.domain.wrapper.order.TotalPrice;
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
