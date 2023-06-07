package com.order.manager.dto.delivery;

import com.order.manager.domain.wrapper.customer.Address;
import com.order.manager.domain.wrapper.customer.Contact;
import com.order.manager.domain.wrapper.delivery.Company;
import com.order.manager.domain.wrapper.delivery.DeliveryTips;
import com.order.manager.domain.wrapper.delivery.DriverName;
import com.order.manager.domain.wrapper.delivery.DriverPhone;
import com.order.manager.enums.state.DeliveryState;
import com.order.manager.enums.type.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDetailResponseDto {

    private DeliveryState deliveryState;
    private Company company;
    private DeliveryTips deliveryTips;
    private DriverName driverName;
    private DriverPhone driverPhone;
    private Contact contact;
    private Address address;
    private LocalDateTime orderTimeStamp;
    private OrderType orderType;
}
