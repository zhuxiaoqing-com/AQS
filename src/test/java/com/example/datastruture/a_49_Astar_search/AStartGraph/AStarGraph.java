package com.example.datastruture.a_49_Astar_search.AStartGraph;

import com.example.datastruture.a_49_Astar_search.AStar;

import java.util.*;

/**
 * A*就用这个吧
 */
public class AStarGraph {

   static class Node implements Comparable<Node> {
       GraphPoint point;
        int gCost; // 实际代价
        int hCost; // 启发式代价
        Node parent;
        boolean open;//是否在open列表里

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



    public static List<Node> findPath(Node start, Node end, Graph graph) {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        HashMap<Node,Node> closedList = new HashMap<>();
        //Map<Node, Node> cameFrom = new HashMap<>();

        start.open = true;
        openList.add(start);
        closedList.put(start,start);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();


            if (currentNode.equals(end)) {
                return reconstructPath(currentNode);
            }
            List<GraphEdge> graphEdges = graph.getGraphEdges(currentNode.point);
            for (GraphEdge graphEdge : graphEdges) {
                GraphPoint p2 = graphEdge.p2;
                Node neighbor = new Node(p2);

                neighbor.gCost = currentNode.gCost + graphEdge.weight;
                neighbor.hCost = heuristic(neighbor, end);
                Node closeNode = closedList.get(neighbor);
                if(closeNode != null && neighbor.fCost() >= closeNode.fCost()) {
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
        return Math.abs(a.point.x - b.point.x) + Math.abs(a.point.y - b.point.y); // 曼哈顿距离
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

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void test(AStar.MapInfo mapInfo) {
        Graph graph = new Graph();
        for (int y = 0; y < mapInfo.maps.length; y++) {
            for (int x = 0; x < mapInfo.maps[y].length; x++) {
                int a = mapInfo.maps[y][x];
                if (a == 1) {
                    continue;
                }

                GraphPoint graphPoint = new GraphPoint(x, y);
                graph.addVertex(graphPoint);
                for (int[] direction : DIRECTIONS) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];
                    int n;
                    try {
                        n = mapInfo.maps[newY][newX];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        n = 1;
                    }
                    if(n == 1){
                        continue;
                    }
                    graph.addGraphEdge(graphPoint, new GraphPoint(newX, newY));
                }
            }
        }

       // graph.print();

        Node start = new Node(new GraphPoint(mapInfo.start.x, mapInfo.start.y));
        Node end = new Node(new GraphPoint(mapInfo.end.x, mapInfo.end.y));
        long begin = System.nanoTime();
        List<Node> path = findPath(start, end, graph);
        double cost = (System.nanoTime() - begin) * 0.000001;
        System.err.println("AStarGraph : " + cost);

        if (!path.isEmpty()) {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.print("(" + node.point.x + ", " + node.point.y + ")");
            }
        } else {
            System.out.println("No path found.");
        }
    }

}
