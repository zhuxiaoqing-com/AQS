package com.example.datastruture.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图 广度优先搜索
 */
public class GraphDFS2 {
	private Graph graph;

	public GraphDFS2(Graph graph) {
		this.graph = graph;
		v = graph.getV();
		adj = graph.getAdj();
	}

	int v;
	LinkedList<Integer>[] adj;

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
		fount= false;

		/**
		 * 需要 prev 记录搜索路径
		 * 需要 visited 记录已经访问过的 顶点
		 */
		int[] prev = new int[v];
		Arrays.fill(prev, -1);
		boolean[] visited = new boolean[v];

		doDfs(prev, visited, s, t);

		// 打印路径
		int lastSummit = t;
		LinkedList<Integer> path = new LinkedList<>();
		path.add(t);
		while (lastSummit != s) {
			int i = prev[lastSummit];
			path.addFirst(i);
			lastSummit = i;
		}

		// 打印路径
		System.out.println(path);
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
			if (!visited[w]) {
				prev[w] = s;
				doDfs(prev, visited, w, t);
			}
		}
	}


	public void dfs2(int s, int t) {
		fount= false;

		/**
		 * 访问过的顶点
		 */
		boolean[] visited = new boolean[v];
		visited[s] = true;

		int[] prevPath = new int[v];
		Arrays.fill(prevPath, -1);
		doDfs2(s, t, prevPath, visited);

		// 打印路径
		int lastSummit = t;
		LinkedList<Integer> path = new LinkedList<>();
		path.add(t);
		while (lastSummit != s) {
			int i = prevPath[lastSummit];
			path.addFirst(i);
			lastSummit = i;
		}

		// 打印路径
		System.out.println(path);
	}

	private void doDfs2(int curr, int t, int[] prevPath, boolean[] visited) {
		if (fount) {
			return;
		}
		if (curr == t) {
			fount = true;
			return;
		}
		LinkedList<Integer> list = adj[curr];
		for (Integer degree : list) {
			if(visited[degree]){
				continue;
			}
			prevPath[degree] = curr;
			visited[degree] = true;
			doDfs2(degree, t, prevPath, visited);
		}
	}


}



















