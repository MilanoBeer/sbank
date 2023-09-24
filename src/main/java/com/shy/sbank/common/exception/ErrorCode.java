package com.shy.sbank.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // 공통
    FAIL(HttpStatus.BAD_REQUEST.value(), "C_01", "잘못된 요청"),

    // 회원관리
    NOT_MATCHED_PASSWORD(HttpStatus.BAD_REQUEST.value(), "M_01", "잘못된 비밀번호입니다.");

    // 개인계좌 관련



    // 그룹계좌 관련


    // 필드
    private final int status;
    private final String code;
    private final String message;

    // 생성자
    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    // 메소드
    public int getStatus() {
        return status;
    }
    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
