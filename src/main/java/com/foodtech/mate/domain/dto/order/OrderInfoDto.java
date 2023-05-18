package com.foodtech.mate.domain.dto.order;

import com.foodtech.mate.domain.wrapper.order.CustomerRequest;
import com.querydsl.core.types.Expression;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDto {

    private Expression<CustomerRequest> customerRequest;
    private List<OrderDetailDto> orderDetailLists;

}
