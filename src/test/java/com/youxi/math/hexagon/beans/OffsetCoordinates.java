package com.youxi.math.hexagon.beans;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OffsetCoordinates)) return false;
        OffsetCoordinates that = (OffsetCoordinates) o;
        return col == that.col && row == that.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }


    @Override
    public String toString() {
        return "OffsetCoordinates{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}
