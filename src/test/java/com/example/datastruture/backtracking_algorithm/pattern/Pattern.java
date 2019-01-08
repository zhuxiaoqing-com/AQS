package com.example.datastruture.backtracking_algorithm.pattern;

/**
 * 正则表达式
 */
public class Pattern {
    private boolean matched = false;
    private char[] pattern; // 正则表达式
    private int plen; // 正则表达式长度

    public void init(char[] pattern, int plen) {
        this.pattern = pattern;
        this.plen = plen;
    }

    // 文本字符串及长度
    public boolean match(char[] text, int tlen) {
        matched = false;
        rmatch(0, 0, text, tlen);
        return matched;
    }


    /**
     * @param ti   当前需要匹配的字符串的 text_index
     * @param pj   正则的下标 pattern_index
     * @param text 需要匹配的字符串
     * @param tlen 下标
     */
    private void rmatch(int ti, int pj, char[] text, int tlen) {
        if (matched) return; // 如果已经匹配了，就不要再继续递归了
        // 文本串也到了结尾了
        if (pj == plen) {
            if (ti == tlen) matched = true;
            return;
        }

        // 匹配任意个字符
        if (pattern[pj] == '*') {
            for (int k = 0; k <= tlen - ti; ++k) {
                rmatch(ti + k, pj + 1, text, tlen);
            }
            // ? 匹配 0个或者 1 个字符
        } else if (pattern[pj] == '?') {
            rmatch(ti, pj + 1, text, tlen);
            rmatch(ti + 1, pj + 1, text, tlen);
        } else if (ti < tlen && pattern[pj] == text[ti]) {// 纯字符串才匹配
            rmatch(ti + 1, pj + 1, text, tlen);
        }
    }


    public void rmatch02(int ti, int pi, char[] text, int tlen) {
        if (plen == pi) {
            if (ti == tlen) matched = true;
            return;
        }
        // * 匹配零个或者多个
        if (pattern[pi] == '*') {
            for (int i = 0; i < tlen - ti; i++) {
                rmatch(ti + i, pi + 1, text, tlen);
            }
            // ? 匹配零个或者一个
        } else if (pattern[pi] == '?') {
            rmatch(ti, pi + 1, text, tlen);
            rmatch(ti + 1, pi + 1, text, tlen);
        } else if (ti < tlen && pattern[pi] == text[ti]) {
            rmatch(ti + 1, pi + 1, text, tlen);
        }
    }
}
