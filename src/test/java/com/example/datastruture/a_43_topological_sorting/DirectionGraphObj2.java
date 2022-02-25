package com.example.datastruture.a_43_topological_sorting;

import io.swagger.models.auth.In;

import java.util.*;

/**
 * 有向图
 */
public class DirectionGraphObj2 extends DirectionGraph {
	private Map<Integer, LinkedList<Integer>> adjMap = new HashMap<>();// 邻接表

	public DirectionGraphObj2() {
	}

	/**
	 * edge 边
	 * 增加 边
	 * <p>
	 * 有向图
	 */
	public void addEdge(int s, int t) {
		LinkedList<Integer> linkedList = adjMap.computeIfAbsent(s, a -> new LinkedList<>());
		linkedList.add(t);
	}


	public int getV() {
		return adjMap.size();
	}

	public Map<Integer, LinkedList<Integer>> getAdjMap() {
		return adjMap;
	}



	boolean error;


	public boolean check() {
		for (Integer integer : adjMap.keySet()) {
			find(integer);
		}
		return error;
	}

	ArrayList<Integer> path = new ArrayList<>();
	public void find(int point) {
		path.clear();
		visit(path, point);
	}

	private void visit(List<Integer> path, int point) {
		// 如果有环
		if(path.contains(point)) {
			error = true;
			System.out.println("error!!! " + path);
		}
		// 点加入路径
		path.add(point);

		// 遍历点的所有路径
		LinkedList<Integer> linkedList = adjMap.get(point);
		if (linkedList != null) {
			for (Integer integer : linkedList) {
				visit(path, integer);
			}
		}else {
			if(path.size() > 4) {
				System.out.println("too long!!!!!!!");
			}
		}
		// 点从路径中删除
		path.remove(point);
	}
}
