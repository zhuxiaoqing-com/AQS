package com.example.datastruture.dynamic_programming.zero_one_bag;

/**
 * 回溯算法的背包实现
 */
public class Zero_One_Bag_HS {
    private int maxW = Integer.MIN_VALUE;
    private int[] weight = {2, 2, 4, 6, 3}; // 物品重量
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量

    /**
     * @param i  index
     * @param cw currentWeight
     */
    public void f(int i, int cw) {
        if (cw == w || i == n) {
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i + 1, cw); // 0
        if (cw + weight[i] < w) {
            f(i + 1, cw + weight[i]); // 1
        }
    }

    // optimize 因为 w = 9; 所以必须为 w+1 才能取到下标 9
    // 而 n 的话本身就是 5{0-4} 递归的备忘录 如果备忘录里面已经有数据了 就直接返回 不重复计算
    private boolean[][] mem = new boolean[n][w + 1];

    public void f02(int index, int currentWeight) {
        if (index == n || currentWeight == w) {
            if (currentWeight > maxW) maxW = currentWeight;
            return;
        }

        // 如果备忘录里面已经有数据就直接返回k
        if (mem[index][currentWeight]) return;
        f(index +1, currentWeight); // 0  不装入
        if(currentWeight + weight[index] <= w) {
            f(index +1, currentWeight + weight[index]); // 1 装入
        }



    }
}
