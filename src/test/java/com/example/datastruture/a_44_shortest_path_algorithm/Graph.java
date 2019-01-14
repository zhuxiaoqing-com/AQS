package com.example.datastruture.a_44_shortest_path_algorithm;

import java.util.LinkedList;

/**
 * 图
 */
public class Graph {
    // 有向有权图的邻接表表示
    private LinkedList<Edge> adj[]; // 邻接表
    private int v; // 顶点个数

    public Graph(int v) {
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

















