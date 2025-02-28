package com.example.datastruture.a_49_Astar_search.AStartGraph3;

import java.util.*;

/**
 * 有向有权图的邻接表表示
 */
public class Graph {

    ThreadLocal<PriorityQueue<AStarGraph3.Node>> openList = new ThreadLocal<>();
    ThreadLocal<HashMap<AStarGraph3.Node, AStarGraph3.Node>> closedList= new ThreadLocal<>();
    ThreadLocal<HashMap<GraphPoint, AStarGraph3.Node>> cacheNodeMap= new ThreadLocal<>();

    public PriorityQueue<AStarGraph3.Node> getOpenList() {
        PriorityQueue<AStarGraph3.Node> nodes = openList.get();
        if(nodes == null){
            nodes = new PriorityQueue<>();
            openList.set(nodes);
        }
        nodes.clear();
        return nodes;
    }

    public HashMap<AStarGraph3.Node, AStarGraph3.Node> getClosedList() {
        HashMap<AStarGraph3.Node, AStarGraph3.Node> nodes = closedList.get();
        if(nodes == null){
            nodes = new HashMap<>();
            closedList.set(nodes);
        }
        nodes.clear();
        return nodes;
    }

    public HashMap<GraphPoint, AStarGraph3.Node> geCacheNodeMap() {
        HashMap<GraphPoint, AStarGraph3.Node> nodes = cacheNodeMap.get();
        if(nodes == null){
            nodes = new HashMap<>();
            cacheNodeMap.set(nodes);

            for (GraphPoint vertex : getVertices()) {
                nodes.put(vertex, new AStarGraph3.Node(vertex));
            }
        }
        for (AStarGraph3.Node value : nodes.values()) {
            value.clear();
        }
        return nodes;
    }

   /* public PriorityQueue<AStarGraph3.Node> openList = new PriorityQueue<>();
    public HashMap<AStarGraph3.Node, AStarGraph3.Node> closedList = new HashMap<>();
    public HashMap<GraphPoint, AStarGraph3.Node> cacheNodeMap = new HashMap<>();*/

    private Map<GraphPoint, List<GraphEdge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(GraphPoint GraphPoint) {
        adjacencyList.putIfAbsent(GraphPoint, new ArrayList<>());
    }

    public void addGraphEdge(GraphPoint p1, GraphPoint p2) {
        int distance = p2.distanceTo(p1);
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

    public static void main(String[] args) {
        Graph graph = new Graph();
        GraphPoint p1 = new GraphPoint(1, 2);
        GraphPoint p2 = new GraphPoint(4, 6);
        GraphPoint p3 = new GraphPoint(7, 8);

        graph.addVertex(p1);
        graph.addVertex(p2);
        graph.addVertex(p3);

        // 添加边时，我们可以使用GraphPoint类的distanceTo方法来计算权重  
        graph.addGraphEdge(p1, p2);
        graph.addGraphEdge(p2, p3);

        // 注意：我们不需要添加p3到p1的边，因为图是无向的

        System.out.println("Vertices: " + graph.getVertices());
        for (com.example.datastruture.a_49_Astar_search.AStartGraph3.GraphPoint GraphPoint : graph.getVertices()) {
            System.out.println("GraphEdges of " + GraphPoint + ": " + graph.getGraphEdges(GraphPoint));
        }
    }

    public void print() {
        System.out.println("Vertices: " + getVertices());
        for (com.example.datastruture.a_49_Astar_search.AStartGraph3.GraphPoint GraphPoint : getVertices()) {
            System.out.println("GraphEdges of " + GraphPoint + ": " + getGraphEdges(GraphPoint));
        }
    }
}
