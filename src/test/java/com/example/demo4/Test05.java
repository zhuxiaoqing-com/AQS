package com.example.demo4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class Test05 {

    @Test
    public void test01() {
        fun1(6);
    }

    private void fun1(int n) {
        ArrayList<int[]> list = new ArrayList<>();
        list.add(fun2(1, n));
        for (int i = 2; i <= n - 1; i++) {
            list.add(fun2(i * n + 1, n));
        }
        list.add(fun2(1 * n + 1, n));

        for(int[] array: list ) {
            System.out.println(Arrays.toString(array));
        }

    }

    private int[] fun2(int num, int n) {
        int[] array = new int[n];
        int index = 0;
        for (int i = num; i < num + n; i++) {
            array[index++] = i;
        }
        return array;
    }

}




















