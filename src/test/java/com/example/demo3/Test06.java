package com.example.demo3;

import org.junit.Test;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test06 {

    @Test
    public void fun1() {
        double s1 = 1.1;
        double s2 = 1.0;
        System.out.println((long)s1 == s1);
        System.out.println((long)s2 == s2);
    }

    @Test
    public void fun2() {
        double s1 = 1111.111111111111111111111111111111111111111111111;
        double s2 = 1.0;
        //System.out.println((int)s1 == s1);
        //System.out.println((int)s2 == s2);
        System.out.println((int) s1);
    }

    @Test
    public void fun3() {
        try{
            System.out.println(1);
        } finally {
            System.out.println(3);
        }
        System.out.println(2);
    }

    @Test
    public void fun4() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        List<Number> collect = list.stream().collect(Collectors.toList());
        System.out.println(collect);
    }



}
