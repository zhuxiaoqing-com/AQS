package com.example.datastruture.dynamic_programming.test02;

import org.junit.Test;

public class Test02Coin {
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


    /**
     * 横轴保存 硬币价值(1元,3元,5元)
     * 纵轴保存 总金额
     * int[][] 的值是 达到总金额硬币需要的数量
     * <p>
     * 公式是 value = 总金额 - 当前硬币 查看 value(已有的总金额) 是否有硬币组合可以达到该总金额
     * 有的话就将硬币数量+1 存入。没有的话就说明无法达到直接跳过
     * 如果 value == 0 说明只要当前 硬币就可以达到直接填入 1
     *
     * @param coinItem
     * @param resultMemory
     */

    // dynamic
    public void coinDynamic02(int[] coinItem, int resultMemory) {
        // 层数为总金额
        int levelNum = resultMemory;
        // 长度为货币数值
        int length = coinItem.length;
        int[][] status = new int[levelNum + 1][length];


        // 初始化状态数组
        for (int i = 0; i <= levelNum; i++) {
            for (int j = 0; j < length; j++) {
                status[i][j] = -1;
            }
        }

        // 将第一层的数据填充
      /*  for (int i = 0; i < length; i++) {
            status[0][i] = 0;
        }*/
        // int minNum = -1;

        // 计算推导状态
        for (int i = 1; i <= levelNum; i++) {
            for (int j = 0; j < coinItem.length; j++) {
                int coin = coinItem[j];
                int result = i - coin;
                if (result < 0) {
                    // 如果小于零,说明货币价值本身超过了 需要的总金额
                    continue;
                } else if (result == 0) {
                    // 如果刚好等于 0 说明该货币价值 == 需要的总金额
                    status[i][j] = 1; // 需要一个货币
                } else {
                    // 总金额 - coin  去找 上一个的总金额 是否有货币数
                    int[] subStatus = status[result];
                    // 最少的货币数
                    int min = -1;
                    // 找到可以凑成总金额的最少的硬币数量的下标
                    for (int t = subStatus.length - 1; t >= 0; --t) {
                        if (subStatus[t] > 0) {
                            min = t;
                            break;
                        }
                    }
                    // 没有找到 就直接返回
                    if (min == -1) {
                        continue;
                    }
                    // 找到减去以后的总金额 最少的硬币数量的下标
                    int num = status[result][min];
                    status[i][j] = num + 1;
                }

            }

        }

        // 找到最小的货币数量
        int i;
        for (i = length - 1; i >= 0; --i) {
            if (status[resultMemory][i] > 0) {
                break;
            }
        }
        // 没有找到
        if (i == -1) {
            return;
        }

        // 需要的硬币数量
        int sum = resultMemory;
        int num = status[resultMemory][i];
        System.out.println("需要" + coinItem[i]);
        for (int j = num - 1; j >= 1; --j) {
            int coin = coinItem[i];
            sum = sum - coin;
            int t;
            for (t = length - 1; t >= 0; --t) {
                if (status[sum][t] > 0) {
                    break;
                }
            }
            System.out.println("需要" + coinItem[t]);
            i = t;
        }

    }

    @Test
    public void test02() {
        int[] coinItem = new int[]{1, 2, 3, 4, 5, 6};
        int resultMoney = 9;
        coinDynamic02(coinItem, resultMoney);
    }

}
