package com.example.rpc.protocol;

public class RpcFrame {
    private final byte messageType;
    private final int requestId;
    private final int rpcId;
    private final boolean needResponse;
    private final boolean success;
    private final byte[] payload;
    private final String errorMessage;

    RpcFrame(byte messageType, int requestId, int rpcId, boolean needResponse,
             boolean success, byte[] payload, String errorMessage) {
        this.messageType = messageType;
        this.requestId = requestId;
        this.rpcId = rpcId;
        this.needResponse = needResponse;
        this.success = success;
        this.payload = payload == null ? new byte[0] : payload;
        this.errorMessage = errorMessage == null ? "" : errorMessage;
    }

    public static RpcFrame request(int requestId, int rpcId, boolean needResponse, byte[] payload) {
        return new RpcFrame(RpcMessageType.REQUEST, requestId, rpcId, needResponse, true, payload, "");
    }

    public static RpcFrame success(int requestId, byte[] payload) {
        return new RpcFrame(RpcMessageType.RESPONSE, requestId, 0, false, true, payload, "");
    }

    public static RpcFrame failure(int requestId, String errorMessage) {
        return new RpcFrame(RpcMessageType.RESPONSE, requestId, 0, false, false, new byte[0], errorMessage);
    }

    public byte getMessageType() {
        return messageType;
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

    public boolean isSuccess() {
        return success;
    }

    public byte[] getPayload() {
        return payload;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
