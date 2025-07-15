package com.mdb.exception;

public class MdbException extends RuntimeException {

    public MdbException() {
    }

    public MdbException(String message) {
        super(message);
    }

    public MdbException(String message, Throwable cause) {
        super(message, cause);
    }

    public MdbException(Throwable cause) {
        super(cause);
    }

    public MdbException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
