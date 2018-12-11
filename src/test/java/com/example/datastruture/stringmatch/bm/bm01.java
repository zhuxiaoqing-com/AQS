package com.example.datastruture.stringmatch.bm;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

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
                if (str[i + j] != pattern[j]) {
                    break;
                }
                if (j < 0) {
                    return i;// 匹配成功，返回主串与模式串第一个匹配的字符的位置
                }
                // 这里等同于将模式串往后滑动 j-bc[(int)a[i+j]] 位
                i = i + (j - hashTable[(int) str[i + j]]);
            }
        }
        return -1;
    }

    private void hash(char[] pattern, int[] hash) {
        Arrays.fill(hash, -1);
        for (int i = 0; i < pattern.length; i++) {
            hash[pattern[i]] = i;
        }
    }

    public int bmBadChar(String str, String pattern) {
        char[] strArray = str.toCharArray();
        char[] patternArray = pattern.toCharArray();
        int[] hash = new int[SIZE];
        // 进行hash
        hash(patternArray, hash);
        /**
         * 进行匹配
         */
        int i = 0;
        while (i <= strArray.length - patternArray.length) {
            int j = strArray.length - 1;
            for (; j >= 0; j--) {
                // 坏字符
                if (strArray[i + j] != patternArray[j]) {
                    break;
                }
            }
            // 如果匹配成功
            if (j < 0) {
                return i;
            }
            // 计算 坏字符的位置
            i = i + (j - hash[strArray[i + j]]);
        }
        return -1;
    }


    public int bmBadChar02(String str, String pattern) {
        char[] strArray = str.toCharArray();
        char[] patternArray = pattern.toCharArray();
        int[] hash = new int[SIZE];
        // 进行hash
        hash(patternArray, hash);
        /**
         * 进行匹配
         */
        int i = 0;
        while (i <= strArray.length - patternArray.length) {
            /**
             * 因为从后面开始匹配
             */
            int j = patternArray.length - 1;
            for (; j >= 0; j--) {
                /**
                 * 查看坏后缀
                 */
                if (strArray[i + j] != patternArray[j]) {
                    break;
                }
            }
            /**
             * 如果匹配到了
             */
            if (j < 0) {
                return i;
            }
            /**
             * 移动位置 str的坏后缀 - pattern的怀后缀
             */
            i = i + (j - hash[strArray[j + i]]);
        }
        return -1;
    }

    public int bmBadChar03(String str, String pattern) {
        char[] strArray = str.toCharArray();
        char[] patternArray = pattern.toCharArray();
        int[] hash = new int[SIZE];
        // 进行hash
        hash(patternArray, hash);
        /**
         * 进行匹配
         */
        int i = 0;
        /**
         * i 记录着模式串的开始要匹配的位置
         */
        while (i < str.length() - pattern.length()) {
            /**
             * 进行匹配
             */
            // 需要使用 j 来进行保存坏后缀 所以定义放在外面，bm规定从后面开始匹配
            int j = patternArray.length;
            for (; j <= 0; j--) {
                // 匹配坏字符串
                if (strArray[i + j] != patternArray[j]) break;
            }
            // 匹配了就返回
            if (j < 0) {
                return i;
            }
            // 计算坏字符
            i = i + (j - hash[strArray[i + j]]);
        }
        // 没有匹配到就返回 -1
        return -1;
    }


}
























