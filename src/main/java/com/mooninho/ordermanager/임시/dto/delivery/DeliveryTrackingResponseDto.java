package com.mooninho.ordermanager.임시.dto.delivery;

import com.mooninho.ordermanager.임시.enums.state.DeliveryState;
import com.mooninho.ordermanager.임시.enums.type.OrderType;
import com.mooninho.ordermanager.임시.enums.type.PaymentType;
import com.mooninho.ordermanager.임시.domain.wrapper.delivery.Company;
import com.mooninho.ordermanager.임시.domain.wrapper.order.TotalPrice;
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
