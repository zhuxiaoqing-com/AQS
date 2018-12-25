package com.example.datastruture.backtracking_algorithm;

/**
 * 八皇后
 */
public class EightQueen02 {
    // row(行)是下标 column(列)是值
    int[] result = new int[8];

    public void cal8Queens() {
        cal8queens(0);
    }

    private void cal8queens02(int row) {
        /**
         * 如果到了 8 就进行打印
         */
        if (row >= 8) {
            printQueens(result);
            return;
        }
        /**
         * 就和树一样的散开 得到所有可能的排序
         */
        for (int column = 0; column < 8; column++) {
            if (isOK(row, column)) {
                result[row] = column;
                cal8queens02(row + 1);
            }
        }
    }


    private void cal8queens(int row) {
        // 8个旗子都放置好了，打印结果
        if (row >= 8) {
            printQueens(result);
            return;
        }
        /**
         * 开始 one by one column
         */
        for (int column = 0; column < 8; column++) {
            if (isOK(row, column)) {
                result[row] = column;
                /**
                 * 这里 不 break 就是为了找出所有满足条件的八皇后的方法
                 */
                cal8queens(row + 1);
            }
        }

    }

    /**
     * 打印
     *
     * @param result
     */
    private void printQueens(int[] result) {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (result[row] == column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * 是否满足条件
     *
     * @param row    行
     * @param column 列
     * @return 返回值
     */
    private boolean isOK(int row, int column) {
        /**
         * 从当前 row 开始搜索，因为 对角的计算从 row 开始递减会很简单
         * 计算 row - 1 所在的 column 是否在 column 的 column
         *
         * 具体还是要看图 不然不会
         */
        int leftUp = column - 1;
        int rightUp = column + 1;
        for (int i = row - 1; i >= 0; i--) {
            if (result[i] == column) return false;
            if (leftUp >= 0) {
                if (result[i] == leftUp) return false;
            }
            if (rightUp < 8) {
                if (result[i] == rightUp) return false;
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }
}
