package com.foodtech.mate.domain.dto.delivery;

import com.foodtech.mate.domain.state.DeliveryState;
import com.foodtech.mate.domain.state.OrderType;
import com.foodtech.mate.domain.wrapper.customer.Contact;
import com.foodtech.mate.domain.wrapper.delivery.Company;
import com.foodtech.mate.domain.wrapper.delivery.DeliveryTips;
import com.foodtech.mate.domain.wrapper.delivery.DriverName;
import com.foodtech.mate.domain.wrapper.delivery.DriverPhone;
import com.foodtech.mate.domain.wrapper.customer.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryInfoResponseDto {

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
