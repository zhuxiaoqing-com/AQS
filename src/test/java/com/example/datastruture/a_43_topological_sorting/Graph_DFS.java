package com.example.datastruture.a_43_topological_sorting;

import java.util.LinkedList;

public class Graph_DFS {
    private int v; // 顶点的个数
    private LinkedList<Integer> adj[];// 邻接表

    public Graph_DFS(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        //s 先于 t,边 s->t
        adj[s].add(t);
    }

    /**
     * 拓扑排序通过DFS
     */
    public void topoSortByDFS() {
        // 先构建逆邻接表，边 s->t 表示，s 依赖于 t, t 先于 s
        LinkedList<Integer>[] inverseAdj = new LinkedList[v];

        // 申请空间
        for (int i = 0; i < v; ++i) {
            inverseAdj[i] = new LinkedList<>();
        }

        // 通过邻接表生成逆邻接表
        for (int i = 0; i < v; ++i) {
            for (int j = 0; j < adj[i].size(); ++j) {
                int w = adj[i].get(j); // i->w
                inverseAdj[w].add(i); // w->i
            }
        }

        boolean[] visited = new boolean[v];
        // 深度优先遍历图
        for (int i = 0; i < v; ++i) {
            if (visited[i] == false) {
                visited[i] = true;
                dfs(i, inverseAdj, visited);
            }
        }

    }

    private void dfs(int vertex, LinkedList<Integer>[] inverseAdj, boolean[] visited) {
        for (int i = 0; i < inverseAdj[vertex].size(); ++i) {
            int w = inverseAdj[vertex].get(i);
            if (visited[w] == true) continue;
            visited[w] = true;
            dfs(w, inverseAdj, visited);
        } // 先把 vertex 这个顶点可达的所有顶点都打印出来之后，再打印它自己
        System.out.println("->" + vertex);
    }

    /**
     * 拓扑排序的dfs 深度优先  bfs广度优先
     *
     * 原理
     * 1、第一部分是通过邻接表构造逆邻接表。邻接表中，边 s->t 表示 s 先于 t 执行，也就是 t 要依赖 s.
     * 在逆邻接表中，边 s->t 表示 s 依赖于 t. s 后于 t 执行。为什么这么转化呢? 这个跟我们这个算法的
     * 实现思想有关。
     *
     *2、第二部分是这个算法的核心，也就是递归处理每个顶点。对于顶点 vertex 来说,我们先输出它可达的所有顶点,
     * 也就是说，先把它依赖的所有的顶点输出了，然后再输出自己。
     */

    /**
     * 拓扑排序通过DFS
     */
    public void topoSortByDFS02() {
        LinkedList<Integer>[] inverseAdj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            inverseAdj[i] = new LinkedList();
        }
        // 1、创建逆邻接表
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj[v].size(); j++) {
                Integer vertex = adj[v].get(j);
                inverseAdj[vertex].add(i);
            }
        }

        // 创建递归备忘录 visit 访问
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs2(i, inverseAdj, visited);
            }
        }
    }

    private void dfs2(int vertex, LinkedList<Integer>[] inverseAdj, boolean[] visited) {
        for (int i = 0; i < inverseAdj[vertex].size(); i++) {
            Integer integer = inverseAdj[vertex].get(i);
            if (visited[integer]) continue;
            visited[integer] = true;
            dfs(integer, inverseAdj, visited);
        }
        System.out.println("->" + vertex);
    }
}





























