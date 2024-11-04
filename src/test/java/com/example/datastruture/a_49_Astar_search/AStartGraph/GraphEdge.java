package com.example.datastruture.a_49_Astar_search.AStartGraph;

import java.util.Objects;

public class GraphEdge {
    GraphPoint p1, p2;
    int weight;

    public GraphEdge(GraphPoint p1, GraphPoint p2, int weight) {
        this.p1 = p1;
        this.p2 = p2;
        this.weight = weight;
    }

    @Override
    public String toString() {
        //return p1 + " - " + p2 + " (weight: " + weight + ")";
        return p2.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GraphEdge)) return false;
        GraphEdge graphEdge = (GraphEdge) o;
        return Objects.equals(p1, graphEdge.p1) && Objects.equals(p2, graphEdge.p2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2);
    }
}
