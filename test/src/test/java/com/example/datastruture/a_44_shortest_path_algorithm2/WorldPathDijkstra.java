package com.example.datastruture.a_44_shortest_path_algorithm2;


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


	public List<ArrayDeque<Integer>> dijkstra(int start, Predicate<Integer> checkEnd) {
		// 用来还原最短路径
		Map<Integer, HashSet<Vertex>> predecessor = new HashMap<>(getAdjMap().size());
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
		ArrayList<ArrayDeque<Integer>> paths = new ArrayList<>();
		while (!queue.isEmpty()) {
			if (count-- <= 0) {
				logger.error("dijkstra 死循环了！！！start:{}", start);
				return null;
			}
			// 取堆顶元素并删除
			Vertex minVertex = queue.poll();
			if (checkEnd.test(minVertex.id)) {
				continue;
			}
			visited.add(minVertex.getId());
			ArrayDeque<Integer> linkedList = getAdjMap().get(minVertex.getId());
			for (Integer integer : linkedList) {
				Vertex vertex = vertexMap.get(integer);
				if (visited.contains(vertex.getId())) {
					continue;
				}
				// 策划说每个点之间的距离都一样; 就都写为了1;
				if (minVertex.dist + 1 > vertex.dist) {
					continue;
				}
				System.out.println();
				vertex.dist = minVertex.dist + 1;
				Set<Vertex> list = predecessor.computeIfAbsent(vertex.getId(), a -> new HashSet<>());
				if (list.isEmpty()) {
					list.add(minVertex);
				} else {
					Vertex min = list.stream().min(Comparator.comparingInt(a -> a.getDist())).orElseGet(null);
					if (min.getDist() > minVertex.dist) {
						list.clear();
						list.add(minVertex);
					} else if (min.getDist() == minVertex.dist) {
						list.add(minVertex);
					} else {

					}
				}
				queue.add(vertex);
			}
		}

		findPath(predecessor, 4);
		return paths;
	}

	private ArrayDeque<Integer> findPath(Map<Integer, HashSet<Vertex>> predecessor, int end) {
		dfs(predecessor, end, new ArrayDeque<>());
		return null;
	}

	private void dfs(Map<Integer, HashSet<Vertex>> predecessor, int start, ArrayDeque<Integer> path) {
		path.addFirst(start);
		if (!predecessor.containsKey(start)) {
			System.out.println(path);
			path.removeFirst();
			return;
		}

		HashSet<Vertex> vertices = predecessor.get(start);
		for (Vertex vertex : vertices) {
			dfs(predecessor, vertex.getId(), path);
		}

		path.removeFirst();
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

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Vertex vertex = (Vertex) o;
			return id == vertex.id && dist == vertex.dist;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id, dist);
		}

		@Override
		public String toString() {
			return "Vertex{" +
					"id=" + id +
					", dist=" + dist +
					'}';
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
	/*	directedGraph.addVertexAndEdge(1,2);
		directedGraph.addVertexAndEdge(2,3);
		directedGraph.addVertexAndEdge(3,2);
		directedGraph.addVertexAndEdge(3,4);
		directedGraph.addVertexAndEdge(4,1);*/

	/*	directedGraph.addVertex(1);
		directedGraph.addVertex(2);
		directedGraph.addVertex(3);
		directedGraph.addVertex(4);
		directedGraph.addVertexAndEdge(1,2);
		directedGraph.addVertexAndEdge(1,3);
		directedGraph.addVertexAndEdge(1,4);
*/

		directedGraph.addVertex(4);
		directedGraph.addVertexAndEdge(1, 2);
		directedGraph.addVertexAndEdge(2, 3);
		directedGraph.addVertexAndEdge(3, 4);
		directedGraph.addVertexAndEdge(1, 5);
		directedGraph.addVertexAndEdge(5, 3);
		directedGraph.addVertexAndEdge(1, 6);
		directedGraph.addVertexAndEdge(6, 7);
		directedGraph.addVertexAndEdge(7, 4);
		directedGraph.addVertexAndEdge(1, 8);
		directedGraph.addVertexAndEdge(8, 7);
		directedGraph.addVertexAndEdge(1, 9);
		directedGraph.addVertexAndEdge(9, 10);
		directedGraph.addVertexAndEdge(10, 11);
		directedGraph.addVertexAndEdge(11, 4);

		WorldPathDijkstra worldPathDijkstra = new WorldPathDijkstra(directedGraph);
		List<ArrayDeque<Integer>> dijkstra = worldPathDijkstra.dijkstra(1, a -> a == 4);
		System.out.println(dijkstra + " dijkstra");
	}

}
