package com.example.datastruture.a_new_yeas_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> arrayLists = new ArrayList();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            /**
             * 因为对于 nums 来说，我们只需要三数和为0; 因为三个数和等于 0 所有一定有一个数是负数的。
             */
            if (nums[i] > 0) {
                return arrayLists;
            }
            // 如果两个数字相同，我们直接跳到下一个循环
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = 0 - nums[i];
            /**
             * 为什么j要从 i+1 开始取呢
             *  因为如果从头开始取的话，有可能重复。
             *
             */
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    arrayLists.add(Arrays.asList(nums[i], nums[j],nums[k]));
                    while (j < k && nums[j] == nums[j + 1]) {
                        j += 1;
                    }
                    while (j < k && nums[k] == nums[k - 1]) {
                        k -= 1;
                    }
                    // 否则就往中间靠拢
                    j = j + 1;
                    k = k - 1;
                } else if (nums[j] + nums[k] < target) {
                    j= j+1;
                } else {
                    k = k-1;
                }
            }
        }
        return arrayLists;
    }
}
