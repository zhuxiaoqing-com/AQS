package com.example.datastruture.a_44_shortest_path_algorithm2;

import java.util.LinkedList;

/**
 * 有向有权图的邻接表表示
 */
public class Graph {
    // 邻接表
    private LinkedList<Edge> adj[];
    // 顶点个数
    private int v;

    public Graph(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            this.adj[i] = new LinkedList<>();
        }
    }

    // 添加一条边
    public void addEdge(int s, int t, int w) {
        this.adj[s].add(new Edge(s, t, w));
    }

    private class Edge {
        // 边的起始顶点编号
        public int sid;
        // 边的终止顶点编号
        public int tid;
        // 权重
        public int w;

        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }

    // 下面这个类是为了 dijkstra 实现用的
    private class Vertex {
        // 顶点编号 ID
        public int id;
        // 从起始顶点到这个顶点的距离
        public int dist;

        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }

    // 因为 java 提供的优先级队列，没有暴露更新数据的接口，所以我们需要重新实现一个
    private class PriorityQueue {
        // 根据 vertex.dist 构建小顶堆
        private Vertex[] nodes;
        private int count;

        public PriorityQueue(int v) {
            this.nodes = new Vertex[v + 1];
            this.count = v;
        }

        public Vertex poll() {
            //todo 空实现
            return nodes[0];
        }

        public void add(Vertex vertex) {
            //todo 空实现
        }


        // 更新结点的值，并且从下往上推化，重新符合堆的定义 时间复杂度 O(logn)
        public void update(Vertex vertex) {

        }

        public boolean isEmpty() {
            return true;
        }
    }

    // 从顶点 s 到顶点 t 的最短路径
    public void dijkstra(int s, int t) {
        // 用来还原最短路径
        int[] predecessor = new int[this.v];
        Vertex[] vertexes = new Vertex[this.v];

        for (int i = 0; i < this.v; ++i) {
            vertexes[i] = new Vertex(i, Integer.MAX_VALUE);
        }

        // 小顶堆
        PriorityQueue queue = new PriorityQueue(this.v);
        // 标记是否进入过队列
        boolean[] inqueue = new boolean[this.v];

        vertexes[s].dist = 0;
        queue.add(vertexes[s]);
        inqueue[s] = true;

        while (!queue.isEmpty()) {
            // 取堆顶元素并删除
            Vertex minVertex = queue.poll();
            // 最短路径产生了
            if (minVertex.id == t) break;
            for (int i = 0; i < adj[minVertex.id].size(); ++i) {
                // 取出一条 minVertex 相连的边
                Edge e = adj[minVertex.id].get(i);
                // minVertex -> nextVertex
                Vertex nextVertex = vertexes[e.tid];
                if (minVertex.dist + e.w < nextVertex.dist) {
                    // 更新 next 的 dist
                    nextVertex.dist = minVertex.dist + e.w;
                    predecessor[nextVertex.id] = minVertex.id;
                    if (inqueue[nextVertex.id] == true) {
                        // 更新队列中的 dist 值
                        queue.update(nextVertex);
                    } else {
                        // 更新队列中的 dist 值
                        queue.update(nextVertex);
                        inqueue[nextVertex.id] = true;
                    }
                }
            }
        }
        // 输出最短路径
        System.out.println(s);
        print(s, t, predecessor);
    }

    private void print(int s, int t, int[] predecessor) {
        if (s == t) return;
        print(s, predecessor[t], predecessor);
        System.out.println("->" + t);
    }
}
