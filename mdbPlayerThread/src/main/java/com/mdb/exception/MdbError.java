package com.mdb.exception;

public class MdbError extends RuntimeException {

    public MdbError() {
    }

    public MdbError(String message) {
        super(message);
    }

    public MdbError(String message, Throwable cause) {
        super(message, cause);
    }

    public MdbError(Throwable cause) {
        super(cause);
    }

    public MdbError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
