package com.foodtech.mate.service;

import com.foodtech.mate.domain.dto.delivery.DeliveryDto;
import com.foodtech.mate.domain.dto.delivery.DeliveryTrackingDto;
import com.foodtech.mate.domain.dto.delivery.RequestDeliveryDto;
import com.foodtech.mate.domain.entity.Delivery;
import com.foodtech.mate.domain.entity.DeliveryCompany;
import com.foodtech.mate.domain.entity.Order;
import com.foodtech.mate.domain.state.DeliveryState;
import com.foodtech.mate.domain.state.OrderState;
import com.foodtech.mate.domain.state.OrderType;
import com.foodtech.mate.domain.wrapper.delivery.Company;
import com.foodtech.mate.exception.exception.NoDeliveryException;
import com.foodtech.mate.repository.DeliveryQueryRepository;
import com.foodtech.mate.repository.DeliveryRepository;
import com.foodtech.mate.repository.OrderQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        if (!foundOrder.getOrderType().equals(OrderType.DELIVERY)) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다");
        }
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
            throw new IllegalArgumentException("올바르지 않은 입력입니다");
        }

        return deliveryQueryRepository.updateDeliveryStateAndDriverId(deliveryId, deliveryDriverId);
    }

    @Transactional
    public void deliveryPickUp(Long deliveryId, Long deliveryDriverId, DeliveryState deliveryState) {

        Delivery foundDelivery = deliveryQueryRepository.findDeliveryByDeliveryId(deliveryId);
        if (foundDelivery == null) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다");
        }
        if (!foundDelivery.getDeliveryDriver().getId().equals(deliveryDriverId)) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다");
        }

        if (!foundDelivery.getDeliveryState().equals(DeliveryState.DISPATCH)) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다");
        }

        deliveryQueryRepository.updateDeliveryState(deliveryId, deliveryState);
    }

    @Transactional
    public void deliveryComplete(Long deliveryId, Long deliveryDriverId, DeliveryState deliveryState) {

        Delivery foundDelivery = deliveryQueryRepository.findDeliveryByDeliveryId(deliveryId);
        if (foundDelivery == null) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다");
        }
        if (!foundDelivery.getDeliveryDriver().getId().equals(deliveryDriverId)) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다");
        }
        if (!foundDelivery.getDeliveryState().equals(DeliveryState.PICKUP)) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다");
        }

        Long orderId = foundDelivery.getOrder().getId();

        deliveryQueryRepository.updateDeliveryState(deliveryId, deliveryState);
        orderQueryRepository.updateOrderState(orderId, OrderState.COMPLETE);
    }

    public List<DeliveryTrackingDto> deliveryTracking(DeliveryState deliveryState) {

        List<DeliveryTrackingDto> fetchedInProgressDeliveryList = deliveryQueryRepository.findInDeliveryByDeliveryState(deliveryState);
        if (fetchedInProgressDeliveryList == null) {
            throw new NoDeliveryException("배달주문이 없습니다");
        }
        return fetchedInProgressDeliveryList;
    }
}
