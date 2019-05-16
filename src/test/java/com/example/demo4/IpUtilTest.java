package com.example.demo4;

import com.example.demo1.util.IPUtil;
import org.junit.Test;

public class IpUtilTest {
    @Test
    public void test01() {
        String ip = "10.42.0.50";
        int port = 7999;
        long l = IPUtil.IpPortToLong(ip, port);
        // 34355613925426
        System.out.println(l);
        String ipByAddr = IPUtil.getIpByAddr(34362970604928l);
        System.out.println(ipByAddr);
    }
}
