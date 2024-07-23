package com.panther.exception;

/**
 * @description: 自定义异常
 */
public class CryptoException extends CustomizeException {

    public CryptoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CryptoException(String message) {
        super(message);
    }

}
