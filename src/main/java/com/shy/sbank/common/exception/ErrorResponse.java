package com.shy.sbank.common.exception;


import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String message;
    private int status;
    private String code;
//    private List<FieldError> errors;

    public ErrorResponse(String message, int status, String code) {
        this.message = message;
        this.status = status;
        this.code = code;
    }

    public static ResponseEntity<ErrorResponse> getResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getStatus())
                        .message(errorCode.getMessage())
                        .code(errorCode.getCode())
                        .build()
                );

    }

}
