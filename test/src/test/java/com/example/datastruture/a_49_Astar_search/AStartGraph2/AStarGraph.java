package com.example.datastruture.a_49_Astar_search.AStartGraph2;

import com.example.datastruture.a_49_Astar_search.AStar;


import java.util.*;

/**
 * A*就用这个吧
 */
public class AStarGraph {

    static class Node implements Comparable<Node> {
        Integer id;
        int gCost; // 实际代价
        Node parent;
        boolean open;//是否在open列表里

        public Node(Integer id) {
            this.id = id;
            this.gCost = 0;
            this.parent = null;
        }

        public int fCost() {
            return gCost;
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
            return Objects.equals(id, node.id);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }
    }

    public static List<Node> findPath(Node start, Node end, Graph graph) {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        HashMap<Node, Node> closedList = new HashMap<>();
        //Map<Node, Node> cameFrom = new HashMap<>();

        start.open = true;
        openList.add(start);
        closedList.put(start, start);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();


            if (currentNode.equals(end)) {
                return reconstructPath(currentNode);
            }
            List<GraphEdge> graphEdges = graph.getGraphEdges(currentNode.id);
            for (GraphEdge graphEdge : graphEdges) {
                Integer p2 = graphEdge.p2;
                Node neighbor = new Node(p2);

                neighbor.gCost = currentNode.gCost + graphEdge.weight;
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

    private static List<Node> reconstructPath(Node current) {
        List<Node> totalPath = new ArrayList<>();
        while (current != null) {
            totalPath.add(current);
            current = current.parent;
        }
        Collections.reverse(totalPath);
        return totalPath;
    }
}
   /* public static void main(String[] args) {
        Node start = new Node(0, 0);
        Node goal = new Node(4, 4);
        Set<Node> obstacles = new HashSet<>(Arrays.asList(
                new Node(1, 2),
                new Node(2, 1),
                new Node(3, 3)
        ));

        List<Node> path = findPath(start, goal, obstacles);

        if (!path.isEmpty()) {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.println("(" + node.x + ", " + node.y + ")");
            }
        } else {
            System.out.println("No path found.");
        }
    }
*/

