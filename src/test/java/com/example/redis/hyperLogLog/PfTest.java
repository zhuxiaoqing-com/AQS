package com.example.redis.hyperLogLog;

import java.util.concurrent.ThreadLocalRandom;

public class PfTest {
    // 通过随机数记录最大的低位零的个数
    static class BitKeeper {
        private int maxbits;

        public void random(long value) {
            int bits = lowZeros(value);
            if (bits > this.maxbits) {
                this.maxbits = bits;
            }

        }
        // 算低位零的个数
        private int lowZeros(long value) {
            int i = 1;
            for (; i < 32; i++) {
                if (value >> i << i != value) {
                    break;
                }
            }
            return i - 1;
        }
    }

    static class Experiment {
        private int n;
        private int k;
        private BitKeeper[] keepers;

        public Experiment(int n) {
            this(n, 1024);
        }

        public Experiment(int n, int k) {
            this.n = n;
            this.k = k;
            this.keepers = new BitKeeper[k];
            for (int i = 0; i < k; i++) {
                this.keepers[i] = new BitKeeper();
            }
        }

        public void work() {
            for (int i = 0; i < this.n; i++) {
                long m = ThreadLocalRandom.current().nextLong(1L << 32);
                // 确保同一个整数被分配到同一个桶里面，摘取高位后取模
                // 这里 0xffff0000 主要是 将前 32 位拿过来取模确定桶的位置。取出桶
                BitKeeper keeper = keepers[(int) (((m & 0xfff0000) >> 16) % keepers.length)];
                // 这里去不去掉前 32 位也无所谓的; 反正都是概率。。
                keeper.random(m);
            }
        }

        public double estimate() {
            double sumbitsInverse = 0.0; // 零位数倒数
            for (BitKeeper keeper : keepers) {
                sumbitsInverse += 1.0 / (float) keeper.maxbits;
            }
            // 平均零位数
            double avgBits = (float) keepers.length / sumbitsInverse;
            // 根据桶的数量对估计值进行放大
            return Math.pow(2, avgBits) * this.k;
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
}