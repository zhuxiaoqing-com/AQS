package com.example.rpc.protocol;

public class RpcResponse {
    private final int requestId;
    private final byte[] payload;
    private final String errorMessage;

    private RpcResponse(int requestId, byte[] payload, String errorMessage) {
        this.requestId = requestId;
        this.payload = payload == null ? new byte[0] : payload;
        this.errorMessage = errorMessage == null ? "" : errorMessage;
    }

    public static RpcResponse success(int requestId, byte[] payload) {
        return new RpcResponse(requestId, payload, "");
    }

    public static RpcResponse failure(int requestId, String errorMessage) {
        return new RpcResponse(requestId, new byte[0], errorMessage);
    }

    public int getRequestId() {
        return requestId;
    }

    public byte[] getPayload() {
        return payload;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isSuccess() {
        return errorMessage.isEmpty();
    }
}
