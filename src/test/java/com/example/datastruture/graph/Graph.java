package com.example.datastruture.graph;

import java.util.LinkedList;

/**
 * 无向图
 */
public class Graph {
    private int v;// 顶点的个数
    private LinkedList<Integer> adj[];// 邻接表

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * edge 边
     * 增加 边
     *
     * 无向图一条边存两次
     */
    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }


	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}

	public LinkedList<Integer>[] getAdj() {
		return adj;
	}

	public void setAdj(LinkedList<Integer>[] adj) {
		this.adj = adj;
	}
}
