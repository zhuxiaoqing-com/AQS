package com.example.demo1.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * IP操作工具类
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/9.
 */
public class IPUtil {


    /**
     * ip  port 转为long 存储
     * <p>
     * Long
     * 保留(16)+端口(16)+ip(32位)
     *
     * @param ip
     * @param port
     */
    public static long IpPortToLong(String ip, int port) {

        long ipLong = 0L;

        //拆分字符串
        String[] intStr = ip.split("\\.");


        /**
         * 依次位移
         * 24 16 8 位
         */
        for (int i = 0; i < intStr.length; i++) {
            Long part = Long.valueOf(intStr[i]);
            ipLong |= part << (3 - i) * 8;
        }
        /**
         * 存储端口
         * 左移32即可
         */
        ipLong |= (long) port << 32;
        return ipLong;
    }

    public static String getIpByAddr(long ipP) {
        return ((ipP & 0xffffffffL) >>> 24) + "." +
                ((ipP & 0xffffff) >>> 16) + "." +
                ((ipP & 0xffff) >>> 8) + "." +
                ((ipP & 0xff));
    }

    public static int getPortByAddr(long ipP) {
        return (int) (ipP >> 32);
    }


    /**
     * 获取本地IP地址
     */
    public static String getLocalIP() throws UnknownHostException, SocketException {
        if (isWindowsOS()) {
            return InetAddress.getLocalHost().getHostAddress();
        } else {
            return getLinuxLocalIp();
        }
    }

    /**
     * 判断操作系统是否是Windows
     *
     * @return
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取Linux下的IP地址
     *
     * @return IP地址
     */
    private static String getLinuxLocalIp() throws SocketException {
        String ip = "";
        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
            NetworkInterface networkInterface = en.nextElement();
            String name = networkInterface.getName();
            if (!name.contains("docker") && !name.contains("lo")) {
                for (Enumeration<InetAddress> enumIpAddress = networkInterface.getInetAddresses(); enumIpAddress.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddress.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ipAddress = inetAddress.getHostAddress();
                        if (!ipAddress.contains("::") && !ipAddress.contains("0:0:") && !ipAddress.contains("fe80")) {
                            ip = ipAddress;
                        }
                    }
                }
            }
        }
        return ip;
    }
}
