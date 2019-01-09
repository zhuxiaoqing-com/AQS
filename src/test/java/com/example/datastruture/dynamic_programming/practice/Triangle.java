package com.example.datastruture.dynamic_programming.practice;

/**
 * 杨辉三角
 */
public class Triangle {

    public void f(int[][] triangle) {
        int num = triangle.length;
        int max = triangle[num - 1].length;

        int[][] status = new int[num][max];
        // 初始化第一个
        status[0][0] = triangle[0][0];
        for (int i = 1; i < num; ++i) {
            // 必须全部都存
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) {
                    int i1 = status[i - 1][0];
                    int i2 = triangle[i][j];
                    status[i][0] = i1 + i2;
                } else if (j == triangle[i].length - 1) {
                    // 如果是最后一个的话
                    int i1 = status[i - 1][triangle[i].length - 2];
                    int i2 = triangle[i][j];
                    status[i][j] = i1 + i2;
                } else {
                    /*
                     不是最前面也不是最后面
                     中间的话, 就需要与本身还有自己本身前一个比较
                      */
                    int i1 = status[i - 1][j - 1];
                    int j1 = status[i - 1][j];
                    int i2 = triangle[i][j];
                    int min = Math.min(i1, j1);
                    status[i][j] = min + i2;
                }
            }
        }

        // 存完以后开始打印出路径
        // 找到最小值
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = num - 1; i >= 0; --i) {
            if (status[num - 1][i] > 0) {
                if (status[num - 1][i] < min) {
                    min = status[num - 1][i];
                    index = i;
                }
            }
        }

        for (int i = num - 1; i >= 1; --i) {
            // 需要输出的值
            int out = -1;
            int i1 = triangle[i][index];
            if (index == 1) {
                // 是第一个
                out = triangle[i - 1][index];
                index = 1;
            } else if (index == triangle[i - 1].length - 1) {
                // 是最后一个
                out = triangle[i - 1][index - 1];
                index = index - 1;
            } else {
                // 在中间的话
                int center1 = triangle[i - 1][index - 1];
                int center2 = triangle[i - 1][index];
                int t = min - i1;
                if (center1 == t) {
                    out = center1;
                    index = index - 1;
                } else {
                    out = center2;
                    index = index;
                }
                System.out.println(out);// 输出
            }
        }


        System.out.println(triangle[0][0]);

    }

}
