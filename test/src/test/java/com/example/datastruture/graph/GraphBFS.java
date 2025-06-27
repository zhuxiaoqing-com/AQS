package com.example.datastruture.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图 广度优先搜索
 */
public class GraphBFS {
    private int v;// 顶点的个数
    private LinkedList<Integer> adj[];// 邻接表

    public GraphBFS(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * edge 边
     * 增加 边
     * <p>
     * 无向图一条边存两次
     */
    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }


    /**
     * 广度优先搜索
     *
     * @param s 起始顶点
     * @param t 终止顶点
     */
    public void bfs(int s, int t) {
        if (s == t) return;
        /**
         * 访问过的顶点
         */
        boolean[] visited = new boolean[v];
        visited[s] = true;
        /**
         * 存储已经被访问、但相连的顶点还没有被访问的顶点。
         */
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        /**
         * 记录搜索路径，当我们从顶点 s 开始，广度优先搜索到顶点 t 后，prev 数组中存储的就是搜索路径。
         * 不过，这个路径是反向存储的。prev[w] 存储的是，顶点 w 是从哪个前驱顶点遍历过来的.比如，我们通过
         * 顶点 2 的邻接表访问到顶点 3，那 prev[3] = 2;
         */
        int[] prev = new int[v];
        Arrays.fill(prev, -1);
        while (queue.size() != 0) {
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); i++) {
                int q = adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }

    }

    /**
     * 递归打印 s -> t 的路径
     *
     * @param prev 路径数组
     * @param s    起始节点
     * @param t    结尾节点
     */
    private void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.println(t + " ");
    }

    /**
     * @param s 起始节点
     * @param t 结束节点
     */
    private void bfs02(int s, int t) {
        /**
         * 访问过的节点 防止重复访问
         */
        boolean[] visited = new boolean[v];
        visited[s] = true;
        /**
         * queue 存储已经访问过了但是相连的顶点还没有访问过的节点
         */
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        /**
         * 元素表示该下标代表的顶点 是通过 该元素代表的顶点 找到的
         * 因为 被顶点连接的定点有两个 是通过同一个定点找到的 所以要这样
         */
        int[] prev = new int[v];
        Arrays.fill(prev, -1);

        while (queue.size() > 0) {
            Integer w = queue.poll();
            for (int i = 0; i < adj[w].size(); i++) {
                Integer n = adj[w].get(i);
                if (n == t) {
                    print2(prev, s, t);
                    return;
                }
                queue.add(n);
                visited[n] = true;
                // 存储的值是 w -> n
                /**
                 * 这表示我们通过 w 找到了 n
                 */
                prev[n] = w;
            }
        }
    }

    private void print2(int[] prev, int s, int t) {
        if (prev[t] != -1 && s != t) {
            print2(prev, s, prev[t]);
        }
        System.out.println(t + " ");
    }
}



















