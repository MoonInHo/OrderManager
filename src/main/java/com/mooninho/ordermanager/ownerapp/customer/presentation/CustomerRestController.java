package com.mooninho.ordermanager.ownerapp.customer.presentation;

import com.mooninho.ordermanager.ownerapp.customer.application.dto.CreateCustomerRequestDto;
import com.mooninho.ordermanager.ownerapp.customer.application.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CreateCustomerRequestDto createCustomerRequestDto) {
        customerService.createCustomer(createCustomerRequestDto);
        return ResponseEntity.ok().build();
    }
}
