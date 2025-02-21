package com.youxi.math.hexagon.beans;

/**
 * 偏移坐标
 */
public class OffsetCoordinates {
    private int col;
    private int row;

    public OffsetCoordinates(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }


    @Override
    public String toString() {
        return "OffsetCoordinates{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}
