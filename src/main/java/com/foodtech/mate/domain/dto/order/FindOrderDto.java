package com.foodtech.mate.domain.dto.order;

import com.foodtech.mate.domain.wrapper.menu.MenuName;
import com.foodtech.mate.domain.wrapper.order.CustomerRequest;
import com.foodtech.mate.domain.wrapper.order.Quantity;
import com.foodtech.mate.domain.wrapper.order.TotalMenuPrice;
import com.foodtech.mate.domain.wrapper.order.TotalPrice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindOrderDto {

    private Long orderId;
    private LocalDateTime orderTimestamp;
    private String menuNames;
    private TotalPrice totalPrice;
    private String customerInfo;
    private CustomerRequest customerRequest;
    private String orderState;
    private String orderType;
    private String paymentType;
}
