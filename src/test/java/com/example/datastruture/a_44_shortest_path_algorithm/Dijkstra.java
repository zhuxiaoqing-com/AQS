package com.example.datastruture.a_44_shortest_path_algorithm;


import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 单源最短路径算法(一个顶点到一个顶点)。
 * dijkstra 算法
 */
public class Dijkstra {
    /**
     * 从顶点 s 到顶点 t 的最短路径
     *
     * @param s vertex
     * @param t vertex
     */

    public void dijkstra(int s, int t) {
        // 前辈 用来还原最短路径
        int[] predecessor = new int[this.v];
        // 记录起始顶点到这个顶点的距离
        Vertex[] vertexs = new Vertex[this.v];

        // 初始化 dist 为无穷大
        for (int i = 0; i < v; i++) {
            vertexs[i] = new Vertex(i, Integer.MAX_VALUE);
        }

        // 小顶堆 priority
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        // 标记是否进入队列
        boolean[] inQueue = new boolean[this.v];
        // 先把起始顶点放到队列中
        queue.add(vertexs[s]);
        vertexs[s].dist = 0;
        inQueue[s] = true;

        while (!queue.isEmpty()) {
            // 取 dist 最小的顶点
            Vertex minVertex = queue.poll();
            // 最短路径产生了
            for (int i = 0; i < adj[minVertex.id].size(); ++i) {
                // 取出一条 minVertex 相连的边
                Edge e = adj[minVertex.id].get(i);
                // minVertex -> nextVertex;
                Vertex nextVertex = vertexs[e.tid];
                // 找到一条到 nextVertex 更短的路径
                if (minVertex.dist + e.w < nextVertex.dist) {
                    // 更新 dist
                    nextVertex.dist = minVertex.dist + e.w;
                    // 更新前驱顶点
                    predecessor[nextVertex.id] = minVertex.id;
                    // 如果没有在队列中
                    if (!inQueue[nextVertex.id]) {
                        // 就把它放到队列中
                        queue.add(nextVertex);
                        inQueue[nextVertex.id] = true;
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







    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    //////////////////  图的定义  ///////////////////
    ///////////////////////////////////////////////
    ///////////////////////////////////////////////


    // 有向有权图的邻接表表示
    private LinkedList<Edge> adj[]; // 邻接表
    private int v; // 顶点个数

    public Dijkstra(int v) {
        this.v = v;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            this.adj[i] = new LinkedList<>();
        }
    }


    /**
     * edge 边
     */
    private class Edge {
        public int sid; // 边的起始顶点编号
        public int tid; // 边的终止顶点编号
        public int w; // 权重

        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }

    // 下面这个类是为了 dijkstra 实现用的
    private class Vertex implements Comparable<Vertex> {
        public int id; // 顶点编号 id
        public int dist; // 从起始顶点到这个顶点的距离

        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }


        /**
         * 按照 dist 从小到大排序
         *
         * @param o
         * @return
         */
        @Override
        public int compareTo(Vertex o) {
            if (o.dist > this.dist) return -1;
            else return 1;
        }
    }

}













