package com.example.datastruture.dynamic_programming.zero_one_bag;

/**
 * 动态规划 01 背包
 */
public class ZeroOneBag_DP {
    private int[] weight = new int[]{2, 4, 5, 6};
    private int n = 5;
    private int w = 9;

    // 用来存储状态
    private boolean[][] status = new boolean[n][w + 1];

    public int f() {
        // 第一行特殊优化可以使用哨兵模式
        status[0][0] = true;
        status[0][weight[0]] = true;
        for (int index = 1; index < n; index++) {
            // 不存
            for (int j = 0; j <= w; ++j) {
                if (status[index - 1][j]) status[index][j] = true;
            }
            // 存
            for (int j = 0; j + weight[index] <= w; ++j) {
                if (status[n - 1][j]) status[index - 1][j + weight[index]] = true;
            }
        }
        // 输出结果
        for (int i = w; i >= 0; --i) {
            if (status[n-1][i]) return i;
        }
        return 0;
    }
}
