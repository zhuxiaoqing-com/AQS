package com.example.datastruture.dynamic_programming.test02;

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
     * <p>
     * 最优子结构：
     * 问题的最优解包含子问题的最优解。反过来说就是，我们可以通过子问题的最优解，推导出问题的最优解。
     * 如果我们把最优子结构，对应到我们前面定义的动态规划问题模型上。也就是，后面阶段的状态可以通过前面阶段
     * 的状态推导出来。
     * <p>
     * 无后效性：
     * 1、在推导后面阶段的状态的时候，我们只关心前面阶段的状态值，不关心这个状态是怎么一步一步推导出来的。。
     * 2、某阶段一旦确定，就不受之后阶段的决策影响。
     * <p>
     * 重复子问题
     * 不同决策序列，到达每个相同阶段时，可能会产生重复的状态。
     */


    // 状态转移方程是解决动态规划

    private int[][] matrix =
            {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
    private int n = 4;
    private int[][] mem = new int[4][4];

    // 调用 minDist(n-1, n-1);
    public int minDist(int i, int j) {
        if (i == 0 && j == 0) {
            return matrix[0][0];
        }
        if (mem[i][j] > 0) {
            return mem[i][j];
        }
        int minLeft = Integer.MAX_VALUE;
        if (j - 1 >= 0) {
            minLeft = minDist(i, j - 1);
        }
        int minUp = Integer.MAX_VALUE;
        if (i - 1 >= 0) {
            minUp = minDist(i - 1, j);
        }
        int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
        mem[i][j] = currMinDist;
        return currMinDist;
    }

}

















