package com.mooninho.ordermanager.ownerapp.order.application.strategy;

import com.mooninho.ordermanager.ownerapp.exception.exception.global.InvalidRequestException;
import com.mooninho.ordermanager.ownerapp.order.domain.enums.DeliveryAppType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCancelStrategyFactory {

    private final BaeminOrderCancellationStrategy baeminOrderCancellationStrategy;
    private final BaeminOneOrderCancelationStrategy baeminOneOrderCancelationStrategy;

    public OrderCancelStrategy getStrategy(DeliveryAppType deliveryAppType) {
        return switch (deliveryAppType) {
            case BAEMIN -> baeminOrderCancellationStrategy;
            case BAEMIN_ONE -> baeminOneOrderCancelationStrategy;
            default -> throw new InvalidRequestException();
        };
    }
}
