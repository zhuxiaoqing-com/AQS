package com.example.datastruture.dynamic_programming.search_string_error_correction;

/**
 * 最长公共子字符串长度
 */
public class LongestCommonSubstringLength {
    /**
     * 状态转移方程
     * <p>
     * 后面的可以由前面的 增加 删除 不变化转换过来
     * if(i != j)
     * max(i,j,min) = max(i-1,j,max) max(i,j-1,max) max(i-1,j-1,max)
     * <p>
     * if(i == j)
     * max(i,j,min) = max(i-1,j,max) max(i,j-1,max) max(i-1,j-1,max)+1
     * <p>
     * <p>
     * 而最长公共子串的大小，表示两个字符串相似
     */

    public int lcs(char[] a, int n, char[] b, int m) {
        int[][] maxlcs = new int[n][m];
        // 初始化 maxlcs[0][0];
        if (a[0] == b[0]) {
            maxlcs[0][0] = 1;
        } else {
            maxlcs[0][0] = 0;
        }
        // 行
        for (int i = 1; i < m; i++) {
            maxlcs[0][i] = maxlcs[0][i - 1];
        }
        // 列
        for (int i = 1; i < n; i++) {
            maxlcs[i][0] = maxlcs[i - 1][0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i] == b[j]) {
                    int vA = maxlcs[i - 1][j];
                    int vB = maxlcs[i][j - 1];
                    int vC = maxlcs[i - 1][j - 1] + 1;
                    int max = Math.max(Math.max(vA, vB), vC);
                    maxlcs[i][j] = max;
                } else {
                    int vA = maxlcs[i - 1][j];
                    int vB = maxlcs[i][j - 1];
                    int vC = maxlcs[i - 1][j - 1];
                    int max = Math.max(Math.max(vA, vB), vC);
                    maxlcs[i][j] = max;
                }
            }
        }
        return maxlcs[n-1][m-1];
    }
}
