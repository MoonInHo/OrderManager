package com.order.manager.controller;

import com.order.manager.dto.ResponseMessageDto;
import com.order.manager.dto.delivery.DeliveryDetailResponseDto;
import com.order.manager.dto.delivery.DeliveryRequestDto;
import com.order.manager.dto.delivery.DeliveryTrackingResponseDto;
import com.order.manager.dto.order.CompletedOrderResponseDto;
import com.order.manager.dto.order.PreparingOrderResponseDto;
import com.order.manager.dto.order.WaitingOrderResponseDto;
import com.order.manager.enums.state.OrderState;
import com.order.manager.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @GetMapping("/waiting")
    public List<WaitingOrderResponseDto> waitingOrdersLookup() {

        Long storeId = orderService.findStoreId();

        return orderService.findWaitingOrders(storeId);
    }

    @GetMapping("/preparing")
    public List<PreparingOrderResponseDto> preparingOrdersLookup() {

        Long storeId = orderService.findStoreId();

        return orderService.findPreparingOrders(storeId);
    }

    @GetMapping("/complete")
    public List<CompletedOrderResponseDto> completedOrdersLookup() {

        Long storeId = orderService.findStoreId();

        return orderService.completeOrdersLookup(storeId);
    }

    @PatchMapping("/{orderId}/accept")
    public ResponseEntity<ResponseMessageDto> acceptOrders(@PathVariable Long orderId) {

        Long storeId = orderService.findStoreId();

        OrderState orderState = orderService.findOrderState(orderId, storeId);
        if (isWaiting(orderState)) {
            orderService.changeOrderState(orderId, storeId, OrderState.PREPARING);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(APPLICATION_JSON)
                    .body(new ResponseMessageDto("주문이 수락되었습니다"));
        }
        return ResponseEntity
                .badRequest()
                .contentType(APPLICATION_JSON)
                .body(new ResponseMessageDto("주문이 대기중 상태가 아닙니다."));
    }

    @PatchMapping("/{orderId}/ready")
    public ResponseEntity<ResponseMessageDto> readyOrders(@PathVariable Long orderId) {

        Long storeId = orderService.findStoreId();

        OrderState orderState = orderService.findOrderState(orderId, storeId);
        if (isPreparing(orderState)) {
            orderService.changeOrderState(orderId, storeId, OrderState.READY);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(APPLICATION_JSON)
                    .body(new ResponseMessageDto("메뉴가 준비되었습니다."));
        }
        return ResponseEntity
                .badRequest()
                .contentType(APPLICATION_JSON)
                .body(new ResponseMessageDto("주문이 준비중 상태가 아닙니다."));
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<ResponseMessageDto> cancelOrder(@PathVariable Long orderId) {

        Long storeId = orderService.findStoreId();
        OrderState orderState = orderService.findOrderState(orderId, storeId);

        ResponseEntity<ResponseMessageDto> APPLICATION_JSON = returnFailureMessage(orderState);
        if (APPLICATION_JSON != null) return APPLICATION_JSON;

        orderService.changeOrderState(orderId, storeId, OrderState.CANCEL);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseMessageDto("주문이 취소되었습니다"));
    }


    @PatchMapping("/{orderId}/pickup")
    public ResponseEntity<ResponseMessageDto> pickUpComplete(@PathVariable Long orderId) {

        Long storeId = orderService.findStoreId();

        OrderState orderState = orderService.findOrderState(orderId, storeId);
        if (isReady(orderState)) {
            orderService.changeOrderStateToPickUp(orderId, storeId, OrderState.COMPLETE);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(APPLICATION_JSON)
                    .body(new ResponseMessageDto("픽업이 완료되었습니다."));
        }
        return ResponseEntity
                .badRequest()
                .contentType(APPLICATION_JSON)
                .body(new ResponseMessageDto("준비 완료된 포장 주문만 픽업할 수 있습니다."));
    }

    @PostMapping("/{orderId}/deliveries")
    public ResponseEntity<ResponseMessageDto> createDeliveryRequest(@PathVariable Long orderId, @RequestBody DeliveryRequestDto requestDeliveryDto) {

        Long storeId = orderService.findStoreId();

        orderService.createDeliveryInfo(orderId, storeId, requestDeliveryDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(APPLICATION_JSON)
                .body(new ResponseMessageDto("배달원 배정을 요청했습니다"));
    }

    @GetMapping("/deliveries")
    public List<DeliveryTrackingResponseDto> lookupInProgressDelivery() {

        Long storeId = orderService.findStoreId();

        return orderService.lookupInProgressDelivery(storeId);
    }

    @GetMapping("/deliveries/{deliveryId}/detail")
    public List<DeliveryDetailResponseDto> deliveryDetailInfoLookup(@PathVariable Long deliveryId) {

        Long storeId = orderService.findStoreId();

        return orderService.deliveryDetailLookup(storeId, deliveryId);
    }

    private ResponseEntity<ResponseMessageDto> returnFailureMessage(OrderState orderState) {
        if (isReady(orderState)) {
            return ResponseEntity
                    .badRequest()
                    .contentType(APPLICATION_JSON)
                    .body(new ResponseMessageDto("준비완료 된 주문은 취소할 수 없습니다."));
        }
        if (isComplete(orderState)) {
            return ResponseEntity
                    .badRequest()
                    .contentType(APPLICATION_JSON)
                    .body(new ResponseMessageDto("완료된 주문은 취소할 수 없습니다."));
        }
        if (isCancel(orderState)) {
            return ResponseEntity
                    .badRequest()
                    .contentType(APPLICATION_JSON)
                    .body(new ResponseMessageDto("이미 취소된 주문입니다."));
        }
        return null;
    }

    private boolean isWaiting(OrderState orderState) {
        return orderState.equals(OrderState.WAITING);
    }

    private boolean isPreparing(OrderState orderState) {
        return orderState.equals(OrderState.PREPARING);
    }

    private boolean isComplete(OrderState orderState) {
        return orderState.equals(OrderState.COMPLETE);
    }

    private boolean isCancel(OrderState orderState) {
        return orderState.equals(OrderState.CANCEL);
    }

    private boolean isReady(OrderState orderState) {
        return orderState.equals(OrderState.READY);
    }


}