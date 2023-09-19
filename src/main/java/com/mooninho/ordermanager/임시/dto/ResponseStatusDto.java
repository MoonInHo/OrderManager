package com.mooninho.ordermanager.임시.dto;

import com.mooninho.ordermanager.임시.enums.state.AccountResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseStatusDto {

    private AccountResponseStatus code;
    private String message;
}
