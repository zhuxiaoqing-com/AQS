package com.example.rpc.client;

import com.example.rpc.protocol.RpcResponse;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;

final class PendingRpc {
    private final int requestId;
    private final InetSocketAddress remoteAddress;
    private final long expireAtMillis;
    private final CompletableFuture<RpcResponse> future;

    PendingRpc(int requestId, InetSocketAddress remoteAddress,
               long expireAtMillis, CompletableFuture<RpcResponse> future) {
        this.requestId = requestId;
        this.remoteAddress = remoteAddress;
        this.expireAtMillis = expireAtMillis;
        this.future = future;
    }

    int getRequestId() {
        return requestId;
    }

    boolean belongsTo(InetSocketAddress remoteAddress) {
        return this.remoteAddress.equals(remoteAddress);
    }

    boolean isExpired(long nowMillis) {
        return nowMillis >= expireAtMillis;
    }

    CompletableFuture<RpcResponse> getFuture() {
        return future;
    }
}
