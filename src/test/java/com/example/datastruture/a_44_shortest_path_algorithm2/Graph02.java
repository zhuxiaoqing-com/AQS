package com.example.datastruture.a_44_shortest_path_algorithm2;

import java.util.LinkedList;

/**
 * 有向有权图的邻接表表示
 */
public class Graph02 {
    // 邻接表
    private LinkedList<Edge> adj[];
    // 顶点个数
    private int v;

    public Graph02(int v) {
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
     * Dijkstra 是在终点出队列的时候才结束,因为当终点出队列的时候，终点的 dist 值是优先级队列中所有定点的最小值，
     * 即便再运行下去，终点的 dist 值也不会在被更新了
     *
     * 优先级队列中出来的那个定点一定是最小的了 距离起点。因为是优先级队列的最小的出来的
     * @param s
     * @param t
     */
    // 从顶点 s 到顶点 t 的最短路径
    public void dijkstra(int s, int t) {
        // 用来还原最短路径
        int[] predecessor = new int[this.v];
        Vertex[] vertexes = new Vertex[this.v];
        // 存着顶点从 s 到 t 的路径总长度
        for (int i = 0; i < this.v; ++i) {
            vertexes[i] = new Vertex(i, Integer.MAX_VALUE);
        }
        // 存着即将扫描的数据
        PriorityQueue queue = new PriorityQueue(this.v);
        // 标记是否进入过队列
        boolean[] inqueue = new boolean[this.v];
        // 从 s 开始扫描  应该是广度搜索吧
        vertexes[s].dist = 0;
        queue.add(vertexes[s]);
        inqueue[s] = true;
        while (!queue.isEmpty()) {
            // 取堆顶元素并删除
            Vertex minVertex = queue.poll();
            // 最短路径产生了
            if (minVertex.id == t) break;
            for (int i = 0; i < adj[minVertex.id].size(); i++) {
                Edge edge = adj[minVertex.id].get(i);
                Vertex vertex = vertexes[edge.tid];
                if (minVertex.dist + edge.w < vertex.dist) {
                    vertex.dist = minVertex.dist + edge.w;
                    predecessor[vertex.id] = minVertex.id;
                    if (inqueue[vertex.id]) {
                        queue.update(vertex);
                    } else {
                        inqueue[vertex.id] = true;
                        queue.add(vertex);
                    }
                }
            }
        }
        System.out.println(s);
        print(s, t, predecessor);
    }

    public void print(int s, int t, int[] predecessor) {
        if (t == s) {
            return;
        }
        print(s, predecessor[t], predecessor);
        System.out.println("-> " + t);
    }


    private void pri2nt(int s, int t, int[] predecessor) {
    }
}
