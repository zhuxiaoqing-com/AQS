package com.example.datastruture.dynamic_programming.test03;

import org.junit.Test;

/**
 * 我们有一个数字序列包含 n 个不同的数字，
 * 如何求出这个序列中的最长递增子序列长度？
 * 比如 2, 9, 3, 6, 5, 1, 7 这样一组数字序列，
 * 它的最长递增子序列就是 2, 3, 5, 7，所以最长递增子序列的长度是 4。
 */
public class Test03 {

    /**
     * 回溯
     */
    int[] list = new int[]{2, 9, 3, 6, 5, 1, 7};
    int max_sequence = Integer.MIN_VALUE;

    // f(1,1); 开始调用
    public void f(int index, int max) {
        if (index == list.length) {
            return;
        }
        if (list[index] < list[index - 1]) {
            if (max > max_sequence) max_sequence = max;
            // 长度从新从 1 开始
            f(index + 1, 1);
        } else {
            f(index + 1, max + 1);
        }
    }

    @Test
    public void test01() {
        //f(1, 1);
        f02(0 + 1, 1);
        System.out.println(max_sequence);
    }

    /**
     * f02(0,1)  回溯
     *
     * @param index
     * @param max 是当前 index 的数量
     */
    public void f02(int index, int max) {
        if (index == list.length) {
            if (max > max_sequence) max_sequence = max;
            return;
        }
        if (list[index] < list[index - 1]) {
            f02(index + 1, max);
        } else {
            f02(index + 1, max + 1);
        }
    }


    /**
     * 动态规划
     */
    public void dynamicP() {
        int[] status = new int[list.length];


    }



}
