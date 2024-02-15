package com.waggle.moneyti.global.exception;

import com.waggle.moneyti.global.response.status.ErrorStatus;

public class ApiException extends RuntimeException{

    private final ErrorStatus errorStatus;

    public ApiException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }

    public ErrorStatus getErrorType() {
        return errorStatus;
    }
}
