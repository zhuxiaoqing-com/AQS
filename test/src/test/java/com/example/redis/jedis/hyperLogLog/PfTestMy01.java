package com.example.redis.jedis.hyperLogLog;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 超级简单的 HyperLogLog
 *
 * 为什么去重，因为 相同的value的hash 是一样的，不能更新桶里面的 maxBits.
 */
public class PfTestMy01 {
   // static long l = ThreadLocalRandom.current().nextLong(1L << 32);
    /**
     * 桶
     */
    static class BitKeeper {
        // 最大的 001 是多少长
        private int maxBits;

        /**
         * 计算是否超出 maxBits
         */
        public void countMaxBits(long value) {
            int i = countLowZeros(value);
            if (i > maxBits) {
                maxBits = i;
            }
        }

        /**
         * 计算 00...1 有多长
         *
         * @param value
         * @return
         */
        private int countLowZeros(long value) {
            int i = 1;
            // 这里其实应该是 value 的长度,但是 maxBits 最多 31 还有一个 符号位，所以就 31 了
            for (; i < 32; i++) {
                // 先右移再左移 这样就能确定 二进制到底有几个 0 了
                if (value >> i << i != value) {
                    break;
                }
            }
            return i - 1;

        }
    }

    static class Experiment {
        // 需要放几个数字
        private int n;
        // 有几个桶
        private int k;
        private BitKeeper[] keepers;

        public Experiment(int n) {
            this(n, 1024);
        }

        public Experiment(int n, int k) {
            this.n = n;
            this.k = k;
            this.keepers = new BitKeeper[k];
            // 初始化桶
            for (int i = 0; i < keepers.length; i++) {
                keepers[i] = new BitKeeper();
            }
        }

        public void work() {
            for (int i = 0; i < n; i++) {
                // 使用随机数来模拟 hash
                long l = ThreadLocalRandom.current().nextLong(1L << 32);
                // 计算桶的位置
                // 确保同一个整数被分配到同一个桶里面，摘取高位后取模
                // 这里 0xffff0000 主要是 将前 32 位拿过来取模确定桶的位置。取出桶
                BitKeeper keeper = keepers[(int) (((l & 0xffff0000) >> 16) % keepers.length)];
                // 这里去不去掉前 32 位也无所谓的; 反正都是概率。。
                keeper.countMaxBits(l);
            }
        }

        public double estimate() {
            // 这里使用 调和平均数 就是用 1/? 来进行让平均数偏向小的那一方
            double sumBitsInverse = 0.0; // 零位数倒数
            int count = 0;
            for (int i = 0; i < keepers.length; i++) {
                if (keepers[i].maxBits != 0) {
                    count++;
                    sumBitsInverse += 1.0 / keepers[i].maxBits;

                }
            }
            // 平均零位数
            double avgBits = (float) keepers.length / sumBitsInverse;
            //double avgBits = sumBitsInverse/(float) keepers.length ;
            // 根据公式 2^avgBits * 桶数(必须是不为空的桶)
            return Math.pow(2, avgBits) * count;
        }
    }

    public static void main(String[] args) {
        for (int i = 100000; i < 1000000; i += 100000) {
            Experiment exp = new Experiment(i);
            exp.work();
            double est = exp.estimate();
            System.out.printf("%d %.2f %.2f\n", i, est, Math.abs(est - i) / i);
        }
    }
}

