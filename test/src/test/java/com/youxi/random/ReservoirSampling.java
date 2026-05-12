package com.youxi.random;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.ToDoubleFunction;

/**
 * @author zhuxiaoqing
 * @Description: ReservoirSampling 蓄水池采样算法;
 * @Date 2026/5/11 15:26
 **/
/*
蓄水池采样算法
    它解决的问题是：数据流长度未知，或者数据太大不能全部放内存，但你想从里面 等概率随机抽取 k 个元素。
    最核心场景：随机抽 1 个
    第1个元素
    第2个元素
    第3个元素
    ...
    第n个元素

    你想最终随机留下 1 个，并且每个元素被选中的概率都是： 1 / n

    核心公式是：第 i 个元素到来时，以 1 / i 的概率替换当前答案

int i = 0;
Object ans = null;
for (Object cur : stream) {
    i++;
        int r = random(1, i);
        if (r == 1) {
            ans = cur;
        }
}
例如第 100 个元素来了，它有 1/100 的概率替换当前保存的元素。

为什么这样是公平的？
看第 3 个元素为例。
它被选中概率是：
来到第3个时被选中：1/3
后面第4个不替换它：3/4
后面第5个不替换它：4/5
...
后面第n个不替换它：(n-1)/n

所以最终留下的概率：
1/3 * 3/4 * 4/5 * ... * (n-1)/n = 1/n

前面的都约掉了，最后就是 1/n。
所以每个元素最后留下的概率都一样。


抽 k 个的版本

如果你要随机抽 k 个：
前 k 个直接放入数组。
第 i 个元素到来时，i > k：
以 k / i 的概率决定是否进入水池。
如果进入，就随机替换水池里的一个元素。

伪代码：

List<Object> reservoir = new ArrayList<>();

int i = 0;
for (Object cur : stream) {
    i++;

    if (i <= k) {
        reservoir.add(cur);
    } else {
        int r = random(1, i);
        if (r <= k) {
            int index = random(0, k - 1);
            reservoir.set(index, cur);
        }
    }
}

核心公式就是：
第 i 个元素被选入水池的概率 = k / i
最终效果是：
n 个元素里，每个元素被抽中的概率都是 k / n
 */
public class ReservoirSampling {


    private static int random(int origin, int i) {
        // nextInt 默认是[start,end)的，所以这里需要 i+1;变成[start,end]
        return ThreadLocalRandom.current().nextInt(origin, i+1);
    }

    public Object random1(Collection<Object> list) {
        int i = 0;
        Object ans = null;
        for (Object cur : list) {
            i++;
            int r = random(1, i);
            if (r == 1) {
                ans = cur;
            }
        }
        return ans;
    }



    public List<Object> randomK(Collection<Object> list, int k) {
        List<Object> reservoir = new ArrayList<>();

        int i = 0;
        for (Object cur : list) {
            i++;

            if (i <= k) {
                reservoir.add(cur);
            } else {
                int r = random(1, i);
                if (r <= k) {
                    // 随机替换水池里的一个元素
                    int index = random(0, k - 1);
                    reservoir.set(index, cur);
                }
            }
        }
        return reservoir;
    }


    /**
     * 权重版本的;仿造navmesh dtNavMeshQuery::findRandomPoint里的算法;
     */
    public static <T> T weightedReservoirSample(Collection<T> list, ToDoubleFunction<T> weightFn) {
        double totalWeight = 0.0;
        T ans = null;

        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (T cur : list) {
            double weight = weightFn.applyAsDouble(cur);

            // 权重 <= 0 的对象不参与随机
            if (weight <= 0) {
                continue;
            }

            totalWeight += weight;

            /*
             * u * totalWeight <= weight
             * u<=area/tsum
             * 而 u 是 [0, 1) 的随机浮点数
             *
             * 如果weight =1;
             * u * totalWeight <= 1
             * u <= 1 / totalWeight
             * 第 i 个有效 tile，以 1 / i 的概率替换答案;
             * 是一个意思
             */
            // 以 weight / totalWeight 的概率替换当前答案
            double u = random.nextDouble();
            if (u * totalWeight <= weight) {
                ans = cur;
            }
        }

        return ans;
    }


}




















