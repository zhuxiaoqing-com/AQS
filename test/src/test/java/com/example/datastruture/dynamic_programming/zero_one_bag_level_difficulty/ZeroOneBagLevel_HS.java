package com.example.datastruture.dynamic_programming.zero_one_bag_level_difficulty;

/**
 * 01 背包升级 需要价值
 * 要价值最大的人
 */
public class ZeroOneBagLevel_HS {
    // 结果放到 maxV 中
    private int maxV = Integer.MIN_VALUE;
    // 物品的重量
    private int[] items = {2, 2, 4, 6, 3};
    // 物品的价值
    private int[] value = {3, 4, 8, 9, 6};
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量

    // 调用 f(0, 0, 0)
    public void f(int i, int cw, int cv) {
        if (cw == w || i == n) {
            if (cv > maxV) maxV = cv;
            return;
        }
        // 0
        f(i + 1, cw, cv);

        if (cw + items[i] <= w) {
            f(i + 1, cw + items[i], cv + value[i]);
        }

    }
}
