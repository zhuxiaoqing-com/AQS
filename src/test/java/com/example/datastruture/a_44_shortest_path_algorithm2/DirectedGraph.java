package com.example.datastruture.a_44_shortest_path_algorithm2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 有向图
 */
public class DirectedGraph {
	private static final Logger logger = LoggerFactory.getLogger(DirectedGraph.class);

	private Map<Integer, ArrayDeque<Integer>> adjMap = new HashMap<>();// 邻接表

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
		ArrayDeque<Integer> linkedList = adjMap.computeIfAbsent(s, a -> new ArrayDeque<>());
		linkedList.add(t);
	}


	public Map<Integer, ArrayDeque<Integer>> getAdjMap() {
		return adjMap;
	}

	boolean error;

	public boolean check() {
		for (Integer integer : getAdjMap().keySet()) {
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
			logger.error("error!!! 有环 {} {}", path, point);
			error = true;
			return;
		}
		path.add(point);

		ArrayDeque<Integer> linkedList = adjMap.get(point);
		if (linkedList != null) {
			for (Integer integer : linkedList) {
				visit(path, integer);
			}
		}else {
			if(path.size() > 4) {
				logger.error("too long!!!!!!!  {}", path);
			}
		}
		path.remove(point);
	}

}
