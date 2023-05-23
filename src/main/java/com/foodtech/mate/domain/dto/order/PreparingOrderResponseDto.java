package com.foodtech.mate.domain.dto.order;

import com.foodtech.mate.domain.state.OrderState;
import com.foodtech.mate.domain.state.OrderType;
import com.foodtech.mate.domain.state.PaymentType;
import com.foodtech.mate.domain.wrapper.order.TotalPrice;
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
//    private List<OrderDetail> orderDetail;
    private OrderState orderState;
    private OrderType orderType;
    private PaymentType paymentType;
}
