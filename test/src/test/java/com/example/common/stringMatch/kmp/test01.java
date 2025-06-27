package com.example.common.stringMatch.kmp;

import org.junit.Test;

import java.util.Arrays;

public class test01 {
    public void makeNext(String pattern, int[] next) {
        int q, k; // q; 模板字符串下标，k:最大前后缀长度
        int m = pattern.length(); // 模板字符串长度
        next[0] = 0; // 模板字符串的第一个字符的最大前后缀长度为 0;
        // for 循环，从第二个字符开始，依次计算每一个字符对应的 next 值
        for (q = 1, k = 0; q < m; q++) {
            // 递归的求出 pattern[0]...pattern[q] 的最大的相同的前后缀长度 k
            while (k > 0 && pattern.charAt(q) != pattern.charAt(k)) {
                k = next[k - 1];
            }
            // 如果相等，那么最大相同前后缀长度加 1
            if (pattern.charAt(q) == pattern.charAt(k)) {
                k++;
            }
            next[q] = k;
        }

    }

    public int[] makeNext05(String pattern) {
        int[] next = new int[pattern.length() + 1];
        // i: 字符串的下标(从1开始) j: 前缀下标
        int i, j;
        // 第一个位置不需要匹配 本来就是 0
        next[0] = 0;
        for (i = 1, j = 0; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
                j = next[j - 1];
            }
            if (pattern.charAt(j) == pattern.charAt(i)) {
                next[i] = ++j;
            } else {
                next[i] = j;
            }
        }
        return next;
    }


    public int[] makeNext04(String pattern) {
        int[] next = new int[pattern.length() + 1];
        // i: 字符串的下标(从1开始) j: 前缀下标
        int i, j;
        // 第一个位置不需要匹配 本来就是 0
        next[0] = 0;
        for (i = 1, j = 0; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
                j = next[j - 1];
            }
            if (pattern.charAt(j) == pattern.charAt(i)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public int[] makeNext02(String pattern) {
        int[] next = new int[pattern.length() + 1];
        int i = 0, j = -1;
        // 第一个位置不需要匹配 本来就是 0
        next[0] = -1;
        while (i < pattern.length()) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }


    @Test
    public void fun1() {
        String pattern = "aabaabaaa";
        int[] next01 = makeNext05(pattern);
        int[] next02 = makeNext04(pattern);
        System.out.println(Arrays.toString(next01));
        System.out.println(Arrays.toString(next02));
    }

    public boolean KMP(String str, String pat) {
        int[] next = makeNext02(pat);
        for (int i = 0, k = 0; i < str.length(); i++) {
            while (k > 0 && str.charAt(i) != pat.charAt(k)) {
                k = next[k-1];
            }
            if(str.charAt(i) == pat.charAt(k)) {
                k++;
            }
            if(k == pat.length()) {
                return true;
            }
        }
        return false;
    }

    public int KMP01(String str, String pat) {
        int i = 0;
        int j = 0;
        int[] next = makeNext02(pat);
        while (i < str.length() && j < pat.length()) {
            if (j == -1 || str.charAt(i) == pat.charAt(j)) {
                j++;
                i++;
            } else {
                j = next[j];
            }
        }
        if (j == pat.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

}
