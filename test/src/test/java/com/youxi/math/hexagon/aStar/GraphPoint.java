package com.youxi.math.hexagon.aStar;

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

    public int getIndex() {
        return index;
    }
}
