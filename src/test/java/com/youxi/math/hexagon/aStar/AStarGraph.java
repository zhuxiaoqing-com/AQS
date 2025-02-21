package com.youxi.math.hexagon.aStar;


import com.youxi.math.hexagon.HexagonManager;
import com.youxi.math.hexagon.beans.Cell;

import java.util.*;

/**
 * A*就用这个吧
 */
public class AStarGraph {

    public static class Node implements Comparable<Node> {
        public GraphPoint point;
        public int gCost; // 实际代价
        public int hCost; // 启发式代价
        public Node parent;
        public boolean open;//是否在open列表里

        public Node(GraphPoint point) {
            this.point = point;
            this.gCost = 0;
            this.hCost = 0;
            this.parent = null;
        }

        public int fCost() {
            return gCost + hCost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.fCost(), other.fCost());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return Objects.equals(point, node.point);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(point);
        }
    }


    public static List<Node> findPath(Node start, Node end, Graph graph, Set<Integer> obstracleSet) {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        HashMap<Node, Node> closedList = new HashMap<>();

        // 所在的点是阻挡
        if (obstracleSet.equals(start.point.getIndex()) || obstracleSet.equals(end.point.getIndex())) {
            return Collections.emptyList();
        }

        start.open = true;
        openList.add(start);
        closedList.put(start, start);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();


            if (currentNode.equals(end)) {
                return reconstructPath(currentNode);
            }
            List<GraphEdge> graphEdges = graph.getGraphEdges(currentNode.point);
            for (GraphEdge graphEdge : graphEdges) {
                GraphPoint p2 = graphEdge.p2;
                // 邻居是阻挡
                if (obstracleSet.contains(p2.getIndex())) {
                    continue;
                }

                Node neighbor = new Node(p2);

                neighbor.gCost = currentNode.gCost + graphEdge.weight;
                neighbor.hCost = heuristic(neighbor, end);
                Node closeNode = closedList.get(neighbor);
                if (closeNode != null && neighbor.fCost() >= closeNode.fCost()) {
                    continue;
                }

                closedList.put(neighbor, neighbor);

                neighbor.open = true;
                if (closeNode != null && closeNode.open) {
                    openList.remove(neighbor);
                }
                openList.add(neighbor);
                neighbor.parent = currentNode;
            }
        }

        return Collections.emptyList(); // 没有找到路径
    }

    private static int heuristic(Node a, Node b) {
        return a.point.distanceTo(b.point);
    }

    private static List<Node> reconstructPath(Node current) {
        List<Node> totalPath = new ArrayList<>();
        while (current != null) {
            totalPath.add(current);
            current = current.parent;
        }
        Collections.reverse(totalPath);
        return totalPath;
    }


    public static Graph buildGraph(HexagonManager hexagonManager) {
        Cell[] cells = hexagonManager.getCells();

        Graph graph = new Graph();
        for (Cell cell : cells) {
            GraphPoint cellP = new GraphPoint(cell.getIndex());
            List<Cell> neighbors = hexagonManager.getNeighbors(cell);
            for (Cell neighbor : neighbors) {
                graph.addGraphEdge(cellP, new GraphPoint(neighbor.getIndex()));
            }
        }
        return graph;
    }
}
