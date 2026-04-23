package com.example.rpc.client;

public interface RpcCallback<T> {
    void onSuccess(T result);

    void onFailure(Throwable error);
}
