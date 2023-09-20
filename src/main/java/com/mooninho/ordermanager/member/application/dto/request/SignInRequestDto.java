package com.mooninho.ordermanager.member.application.dto.request;

public record SignInRequestDto(
        String userId,
        String password
) {
}
