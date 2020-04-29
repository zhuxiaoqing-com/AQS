package com.example.datastruture.a_43_topological_sorting;

import java.util.LinkedList;

/**
 * 有向图
 */
public class DirectionGraph {
    private int v;// 顶点的个数
    private LinkedList<Integer> adj[];// 邻接表

    public DirectionGraph(int v) {
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
     * 有向图
     */
    public void addEdge(int s, int t) {
        adj[s].add(t);
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
