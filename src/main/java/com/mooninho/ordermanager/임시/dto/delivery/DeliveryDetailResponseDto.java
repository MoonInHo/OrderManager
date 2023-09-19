package com.mooninho.ordermanager.임시.dto.delivery;

import com.mooninho.ordermanager.임시.domain.wrapper.customer.Address;
import com.mooninho.ordermanager.임시.domain.wrapper.customer.Contact;
import com.mooninho.ordermanager.임시.domain.wrapper.delivery.Company;
import com.mooninho.ordermanager.임시.domain.wrapper.delivery.DeliveryTips;
import com.mooninho.ordermanager.임시.domain.wrapper.delivery.DriverName;
import com.mooninho.ordermanager.임시.domain.wrapper.delivery.DriverPhone;
import com.mooninho.ordermanager.임시.enums.state.DeliveryState;
import com.mooninho.ordermanager.임시.enums.type.OrderType;
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
