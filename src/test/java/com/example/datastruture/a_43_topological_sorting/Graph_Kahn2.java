package com.example.datastruture.a_43_topological_sorting;

import java.util.*;

public class Graph_Kahn2 {
	private DirectionGraph graph;
	private int v; // 顶点的个数
	private LinkedList<Integer> adj[];// 邻接表

	public Graph_Kahn2(DirectionGraph graph) {
		this.graph = graph;
		this.v = graph.getV();
		this.adj = graph.getAdj();
	}

	/**
	 * 入度：指向该顶点的边的数量
	 * 出度：以该顶点为起点指向其他顶点的数量
	 * <p>
	 * 如果 s 需要先于 t 执行，
	 * 就添加一条 s 指向 t 的边
	 * t 依赖 s   s->t  t的入度为 1
	 * 如果如果 t的入度为 0 就说明 t 可以直接运行了 因为 t 不需要依赖别人了
	 * <p>
	 * 思路：
	 * 统计所有顶点的入度;
	 * 然后找到入度为0的顶点A, 将A指向的所有顶点的入度 -1; 如果所有入度全部被减完了  说明排序完成
	 * <p>
	 * 怎么打印拓扑顺序呢？
	 */
	public void topoSortByKahn02() {
		// 统计所有的顶点
		Map<Integer, Integer> inDegreeMap = new HashMap<>();

		// 统计所有顶点的入度  (其实就是找到所有它要依赖的 点的数量)
		for (int i = 0; i < adj.length; i++) {
			LinkedList<Integer> vertex = adj[i];
			inDegreeMap.putIfAbsent(i, 0);
			for (Integer outDegree : vertex) {
				inDegreeMap.merge(outDegree, 1, Integer::sum);
			}
		}

		LinkedList<Integer> queue = new LinkedList<>();
		inDegreeMap.forEach((key, value) -> {
			if (value == 0) {
				queue.add(key);
			}
		});

		while (!queue.isEmpty()) {
			// 输出顺序
			Integer poll = queue.poll();
			System.out.print("->" + poll);
			LinkedList<Integer> integers = adj[poll];
			for (Integer vertex : integers) {
				Integer merge = inDegreeMap.merge(vertex, -1, Integer::sum);
				if (merge == 0) {
					queue.add(vertex);
				}
			}
		}
		System.out.println();
		System.out.println(inDegreeMap);

	}

	/**
	 * kahn 算法
	 *
	 * 定义数据结构的时候，如果 s 需要先于 t 执行，那就添加一条 s 指向 t 的边。所以，如果某个顶点的入度为 0，
	 * 也就表示，没有任何顶点必须先于这个顶点执行，那么这个顶点就可以执行了。
	 *
	 */
	/**
	 * 入度：指向该顶点的边的数量
	 * 出度：以该顶点为起点指向其他顶点的数量
	 * <p>
	 * 如果 s 需要先于 t 执行，
	 * 就添加一条 s 指向 t 的边
	 * t 依赖 s   s->t  t的入度为 1
	 * 如果如果 t的入度为 0 就说明 t 可以直接运行了 因为 t 不需要依赖别人了
	 */
	public void topoSortByKahn() {
		/**
		 * i -> w  说明 w 需要依赖于 i
		 *
		 * 这里循环查询所有需要依赖 别的顶点 的 顶点
		 * 吧不需要依赖别的顶点的顶点筛选出来
		 */
		/**
		 * 因为 数组里面的list 是指向别的顶点的 链表
		 */
		int[] inDegree = new int[v]; // 统计每个顶点的入度
		for (int i = 0; i < v; ++i) {
			for (int j = 0; j < adj[i].size(); ++j) {
				/**
				 * w 依赖于 i
				 */
				int w = adj[i].get(j);// i->w
				inDegree[w]++;
			}
		}

		LinkedList<Integer> queue = new LinkedList<>();
		for (int i = 0; i < v; ++i) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}
		while (!queue.isEmpty()) {
			int i = queue.remove();
			System.out.print("->" + i);
			for (int j = 0; j < adj[i].size(); ++j) {
				int k = adj[i].get(j);
				inDegree[k]--;
				if (inDegree[k] == 0) queue.add(k);
			}
		}

		System.out.println();
	}


}





























