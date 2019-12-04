package com.windf.study.core.exception;

/**
 * 提示给用户的异常
 */
public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
