package com.example.datastruture.graph;

import org.junit.Test;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/28 19:52
 * @Description:
 */
public class TestGraph {
	Graph graph = null;
	DirectionGraph dGraph = null;
	{
		graph = new Graph(8);
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(1, 2);
		graph.addEdge(1, 4);
		graph.addEdge(3, 4);
		graph.addEdge(4, 5);
		graph.addEdge(4, 6);
		graph.addEdge(5, 7);
		graph.addEdge(6, 7);
	}
	{
		dGraph = new DirectionGraph(8);
		dGraph.addEdge(0, 1);
		dGraph.addEdge(0, 3);
		dGraph.addEdge(1, 2);
		dGraph.addEdge(1, 4);
		dGraph.addEdge(3, 4);
		dGraph.addEdge(4, 5);
		dGraph.addEdge(4, 6);
		dGraph.addEdge(5, 7);
		dGraph.addEdge(6, 7);
	}

	private void graphPrint(Graph graph) {

	}

	@Test
	public void test01() {
		GraphBFS2 graphBFS2 = new GraphBFS2(graph);
		graphBFS2.bfs03(0, 6);
		graphBFS2.bfs02(0, 6);
		testTime(() -> graphBFS2.bfs03(0, 6),"");
		testTime(() -> graphBFS2.bfs02(0, 6),"");
	}

	@Test
	public void test02() {
		GraphDFS2 graphBFS2 = new GraphDFS2(graph);
		graphBFS2.dfs2(0, 6);
		graphBFS2.dfs(0, 6);
	/*	testTime(() -> graphBFS2.bfs03(0, 6), "");
		testTime(() -> graphBFS2.bfs02(0, 6), "");*/
	}

	private void testTime(Runnable runnable, String str) {
		long startNano = System.nanoTime();
		for (int i = 0; i < 1000; i++) {
			runnable.run();
		}
		long endNano = System.nanoTime();
		System.out.println(str + (endNano - startNano));
	}
}
