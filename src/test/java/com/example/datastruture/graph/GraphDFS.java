package com.example.datastruture.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图 广度优先搜索
 */
public class GraphDFS {
    private int v;// 顶点的个数
    private LinkedList<Integer> adj[];// 邻接表

    public GraphDFS(int v) {
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
     * 用来标记是否找到结束节点
     */
    boolean fount;

    /**
     * 深度优先算法
     *
     * @param s 起始顶点
     * @param t 结束顶点
     */
    public void dfs(int s, int t) {
        /**
         * 需要 prev 记录搜索路径
         * 需要 visited 记录已经访问过的 顶点
         */
        int[] prev = new int[v];
        Arrays.fill(prev, -1);
        boolean[] visited = new boolean[v];

        doDfs(prev, visited, s, t);
    }

    private void doDfs(int[] prev, boolean[] visited, int s, int t) {
        if (fount) {
            return;
        }
        visited[s] = true;
        // 这里就是搜索 比较是否是要找的顶点
        if (s == t) {
            fount = true;
            return;
        }
        /**
         * 开始递归
         */
        for (int i = 0; i < adj[s].size(); i++) {
            Integer w = adj[s].get(i);
            if(!visited[w]) {
                prev[w] = s;
                doDfs(prev, visited, s, t);
            }
        }
    }

}



















