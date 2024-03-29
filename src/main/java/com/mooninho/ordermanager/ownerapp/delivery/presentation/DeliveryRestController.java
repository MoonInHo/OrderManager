package com.mooninho.ordermanager.ownerapp.delivery.presentation;

import com.mooninho.ordermanager.ownerapp.delivery.application.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryRestController {  // TODO 배달 기사용 앱 분리 고민

    private final DeliveryService deliveryService;

    @PatchMapping("/{deliveryId}/{deliveryDriverId}/assignment") // TODO 배달원 역할에 배달업체 명을 포함시켜서 접근 권한 관리
    public ResponseEntity<Void> deliveryDriverAssignment(
            @PathVariable Long deliveryId,
            @PathVariable Long deliveryDriverId // TODO 배달기사 아이디는 파라미터가 아닌 토큰에서 꺼낸 아이디로 조회하도록 변경 고민
    ) {
        deliveryService.deliveryDriverAssignment(deliveryId, deliveryDriverId);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{deliveryId}/{deliveryDriverId}/pick-up")
    public ResponseEntity<Void> deliveryPickUp(
            @PathVariable Long deliveryId,
            @PathVariable Long deliveryDriverId
    ) {
        deliveryService.deliveryPickUp(deliveryId, deliveryDriverId);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{deliveryId}/{deliveryDriverId}/complete")
    public ResponseEntity<Void> deliveryComplete(
            @PathVariable Long deliveryId,
            @PathVariable Long deliveryDriverId
    ) {
        deliveryService.deliveryComplete(deliveryId, deliveryDriverId);

        return ResponseEntity.ok().build();
    }
}
