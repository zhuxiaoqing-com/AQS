package com.example.datastruture.a_43_topological_sorting;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 有向图
 */
public class DirectionGraphObj extends DirectionGraph{
    private Map<Integer, LinkedList<Integer>> adjMap = new HashMap<>();// 邻接表

	public DirectionGraphObj() {
	}

	/**
     * edge 边
     * 增加 边
     *
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
}
