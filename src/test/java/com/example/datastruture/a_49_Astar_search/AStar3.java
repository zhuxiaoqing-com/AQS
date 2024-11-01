package com.example.datastruture.a_49_Astar_search;
    import java.util.*;

public class AStar3 {

    class Node implements Comparable<Node> {
        int x, y;
        int gCost; // 实际代价
        int hCost; // 启发式代价
        Node parent;

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

    class AStarPathfinding {
        private  final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public  List<Node> findPath(Node start, Node goal, Set<Node> obstacles) {
            PriorityQueue<Node> openList = new PriorityQueue<>();
            Set<Node> closedList = new HashSet<>();
            Map<Node, Node> cameFrom = new HashMap<>();

            openList.add(start);

            while (!openList.isEmpty()) {
                Node currentNode = openList.poll();

                if (currentNode.equals(goal)) {
                    return reconstructPath(cameFrom, currentNode);
                }

                closedList.add(currentNode);

                for (int[] direction : DIRECTIONS) {
                    int newX = currentNode.x + direction[0];
                    int newY = currentNode.y + direction[1];

                    Node neighbor = new Node(newX, newY);

                    if (closedList.contains(neighbor) || obstacles.contains(neighbor)) {
                        continue;
                    }

                    int tentativeGCost = currentNode.gCost + 1;

                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    } else if (tentativeGCost >= neighbor.gCost) {
                        continue;
                    }

                    cameFrom.put(neighbor, currentNode);
                    neighbor.gCost = tentativeGCost;
                    neighbor.hCost = heuristic(neighbor, goal);
                }
            }

            return Collections.emptyList(); // 没有找到路径
        }

        private  int heuristic(Node a, Node b) {
            return Math.abs(a.x - b.x) + Math.abs(a.y - b.y); // 曼哈顿距离
        }

        private  List<Node> reconstructPath(Map<Node, Node> cameFrom, Node current) {
            List<Node> totalPath = new ArrayList<>();
            while (current != null) {
                totalPath.add(current);
                current = cameFrom.get(current);
            }
            Collections.reverse(totalPath);
            return totalPath;
        }

        public  void main(String[] args) {
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
    }
}
