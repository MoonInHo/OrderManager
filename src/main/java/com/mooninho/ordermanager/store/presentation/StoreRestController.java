package com.mooninho.ordermanager.store.presentation;

import com.mooninho.ordermanager.store.application.dto.request.CreateStoreRequestDto;
import com.mooninho.ordermanager.store.application.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreRestController {

    private final StoreService storeService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> createStore(
            @RequestBody CreateStoreRequestDto createStoreRequestDto,
            Authentication authentication
    ) {
        storeService.createStore(createStoreRequestDto, authentication.getName());
        return ResponseEntity.ok().build();
    }
}
