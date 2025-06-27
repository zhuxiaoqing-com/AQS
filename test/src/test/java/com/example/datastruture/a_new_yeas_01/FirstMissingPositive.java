package com.example.datastruture.a_new_yeas_01;

import org.junit.Test;

/**
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * <p>
 * 解答方案：
 * 遍历一次数组把大于等于1的和小于数组大小的值放到原数组对应位置，然后再遍历一次数组查当前下标是否和值对应，
 * 如果不对应那这个下标就是答案，否则遍历完都没出现那么答案就是数组长度加1。
 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        /*思路：
        从前往后遍历数组，看该位置的值是否在它数组下标那个位置
        比如：3,4，-1,1，3在第一个，应该和-1交换，如果该值大于数组长度，
        或者该值为负数，那么就不进行交换，处理完所有的值，遍历数组，看哪个位置的
        值不等于下标，这个位置就是要找的数
        */
        if (nums == null || nums.length == 0) return 1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                //值小于数组长度，值大于0，值应在的位置不是这个值
                //用while循环直到找到交换后值的正确位置，或者不需要交换
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }//end while
        }//end for
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;//找到该数，返回
            }
        }//end for
        return nums.length + 1;//找不到，说明1-n齐全，返回数组长度加1
    }


    @Test
    public void fun01() {
        int[] nums = new int[]{2, 1};
        int i = firstMissingPositive(nums);
        System.out.println(i);
    }
}
