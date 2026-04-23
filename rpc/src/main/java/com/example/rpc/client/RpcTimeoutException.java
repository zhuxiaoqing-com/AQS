package com.example.rpc.client;

public class RpcTimeoutException extends RpcException {
    public RpcTimeoutException(String message) {
        super(message);
    }
}
