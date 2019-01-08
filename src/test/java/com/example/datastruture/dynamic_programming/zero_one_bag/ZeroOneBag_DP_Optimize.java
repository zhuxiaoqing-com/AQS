package com.example.datastruture.dynamic_programming.zero_one_bag;

/**
 * 动态规划 01 背包 优化版
 */
public class ZeroOneBag_DP_Optimize {
    private int[] items = new int[]{2, 4, 5, 6};
    private int n = 5;
    private int w = 9;

    // 用来存储状态
    private boolean[] status = new boolean[w + 1];

    /**
     * @return
     */
    public int f() {
        // 第一行需要特殊处理
        status[0] = true;
        status[items[0]] = true;

        for (int i = 1; i < n; ++i) {
            // 吧第 i 个物品放入背包
            for (int j = w - items[i]; j >= 0; --j) {
                if (status[j]) status[j + items[i]] = true;
            }
        }

        for (int i = w; i >= 0; --i) {
            if (status[i]) return i;
        }
        return 0;
    }

    /**
     * 只保存当前的状态就好了
     *
     * @return
     */
    public int f02() {
        // 初始化
        status[0] = true;
        status[items[0]] = true;

        for (int i = 1; i < n; i++) {
            /**
             * 必须从后往前 不然会被前一个的数据给覆盖掉 或乱掉
             * 臂如 最后一个是 3 而之前都没有 3 那么这个 3 就乱了
             */
            for (int j = w - items[i]; j >= 0; j--) {
                if (status[j]) status[j + items[i]] = true;
            }
        }

        /**
         * 肯定是从大到小的.
         */
        for(int i = n; i>=0; --i) {
            if(status[i]) return i;
        }
        return 0;
    }
}
