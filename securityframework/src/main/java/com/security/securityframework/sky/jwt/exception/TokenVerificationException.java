package com.security.securityframework.sky.jwt.exception;

/**
 * @author sky
 * @date 2020/6/29 10:14
 */
public class TokenVerificationException extends Exception {
    public TokenVerificationException(String message) {
        super(message);
    }
}
