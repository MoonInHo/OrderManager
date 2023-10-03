package com.mooninho.ordermanager.ownerapp.order.infrastructure.utils;

import com.mooninho.ordermanager.ownerapp.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationUtils {

    private final OrderRepository orderRepository;
}