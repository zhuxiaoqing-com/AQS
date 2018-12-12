package com.example.datastruture.stringmatch.bm;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private void generateGS(char[] pattern, int[] suffix, boolean[] prefix) {
        Arrays.fill(suffix, -1);

        for (int i = 0; i < pattern.length - 1; i++) { // b[0, i]
            int j = i;
            int k = 0;// 公共后缀子串长度
            // 与 b[0, m-1] 求公共后缀子串
            while (j >= 0 && pattern[j] == pattern[pattern.length - 1 - k]) {
                --j;
                ++k;
                suffix[k] = j + 1; // j+1 表示公共后缀子串在 b[0, i] 中的起始下标
            }
            if (j == -1) prefix[k] = true; // 如果是公共后缀子串也是模式串的前缀子串
        }
    }

    private void generateGS01(char[] pattern, int[] suffix, boolean[] prefix) {
        Arrays.fill(suffix, -1);
        /**
         * 进行匹配后缀 从 [0,length-1] 到[length-1,length-1]
         * 将每一个前缀匹配
         */
        for (int i = 0; i < pattern.length - 1; i++) {
            int j = i;
            int k = 0; // 后缀的长度
            while (j >= 0 && pattern[j] == pattern[pattern.length - 1 - k]) {
                j--;
                k++;
                // 因为 k 是从 0 开始的所以 需要 k++; 以后再记录
                // j+1 是因为 之前已经 j-- 过了
                suffix[k] = j + 1;
            }
            if (j == -1) prefix[k] = true; // 如果公共后缀子串也是模式串的前缀子串
        }
    }

    private void generateGS02(char[] pattern, int[] suffix, boolean[] prefix) {
        Arrays.fill(suffix, -1);
        /**
         * 这里使用了非常人性化的方式，将前缀全部拆分出来， cabcab
         * 分为 c,ca,cab,cabc,cabca
         * 然后依次和 b,ab,cab,bcab,abcab 比较
         * 而 abcab 只要 往前移动下标就可以完全匹配了
         * 如果中间有比如 cab 比较 abcab 就将匹配的 b,ab,cab 都给记录下来
         * 这样的话就做到了每一个都匹配了
         */
        for (int i = 0; i < pattern.length - 1; i++) {
            int j = i;
            int k = 0;// 记录当前 后缀的长度
            while (j >= 0 && pattern[j] == pattern[pattern.length - 1 - k]) {
                j--;
                k++;
                suffix[k] = j + 1;
            }
            if (j == -1) {
                prefix[k] = true;
            }
        }

    }

    /**
     * 好后缀
     *
     * @param str
     * @param pattern
     * @return
     */
    public int bmGoodSuffix(char[] str, char[] pattern) {
        int[] hash = new int[SIZE];
        hash(pattern, hash); // 构建坏字符哈希表
        int[] suffix = new int[pattern.length]; // 后缀
        boolean[] prefix = new boolean[pattern.length];// 是否为前缀
        int i = 0; // j 表示主串与模式串匹配的第一个字符
        while (i <= str.length - pattern.length) {
            // 记录坏后缀的下标 从后面开始匹配
            int j = str.length - 1;
            for (; j < 0; j--) {
                // 寻找坏字符串
                if (str[j + i] != pattern[j]) {
                    break;
                }
            }
            if (j < 0) {
                return i;
            }
            /**
             * 根据 j 坏后缀的下标查找 i 的下标
             * 因为 j - str 对应的坏后缀的下标 就要移动的下标
             */
            int x = j - hash[str[j + i]];
            int y = 0;
            // 如果有好后缀的话
            if (j < pattern.length - 1) {
                y = moveByGS(j, pattern.length, suffix, prefix);
            }
            i = i + Math.max(x, y);
        }
        return -1;
    }

    /**
     * j 表示坏字符对应的模式串中的字符下标; m 表示模式串长度
     *
     * @param j
     * @param pLength
     * @param suffix
     * @param prefix
     * @return
     */
    private int moveByGS(int j, int pLength, int[] suffix, boolean[] prefix) {
        // 取得 好后缀的长度
        int k = pLength - 1 - j;
        /**
         * 因为 suffix[k]= ? ; ? 对应的是好后缀的 cba, c 对应的上一个 c;
         * 而 j 是坏字符，坏字符的后面的一个下标就是 好后缀 cab 的 c;
         */
        if (suffix[k] != -1) return j - suffix[k] + 1;
        // 如果没有就看是否有前缀符合的
        /**
         *  r = j + 2; 因为 j+1 是完成的好后缀
         *  又因为没有符合的好后缀，如果就 j+2;直接对应是否有符合条件的前缀
         */
        for (int r = j + 2; r < pLength; r++) {
            // 直接减少看是否有前缀
            if (prefix[pLength - r]) {
                return r;
            }
        }
        return pLength;
    }

    private void test(char[] pattern, int[] suffix, boolean[] prefix) {
        Arrays.fill(suffix, -1);
        /**
         * 前缀和后缀进行匹配
         */
        for (int i = 0; i < pattern.length - 1; i++) {
            int k = 0;
            int j = i;
            while (j >= 0 && pattern[j] == pattern[pattern.length - 1 - k]) {
                j--;
                k++;
                suffix[k] = j + 1;
            }
            if (j == -1) {
                prefix[k] = true;
            }
        }
    }
}
























