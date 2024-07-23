package com.panther.exception;

/**
 * @description: 自定义异常
 */
public class CustomizeException extends Exception {

    public CustomizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomizeException(String message) {
        super(message);
    }

}
