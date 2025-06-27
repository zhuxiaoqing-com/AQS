package com.example.datastruture.dynamic_programming.zero_one_bag_level_difficulty;

/**
 * 01 背包 动态规划
 */
public class ZeroOneBagLevel_dp_Optimize {
    // 物品的重量
    private int[] items = {2, 2, 4, 6, 3};
    // 物品的价值
    private int[] value = {3, 4, 8, 9, 6};
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量

    /**
     * 我们用一个二维数组 status[n][w+1],来记录每层可以达到的不同状态。不过这里数组存储的值
     * 不再是 boolean 类型的了，而是当前状态对应的最大总价值。我们把每一层中(i,cw)重复的状态
     * (节点)合并，只记录cv值最大的那个状态，然后基于这些状态来推导下一层的状态
     */

    private int[] status = new int[w + 1];

    public void f() {
        // 初始化
        for (int j = 0; j < w + 1; j++) {
            status[j] = -1;
        }

        status[0] = 0;
        status[items[0]] = value[0];

        for (int i = 1; i < n; i++) {
            /**
             * 不会出现先前没有然后现在有的情况的。
             * 因为先前没有现在有就是 先前是 0 而零的话也是初始化过的
             */
            for (int j = w - items[i]; j >= 0; --j) {
                if (status[j] >= 0) {
                    int oldV = status[j + items[i]];
                    int newV = status[j] + value[i];
                    if (newV > oldV) {
                        status[j + items[i]] = newV;
                    }
                }
            }
        }

        // 找出最大值
        int maxValue = -1;
        for (int j = 0; j <= w; j++) {
            if (status[j] > maxValue) maxValue = status[j];
        }
        return;
    }
}
