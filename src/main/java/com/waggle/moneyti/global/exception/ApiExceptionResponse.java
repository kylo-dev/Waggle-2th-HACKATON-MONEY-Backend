package com.waggle.moneyti.global.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApiExceptionResponse {

    private int httpStatus;
    private String code;
    private String message;
}
