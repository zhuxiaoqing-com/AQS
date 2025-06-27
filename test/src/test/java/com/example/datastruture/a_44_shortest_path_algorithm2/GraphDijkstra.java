package com.example.datastruture.a_44_shortest_path_algorithm2;

import java.util.LinkedList;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/29 17:33
 * @Description:
 */
public class GraphDijkstra {
	// 邻接表
	private LinkedList<Edge> adj[];
	// 顶点个数
	private int v;

	public GraphDijkstra(int v) {
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


	public LinkedList<Edge>[] getAdj() {
		return adj;
	}

	public int getV() {
		return v;
	}
}
