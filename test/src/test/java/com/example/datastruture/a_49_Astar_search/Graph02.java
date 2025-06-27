package com.example.datastruture.a_49_Astar_search;

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
        public int f; // 新增f(i) = g(i) + h(i)
        public int x, y; //新增：顶点在地图中的坐标(x,y)

        public Vertex(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.f = Integer.MAX_VALUE;
            this.dist = Integer.MAX_VALUE;
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

        public void clear() {
        }
    }

    // Graph 类的成员变量，在构造函数中初始化
    Vertex[] vertexes = new Vertex[this.v];

    // 新增一个方法，添加顶点的坐标
    public void addVertex(int id, int x, int y) {
        vertexes[id] = new Vertex(id, x, y);
    }

    /**
     * A*
     *
     * @param s
     * @param t
     */
    // 从顶点 s 到顶点 t 的最短路径
    public void aStar(int s, int t) {
        // 用来还原最短路径
        int[] predecessor = new int[this.v];
        // 用来判断是否在优先级队列中
        boolean[] inQueue = new boolean[this.v];
        // 用 Vertex.f 来构建最小堆
        PriorityQueue queue = new PriorityQueue(this.v);
        queue.add(vertexes[s]);
        vertexes[s].dist = 0;
        /*
         f(i) = g(i) + h(i)
         g(i) 是 顶点到起点的位置
         h(i) 是 估算的位置
         因为 f 的主要作用是用来出优先级队列的，所以这里可以不用计算直接为 0
          */
        vertexes[s].f = 0;
        while (!queue.isEmpty()) {
            Vertex poll = queue.poll();
            for (int i = 0; i < adj[poll.id].size(); i++) {
                Edge edge = adj[poll.id].get(i);
                Vertex nextVertex = vertexes[edge.tid];
                // 如果 f 到起点的距离g  和该顶点到终点的距离的估算值h
                /**
                 * 这里只能这样, 不能通过 f 比较这里也没有通过 f 比较必须通过
                 * dist 加上 w 来比较
                 * 因为 f 是总距离要更新还是要 dist 来更新的
                 */
                if(nextVertex.dist > poll.dist + edge.w) {
                    predecessor[nextVertex.id] = poll.id;
                    nextVertex.dist = poll.dist + edge.w;
                    nextVertex.f = nextVertex.dist + hManhattan(nextVertex, vertexes[t]);
                    if(inQueue[nextVertex.id]) {
                        queue.update(nextVertex);
                    } else {
                        queue.add(nextVertex);
                    }
                }
                // 只要遍历到终点就结束
                if(nextVertex.id == t) {
                    // 清空queue ，才能推出 while 循环
                    queue.clear();
                    break;
                }
            }
        }
        // 输出路径
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

    /**
     * 哈曼顿函数
     * <p>
     * 欧几里得
     * 根号 (x1-y1)^2 + (x2-y2)^2 ...
     *
     * @param v1
     * @param v2
     * @return
     */
    int hManhattan(Vertex v1, Vertex v2) {
        return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
    }
}

/**
 * 在 Dijkstra 算法的实现思路中，我们用一个优先级队列，来记录已经遍历到的顶点以及这个顶点与起点的路径长度。顶点与
 * 起点路径长度越小，就越先被从优先级队列中取出来扩展，从图中的例子(图在极客时间里面自己看)可以看出。尽管我们找的是从
 * s 到 t 的路线，但是最先被搜索道德定点依次是 1,2,3.通过肉眼来观察，这个搜索方向跟我们期望的路线方向是反着的，
 * 路线搜索的方向明显"跑偏"了。
 * <p>
 * 之所以会"跑偏"，那是因为我们是按照顶点与起点的路径长度的大小，来安排出队列顺序的。与起点越近的顶点，就会越早出队列。
 * 我们并没有考虑到这个顶点到终点的距离，所以，在地图中，尽管 1,2,3 三个顶点离起始顶点最近，但离终点却越来越远。
 * <p>
 * 吐过我们综合更多的因素，把这个顶点到终点可能还要走多远，也考虑进去，综合来判断哪个顶点该先出队列，那是不是就可以避免
 * "跑偏"呢？
 * <p>
 * 当我们遍历到某个顶点的时候，从起点走到这个顶点的路径长度是确定的，我们记作 g(i)i表示顶点编号。但是，从这个顶点
 * 到终点的路径长度，我们是未知的。虽然确切的值无法提前知道，但是我们可以用其他估计值来代替。
 * <p>
 * 这里我们可以通过这个顶点跟终点之间的直线距离，也就是欧几里得距离，来近似地估计这个顶点跟终点的路径长度(注意，路径长度跟直线距离是两个概念)。
 * 我们把这个距离记作 h(i)。专业的叫法是 启发函数(heuristic function)。因为欧几里得距离的计算公式，会涉及到比较耗时的
 * 开根号计算，所以，我们一般通过另外一个更加简单的距离计算公式，那就是曼哈顿距离(Manhattan distance)。曼哈顿距离是两点之前横纵
 * 坐标的距离之和。计算过的过程只设计加减法、符号位反转，所以比欧几里得距离更加高效。
 * <p>
 * 原来只是单纯的通过顶点与起点之间的路径长度g(i)，来判断谁先出队列，现在有了顶点到终点的路径长度估计值，我们通过两者
 * 之和 f(i) = g(i) + h(i)，来判断哪个顶点该最先出队列。综合两部分，我们就能有效的避免刚刚讲的"跑偏"。这里f(i)的专业
 * 叫法是 估价函数(evaluation function).
 */

















