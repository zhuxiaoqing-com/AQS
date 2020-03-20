package com.example.demo1.util;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Bit操作
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2017/10/25.
 */
public class BitUtil {

    private static final long LOW_32BIT = 0xFFFFFFFFL;          //二进制：1111 1111 1111 1111 1111 1111 1111 1111

    private static final long HIGH_32BIT = 0xFFFFFFFF00000000L; //二进制：1111 1111 1111 1111 1111 1111 1111 1111 0000 0000 0000 0000 0000 0000 0000 0000

    private static final int LOW_16BIT = 0xFFFF;          //二进制：1111 1111 1111 1111

    private static final int HIGH_16BIT = 0xFFFF0000; //二进制：1111 1111 1111 1111 0000 0000 0000 0000


    /**
     * 将两个int 16位表示拼接成一个int 32位表示
     *
     * @param highNum int的高16位
     * @param lowNum  int的低16位
     */
    public static int combineIntToTwo(int highNum, int lowNum) {
        return highNum << 16 & HIGH_16BIT | lowNum & LOW_16BIT;
    }

    /**
     * 获得int的高16位对应的int类型
     */
    public static int separateIntHigh16Bit(int number) {
        return (HIGH_16BIT & number) >> 16;
    }

    /**
     * 获得int的低16位对应的int类型
     */
    public static int separateIntLow16Bit(int number) {
        return LOW_16BIT & number;
    }
    /**
     * int组合long类型
     *
     * @param highNum long的高32位
     * @param lowNum  long的低32位
     */
    public static long combineInt2Long(int highNum, int lowNum) {
        return (long) highNum << 32 & HIGH_32BIT | (long) lowNum & LOW_32BIT;
    }

    /**
     * 获得long的高32位对应的int类型
     */
    public static int separateLongHigh32BitToInt(long number) {
        return (int) ((HIGH_32BIT & number) >> 32);
    }

    /**
     * 获得long的低32位对应的int类型
     */
    public static int separateLongLow32BitToInt(long number) {
        return (int) (LOW_32BIT & number);
    }

    /**
     * 保留2位小数函数
     *
     * @param souse
     * @return
     */
    static public float getFloat2(float souse) {
        return Math.round(souse * 100f) / 100f;
    }

    static public float getFloat3(float souse) {
        return Math.round(souse * 1000f) / 1000f;
    }

    /**
     * 保留4位小数函数
     *
     * @param souse
     * @return
     */
    static public float getFloat4(float souse) {
        return Math.round(souse * 10000f) / 10000f;
    }

    /**
     * 保留2位小数函数
     *
     * @param souse
     * @return
     */
    static public double getDouble2(double souse) {
        return Math.round(souse * 100d) / 100d;
    }

    /**
     * 保留4位小数函数
     *
     * @param souse
     * @return
     */
    static public double getDouble4(double souse) {
        return Math.round(souse * 10000d) / 10000d;
    }

    /**
     * 将int转为低字节在前，高字节在后的byte数组
     *
     * @param n
     * @return
     */
    public static final byte[] writeIntToBytesLittleEnding(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    /**
     * 读取小端绪的long
     *
     * @param buf
     * @return
     *//*
    public static final long readBytesToLongLittleEnding(ByteBuf buf) {
        byte[] b = new byte[8];
        buf.readBytes(b, 0, 8);
        return bytes2Long(b, ByteOrder.LITTLE_ENDIAN);
    }

    *//**
     * 端序换算
     *
     * @param buf
     * @return
     *//*
    public static final int readBytesToIntLittleEnding(ByteBuf buf) {
        byte[] b = new byte[4];
        buf.readBytes(b, 0, 4);

        return bytes2Int(b, ByteOrder.LITTLE_ENDIAN);
    }

    *//**
     * 端序换算
     *
     * @param buf
     * @return
     *//*
    public static final short readBytesToShortLittleEnding(ByteBuf buf) {
        byte[] b = new byte[2];
        buf.readBytes(b, 0, 2);
        return bytes2Short(b, ByteOrder.LITTLE_ENDIAN);
    }*/

    /**
     * 将int转为低字节在前，高字节在后的byte数组
     *
     * @param bytes
     * @return
     */
    public static byte[] reverseBytes(byte[] bytes) {
        int len = bytes.length;
        byte[] b = new byte[len];
        for (int i = len - 1; i >= 0; i--) {
            b[len - i - 1] = bytes[i];
        }
        return b;
    }

    /**
     * 端序换算
     *
     * @param from
     * @param fromIndex
     * @return
     */
    public static short get2Bytes(byte[] from, int fromIndex) {
        int high = from[fromIndex] & 0xff;
        int low = from[fromIndex + 1] & 0xff;
        return (short) (high << 8 + low);
    }


    /**
     * 端序换算
     *
     * @param x
     * @param byteOrder
     * @return
     */
    public static byte[] short2Bytes(short x, ByteOrder byteOrder) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.order(byteOrder);
        buffer.putShort(x);
        return buffer.array();
    }

    /**
     * @param x
     * @return
     */
    public static byte[] short2Bytes(short x) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.putShort(x);
        return buffer.array();
    }

    /**
     * 端序换算
     *
     * @param x
     * @param byteOrder
     * @return
     */
    public static final byte[] int2Bytes(int x, ByteOrder byteOrder) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.order(byteOrder);
        buffer.putInt(x);
        return buffer.array();
    }

    /**
     * 端序换算
     *
     * @param x
     * @param byteOrder
     * @return
     */
    public static byte[] long2Bytes(long x, ByteOrder byteOrder) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.order(byteOrder);
        buffer.putLong(x);
        return buffer.array();
    }

    /**
     * 端序换算
     *
     * @param src
     * @param byteOrder
     * @return
     */
    public static final short bytes2Short(byte[] src, ByteOrder byteOrder) {
        ByteBuffer buffer = ByteBuffer.wrap(src);
        buffer.order(byteOrder);
        return buffer.getShort();
    }

    /**
     * 端序换算
     *
     * @param src
     * @param byteOrder
     * @return
     */
    public static final int bytes2Int(byte[] src, ByteOrder byteOrder) {
        ByteBuffer buffer = ByteBuffer.wrap(src);
        buffer.order(byteOrder);
        return buffer.getInt();
    }

    /**
     * 端序换算
     *
     * @param src
     * @param byteOrder
     * @return
     */
    public static long bytes2Long(byte[] src, ByteOrder byteOrder) {
        ByteBuffer buffer = ByteBuffer.wrap(src);
        buffer.order(byteOrder);
        return buffer.getLong();
    }

}
