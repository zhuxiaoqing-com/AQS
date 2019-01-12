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
     *
     *
     * 如果：a[i]!=b[j]，那么：min_edist(i, j) 就等于：
     * min(min_edist(i-1,j)+1, min_edist(i,j-1)+1, min_edist(i-1,j-1)+1)
     *
     * 如果：a[i]==b[j]，那么：min_edist(i, j) 就等于：
     * min(min_edist(i-1,j)+1, min_edist(i,j-1)+1，min_edist(i-1,j-1))
     *
     * 其中，min 表示求三数中的最小值。
     *
     * 为什么 a[i]!=b[j] 和 a[i]==b[j] 的最后一个不相同呢 因为
     */
}
