package com.example.demo3;

import org.junit.Test;

public class Test07 {
    @Test
    public void test1() {
        Long s = 211L;
        System.out.println(s.equals(211));
        System.out.println(s.equals(211L));
        System.out.println(s == 211);
        System.out.println(s == 211L);
    }
}
