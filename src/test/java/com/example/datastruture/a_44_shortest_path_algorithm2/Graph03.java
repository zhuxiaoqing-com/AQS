package com.example.datastruture.a_44_shortest_path_algorithm2;

import java.util.LinkedList;

/**
 * 有向有权图的邻接表表示
 */
public class Graph03 {
    // 邻接表
    private LinkedList<Edge> adj[];
    // 顶点个数
    private int v;

    public Graph03(int v) {
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

    /**
     * 思路：需要一个保存顶点的距离的容器
     * 需要一个保存最短距离的容器 以便保存节点的上一个最短节点是哪个
     * 需要一个 boolean[] 用来保存节点是否已经被遍历过，遍历过了就不再遍历了 去环
     * 需要一个 需要一个队列保存需要遍历的顶点。
     *
     * @param s 起始顶点
     * @param t 结束顶点
     */
    // 从顶点 s 到顶点 t 的最短路径
    public void dijkstra(int s, int t) {
        // 需要存储s 到顶点之间的距离
        Vertex[] vertexes = new Vertex[this.v];
        // 初始化所有的节点
        for (int i = 0; i < this.v; i++) {
            vertexes[i] = new Vertex(i, Integer.MAX_VALUE);
        }
        // 保存前一个最短距离的节点的数组
        int[] predecessor =  new int[this.v];
        /*
         保存需要遍历的顶点的优先级队列 这里使用优先级队列而不是普通队列，极大的减少了遍历顶点的数量
         每次就找离 s 顶点最近的顶点遍历。
          */
        PriorityQueue queue = new PriorityQueue(this.v);
        /*
         用来记录是否已经遍历过了。遍历过了就更新 predecessor 里面的值而不是新加。
         防止一个顶点多次添加到优先级队列。
          */
        boolean[] inqueue = new boolean[this.v];

        // 开始遍历第一个
        vertexes[s].dist = 0;
        inqueue[s] = true;
        queue.add( vertexes[s]);
        

    }


}
