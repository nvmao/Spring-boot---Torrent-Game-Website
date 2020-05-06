package com.mao.springboot.gameshop.Util;

public class SimpleException extends Exception {

    public SimpleException(String message) {
        super(message);
    }

    public SimpleException() {
        super();
    }

    public SimpleException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimpleException(Throwable cause) {
        super(cause);
    }

    protected SimpleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
