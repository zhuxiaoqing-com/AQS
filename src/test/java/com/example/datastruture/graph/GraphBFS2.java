package com.example.datastruture.graph;

import java.util.*;

/**
 * 无向图 广度优先搜索
 */
public class GraphBFS2 {
	private Graph graph;

	public GraphBFS2(Graph graph) {
		this.graph = graph;
	}

	/**
	 * 广度优先搜索
	 *
	 * @param s 起始顶点
	 * @param t 终止顶点
	 */
	public void bfs(int s, int t) {
		if (s == t) return;

		int v = graph.getV();
		LinkedList<Integer>[] adj = graph.getAdj();
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

			LinkedList<Integer> edge = adj[w];
			for (int i = 0; i < edge.size(); i++) {
				int q = edge.get(i);
				if (visited[q]) {
					continue;
				}
				prev[q] = w;
				if (q == t) {
					//print(prev, s, t);
					return;
				}
				visited[q] = true;
				queue.add(q);
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
	public void bfs02(int s, int t) {
		if (s == t) {
			return;
		}


		int v = graph.getV();
		LinkedList<Integer>[] adj = graph.getAdj();

		// 记录已经访问过得节点
		boolean[] visited = new boolean[v];
		visited[s] = true;
		// 将要访问的节点
		LinkedList<Integer> willVisit = new LinkedList<>();
		willVisit.add(s);
		// 路径 存储搜索路径 由终点节点找到起点; 所以就需要终点节点索引上一个节点; 直到 -1(或者 s(起点)); 就是说到终点了
		int[] prevPath = new int[v];
		Arrays.fill(prevPath, -1);

		outermost:
		while (!willVisit.isEmpty()) {
			int poll = willVisit.poll();
			LinkedList<Integer> edge = adj[poll];
			// 遍历该顶点指向的所有顶点
			for (Integer summit : edge) {
				// 如果该节点已经访问过了
				if (!visited[summit]) {
					continue;
				}
				// poll -> summit
				prevPath[summit] = poll;
				// 找到终点了
				if (summit == t) {
					break outermost;
				}
				visited[summit] = true;
				willVisit.add(summit);
			}
			/*for (int i = 0; i < edge.size(); i++) {
				int summit = edge.get(i);
				// 如果该节点已经访问过了
				if (!visited[summit]) {
					continue;
				}
				// poll -> summit
				prevPath[summit] = poll;
				// 找到终点了
				if (summit == t) {
					break outermost;
				}
				visited[summit] = true;
				willVisit.add(summit);
			}*/
		}

		// 等于-1 代表没有找到终点
		if (prevPath[t] == -1) {
			return;
		}

		// 打印路径
	/*	int lastSummit = t;
		LinkedList<Integer> path = new LinkedList<>();
		path.add(t);
		while (lastSummit != s) {
			int i = prevPath[lastSummit];
			path.addFirst(i);
			lastSummit = i;
		}

		// 打印路径
		System.out.println(path);*/
	}


	/**
	 * @param s 起始节点
	 * @param t 结束节点
	 */
	public void bfs03(int s, int t) {
		if (s == t) {
			return;
		}


		int v = graph.getV();
		LinkedList<Integer>[] adj = graph.getAdj();

		// 记录已经访问过得节点
		boolean[] visited = new boolean[v];
		visited[s] = true;
		// 将要访问的节点
		LinkedList<Integer> willVisit = new LinkedList<>();
		willVisit.add(s);
		// 路径 存储搜索路径 由终点节点找到起点; 所以就需要终点节点索引上一个节点; 直到 -1(或者 s(起点)); 就是说到终点了
		int[] prevPath = new int[v];
		Arrays.fill(prevPath, -1);

		outermost:
		while (!willVisit.isEmpty()) {
			int poll = willVisit.poll();
			LinkedList<Integer> edge = adj[poll];
			// 遍历该顶点指向的所有顶点

			for (int i = 0; i < edge.size(); i++) {
				int summit = edge.get(i);
				// 如果该节点已经访问过了
				if (!visited[summit]) {
					continue;
				}
				// poll -> summit
				prevPath[summit] = poll;
				// 找到终点了
				if (summit == t) {
					break outermost;
				}
				visited[summit] = true;
				willVisit.add(summit);
			}
		}

		// 等于-1 代表没有找到终点
		if (prevPath[t] == -1) {
			return;
		}

		// 打印路径
	/*	int lastSummit = t;
		LinkedList<Integer> path = new LinkedList<>();
		path.add(t);
		while (lastSummit != s) {
			int i = prevPath[lastSummit];
			path.addFirst(i);
			lastSummit = i;
		}

		// 打印路径
		System.out.println(path);*/
	}

}



















