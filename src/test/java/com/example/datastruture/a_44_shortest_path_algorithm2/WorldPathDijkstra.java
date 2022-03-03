package com.example.datastruture.a_44_shortest_path_algorithm2;

import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Predicate;

/**
 * 有向图Dijkstra 寻路
 */
public class WorldPathDijkstra {
	private static final Logger logger = LoggerFactory.getLogger(WorldPathDijkstra.class);

	private DirectedGraph directedGraph;

	public WorldPathDijkstra(DirectedGraph directedGraph) {
		this.directedGraph = directedGraph;
		getVertexMap();
	}


	public int dijkstra(int start, Predicate<Integer> checkEnd) {
		HashMap<Integer, Vertex> vertexMap = getVertexMap();

		// 存着即将扫描的数据
		PriorityQueue<Vertex> queue = new PriorityQueue<>(vertexMap.size(), Comparator.comparing(Vertex::getDist));
		Set<Integer> visited = new HashSet<>();

		// 从 s 开始扫描
		Vertex startVertex = vertexMap.get(start);
		startVertex.dist = 0;
		queue.add(startVertex);
		visited.add(startVertex.getId());

		int endId = 0;

		int count = 1000;
		while (!queue.isEmpty()) {
			if (count-- <= 0) {
				logger.error("死循环了！！！start:{}", start);
				return 0;
			}
			// 取堆顶元素并删除
			Vertex minVertex = queue.poll();
			if (checkEnd.test(minVertex.id)) {
				endId = minVertex.id;
				break;
			}
			ArrayDeque<Integer> linkedList = getAdjMap().get(minVertex.getId());
			for (Integer integer : linkedList) {
				Vertex vertex = vertexMap.get(integer);
				// 策划说每个点之间的距离都一样; 就都写为了1;
				if (minVertex.dist + 1 < vertex.dist) {
					vertex.dist = minVertex.dist + 1;
				}
				if (visited.contains(vertex.getId())) {
					continue;
				}
				visited.add(vertex.getId());
				queue.add(vertex);
			}
		}
		return endId;
	}


	// 下面这个类是为了 dijkstra 实现用的
	private class Vertex {
		// 顶点编号 ID
		public int id;
		// 从起始顶点到这个顶点的距离
		public int dist;

		public Vertex(int id, int dist) {
			this.id = id;
			this.dist = dist;
		}

		public int getId() {
			return id;
		}

		public int getDist() {
			return dist;
		}
	}

	HashMap<Integer, Vertex> vertexMap = new HashMap<>();

	private HashMap<Integer, Vertex> getVertexMap() {
		if (vertexMap.isEmpty()) {
			for (Integer integer : getAdjMap().keySet()) {
				Vertex vertex = new Vertex(integer, Integer.MAX_VALUE);
				vertexMap.put(vertex.getId(), vertex);
			}
		} else {
			for (Vertex value : vertexMap.values()) {
				value.dist = Integer.MAX_VALUE;
			}
		}
		return vertexMap;
	}

	private Map<Integer, ArrayDeque<Integer>> getAdjMap() {
		return directedGraph.getAdjMap();
	}



	private void update(PriorityQueue<Vertex> queue, Vertex vertex) {
		queue.remove(vertex);
		queue.add(vertex);
	}


	public static void main(String[] args) {
		DirectedGraph directedGraph = new DirectedGraph();
		directedGraph.addEdge(1,2);
		directedGraph.addEdge(2,3);
		directedGraph.addEdge(3,2);
		directedGraph.addEdge(3,4);
		directedGraph.addEdge(4,1);

		WorldPathDijkstra worldPathDijkstra = new WorldPathDijkstra(directedGraph);
		int dijkstra = worldPathDijkstra.dijkstra(1, a -> a == 4);
		System.out.println(dijkstra+" dddddddddddddddddddddddddd");
	}

}
