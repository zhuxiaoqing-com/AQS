package com.example.datastruture.dynamic_programming.goods;

/**
 * 双十一薅羊毛
 */
public class goods11 {
    // items 商品价格， n 商品个数，w 表示满减条件，比如 200
    public static void double11Advance(int[] items, int n, int w) {
        boolean[][] states = new boolean[n][3 * w + 1];// 超过 3 倍就没有薅羊毛的价值了
        states[0][0] = true; // 第一行的数据要特殊处理
        states[0][items[0]] = true;

        for (int i = 1; i < n; ++i) {
            // 不存
            for (int j = 0; j <= 3 * w; ++j) {
                if (states[i - 1][j]) states[i][j] = states[i - 1][j];
            }

            // 存
            for (int j = 0; j <= 3 * w - items[j]; j++) {
                if (states[i - 1][j]) states[i][j + items[i]] = true;
            }
        }

        int j;
        for (j = w; j < 3 * w + 1; ++j) {
            // 输出结果大于等于 w 的最大值
            if (states[n - 1][j]) break;
        }
        if (j <= 0) return; // 没有可行解

        // i 表示二维数组中的行, j 表示列
        for (int i = n - 1; i >= 1; --i) {
            if (j - items[i] >= 0 && states[i - 1][j - items[i]] == true) {
                // 购买这个商品
                System.out.println(items[i] + " ");
                j = j - items[i];
            } // else 没有购买这个商品, j 不变.
        }
        if (j != 0) System.out.println(items[0]);
    }

    // items 商品价格， n 商品个数，w 表示满减条件，比如 200
    public static void double11Advance02(int[] items, int n, int w) {
        boolean[][] states = new boolean[n][3 * w + 1];// 超过 3 倍就没有薅羊毛的价值了
        states[0][0] = true; // 第一行的数据要特殊处理
        states[0][items[0]] = true;

        for (int i = 1; i < n; ++i) {
            // 不存
            for (int j = 0; j <= 3 * w; ++j) {
                if (states[i - 1][j]) states[i][j] = states[i - 1][j];
            }

            // 存
            for (int j = 0; j <= 3 * w - items[j]; j++) {
                if (states[i - 1][j]) states[i][j + items[i]] = true;
            }
        }

        int j;
        // 先找到最大值
        for (j = w * 3; j >= 0; --j) {
            if (states[n - 1][j]) break;
        }
        /**
         * 然后最大值减去当前的物品的价格 就知道上一个价格在哪里了
         */
        for (int i = n - 1; i <= 1; i++) {
            /**
             * status[i-1][j-items[i]] 为 true 代表可以买
             * status[i-1][j] 为 true 代表不能买，因为和前一个物品的价格一样就说明没有买
             */
            /*
               // 这是第一种放法 第二种方法也可以买
            if (j - items[i] >= 0 && states[i - 1][j - items[i]]) {
                System.out.println(j + ""); // 可以，买
                j = j - items[i];
            }*/

            if(j - items[i] >= 0 && !states[i-1][j]) {
                System.out.println(j + ""); // 可以，买
                j = j - items[i];
            }
        }

        // 因为需要 i- 1 取下标所以最后一个要自己打印
        // 如果还有 j 不是为零的话就说明还有物品,上面的都选完了还有物品的话就是 item[0] 了
        if(j != 0) System.out.println(items[0]);
    }

}










