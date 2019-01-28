package com.example.redis.scanExplain;

import org.junit.Test;

public class BinaryReverse {

    /**
     * 1、指数法
     */
    @Test
    public void fun1() {
        int i = 0;
        Integer.reverse(i);
    }


    /**
     *  i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
     *  0101
     * 0111 -> 1011
     * 0111 & 0101 = 0101
     * 0101 << 1 = 1010
     *
     * 0101 >>> 1 = 0010
     * 0010 & 0101 = 0000
     *
     * 1010 | 0000 = 1010
     *
     * 1111
     *
     * 1111 & 0101 = 0101
     * 0101 << 1 = 1010
     *
     * 1111 >>> 1 = 0111
     * 0111 & 0101 = 0101
     * 1010 | 0101 = 1111
     *
     *
     */


    /**
     * 0x55555555
     * 0101   1010 10
     * <p>
     * 0x33333333
     * 0011
     * <p>
     * 0x0f0f0f0f
     * 0000 1111
     *
     * @param i
     * @return
     */
    public int reverse(int i) {
        // HD, Figure 7-1
        /**
         * (i & 0x55555555) << 1
         * 先 & 0101 取出 奇数位, 然后 << 1  将其位置往前提
         * 例子： 1111 取出低位的1然后将 0101 << 1 变为 1010; 也就是说将低位的 1 变为了高位的1
         * 我们现在还需要将高位的1变为低位的1
         *
         * (i >>> 1) & 0x55555555;
         * (i >>> 1) & 0x55555555 为什么
         * i >>> 1 就将高位的 1位移到了 低位的 1;然后取0101 就是取到了高位的1 还顺便将高位的1变为了低位
         */
        // 交换每两位
        i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
        // 交换每四位中的前两位和后两位
        i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
        // 交换每八位中的前四位和后四位
        i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
        /**
         * 直接交换了 16位 16位
         */
        i = (i << 24) | ((i & 0xff00) << 8) |
                ((i >>> 8) & 0xff00) | (i >>> 24);
        return i;
    }

    public void fun05(int i) {
        // 交换每两位
        i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
        // 交换每四位中的前两位和后两位
        i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
        // 交换每八位中的前四位和后四位
        i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
        i = (i & 0x00ff00ff) << 8 | (i >>> 8) & 0x0f0f0f0f;
        i = (i & 0x0000ffff) << 16 | (i >>> 16) & 0x0000ffff;
    }

    /**
     * 怎么换  应该是 先取 奇数位的所有数字 左移
     * 然后 取 偶数位的所有数字
     */
    @Test
    public void fun02() {
        int i = 44;
        // 交换每两位
        System.out.println(Integer.toBinaryString((i & 0x55555555) << 1));
        System.out.println(Integer.toBinaryString((i >>> 1) & 0x55555555));

        System.out.println();
        System.out.println(Integer.toBinaryString(i));
        i = (i & 0x55555555) << 1 | (i & 0xaaaaaaaa) >> 1;
        //i = (i<< 1 )& 0x55555555  | (i >>> 1) & 0x55555555;
        System.out.println(Integer.toBinaryString(i));

        System.out.println();
        System.out.println(Integer.toBinaryString(0xaaaaaaaa));
        System.out.println(Integer.toBinaryString(0x55555555));
    }

    /****************************
     * ********看这里*************
     * **************************
     *  https://blog.csdn.net/mickey35/article/details/72929883
     * 在笼统的讲一遍
     * i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
     *
     * (i & 0x55555555) << 1
     * 这里是将低位的 1 位移到了高位
     * i & 0x55555555   (0101) 这里首先提取出了 低位的 1  (将高位的 1全部变为了 0);
     * << 1 这里将低位的1位移到了高位 (现在低位的位置已经调换到了 高位)
     *
     *
     * 然后还差高位的1变为低位
     *
     * 就是 (i >>> 1) & 0x55555555;
     *
     * 这里先将高位的 1 变为低位的1
     * &0x55555555 这里取到了高位变地位的 1 的所有的 1;
     *  这里的话 高位的位置已经变为了低位  而且还把原本低位的数据置为了 0
     * 然后就是 | 将两者进行组合 就是替换过位置的Bit
     *
     */

    /**
     * i = (i << 24) | ((i & 0xff00) << 8) |
     * ((i >>> 8) & 0xff00) | (i >>> 24);
     * <p>
     * 这里的话
     * i = (i << 24) 将低8位直接移到了最高的8位的位置
     * ((i & 0xff00) << 8) 这里将右边的16为的 8位的高位 0000 0000 1111 000
     * 变为了 0000 1111 0000 0000 也就是说将 右边的高位变为了 左边的低位
     * (i << 24) | ((i & 0xff00) << 8) 组合就成了
     * 1111 1111 0000 0000 这里直接就将8位的运算和16位的运算放在了一起
     * <p>
     * ((i >>> 8) & 0xff00) 将左边的低位的1 移动了右边的高位
     * 0000 1111 0000 0000 -> 0000 0000 1111 0000
     * 再加上  (i >>> 24) 直接把最高位的 1111 0000 0000 0000
     * 移动到了 0000 0000 0000 1111
     * 就变成了   0000 0000 1111 1111
     * 这样直接就完成了反转
     */
    @Test
    public void fun03() {
        // 这里是 abc1  因为先计算方法里面的值再从右到左
        int w = a() * (b() * c());
        System.out.println(w);
    }

    public int a() {
        System.out.println("a");
        return 1;
    }

    public int b() {
        System.out.println("b");
        return 1;
    }

    public int c() {
        System.out.println("c");
        return 1;
    }



    public static int reverse02(int i) {
        // 1010 = 10 a
        i = ((i & 0xaaaaaaaa) >>> 1) | (i << 1) & 0xaaaaaaaa;
        // 1100 = 12 c
        i = ((i & 0xcccccccc) >>> 2) | (i << 2) & 0xcccccccc;
        // 11110000
        i = ((i & 0xf0f0f0f0) >>> 4) | (i << 4) & 0xf0f0f0f0;
        i = (i<<24) | ((i&0xff00)<<8) |
                ((i>>>8)&0xff00) | (i>>>24);
        return i;
    }

    @Test
    public void fun04() {
        for(int i = -3; i<10000;i++) {
            if(Integer.reverse(i) != reverse02(i)){
                System.out.println(" reverser02 error!!!");
            }
        }
    }

}
