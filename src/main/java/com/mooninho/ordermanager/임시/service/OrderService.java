package com.mooninho.ordermanager.임시.service;

import com.mooninho.ordermanager.임시.domain.entity.Delivery;
import com.mooninho.ordermanager.임시.domain.wrapper.delivery.Company;
import com.mooninho.ordermanager.임시.dto.delivery.DeliveryDetailResponseDto;
import com.mooninho.ordermanager.임시.dto.delivery.DeliveryDto;
import com.mooninho.ordermanager.임시.dto.delivery.DeliveryRequestDto;
import com.mooninho.ordermanager.임시.dto.delivery.DeliveryTrackingResponseDto;
import com.mooninho.ordermanager.임시.dto.order.CompletedOrderResponseDto;
import com.mooninho.ordermanager.임시.dto.order.OrderTypeResponseDto;
import com.mooninho.ordermanager.임시.dto.order.PreparingOrderResponseDto;
import com.mooninho.ordermanager.임시.dto.order.WaitingOrderResponseDto;
import com.mooninho.ordermanager.임시.enums.state.DeliveryState;
import com.mooninho.ordermanager.임시.enums.state.OrderState;
import com.mooninho.ordermanager.임시.enums.type.OrderType;
import com.mooninho.ordermanager.임시.repository.DeliveryRepository;
import com.mooninho.ordermanager.임시.repository.OrderQueryRepository;
import com.mooninho.ordermanager.임시.repository.StoreQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.mooninho.ordermanager.임시.domain.wrapper.delivery.Company.findByCompanyName;
import static com.mooninho.ordermanager.임시.util.account.LoggedInUserinfoProvider.loggedInUserKeyFetcher;

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
//        if (isEmptyWaitingOrder(waitingOrder)) {
//            throw new EmptyOrderListException();
//        }
        return waitingOrder;
    }

    @Transactional
    public List<PreparingOrderResponseDto> findPreparingOrders(Long storeId) {

        List<PreparingOrderResponseDto> preparingOrder = orderQueryRepository.findPreparingOrder(storeId);
//        if (isEmptyPreparingOrder(preparingOrder)) {
//            throw new EmptyOrderListException();
//        }
        return preparingOrder;
    }

    @Transactional
    public List<CompletedOrderResponseDto> completeOrdersLookup(Long storeId) {

        List<CompletedOrderResponseDto> completeOrder = orderQueryRepository.findCompleteOrder(storeId);
//        if (isEmptyCompleteOrder(completeOrder)) {
//            throw new EmptyOrderListException();
//        }
        return completeOrder;
    }

    @Transactional
    public void changeOrderStateToPreparing(Long storeId, Long orderId) {

        OrderState orderState = orderQueryRepository.findOrderStateByOrderId(storeId, orderId);
//        if (isNotWaiting(orderState)) {
//            throw new InvalidOrderStateException("주문 대기중 상태에서만 가능한 요청입니다.");
//        }

        Long updatedRow = orderQueryRepository.updateOrderStateToPreparing(storeId, orderId);
//        if (updatedRow == 0) {
//            throw new NotChangedOrderStateException();
//        }
    }

    @Transactional
    public void changeOrderStateToReady(Long storeId, Long orderId) {

        OrderState orderState = orderQueryRepository.findOrderStateByOrderId(storeId, orderId);
//        if (isNotPreparing(orderState)) {
//            throw new InvalidOrderStateException("주문 준비중 상태에서만 가능한 요청입니다.");
//        }

        Long updatedRow = orderQueryRepository.updateOrderStateToReady(storeId, orderId);
//        if (updatedRow == 0) {
//            throw new NotChangedOrderStateException();
//        }
    }

    @Transactional
    public void changeOrderStateToCancel(Long storeId, Long orderId) {

        OrderState orderState = orderQueryRepository.findOrderStateByOrderId(storeId, orderId);

//        if (isCancel(orderState)) {
//            throw new InvalidOrderStateException("이미 취소된 주문입니다.");
//        }

        Long updatedRow = orderQueryRepository.updateOrderStateToCancel(storeId, orderId);
//        if (updatedRow == 0) {
//            throw new NotChangedOrderStateException();
//        }
    }

    @Transactional
    public void changeOrderStateToComplete(Long storeId, Long orderId) {

        OrderType orderType = orderQueryRepository.findOrderTypeByOrderId(storeId, orderId);
//        if (isNotDelivery(orderType)) {
//            throw new InvalidOrderTypeException();
//        }

        DeliveryState deliveryState = orderQueryRepository.findDeliveryState(storeId, orderId);
//        if (incompleteDelivery(deliveryState)) {
//            throw new IncompleteDeliveryException();
//        }

        Long updatedRow = orderQueryRepository.updateOrderStateToComplete(storeId, orderId);
//        if (updatedRow == 0) {
//            throw new NotChangedOrderStateException();
//        }
    }

    @Transactional
    public void toPickup(Long storeId, Long orderId) {

        OrderTypeResponseDto orderTypes = orderQueryRepository.findOrderTypes(storeId, orderId);
//        if (isNotTogo(orderTypes)) {
//            throw new InvalidOrderTypeException();
//        }
//        if (isNotReady(orderTypes)) {
//            throw new InvalidOrderStateException("주문 준비완료 상태에서만 가능한 요청입니다.");
//        }

        Long updatedRow = orderQueryRepository.updateOrderStateToComplete(storeId, orderId);
//        if (updatedRow == 0) {
//            throw new NotChangedOrderStateException();
//        }
    }

    @Transactional
    public void createDeliveryInfo(Long orderId, Long storeId, DeliveryRequestDto requestDeliveryDto) {

        String inputCompanyName = requestDeliveryDto.getCompanyName();
        Integer deliveryTips = requestDeliveryDto.getDeliveryTips();

        Company companyName = findByCompanyName(inputCompanyName);

        OrderTypeResponseDto orderTypes = orderQueryRepository.findOrderTypes(orderId, storeId);
        orderTypeValidation(orderTypes);

        Long deliveryCompanyId = orderQueryRepository.findDeliveryCompanyIdByCompanyName(companyName);
        Delivery delivery = DeliveryDto.toEntity(orderId, deliveryCompanyId, deliveryTips);

        deliveryRepository.save(delivery);
    }

    @Transactional
    public List<DeliveryTrackingResponseDto> lookupInProgressDelivery(Long storeId) {

        List<DeliveryTrackingResponseDto> fetchedInProgressDeliveryList = orderQueryRepository.findDeliveryByDeliveryState(storeId);
//        if (isEmptyInProgressDelivery(fetchedInProgressDeliveryList)) {
//            throw new EmptyDeliveryListException();
//        }
        return fetchedInProgressDeliveryList;
    }

    @Transactional
    public List<DeliveryDetailResponseDto> deliveryDetailLookup(Long storeId, Long deliveryId) {

        List<DeliveryDetailResponseDto> deliveryInfo = orderQueryRepository.findDeliveryDetail(storeId, deliveryId);
//        if (isEmptyDeliveryDetail(deliveryInfo)) {
//            throw new EmptyDeliveryException();
//        }
        return deliveryInfo;
    }

    private boolean isNotWaiting(OrderState orderState) {
        return !orderState.equals(OrderState.WAITING);
    }

    private boolean isNotPreparing(OrderState orderState) {
        return !orderState.equals(OrderState.PREPARING);
    }

    private boolean isNotReady(OrderState orderState) {
        return !orderState.equals(OrderState.READY);
    }

    private boolean isNotReady(OrderTypeResponseDto orderState) {
        return !orderState.getOrderState().equals(OrderState.READY);
    }

    private boolean isCancel(OrderState orderState) {
        return orderState.equals(OrderState.CANCEL);
    }

    private boolean isNotTogo(OrderTypeResponseDto orderType) {
        return !orderType.getOrderType().equals(OrderType.TOGO);
    }

    private boolean isNotDelivery(OrderType orderType) {
        return !orderType.equals(OrderType.DELIVERY);
    }

    private static boolean incompleteDelivery(DeliveryState deliveryState) {
        return !deliveryState.equals(DeliveryState.COMPLETE);
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

    private void orderTypeValidation(OrderTypeResponseDto orderDivision) {
//        if (orderDivision == null) {
//            throw new EmptyOrderListException();
//        }
//        if (isNotDelivery(orderDivision.getOrderType())) {
//            throw new NotDeliveryException();
//        }
//        if (isNotReady(orderDivision.getOrderState())) {
//            throw new NotReadyException();
//        }
    }
}
