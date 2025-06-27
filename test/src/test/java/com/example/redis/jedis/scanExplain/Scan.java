package com.example.redis.jedis.scanExplain;

import org.junit.Test;

public class Scan {

    /**
     * @see <a href="https://blog.csdn.net/hackersuye/article/details/82831565">
     * https://blog.csdn.net/hackersuye/article/details/82831565</a>
     * <p>
     * <p>
     *   假设mask=111，hashcode=101，步骤如下：
     * <p>
     * 1. hashcode |= ～mask：保留与mask长度相同的低n位数，其余为全为1，hashcode=1…101
     * 2. rev（hashcoded）：二进制反转，hashcode=101…1
     * 3. hashcode++:hashcode=1100…
     * 4. rev（hashcoded）：二进制再次反转，hashcode=011
     * <p>
     *     可见，经过两次反转后，hashcode成功的向低位进了1：
     */
    @Test
    public void test01() {
        // 掩码 就是 binary 全为 1 的值也是 hashTable 的大小-1(一般都是2^n - 1)
        int mask = 8 - 1;
        int hashCode = 5;

        System.out.println("1..." + Integer.toBinaryString(hashCode));
        // 保留与 mask 长度相同的低n位数，
        System.out.println(mask);
        System.out.println("2..." + Integer.toBinaryString(mask));
        System.out.println("3..." + Integer.toBinaryString(~mask));
        hashCode |= ~mask;
        // 1111 1111 1111 1111 1111 1111 1111 1111
        System.out.println("4..." + Integer.toBinaryString(hashCode));
        // 反转
        //hashCode = BinaryReverse.reverse02(hashCode);
        hashCode = Integer.reverse(hashCode);
        System.out.println("5..." + Integer.toBinaryString(hashCode));
        // +1
        hashCode++;
        System.out.println("6..." + Integer.toBinaryString(hashCode));
        // 反转回来
        hashCode = BinaryReverse.reverse02(hashCode);
        System.out.println("7..." + Integer.toBinaryString(hashCode));

        /*
         0010 -> 0100
         0100 +1 = 1001
         1001
          */
    }

    /**
     *  hashCode |= ~mask;
     *  这个操作直接将 掩码的位置独立出来了
     *  11 掩码是 1111 那么 11 就是 0011
     *  那么就是 1...0011
     *  反转就是 1100...1
     *  加 1 就是 1101...
     *  反转回来就是 1011
     */
    /**
     * 这样有什么好处
     * 0001 -> 1001
     * 1001 -> 1101
     * 0111
     */
    @Test
    public void test02() {
        // 掩码 就是 binary 全为 1 的值也是 hashTable 的大小-1(一般都是2^n - 1)
        int mask = 8 - 1;
        int hashCode = 5;
        // 保留与 mask 长度相同的低n位数
        hashCode |= ~mask;
        // 反转
        hashCode = Integer.reverse(hashCode);
        // +1
        hashCode++;
        // 反转回来
        hashCode = BinaryReverse.reverse02(hashCode);
    }


    /**
     * 没用的代码 ()
     *
     * @param v
     * @return
     */
    public int reverse(int v) {
        int mask = ~0;
        //int
        int s = 8;
        while ((s >>= 1) > 0) {
            mask ^= (mask << s);
            v = ((v >> s) & mask) | ((v << s) & ~mask);
        }
        return v;
    }


    /**
     *
     * @see  <a href="https://mp.weixin.qq.com/s/ufoLJiXE0wU4Bc7ZbE9cDQ">
     *
     *
     * Ⅰ. v低位加1向高位进位；
     * Ⅱ. 去掉v最前面和最后面的部分，只保留v相较于m0的高位部分；
     * Ⅲ. 保留v的低位，高位不断加1。即低位不变，高位不断加1，实现了小表到大表桶的关联。
     */
    @Test
    public void reverse02() {
        int v = 2;
        System.out.println("1..." + Integer.toBinaryString(v));
        int m0 = 8 - 1;
        /**
         * v|m0
         *     0010 | 1111 = 1111
         *
         */
        v = (((v | m0) + 1) & ~m0) | (v & m0);
        System.out.println(v);
        System.out.println("2..." + Integer.toBinaryString(v));
    }

}
