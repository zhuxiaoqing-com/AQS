package com.example.datastruture.dynamic_programming.test02;

import org.junit.Test;

/**
 * 寻找路径
 */
public class Test01 {
    // 最大值
    int min = Integer.MAX_VALUE;
    int maxX = 3;
    int maxY = 3;
    int[][] square = new int[maxX][maxY];

    // 回溯写法
    void f(int x, int y, int sum) {
        if (x == maxX && y == maxY) {
            if (sum < min) min = sum;
        }
        /**
         * 想象是一颗树 不断的生长
         */
        // 向右
        if (x < maxX) {
            // 这里的 sum 是将当前的 sum 传递下去
            f(x + 1, y, sum + square[x][y]);
        }
        // 向下
        if (y < maxY) {
            f(x, y + 1, sum + square[x][y]);
        }
    }

    int[][] status = new int[maxX][maxY];

    // 动态规划
    void dynamicF() {
        // 初始化边界
        status[0][0] = square[0][0];
        // x 轴初始化
        for (int i = 1; i < maxX; i++) {
            status[0][i] = status[0][i - 1] + square[0][i];
        }
        // y 轴初始化
        for (int i = 1; i < maxX; i++) {
            status[i][0] = status[i - 1][0] + square[i][0];
        }

        for (int x = 1; x < maxX; x++) {
            for (int y = 1; y < maxY; y++) {
                // 右
                int right = status[x - 1][y] + square[x][y];
                // 下
                int down = status[x][y - 1] + square[x][y];
                status[x][y] = Math.min(right, down);
            }
        }

    }

    /**
     * 刚刚定义状态的时候，我们把从起始位置(0,0)到(i,j)的最小路径，记作 min_dist(i,j).因为我们只能
     * 往右或往下移动，所以，我们只有可能从(i,j-1)或者(i-1,j)两个位置到达(i,j)。也就是说，到达(i,j)
     * 的最短路径要么经过(i,j-1),要么经过(i-1,j),而且到达(i,j)的最短路径肯定包含到达这两个位置的最短
     * 路径之一。换句话说就是, min_dist(i,j)可以通过 min_dist(i,j-1)和min_dist(i-1,j)两个状态推导出来。
     * 这就说明，这个问题符合"最优子结构"
     *
     * 最优子结构：
     *  问题的最优解包含子问题的最优解。反过来说就是，我们可以通过子问题的最优解，推导出问题的最优解。
     *  如果我们把最优子结构，对应到我们前面定义的动态规划问题模型上。也就是，后面阶段的状态可以通过前面阶段
     *  的状态推导出来。
     *
     * 无后效性：
     *  1、在推导后面阶段的状态的时候，我们只关心前面阶段的状态值，不关心这个状态是怎么一步一步推导出来的。。
     *  2、某阶段一旦确定，就不受之后阶段的决策影响。
     *
     * 重复子问题
     *  不同决策序列，到达每个相同阶段时，可能会产生重复的状态。
     *
     */

    /**
     * 硬币找零问题，我们在贪心算法那一节中讲过一次。我们今天来看一个新的硬币找零问题。
     * 假设我们有几种不同币值的硬币 v1，v2，……，vn（单位是元）。如果我们要支付 w 元，求最少需要多少个硬币。
     * 比如，我们有 3 种不同的硬币，1 元、3 元、5 元，我们要支付 9 元，最少需要 3 个硬币（3 个 3 元的硬币）。
     */

    int maxCoin = 9;
    int numCoin = Integer.MAX_VALUE;
    int[] coin = new int[]{1, 2, 4, 5, 6};

    // 回溯解法
    public void coin(int num, int sum) {
        if (sum >= maxCoin) {
            if (maxCoin == sum) {
                if (num < numCoin) {
                    numCoin = num;
                }
            }
            return;
        }

        for (int s : coin) {
            coin(num + 1, sum + s);
        }
    }

    @Test
    public void test01() {
        coin(0, 0);
        System.out.println(numCoin);
    }


    // dynamic
    public void coinDynamic(int[] coinItem, int resultMemory) {
        // 计算遍历的层数，此按最小金额来支付即为最大层数
        int levelNum = resultMemory / coinItem[0] + 1;
        int length = coinItem.length;
        int[][] status = new int[levelNum + 1][length];

        for (int i = 0; i <= levelNum; i++) {
            for (int j = 0; j < length; j++) {
                status[]
            }
        }
    }
}

















