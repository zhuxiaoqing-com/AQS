package com.example.CSAPP.socket;

public class SocketUtil {
    public static final int SOCK_STREAM =1;

    // host to network
    public static int htonl(int hostlong) {
        return -1;
    }

    public static int ntohl(int networklong) {
        return -1;
    }

    // 把十进制位addr地址，转换为 点分十进制字符串。
    public static int inet_pton(int addrType, Object src, Object dst) {
        return -1;
    }

    public static String inet_ntop(int addrType, String src, Object dst, int dstLen) {
        return "";
    }

    // 清空该类的内存
    public static void memset(AddrInfo hints, int type, int hintsLen){}

}
