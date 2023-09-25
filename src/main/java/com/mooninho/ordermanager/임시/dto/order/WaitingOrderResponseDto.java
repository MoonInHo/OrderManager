package com.mooninho.ordermanager.임시.dto.order;

import com.mooninho.ordermanager.order.domain.enums.OrderStatus;
import com.mooninho.ordermanager.order.domain.enums.OrderType;
import com.mooninho.ordermanager.order.domain.enums.PaymentType;
import com.mooninho.ordermanager.order.domain.vo.CustomerRequest;
import com.mooninho.ordermanager.order.domain.vo.TotalPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WaitingOrderResponseDto {

    private Long orderId;
    private LocalDateTime orderTimestamp;
    private String menuNames;
    private TotalPrice totalPrice;
    private String customerInfo;
    private CustomerRequest customerRequest;
    private OrderStatus orderState;
    private OrderType orderType;
    private PaymentType paymentType;
}
