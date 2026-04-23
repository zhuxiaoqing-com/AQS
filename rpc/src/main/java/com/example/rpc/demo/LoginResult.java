package com.example.rpc.demo;

public class LoginResult {
    private final boolean success;
    private final long userId;
    private final String message;

    public LoginResult(boolean success, long userId, String message) {
        this.success = success;
        this.userId = userId;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public long getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "success=" + success +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                '}';
    }
}
