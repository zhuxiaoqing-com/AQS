package com.example.datastruture.backtracking_algorithm.zero_one_bag;

/**
 * 0-1 背包
 */

import org.junit.Test;

/**
 * 对于每个物品来说,都有两种选择，装进背包或者不装进背包。对于 n 个物品来说，总的装法就有
 * 2^n 种，去掉总重量超过 wkg 的，从剩下的装法中选择总重量最接近 Wkg 的。不过, 我们如何才能不重复
 * 地穷举出这 2^n 种装法呢
 * <p>
 * 这里就可以用回溯的方法。我们可以把物品以此排列，整个问题就分解为了 n 个阶段，每个阶段对应一个物品怎么选择。
 * 先对第一个物品进行处理, 选择装进去或者不装进去, 然后再递归地处理剩下的物品。
 */
public class ZeroOneBag {
    public int maxW = Integer.MIN_VALUE; // 存储背包中物品总重量的最大值

    // cw 表示当前已经装进去的物品的重量和; i 表示考察到哪个物品了;
    // w 背包重量; items 表示每个物品的重量; i 表示考察到哪个物品了;
    // 假设背包可承受重量 100; 物品个数 10, 物品重量存储在数组 a 中, 那可以这样调用函数:
    // f(0, 0, a, 10, 100)

    /**
     * 我们可以把物品依次排列，整个问题就分解为了 n 个阶段，每个阶段对应一个物品怎么选择。
     * 先对第一个物品进行处理，选择装进去或者不装进去，然后再递归地处理剩下的物品。
     *
     * @param i     考察到哪个物品了
     * @param cw    当前已经装进去的物品的重量和
     * @param items 每个物品的重量
     * @param n     可装载物品数量
     * @param w     背包可承受重量
     */
    public void f(int i, int cw, int[] items, int n, int w) {
        // cw == w 表示装满了; i == n 表示已经考察完所有的物品
        if (cw == w || i == n) {
            if (cw > maxW) maxW = cw;
            return;
        }
        //你要对比着13行来看 11行表示不把第i个物品装进去 13行表示把第i个物品装进去
        // 11 行
        f(i + 1, cw, items, n, w);

        // 已经超过可以背包承受的重量的时候, 就不要再装了
        if (cw + items[i] <= w) {
            // 13 行
            f(i + 1, cw + items[i], items, n, w);
        }
    }

    /**
     * 就相当于一棵树
     * 先全部 0 然后返会上一层开始尝试 1 如果可以就输出
     */


    @Test
    public void test01() {
        int[] a = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 0, 2, 2, 3};
        f(0, 0, a, 10, 100);
    }
}
