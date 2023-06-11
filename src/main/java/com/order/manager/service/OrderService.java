package com.order.manager.service;

import com.order.manager.domain.entity.Delivery;
import com.order.manager.domain.wrapper.delivery.Company;
import com.order.manager.dto.delivery.*;
import com.order.manager.dto.order.CompletedOrderResponseDto;
import com.order.manager.dto.order.OrderTypeResponseDto;
import com.order.manager.dto.order.PreparingOrderResponseDto;
import com.order.manager.dto.order.WaitingOrderResponseDto;
import com.order.manager.enums.state.OrderState;
import com.order.manager.enums.type.OrderType;
import com.order.manager.exception.exception.ChangeOrderStatusException;
import com.order.manager.exception.exception.delivery.EmptyDeliveryException;
import com.order.manager.exception.exception.delivery.EmptyDeliveryListException;
import com.order.manager.exception.exception.delivery.NotDeliveryException;
import com.order.manager.exception.exception.delivery.NotReadyException;
import com.order.manager.exception.exception.order.EmptyOrderListException;
import com.order.manager.exception.exception.order.InvalidOrderTypeException;
import com.order.manager.repository.DeliveryRepository;
import com.order.manager.repository.OrderQueryRepository;
import com.order.manager.repository.StoreQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.order.manager.domain.wrapper.delivery.Company.findByCompanyName;
import static com.order.manager.util.account.LoggedInUserinfoProvider.loggedInUserKeyFetcher;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final DeliveryRepository deliveryRepository;
    private final StoreQueryRepository storeQueryRepository;
    private final OrderQueryRepository orderQueryRepository;

    @Transactional
    public Long findStoreId() {
        return storeQueryRepository.findStoreIdByUserKey(loggedInUserKeyFetcher());
    }

    @Transactional
    public List<WaitingOrderResponseDto> findWaitingOrders(Long storeId) {

        List<WaitingOrderResponseDto> waitingOrder = orderQueryRepository.findWaitingOrder(storeId);
        if (isEmptyWaitingOrder(waitingOrder)) {
            throw new EmptyOrderListException();
        }
        return waitingOrder;
    }

    @Transactional
    public List<PreparingOrderResponseDto> findPreparingOrders(Long storeId) {

        List<PreparingOrderResponseDto> preparingOrder = orderQueryRepository.findPreparingOrder(storeId);
        if (isEmptyPreparingOrder(preparingOrder)) {
            throw new EmptyOrderListException();
        }
        return preparingOrder;
    }

    @Transactional
    public List<CompletedOrderResponseDto> completeOrdersLookup(Long storeId) {

        List<CompletedOrderResponseDto> completeOrder = orderQueryRepository.findCompleteOrder(storeId);
        if (isEmptyCompleteOrder(completeOrder)) {
            throw new EmptyOrderListException();
        }
        return completeOrder;
    }

    @Transactional
    public OrderState findOrderState(Long orderId, Long storeId) {

        OrderState orderState = orderQueryRepository.findOrderStateByOrderId(orderId, storeId);
        if (orderState == null) {
            throw new EmptyOrderListException();
        }
        return orderState;
    }

    @Transactional
    public void changeOrderState(Long orderId, Long storeId, OrderState orderStateCode) {

        Long updatedRow = orderQueryRepository.updateOrderState(orderId, storeId, orderStateCode);
        if (updatedRow == 0) {
            throw new ChangeOrderStatusException();
        }
    }

    @Transactional
    public void changeOrderStateToPickUp(Long orderId, Long storeId, OrderState orderStateCode) {

        OrderType orderType = orderQueryRepository.findOrderTypeByOrderId(orderId);
        if (isNotTogo(orderType)) {
            throw new InvalidOrderTypeException();
        }
        orderQueryRepository.updateOrderState(orderId, storeId, orderStateCode);
    }

    @Transactional
    public void createDeliveryInfo(Long orderId, Long storeId, DeliveryRequestDto requestDeliveryDto) {

        String inputCompanyName = requestDeliveryDto.getCompanyName();
        Integer deliveryTips = requestDeliveryDto.getDeliveryTips();

        Company companyName = findByCompanyName(inputCompanyName);

        OrderTypeResponseDto orderTypes = orderQueryRepository.findOrderTypeByOrderIdAndStoreId(orderId, storeId);
        orderTypeValidation(orderTypes);

        Long deliveryCompanyId = orderQueryRepository.findDeliveryCompanyIdByCompanyName(companyName);
        Delivery delivery = DeliveryDto.toEntity(orderId, deliveryCompanyId, deliveryTips);

        deliveryRepository.save(delivery);
    }



    @Transactional
    public List<DeliveryTrackingResponseDto> lookupInProgressDelivery(Long storeId) {

        List<DeliveryTrackingResponseDto> fetchedInProgressDeliveryList = orderQueryRepository.findDeliveryByDeliveryState(storeId);
        if (isEmptyInProgressDelivery(fetchedInProgressDeliveryList)) {
            throw new EmptyDeliveryListException();
        }
        return fetchedInProgressDeliveryList;
    }

    @Transactional
    public List<DeliveryDetailResponseDto> deliveryDetailLookup(Long storeId, Long deliveryId) {

        List<DeliveryDetailResponseDto> deliveryInfo = orderQueryRepository.findDeliveryDetail(storeId, deliveryId);
        if (isEmptyDeliveryDetail(deliveryInfo)) {
            throw new EmptyDeliveryException();
        }
        return deliveryInfo;
    }

    private boolean isEmptyWaitingOrder(List<WaitingOrderResponseDto> waitingOrder) {
        return waitingOrder.isEmpty();
    }

    private boolean isEmptyCompleteOrder(List<CompletedOrderResponseDto> completeOrder) {
        return completeOrder.isEmpty();
    }

    private boolean isEmptyPreparingOrder(List<PreparingOrderResponseDto> preparingOrder) {
        return preparingOrder.isEmpty();
    }

    private boolean isEmptyInProgressDelivery(List<DeliveryTrackingResponseDto> fetchedInProgressDeliveryList) {
        return fetchedInProgressDeliveryList.isEmpty();
    }

    private boolean isEmptyDeliveryDetail(List<DeliveryDetailResponseDto> deliveryInfo) {
        return deliveryInfo.isEmpty();
    }

    private boolean isNotTogo(OrderType orderType) {
        return !orderType.equals(OrderType.TOGO);
    }

    private boolean isNotDelivery(OrderType orderType) {
        return !orderType.equals(OrderType.DELIVERY);
    }

    private boolean isNotReady(OrderState orderState) {
        return !orderState.equals(OrderState.READY);
    }

    private void orderTypeValidation(OrderTypeResponseDto orderDivision) {
        if (orderDivision == null) {
            throw new EmptyOrderListException();
        }
        if (isNotDelivery(orderDivision.getOrderType())) {
            throw new NotDeliveryException();
        }
        if (isNotReady(orderDivision.getOrderState())) {
            throw new NotReadyException();
        }
    }
}
