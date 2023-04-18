package com.foodtech.mate.exception.dto;

import com.foodtech.mate.exception.code.MemberErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDto {

    private MemberErrorCode code;
    private String message;
}