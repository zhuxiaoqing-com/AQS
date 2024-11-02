package com.example.datastruture.a_49_Astar_search.AStartGraph;

public class GraphPoint {
    int x, y;

    public GraphPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GraphPoint point = (GraphPoint) obj;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // 可以添加一个方法来计算两个点之间的距离（例如欧几里得距离）
    public long distanceTo(GraphPoint other) {
        int dx = this.x - other.x;
        int dy = this.y - other.y;
        return (long) dx * dx + (long) dy * dy;
    }
}
