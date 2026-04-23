package com.example.rpc.protocol;

import java.util.Arrays;

public class RpcCommand<T> {
    private final int rpcId;
    private final byte[] payload;
    private final Class<T> resultType;

    private RpcCommand(int rpcId, byte[] payload, Class<T> resultType) {
        this.rpcId = rpcId;
        this.payload = payload == null ? new byte[0] : payload;
        this.resultType = resultType;
    }

    public static RpcCommand<Void> fire(int rpcId, byte[] payload) {
        return new RpcCommand<Void>(rpcId, payload, Void.class);
    }

    public static <T> RpcCommand<T> call(int rpcId, byte[] payload, Class<T> resultType) {
        return new RpcCommand<T>(rpcId, payload, resultType);
    }

    public int getRpcId() {
        return rpcId;
    }

    public byte[] getPayload() {
        return payload;
    }

    public Class<T> getResultType() {
        return resultType;
    }

    @Override
    public String toString() {
        return "RpcCommand{" +
                "rpcId=" + rpcId +
                ", payload=" + Arrays.toString(payload) +
                ", resultType=" + resultType.getName() +
                '}';
    }
}
