package com.order.manager.dto;

import com.order.manager.enums.state.AccountResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseStatusDto {

    private AccountResponseStatus code;
    private String message;
}
