package com.example.datastruture.binarysearch;

import org.junit.Test;

public class Test01 {
    /**
     * >> 的优先级 低于 +
     */
    @Test
    public void test01() {
        int i = 8>>1+1; // 2
        System.out.println(i);
    }
}
