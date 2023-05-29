package com.foodtech.mate.dto.order;

import com.foodtech.mate.enums.state.OrderState;
import com.foodtech.mate.enums.type.OrderType;
import com.foodtech.mate.enums.type.PaymentType;
import com.foodtech.mate.domain.wrapper.order.TotalPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    private OrderState orderState;
    private OrderType orderType;
    private PaymentType paymentType;
}
