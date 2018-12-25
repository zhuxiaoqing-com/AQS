package com.example.datastruture.backtracking_algorithm;

/**
 * 八皇后
 */
public class EightQueen {
    // 全局或成员变量，下标表示行，值表示 queen 存储在哪一列
    int[] result = new int[8];

    public void cal8queen(int row) {
        if (row == 8) {
            printQueen(result);
            // 8 行棋子都放好了，已经没法再往下递归了，所以就 return
            return;
        }
        // 每一行都有8中放法
        for (int column = 0; column < 8; ++column) {
            // 有些放法不满足要求
            if (isOK2(row, column)) {
                // 第 row 行的棋子放到了 column 列
                result[row] = column;
                // 考察下一行
                cal8queen(row + 1);
            }
        }
    }

    /**
     * 打印八皇后
     *
     * @param result
     */
    private void printQueen(int[] result) {
        for (int row = 0; row < 8; row++) {
            for(int column = 0;column<8; column++) {
                if(result[row] == column) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 判断 row 行 column 列放置是否合适
     *
     * @param row    行
     * @param column 列
     * @return
     */
    private boolean isOK(int row, int column) {
        return false;
    }

    /**
     * 因为 row 以及 row 后面的都不需要检测 对角线也是
     * 所以只需要检测 row - 1 开始
     *
     * @param row
     * @param column
     * @return
     */
    private boolean isOK2(int row, int column) {
        int leftUp = column - 1;
        int rightUp = column + 1;
        for (int i = row - 1; i >= 0; i--) {
            if (result[i] == column) return false;
            if (leftUp > 0) {
                if (result[i] == leftUp) return false;
            }
            if (rightUp > 0) {
                if (result[i] == rightUp) return false;
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }
}
