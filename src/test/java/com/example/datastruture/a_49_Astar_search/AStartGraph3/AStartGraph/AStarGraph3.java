package com.example.datastruture.a_49_Astar_search.AStartGraph3.AStartGraph;

import com.example.datastruture.a_49_Astar_search.AStar;
import org.apache.poi.ss.formula.functions.T;

import java.util.*;

/**
 * A*就用这个吧
 */
public class AStarGraph3 {

   static class Node implements Comparable<Node> {
       GraphPoint point;
        int gCost; // 实际代价
        int hCost; // 启发式代价
        Node parent;
        boolean open;//是否在open列表里

       private Node() {

       }
       public static Node create(HashMap<GraphPoint, Node> cacheNodeMap, GraphPoint point) {
           return cacheNodeMap.get(point);
       }


        public Node(GraphPoint point) {
            this.point = point;
            this.gCost = 0;
            this.hCost = 0;
            this.parent = null;
        }

       public void clear() {
           this.gCost = 0;
           this.hCost = 0;
           this.parent = null;
           this.open = false;
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

    public static List<Node> findPath(GraphPoint startP, GraphPoint endP, Graph graph) {
        PriorityQueue<Node> openList = graph.getOpenList();
        HashMap<Node, Node> closedList = graph.getClosedList();
        HashMap<GraphPoint, Node> cacheNodeMap = graph.geCacheNodeMap();


        if (graph.getGraphEdges(startP).isEmpty() || graph.getGraphEdges(endP).isEmpty()) {
            return Collections.emptyList();
        }


        //Map<Node, Node> cameFrom = new HashMap<>();

        Node start = Node.create(cacheNodeMap, startP);
        Node end = Node.create(cacheNodeMap, endP);

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
                Node neighbor = Node.create(cacheNodeMap, p2);

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

    public boolean test(AStar.MapInfo mapInfo) {
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

       /* Node start = new Node();
        Node end = new Node();*/
        long begin = System.nanoTime();
        int index = 100;
        ArrayList<List<Node>> objects = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            List<Node> path = findPath(new GraphPoint(mapInfo.start.x, mapInfo.start.y), new GraphPoint(mapInfo.end.x, mapInfo.end.y), graph);
            objects.add(path);
        }
        List<Node> path = objects.get(0);

        for (List<Node> object : objects) {
            for (int i = 0; i < object.size(); i++) {
                if(!path.get(i).equals(object.get(i))) {
                    System.out.println("缓存出错了！！！！");
                    return false;
                }
            }
        }


        double cost = (System.nanoTime() - begin) * 0.000001;
        System.err.println("AStarGraph : " + cost);

        if (!path.isEmpty()) {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.print("(" + node.point.x + ", " + node.point.y + ")");
            }
            return true;
        } else {
            System.out.println("No path found.");
            return false;
        }
    }


    public void testThread(AStar.MapInfo mapInfo) {
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

        int index = 10;
        for (int i = 0; i < index; i++) {
            new Thread(() -> {
                long begin = System.nanoTime();
                while (true) {
                    try {
                        Thread.sleep(300);
                        text01(mapInfo, graph);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static boolean text01(AStar.MapInfo mapInfo, Graph graph) {
        int index = 100;
        ArrayList<List<Node>> objects = new ArrayList<>();
        for (int i = 0; i < index; i++) {
            List<Node> path = findPath(new GraphPoint(mapInfo.start.x, mapInfo.start.y), new GraphPoint(mapInfo.end.x, mapInfo.end.y), graph);
            objects.add(path);
        }
        List<Node> path = objects.get(0);

        for (List<Node> object : objects) {
            for (int i = 0; i < object.size(); i++) {
                if (!path.get(i).equals(object.get(i))) {
                    System.out.println("缓存出错了！！！！");
                    return true;
                }
            }
        }
        return false;
    }

}
