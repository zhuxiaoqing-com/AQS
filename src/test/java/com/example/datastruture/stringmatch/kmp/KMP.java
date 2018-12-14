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


    private int[] getNext02(char[] pattern) {
        int[] next = new int[pattern.length];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < pattern.length; i++) {
            // 如果 k 已经是 0 了就从头开始匹配
            while (k >= 0 && pattern[k + 1] != pattern[i]) {
                /**
                 * 不能匹配就移动到 前一个可以匹配的前缀,直到前缀已经移除完毕
                 * 因为有很多前缀，next 记录的只是最长前缀，除了最长前缀应该还有次长，次次长
                 *
                 * 精华：
                 * 因为最长前缀 和 最长后缀的元素是一样的。如果要找 最长后缀的次长后缀 的次长前缀。
                 * 最长后缀 == 最长前缀
                 *  最长后(前)缀的次长后缀 的次长前缀。
                 *  将最长前缀当做一个字符串 字符串的最长前缀已经保存在了 next 数组里面
                 *  而最长前缀的次长前缀已经保存在了 next 数组里面;
                 *  所以最终就变成了
                 *  k = next[k];
                 */
                /**
                 * 因为[0, i-1](字符串) [0, q-1](字符串的最长可匹配前缀);
                 * i != q; 不匹配
                 * 所以 就拿[0,q-1]的次长后缀的 可匹配前缀 进行比较。
                 *
                 */
                k = next[k];
            }
            if (pattern[k + 1] == pattern[i]) {
                k++;
            }
        }
        return next;
    }
}






































