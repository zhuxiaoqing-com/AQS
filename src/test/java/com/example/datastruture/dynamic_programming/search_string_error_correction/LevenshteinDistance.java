package com.example.datastruture.dynamic_programming.search_string_error_correction;

/**
 * 莱文斯坦距离
 */
public class LevenshteinDistance {

    /**
     * 允许增加 删除 替换
     * 最小距离(不符合的个数越多 距离越大)
     */
    // 存储结果
    int minDist = Integer.MAX_VALUE;

    private char[] a = "mitcmu".toCharArray();
    private char[] b = "mtacnu".toCharArray();

    // 调用 distance01(0,0,0)
    public void distance01(int index1, int index2, int dist) {
        if (index1 == a.length || index2 == b.length) {
            if (index1 < a.length) dist = dist + (a.length - index1);
            if (index2 < b.length) dist = dist + (b.length - index2);
            if (dist < minDist) minDist = dist;
        }

        // 相等的话减
        if (a[index1] == b[index2]) distance01(index1 + 1, index2 + 1, dist);
        else if (a[index1] != b[index2]) {
            // 增加 相当于在 index1 前面增加了一位, 将 index1 与 Index2+1 比较
            distance01(index1, index2 + 1, dist + 1);
            // 删除 相当于将 index1 删除了 将 index1 +1 来与 index2 比较
            distance01(index1 + 1, index2, dist + 1);
            // 替换 两者都加一 相当于 index1和 Index2 相等
            distance01(index1 + 1, index2 + 1, dist + 1);
        }
    }

    /**
     * 1、画递归树
     * 2、根据递归树推出 状态转移方程
     * i,j 可能是 (i-1,j) (i,j-1), (i-1,j-1) 三个状态转换过来的
     * dist(i,j,d) = min((i-1,j)(i,j-1)(i-1,j-1));
     * <p>
     * <p>
     * 如果：a[i]!=b[j]，那么：min_edist(i, j) 就等于：
     * min(min_edist(i-1,j)+1, min_edist(i,j-1)+1, min_edist(i-1,j-1)+1)
     * <p>
     * 如果：a[i]==b[j]，那么：min_edist(i, j) 就等于：
     * min(min_edist(i-1,j)+1, min_edist(i,j-1)+1，min_edist(i-1,j-1))
     * <p>
     * 其中，min 表示求三数中的最小值。
     * <p>
     * min_edist 表示的是 (i,j) 操作完成的操作次数
     * <p>
     * 如果：a[i]!=b[j]，那么：min_edist(i, j) 就等于：
     * min(min_edist(i-1,j)+1, min_edist(i,j-1)+1, min_edist(i-1,j-1)+1)
     * 表示 反正都是不等于 都是需要操作的 就选最小的 不相等可以选择 增加  删除 替换(替换成相等的字母)
     * <p>
     * 如果：a[i]==b[j]，那么：min_edist(i, j) 就等于：
     * min(min_edist(i-1,j)+1, min_edist(i,j-1)+1，min_edist(i-1,j-1))
     * 相等的话 可以选择不移动 就是 min_edist(i-1,j-1)
     * 也可以选择移动 min_edist(i-1,j)+1, min_edist(i,j-1)+1
     * 反正就是选择最小的 相等可以选择 增加 删除 替换(替换的话因为本来就是相等的就没有操作了)
     */


    public int lwstDP(char[] a, int n, char[] b, int m) {
        // 保存着编辑距离
        int[][] minDist = new int[n][m];
        // 初始化第一个
        if (a[0] == b[0]) {
            minDist[0][0] = 0;
        } else {
            minDist[0][0] = 1;
        }
        // 初始化第一行
        for (int j = 1; j < m; ++j) {
            // 因为第一行只能使 a 不断减少 或者 b 不断增加;
            // 所以只能选择 (0,j) = minDist(0,j-1)+1 这里是 j不断增加 因为 j = j-1;
            minDist[0][j] = minDist[0][j - 1];
        }
        // 初始化第一列
        for (int i = 1; i < n; ++i) {
            // 因为第一列只能使 a 不断增加 不能使 b 增加 因为 b 只有一个
            minDist[i][0] = minDist[i - 1][0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i] == b[j]) {
                    int vA = minDist[i][j - 1] + 1;
                    int vB = minDist[i - 1][j] + 1;
                    int vC = minDist[i - 1][j - 1];
                    minDist[i][j] = Math.min(Math.min(vA, vB), vC);
                } else {
                    int vA = minDist[i][j - 1] + 1;
                    int vB = minDist[i - 1][j] + 1;
                    int vC = minDist[i - 1][j - 1] + 1;
                    minDist[i][j] = Math.min(Math.min(vA, vB), vC);
                }
            }
        }

        return minDist[n - 1][m - 1];
    }

}
