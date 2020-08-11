package com.example.datastruture.a_44_shortest_path_algorithm2;


import org.junit.Test;

/**
 * 单源最短路径算法(从一个顶点到一个顶点)
 */
public class UndigraphDijkstraTest {
	GraphDijkstra graph;

	{
		graph = new GraphDijkstra(6);
		graph.addEdge(0, 1, 10);
		graph.addEdge(0, 4, 15);
		graph.addEdge(1, 2, 15);
		graph.addEdge(1, 3, 2);
		graph.addEdge(4, 5, 10);
		graph.addEdge(2, 5, 5);
		graph.addEdge(3, 2, 1);
		graph.addEdge(3, 5, 12);

		graph.addEdge(1, 0, 10);
		graph.addEdge(4, 0, 15);
		graph.addEdge(2, 1, 15);
		graph.addEdge(3, 1, 2);
		graph.addEdge(5, 4, 10);
		graph.addEdge(5, 2, 5);
		graph.addEdge(2, 3, 1);
		graph.addEdge(5, 3, 12);
	}

	@Test
	public void test01() {
		Dijkstra dijkstra = new Dijkstra(graph);
		dijkstra.dijkstra(0, 5);
		dijkstra.dijkstra02(0, 5);
	}

}
