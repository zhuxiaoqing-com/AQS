package com.example.rpc.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class RpcFrameCodec extends ByteToMessageCodec<RpcFrame> {
    private static final int HEADER_LENGTH = 1 + 4 + 4 + 1 + 1 + 4 + 4;

    @Override
    protected void encode(ChannelHandlerContext ctx, RpcFrame frame, ByteBuf out) {
        byte[] payload = frame.getPayload();
        byte[] errorBytes = frame.getErrorMessage().getBytes(StandardCharsets.UTF_8);

        out.writeInt(HEADER_LENGTH + payload.length + errorBytes.length);
        out.writeByte(frame.getMessageType());
        out.writeInt(frame.getRequestId());
        out.writeInt(frame.getRpcId());
        out.writeBoolean(frame.isNeedResponse());
        out.writeBoolean(frame.isSuccess());
        out.writeInt(payload.length);
        out.writeInt(errorBytes.length);
        out.writeBytes(payload);
        out.writeBytes(errorBytes);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() < 4) {
            return;
        }

        in.markReaderIndex();
        int frameLength = in.readInt();
        if (in.readableBytes() < frameLength) {
            in.resetReaderIndex();
            return;
        }

        byte messageType = in.readByte();
        int requestId = in.readInt();
        int rpcId = in.readInt();
        boolean needResponse = in.readBoolean();
        boolean success = in.readBoolean();
        int payloadLength = in.readInt();
        int errorLength = in.readInt();

        byte[] payload = new byte[payloadLength];
        in.readBytes(payload);

        byte[] errorBytes = new byte[errorLength];
        in.readBytes(errorBytes);
        String errorMessage = new String(errorBytes, StandardCharsets.UTF_8);

        out.add(new RpcFrame(messageType, requestId, rpcId, needResponse, success, payload, errorMessage));
    }
}
