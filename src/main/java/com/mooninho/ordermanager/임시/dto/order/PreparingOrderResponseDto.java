package com.mooninho.ordermanager.임시.dto.order;

import com.mooninho.ordermanager.order.domain.enums.OrderStatus;
import com.mooninho.ordermanager.order.domain.enums.OrderType;
import com.mooninho.ordermanager.order.domain.enums.PaymentType;
import com.mooninho.ordermanager.order.domain.vo.TotalPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PreparingOrderResponseDto {

    private Long orderId;
    private LocalDateTime orderTimeStamp;
    private String menuName;
    private TotalPrice totalPrice;
    private String customerInfo;
//    private List<OrderDetailsResponseDto> orderDetail;
    private OrderStatus orderState;
    private OrderType orderType;
    private PaymentType paymentType;
}
