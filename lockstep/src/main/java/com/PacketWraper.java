package com;

import com.google.protobuf.Message;
import com.room.Game;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;
import kcp.Ukcp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PacketWraper {

    static Logger logger = LoggerFactory.getLogger(Game.class);

    public int id;
    public byte[] data;


    public static void send(Ukcp ukcp, int id, Message msg) {
        int msgLength = msg == null ? 0 : msg.getSerializedSize();
        ByteBuf byteBuf = null;
        try {
            byteBuf = UnpooledByteBufAllocator.DEFAULT.buffer(1 + msgLength);
            byteBuf.writeShort(msgLength);
            byteBuf.writeByte(id);

            if (msg != null) {
                try (ByteBufOutputStream out = new ByteBufOutputStream(byteBuf)) {
                    msg.writeTo(out); // 直接写进 ByteBuf
                } catch (Exception e) {
                    logger.error("", e);
                }
            }

            ukcp.write(byteBuf);
        } finally {
            if (byteBuf != null) {
                byteBuf.release();
            }
        }
    }

    public static PacketWraper readPacket(ByteBuf buf) {
        PacketWraper packetWraper = new PacketWraper();
        int dataLength = buf.readShort();
        int messageId = buf.readByte();
        byte[] bytes = new byte[dataLength];
        buf.readBytes(bytes);

        packetWraper.id = messageId;
        packetWraper.data = bytes;
        return packetWraper;
    }

}
