package com.shy.sbank.domain.member.exception;

// 언체크 예외 상속받기
public class PasswordMatchException extends RuntimeException{
    // 예외 기본 생성자
    public PasswordMatchException() {
        super();
    }
    // 메세지 출력 용도
    public PasswordMatchException(String message) {
        super(message);
    }
}
