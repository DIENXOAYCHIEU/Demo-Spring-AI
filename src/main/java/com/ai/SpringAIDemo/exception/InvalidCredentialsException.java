package com.ai.springaidemo.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidCredentialsException extends AuthenticationException {

    public InvalidCredentialsException() {
        super("Thông tin đăng nhập không hợp lệ");
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}