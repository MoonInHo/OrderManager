package com.mooninho.ordermanager.member.application.dto;

public record SignInRequestDto(
        String userId,
        String password
) {
}
