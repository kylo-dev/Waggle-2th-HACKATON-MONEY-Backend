package com.waggle.moneyti.global.exception;

import com.waggle.moneyti.global.response.status.ErrorStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiExceptionResponse> handleApiException(ApiException e) {
        ErrorStatus errorStatus = e.getErrorType();
        ApiExceptionResponse response = new ApiExceptionResponse(
            errorStatus.getHttpStatus().value(),
            errorStatus.getCode(),
            errorStatus.getMessage()
        );
        return ResponseEntity.status(errorStatus.getHttpStatus()).body(response);
    }
}
