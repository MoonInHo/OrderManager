package com.foodtech.mate.domain.dto.order;

import com.foodtech.mate.domain.state.OrderState;
import com.foodtech.mate.domain.state.OrderType;
import com.foodtech.mate.domain.state.PaymentType;
import com.foodtech.mate.domain.wrapper.order.CustomerRequest;
import com.foodtech.mate.domain.wrapper.order.OrderTimestamp;
import com.foodtech.mate.domain.wrapper.order.TotalPrice;
import com.foodtech.mate.domain.wrapper.store.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PendingOrderDto {

    private Long orderId;
    private LocalDateTime orderTimestamp;
    private String menuName;
    private TotalPrice totalPrice;
    private CustomerRequest customerRequest;
    private Address customerAddress;
    private String orderState;
    private String orderType;
    private String paymentType;
}
