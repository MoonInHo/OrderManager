package com.foodtech.mate.service;

import com.foodtech.mate.domain.dto.delivery.DeliveryDto;
import com.foodtech.mate.domain.dto.delivery.RequestDeliveryDto;
import com.foodtech.mate.domain.entity.Delivery;
import com.foodtech.mate.domain.entity.DeliveryCompany;
import com.foodtech.mate.domain.entity.Order;
import com.foodtech.mate.domain.state.DeliveryState;
import com.foodtech.mate.domain.state.OrderState;
import com.foodtech.mate.domain.wrapper.delivery.Company;
import com.foodtech.mate.repository.DeliveryQueryRepository;
import com.foodtech.mate.repository.DeliveryRepository;
import com.foodtech.mate.repository.OrderQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final OrderQueryRepository orderQueryRepository;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryQueryRepository deliveryQueryRepository;

    @Transactional
    public void createDeliveryInfo(RequestDeliveryDto requestDeliveryDto, Company companyName) {

        Long orderId = requestDeliveryDto.getOrderId();
        Integer deliveryTips = requestDeliveryDto.getDeliveryTips();

        Order foundOrder = orderQueryRepository.findOrderByOrderId(orderId);
        if (!foundOrder.getOrderState().equals(OrderState.READY)) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다");
        }

        DeliveryCompany deliveryCompany = deliveryQueryRepository.findDeliveryCompanyByCompanyName(companyName);
        Delivery delivery = DeliveryDto.toEntity(foundOrder, deliveryCompany, deliveryTips);

        deliveryRepository.save(delivery);
    }

    @Transactional
    public Long deliveryDriverAssignment(Long deliveryId, Long deliveryDriverId) {

        Delivery foundDelivery = deliveryQueryRepository.findDeliveryByDeliveryId(deliveryId);
        if (!foundDelivery.getDeliveryState().equals(DeliveryState.WAITING)) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다");
        }
        Long driverCompanyId = deliveryQueryRepository.findDeliveryDriverCompanyIdByDeliveryDriverId(deliveryDriverId);
        if (!driverCompanyId.equals(foundDelivery.getDeliveryCompany().getId())) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다.");
        }

        return deliveryQueryRepository.updateDeliveryState(deliveryId, deliveryDriverId);
    }
}
