package com.example.datastruture.a_49_Astar_search.AStartGraph2;

import io.swagger.models.auth.In;

import java.util.*;

/**
 * 有向有权图的邻接表表示
 */
public class Graph {
    private Map<Integer, List<GraphEdge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(Integer GraphPoint) {
        adjacencyList.putIfAbsent(GraphPoint, new ArrayList<>());
    }

    public void addGraphEdge(Integer p1, Integer p2, int weight) {
        GraphEdge GraphEdge1 = new GraphEdge(p1, p2, weight);
        GraphEdge GraphEdge2 = new GraphEdge(p2, p1, weight);

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
    public List<GraphEdge> getGraphEdges(Integer GraphPoint) {
        return adjacencyList.getOrDefault(GraphPoint, Collections.emptyList());
    }

    // 获取图中所有的顶点  
    public Set<Integer> getVertices() {
        return adjacencyList.keySet();
    }


    public void print() {
        System.out.println("Vertices: " + getVertices());
        for (Integer GraphPoint : getVertices()) {
            System.out.println("GraphEdges of " + GraphPoint + ": " + getGraphEdges(GraphPoint));
        }
    }
}
