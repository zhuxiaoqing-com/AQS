package com.example.demo4;

import org.junit.Test;

import java.util.TreeMap;

public class Test10 {
    @Test
    public void test01(){
        long start = System.nanoTime();
        short s;
        for(int i = 1;i<=100_000_000_0; i++){
            s = 0x00;
        }
        long end = System.nanoTime();
        System.out.println((end - start)/1000);
    }

    @Test
    public void test02(){
        long start = System.nanoTime();
        short s;
        for(int i = 1;i<=100_000_000_0; i++){
            s = 0;
        }
        long end = System.nanoTime();
        System.out.println((end - start)/1000);
    }
}























