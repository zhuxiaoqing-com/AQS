package com.example.datastruture.stringmatch.bm;

import java.util.Arrays;

/**
 * 坏字符 ：移动到最后面出现的坏字符的位置
 * 好后缀： 直接移动到好后缀出现的地方，如果没有就说明最终都会碰到这个好后缀，而我们没有最终肯定是没有办法匹配的
 * <p>
 * 所以我们只要挑选两者之前跨度大的进行跨越就好了
 */
public class bm02 {
    private static final int SIZE = 256;// 全局变量或成员变量

    /**
     * 坏字符
     *
     * @param pattern   模式串
     * @param hashTable 散列表
     */
    private void generateBC(char[] pattern, int[] hashTable) {
        Arrays.fill(hashTable, -1);
        for (int i = 0; i < pattern.length; i++) {
            hashTable[pattern[i]] = i;
        }
    }

    /**
     * 好后缀
     */
    private void generateGS(char[] pattern, int[] suffix, boolean[] prefix) {
        /**
         * 进行匹配一个一个好后缀
         */
        for (int i = 0; i < pattern.length - 1; i++) {
            int j = i;// j 代替 i 从后往前匹配
            int k = 0; // k 代表好后缀的长度
            while (j >= 0 && pattern[j] == pattern[pattern.length - 1 - k]) {
                j--;
                k++;
                suffix[k] = j + 1;
            }
            if (j < 0) {
                prefix[k] = true;
            }
        }
    }

    /**
     * 如果坏后缀为 -1 的话就变为移动很多步;
     * 如果坏后缀在 匹配串里面的第二个 坏字符 比坏字符还后面的话就要移动负数了
     * 但是既然 比坏字符还后面 那么说明坏字符不是最后一个;就一定有好后缀
     */

    /**
     * 进行匹配
     *
     * @param str
     * @param pattern
     */
    public int bm(char[] str, char[] pattern) {
        int[] hash = new int[SIZE]; // 用于快速计算坏字符的位置
        int[] suffix = new int[pattern.length];
        boolean[] prefix = new boolean[pattern.length];
        generateBC(pattern, hash);
        generateGS(pattern, suffix, prefix);
        /**
         * 进行匹配 不断的循环
         */
        int i = 0;
        while (i <= str.length - pattern.length) {
            int j = pattern.length; // 需要记住坏字符的下标
            for (; j >= 0; j--) {
                if (pattern[j] != str[i + j]) {
                    break;
                }
            }
            // 如果小于 0 说明匹配成功没有坏字符
            if (j < 0) {
                return i;
            }

            // 记录坏字符需要移动的位置
            int x = j - hash[str[i + j]];
            int y = 0;

            // 判断是否有好后缀存在
            if (j < pattern.length - 1) {
                y = moveGS(j, pattern.length, suffix, prefix);
            }

            i = i + Math.max(x, y);
        }

        return -1;
    }

    private int moveGS(int j, int pLength, int[] suffer, boolean[] prefix) {
        // 计算好后缀的长度 j 是坏字符 j+1 就是好后缀
        int gsLength = pLength - (j + 1);
        // 如果有好后缀对应的话
        if (suffer[gsLength] != -1) return j + 1 - suffer[gsLength];
        // 查看是否有好前缀对应
        for (int k = j + 2; k < pLength; k++) {
            if (prefix[pLength - k]) {
                /**
                 * k 是当前匹配串的对应的好后缀的下标
                 * 如果正好有前缀对应; 要将其移动到匹配串的 0 号下标
                 * 也就是说需要将 k下标 移动成 0下标 所以移动 k
                 */
                return k;
            }
        }
        return pLength;
    }
}
























