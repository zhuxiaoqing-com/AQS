package com.example.datastruture.a_43_topological_sorting;

import io.swagger.models.auth.In;

import java.util.*;
import java.util.stream.Collectors;

public class Graph_KahnObj extends DirectionGraph {
	private Map<Integer, LinkedList<Integer>> adjMap = new HashMap<>();// 邻接表

	/**
	 * edge 边
	 * 增加 边
	 * <p>
	 * 有向图
	 */
/*	public void addEdge(int s, int t) {
		LinkedList<Integer> linkedList = adjMap.computeIfAbsent(s, a -> new LinkedList<>());
		linkedList.add(t);
	}*/
	public void addEdge(int s, int t) {
		LinkedList<Integer> linkedList = adjMap.computeIfAbsent(s, a -> new LinkedList<>());
		linkedList.add(t);
	}


	public Map<Integer, LinkedList<Integer>> getAdjMap() {
		return adjMap;
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
		for (Map.Entry<Integer, LinkedList<Integer>> entry : getAdjMap().entrySet()) {
			inDegreeMap.putIfAbsent(entry.getKey(), 0);
			for (Integer outDegree : entry.getValue()) {
				inDegreeMap.merge(outDegree, 1, Integer::sum);
			}
		}

		LinkedList<Integer> queue = new LinkedList<>();
		inDegreeMap.forEach((key, value) -> {
			if (value == 0) {
				queue.add(key);
			}
		});

		LinkedList<Integer> path = new LinkedList<>();
		while (!queue.isEmpty()) {
			// 输出顺序
			Integer poll = queue.poll();
			path.add(poll);
			LinkedList<Integer> integers = getAdjMap().get(poll);
			if (integers == null) {
				continue;
			}
			for (Integer vertex : integers) {
				Integer merge = inDegreeMap.merge(vertex, -1, Integer::sum);
				if (merge == 0) {
					queue.add(vertex);
				}
			}
		}
		System.out.println(path);
		System.out.println(inDegreeMap);

		List<Map.Entry<Integer, Integer>> collect = inDegreeMap.entrySet().stream().filter(a -> a.getValue() > 0).collect(Collectors.toList());
		for (Map.Entry<Integer, Integer> entry : collect) {
			find(entry.getKey());
		}
	}

	boolean error;

	public boolean check() {
		for (Integer integer : getAdjMap().keySet()) {
			System.out.println("---------------------- " + integer);
			find(integer);
		}
		return error;
	}

	ArrayList<Integer> path = new ArrayList<>();

	public void find(Integer point) {
		path.clear();
		visit(path, point);
	}

	private void visit(List<Integer> path, Integer point) {
		if (path.contains(point)) {
			System.out.println("error!!! " + path + " " + point);
			error = true;
			return;
		}
		path.add(point);

		LinkedList<Integer> linkedList = adjMap.get(point);
		if (linkedList != null) {
			for (Integer integer : linkedList) {
				visit(path, integer);
			}
		}else {
			if(path.size() > 3) {
				System.out.println("too long!!!!!!! " + path);
			}
		}
		path.remove(point);
	}


	/**
	 * visit(路径, 点)
	 *   if 点在路径里
	 *     return
	 *   if 点是终点
	 *     输出路径,点
	 *     return
	 *   点加入路径
	 *   for 点的所有邻居
	 *     visit(路径, 邻居)
	 *   点从路径中删除
	 *
	 * main
	 *   清空路径
	 *   visit(路径, 起点)
	 *
	 * 作者：海鹏
	 * 链接：https://www.zhihu.com/question/384697042/answer/1130555530
	 * 来源：知乎
	 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
	 */

}
