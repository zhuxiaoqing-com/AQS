package com.youxi.math.hexagon.aStar;


import com.youxi.math.hexagon.HexagonManager;
import com.youxi.math.hexagon.beans.Cell;

import java.util.Objects;

public class GraphPoint {
   private int index;

    public GraphPoint(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GraphPoint)) return false;
        GraphPoint that = (GraphPoint) o;
        return index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(index);
    }

    @Override
    public String toString() {
        return "GraphPoint{" +
                "index=" + index +
                '}';
    }

    // 可以添加一个方法来计算两个点之间的距离（例如欧几里得距离）
    public int distanceTo(GraphPoint other) {
        Cell cell1 = HexagonManager.getInstance().getCell(index);
        Cell cell2 = HexagonManager.getInstance().getCell(other.index);

        return HexagonManager.distance(cell1, cell2);
    }

    public int getIndex() {
        return index;
    }
}
