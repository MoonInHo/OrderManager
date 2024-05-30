package com.mooninho.ordermanager.ownerapp.order.presentation;

import com.mooninho.ordermanager.ownerapp.delivery.application.dto.request.CreateDeliveryRequestDto;
import com.mooninho.ordermanager.ownerapp.delivery.infrastructure.dto.response.GetInProgressDeliveryOrdersResponseDto;
import com.mooninho.ordermanager.ownerapp.order.infrastructure.dto.response.*;
import com.mooninho.ordermanager.ownerapp.orderhistory.application.dto.request.CreateOrderCancelHistoryRequestDto;
import com.mooninho.ordermanager.ownerapp.order.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/{storeId}/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping("/waiting")
    public ResponseEntity<List<GetWaitingOrderResponseDto>> getWaitingOrders(
            @PathVariable Long storeId,
            Authentication authentication
    ) {
        return ResponseEntity.ok()
                .body(orderService.getWaitingOrders(storeId, authentication.getName()));
    }

    @GetMapping("/preparing")
    public ResponseEntity<List<GetPreparingOrderResponseDto>> getPreparingOrders(
            @PathVariable Long storeId,
            Authentication authentication
    ) {
        return ResponseEntity.ok()
                .body(orderService.getPreparingOrders(storeId, authentication.getName()));
    }

    @GetMapping("/complete")
    public ResponseEntity<List<GetCompletedOrderResponseDto>> getCompleteOrders(
            @PathVariable Long storeId,
            Authentication authentication
    ) {
        return ResponseEntity.ok()
                .body(orderService.getCompletedOrders(storeId, authentication.getName()));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<GetOrderDetailResponseDto> getOrderDetail(
            @PathVariable Long storeId,
            @PathVariable Long orderId,
            Authentication authentication
    ) {
        return ResponseEntity.ok()
                .body(orderService.getOrderDetail(storeId, orderId, authentication.getName()));
    }

    @PatchMapping("/{orderId}/accept")
    public ResponseEntity<Void> acceptOrders( // TODO 배달 예상시간 & 조리 예상 시간 추가
            @PathVariable Long storeId,
            @PathVariable Long orderId,
            Authentication authentication
    ) {
        orderService.changeOrderToPreparing(storeId, orderId, authentication.getName());

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{orderId}/ready")
    public ResponseEntity<Void> readyOrders(
            @PathVariable Long storeId,
            @PathVariable Long orderId,
            Authentication authentication
    ) {
        orderService.changeOrderToReady(storeId, orderId, authentication.getName());

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<Void> cancelOrder( // TODO 배달이 시작된 주문도 취소가 되게 할지 고민 (물어보기)
            @PathVariable Long storeId,
            @PathVariable Long orderId,
            @RequestBody(required = false) CreateOrderCancelHistoryRequestDto createOrderCancelHistoryRequestDto,
            Authentication authentication
    ) {
        orderService.cancelOrder(
                storeId,
                orderId,
                createOrderCancelHistoryRequestDto,
                authentication.getName()
        );

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{orderId}/pickup")
    public ResponseEntity<Void> pickUpTogoOrder(
            @PathVariable Long storeId,
            @PathVariable Long orderId,
            Authentication authentication
    ) {
        orderService.changeTogoOrderToComplete(storeId, orderId, authentication.getName());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{orderId}/deliveries")
    public ResponseEntity<Void> requestDeliveryDriver(
            @PathVariable Long storeId,
            @PathVariable Long orderId,
            @RequestBody CreateDeliveryRequestDto createDeliveryRequestDto,
            Authentication authentication
    ) {
        orderService.createDeliveryRequest( // TODO 배민원, 요기요익스프레스, 쿠팡이츠의 경우 주문 시점에 자동으로 배달 생성
                storeId,
                orderId,
                createDeliveryRequestDto,
                authentication.getName()
        );

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/deliveries/{orderId}/complete")
    public ResponseEntity<Void> completeDeliveryOrder(
            @PathVariable Long storeId,
            @PathVariable Long orderId,
            Authentication authentication
    ) {
        orderService.changeDeliveryOrderToComplete(storeId, orderId, authentication.getName());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/deliveries")
    public ResponseEntity<List<GetInProgressDeliveryOrdersResponseDto>> getProgressDeliveries(
            @PathVariable Long storeId,
            Authentication authentication
    ) {
        return ResponseEntity.ok()
                .body(orderService.getInProgressDeliveryOrders(storeId, authentication.getName()));
    }

    @GetMapping("/deliveries/{deliveryId}")
    public ResponseEntity<GetDeliveryOrderResponseDto> getDeliveryOrder(
            @PathVariable Long storeId,
            @PathVariable Long deliveryId,
            Authentication authentication
    ) {
        return ResponseEntity.ok()
                .body(orderService.getDeliveryOrder(storeId, deliveryId, authentication.getName()));
    }
}