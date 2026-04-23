package com.example.rpc.server;

public interface RpcHandler {
    byte[] handle(byte[] payload) throws Throwable;
}
