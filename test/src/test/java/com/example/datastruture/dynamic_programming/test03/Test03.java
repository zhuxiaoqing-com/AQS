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
     * @param max   是当前 index 的数量
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
     * @param arrays
     * @param index
     */
    public int recursionCount(int[] arrays, int index) {
        if (index == 0) {
            return 1;
        }
        int max = 0;
        // 此问题的解，递归的核心就是在之前的序列中找到最大递增子序列加1
        // 所以需要遍历此之前的全部数据项
        for (int i = 0; i < index; i++) {
            // 递归求解每项的最第增序列
            int value = recursionCount(arrays, i);
            if (arrays[i] < arrays[index]) {
                if (value > max) {
                    max = value;
                }
            }
        }
        return max + 1;
    }

    /**
     * 原理就是 获取之前的序列号中找到最大递增子序列
     * 与当前序列比较大于 +1 小于 不加 取最大的存入
     *
     * @param arrays
     * @param index
     */
    public int recursionCount02(int[] arrays, int index) {
        if (index == 0) {
            return 1;
        }
        int max = 0;
        for (int i = 0; i < index; i++) {
// 取到 index 到 i 的最大值
            int value = recursionCount(arrays, i);
            if (arrays[i] < arrays[index]) {
                if (value + 1 > max) {
                    max = value + 1;
                }
            } else {
                if (value > max) {
                    max = value;
                }
            }
        }
        return max;
    }

    @Test
    public void test02() {
        int[] array = new int[]{2, 3, 4, 6, 5, 1};
        System.out.println(recursionCount02(array, array.length - 1));
    }


    /**
     * 动态规划
     * <p>
     * 等于前面所有的最大的然后 +1
     */
    public int dynamicP(int[] array) {
        int[] status = new int[array.length];

        status[0] = 1;
        for (int i = 1; i < array.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j]) {
                    if (status[j] + 1 > max) {
                        max = status[j] + 1;
                    }
                } else {
                    if (status[j] > max) {
                        max = status[j];
                    }
                }
            }
            status[i] = max;
        }
        return status[status.length - 1];
    }

    @Test
    public void test03() {
        int[] array = new int[]{2, 3, 4, 6, 8, 1};
        System.out.println(recursionCount02(array, array.length - 1));
        System.out.println(dynamicP(array));
    }

}
