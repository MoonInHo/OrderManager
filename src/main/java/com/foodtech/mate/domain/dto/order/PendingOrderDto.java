package com.foodtech.mate.domain.dto.order;

import com.foodtech.mate.domain.wrapper.order.CustomerRequest;
import com.foodtech.mate.domain.wrapper.order.TotalPrice;
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
    private String customerInfo;
    private CustomerRequest customerRequest;
    private String orderState;
    private String orderType;
    private String paymentType;
}
