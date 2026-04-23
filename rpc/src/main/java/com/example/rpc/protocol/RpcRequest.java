package com.example.rpc.protocol;

public class RpcRequest {
    private final int requestId;
    private final int rpcId;
    private final byte[] payload;
    private final boolean needResponse;

    public RpcRequest(int requestId, int rpcId, byte[] payload, boolean needResponse) {
        this.requestId = requestId;
        this.rpcId = rpcId;
        this.payload = payload == null ? new byte[0] : payload;
        this.needResponse = needResponse;
    }

    public int getRequestId() {
        return requestId;
    }

    public int getRpcId() {
        return rpcId;
    }

    public boolean isNeedResponse() {
        return needResponse;
    }

    public byte[] getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "requestId=" + requestId +
                ", rpcId=" + rpcId +
                ", needResponse=" + needResponse +
                '}';
    }
}
