package com.example.datastruture.a_49_Astar_search;

import java.util.*;

/**
 * A*就用这个吧
 */
public class AStar3 {

   static class Node implements Comparable<Node> {
        int x, y;
        int gCost; // 实际代价
        int hCost; // 启发式代价
        Node parent;
        boolean open;//是否在open列表里

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
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
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static List<Node> findPath(Node start, Node goal, Set<Node> obstacles) {
        if(obstacles.contains(start) || obstacles.contains(goal)) {
            System.out.println("AStar3 起点或者终点是阻挡");
            return Collections.emptyList();
        }
        PriorityQueue<Node> openList = new PriorityQueue<>();
        HashMap<Node,Node> closedList = new HashMap<>();
        //Map<Node, Node> cameFrom = new HashMap<>();

        start.open = true;
        openList.add(start);
        closedList.put(start,start);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();


            if (currentNode.equals(goal)) {
                return reconstructPath(currentNode);
            }

            for (int[] direction : DIRECTIONS) {
                int newX = currentNode.x + direction[0];
                int newY = currentNode.y + direction[1];

                Node neighbor = new Node(newX, newY);

                if (obstacles.contains(neighbor)) {
                    continue;
                }
                neighbor.gCost = currentNode.gCost + 1;
                neighbor.hCost = heuristic(neighbor, goal);
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
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y); // 曼哈顿距离
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

    public static void main(String[] args) {
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

    public boolean test(AStar.MapInfo mapInfo) {
        Set<Node> obstacles = new HashSet<>();
        for (int y = 0; y < mapInfo.maps.length; y++) {
            for (int x = 0; x < mapInfo.maps[y].length; x++) {
                int a = mapInfo.maps[y][x];
                if(a != 1) {
                    continue;
                }
                obstacles.add(new Node(x, y));
            }
        }


        Node start = new Node(mapInfo.start.x, mapInfo.start.y);
        Node end = new Node(mapInfo.end.x, mapInfo.end.y);
        long begin = System.nanoTime();
        List<Node> path = findPath(start, end, obstacles);
        double cost = (System.nanoTime() - begin) * 0.000001;
        System.err.println("AStar3cost : " + cost);

        if (!path.isEmpty()) {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.print("(" + node.x + ", " + node.y + ")");
            }
            System.out.println();
            return true;
        } else {
            System.out.println("No path found.");
            return false;
        }
    }

}
