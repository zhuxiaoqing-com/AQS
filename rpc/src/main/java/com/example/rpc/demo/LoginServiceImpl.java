package com.example.rpc.demo;

public class LoginServiceImpl {
    public LoginResult login(LoginRequest request) {
        boolean success = "AQS".equals(request.getAccount()) && "123456".equals(request.getPassword());
        if (success) {
            return new LoginResult(true, 10001L, "login success");
        }
        return new LoginResult(false, 0L, "bad account or password");
    }
}
