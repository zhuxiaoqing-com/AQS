package com.example.datastruture.dynamic_programming.zero_one_bag;

/**
 * 回溯算法的背包实现
 * <p>
 * 从递归树中,你应该会发现，有些子问题的求解是重复的，我们可以借助地柜那一节讲的"备忘录"的解决方式，
 * 记录已经计算好的 f(i, cw), 当再次计算到重复的 f(i, cw) 的时候，可以直接从备忘录中取出来用, 就不用再
 * 递归计算了，这样就可以避免冗余计算。
 */
public class Zero_One_Bag_HS_Optimize {
    private int maxW = Integer.MIN_VALUE;
    private int[] weight = {2, 2, 4, 6, 3}; // 物品重量
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量
    //  boolean[5][10];
    private boolean[][] mem = new boolean[n][w + 1]; // 备忘录，默认值为 false

    /**
     * @param i  index
     * @param cw currentWeight
     */
    public void f(int i, int cw) {
        if (cw == w || i == n) {
            // 为了找最大的重量
            if (cw > maxW) maxW = cw;
            return;
        }
        if (mem[i][cw]) return;
        mem[i + 1][cw] = true;
        f(i + 1, cw);
        if (cw + weight[i] < w) {
            mem[i + 1][cw + weight[i]] = true;
            f(i + 1, cw + weight[i]);
        }
    }


}
