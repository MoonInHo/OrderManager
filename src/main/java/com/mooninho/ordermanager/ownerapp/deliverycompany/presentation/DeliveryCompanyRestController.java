package com.mooninho.ordermanager.ownerapp.deliverycompany.presentation;

import com.mooninho.ordermanager.ownerapp.deliverycompany.application.dto.request.CreateDeliveryCompanyRequestDto;
import com.mooninho.ordermanager.ownerapp.deliverycompany.application.service.DeliveryCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{storeId}/delivery-companies")
@RequiredArgsConstructor
public class DeliveryCompanyRestController {

    private final DeliveryCompanyService deliveryCompanyService;

    @PostMapping
    public ResponseEntity<Void> addDeliveryCompany(
            @PathVariable Long storeId,
            @RequestBody CreateDeliveryCompanyRequestDto createDeliveryCompanyRequestDto
    ) {
        deliveryCompanyService.createDeliveryCompany(storeId, createDeliveryCompanyRequestDto);

        return ResponseEntity.ok().build();
    }
}
