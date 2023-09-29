package com.mooninho.ordermanager.ownerapp.menu.presentation;

import com.mooninho.ordermanager.ownerapp.menu.application.dto.request.CreateMenuRequestDto;
import com.mooninho.ordermanager.ownerapp.menu.application.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuRestController {

    private final MenuService menuService;

    @PostMapping("/{storeId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> createMenu(
            @PathVariable Long storeId,
            @RequestBody CreateMenuRequestDto createMenuRequestDto,
            Authentication authentication
    ) {
        menuService.createMenu(storeId, createMenuRequestDto, authentication.getName());
        return ResponseEntity.ok().build();
    }
}
