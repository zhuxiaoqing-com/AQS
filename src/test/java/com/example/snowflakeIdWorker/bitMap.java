package com.example.snowflakeIdWorker;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * int 类型的 位数组
 * 基本思路
 * 就是将数值的大小转换为 位的位数  3 就是第三位  222222 就是 第 222222 位
 * 1、首先开辟一个字节(8byte)的空间, 将这些空间的所有的 byte 位都设置为0
 * (直接初始化数组就好了 数组会自动初始话为默认值的)
 * <p>
 * 求十进制数如何转换为对应的 bit 位
 * 1、求十进制数在对应 数组 中的下标
 * 十进制数 0-31, 对应在数组 a[0] 中, 32-63 对应在数组 a[1] 中。使用数学归纳分析可以得出
 * n/32 就是十进制数 在数组中对应的下标 而 n/32 可以转换为 n >> 5  (2^5 = 32)
 * <p>
 * 2、求在十进制数在对应数a[i]中的下标
 * 例如十进制数 1 在 a[0] 的下标为 1, 十进制数 31 在 a[0]中的下标为 31, 十进制数在 a[1] 中下标为 0. 在十进制
 * 0-31就对应0-31，而32-63则对应也是0-31，即给定一个数n可以通过模32求得在对应数组a[i]中的下标。
 * 那么就是 n%32 可以转换为  n & 0x1F  0x1F 是十六进制 因为F 是 1111 二进制, 而 1 代表 1 0000
 * 就是 0x1F 就是 11111; 也就是 n&11111  & 必须 11 才能为 1   01 10 00 都是为 0
 * 所以就只会吧 后面 5位保存下来. 5位的最大值也就是 31
 * <p>
 * 3、位移
 * 对于一个十进制数 n, 对应在数组 a[n/32][n%32] a[n>>5][n&0x1F] 中，但数组a毕竟不是一个二维数组，我们通过移位操作实现
 * a[n>>5] |= 1<<(n&0x1F)    1<<(n&0x1F) 可以取出 十进制在的位数 然后 | a[n>>5] 原有的值。就将其添加进去了
 * <p>
 * n & 0x1F 保留n的后五位 相当于 n % 32 求十进制数在数组a[i]中的下标
 * <p>
 * -1L ^ (-1L << n) n 的最大位  ^ 相同为 0 不相同为 1
 */
public class bitMap {

    public void add(int[] bitMap, int num) {
        int index = num >> 5; // num / 32
        int bit = num & 0x1F; // num % 32
        bitMap[index] |= 1 << bit;
    }

    public void remove(int[] bitMap, int num) {
        int index = num >> 5; // num/32;
        int bit = num & 0x1F; // num%32;
        bitMap[index] &= ~(1 << bit);
    }

    public boolean contain(int[] bitMap, int num) {
        int index = num >> 5; // num/32
        int bit = num & 0x1F; // num%32
        return (bitMap[index] | 1 << bit) != 0;
    }

    /**
     * 要存储的最大的值
     *
     * @param num
     * @return
     */
    public int[] create(int num) {
        int index = num >> 5;// 求出的是下标 所以还需要 +1
        int[] bitMap = new int[1 + index];
        return bitMap;
    }

    public void print(int[] bitMap) {
        /**
         * 将 int 转换为 32 的 byte[]
         */
        for (int i = 0; i < bitMap.length; i++) {
            ArrayList<Byte> bitList = new ArrayList<>();
            for (int x = 0; i <= 0x1F; i++) {
                bitList.add((byte) (x & 1)); // x & 1 就把 位数 1 给拿下来了
                x >>= 1;
            }
            System.out.println(bitList);
        }
    }
}
