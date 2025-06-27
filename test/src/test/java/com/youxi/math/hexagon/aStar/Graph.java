package com.youxi.math.hexagon.aStar;


import com.youxi.math.hexagon.HexagonManager;
import com.youxi.math.hexagon.beans.Cell;

import java.util.*;

/**
 * 有向有权图的邻接表表示
 */
public class Graph {
    HexagonManager hexagonManager;
    private Map<GraphPoint, List<GraphEdge>> adjacencyList;

    public Graph(HexagonManager hexagonManager) {
        this.hexagonManager = hexagonManager;
        adjacencyList = new HashMap<>();
    }

    public Graph buildGraph() {
        Cell[] cells = hexagonManager.getCells();

        Graph graph = new Graph(hexagonManager);
        for (Cell cell : cells) {
            GraphPoint cellP = new GraphPoint(cell.getIndex());
            for (Integer neighbor : cell.getNeighbors()) {
                graph.addGraphEdge(cellP, new GraphPoint(neighbor));
            }
        }
        return graph;
    }

    public void addVertex(GraphPoint GraphPoint) {
        adjacencyList.putIfAbsent(GraphPoint, new ArrayList<>());
    }

    public void addGraphEdge(GraphPoint p1, GraphPoint p2) {
        int distance = hexagonManager.distance(p1.getIndex(), p2.getIndex());
        GraphEdge GraphEdge1 = new GraphEdge(p1, p2, distance);
        GraphEdge GraphEdge2 = new GraphEdge(p2, p1, distance);

        adjacencyList.putIfAbsent(p1, new ArrayList<>());
        adjacencyList.putIfAbsent(p2, new ArrayList<>());

        if (adjacencyList.get(p1).contains(GraphEdge1)) {
            return;
        }
        if (adjacencyList.get(p2).contains(GraphEdge2)) {
            return;
        }

        adjacencyList.get(p1).add(GraphEdge1);
        adjacencyList.get(p2).add(GraphEdge2);
    }

    // 获取与给定点直接相连的所有边（注意这里返回的是GraphEdge列表，而不是GraphPoint列表）
    public List<GraphEdge> getGraphEdges(GraphPoint GraphPoint) {
        return adjacencyList.getOrDefault(GraphPoint, Collections.emptyList());
    }

    // 获取图中所有的顶点  
    public Set<GraphPoint> getVertices() {
        return adjacencyList.keySet();
    }

    public HexagonManager getHexagonManager() {
        return hexagonManager;
    }
}
