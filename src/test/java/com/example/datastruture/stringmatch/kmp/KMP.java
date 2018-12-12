package com.example.datastruture.stringmatch.kmp;

public class KMP {
    public int kmp(char[] str, char[] pattern) {
        int[] next = getNext(pattern);
        int j = 0;
        for (int i = 0; i < str.length; i++) {
            while (i > 0 && str[i] != pattern[j]) {
                j = next[j - 1] + 1;
            }
            if (str[i] == pattern[j]) {
                ++j;
            }
            if (j == pattern.length) {
                return i - pattern.length + 1;
            }
        }
        return -1;
    }

    private int[] getNext(char[] pattern) {
        int[] next = new int[pattern.length];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < pattern.length; i++) {
            while (k != -1 && pattern[k + 1] != pattern[i]) {
                k = next[k];
            }
            if (pattern[k + 1] == pattern[i]) {
                k++;
            }
            next[i] = k;
        }
        return next;
    }

    private int[] getNext01(char[] pattern) {
        int[] next = new int[pattern.length];
        /**
         * 因为 0 是前缀 并不是后缀
         */
        next[0] = -1;
        /**
         * k 是前缀
         * 后缀不断的和前缀比较
         */
        int k = -1;
        /**
         * 因为后缀不包含 0 下标
         */
        for (int i = 1; i < pattern.length; i++) {
            /**
             * 如果不匹配
             * 就查找是不是模式串里面还有其他的匹配前缀;
             */
            while (k >= 0 && pattern[k + 1] != pattern[i]) {
                // 取出对应的下一个 k 的下标
                k = next[k];
            }
            if (pattern[k + 1] == pattern[i]) {
                k++;
            }
            next[i] = k;
        }
        return next;
    }
}






































