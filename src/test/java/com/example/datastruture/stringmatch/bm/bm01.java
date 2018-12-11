package com.example.datastruture.stringmatch.bm;

import org.junit.Test;

import java.util.Arrays;

public class bm01 {
    private static final int SIZE = 256;// 全局变量或成员变量

    /**
     * @param pattern   模式串
     * @param hashTable 散列表
     */
    private void generateBC(char[] pattern, int[] hashTable) {
        Arrays.fill(hashTable, -1);
        for (int i = 0; i < pattern.length; i++) {
            int ascii = (int) pattern[i];
            hashTable[ascii] = i;
        }
    }

    /**
     * 掌握了坏字符规则之后，我们先把 BM 算法代码的大框架写好，先不考虑好后缀规则，仅用坏字符规则，
     * 并且不考虑 si-xi 计算得到的移动位数可能会出现负数的情况
     */
    public int bm(char[] str, char[] pattern) {
        int[] hashTable = new int[SIZE];
        // 构建坏字符哈希表
        generateBC(pattern, hashTable);
        int i = 0;// i 表示主串与模式串对齐的第一个字符
        while (i <= str.length - pattern.length) {
            int j;
            // 模式串从后往前匹配
            for (j = pattern.length - 1; j >= 0; --j) {
                // 获取坏字符对应模式串中的下标是 j
                if(str[i+j] != pattern[j]) {
                    break;
                }
                if(j<0) {
                    return i;// 匹配成功，返回主串与模式串第一个匹配的字符的位置
                }
                // 这里等同于将模式串往后滑动 j-bc[(int)a[i+j]] 位
                i = i+(j-hashTable[int ])
            }
        }
    }
}
























