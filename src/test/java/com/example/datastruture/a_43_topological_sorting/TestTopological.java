package com.example.datastruture.a_43_topological_sorting;


import org.junit.Test;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/29 10:45
 * @Description:
 */
public class TestTopological {
	DirectionGraph dGraph = null;
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

	@Test
	public void test01(){
		Graph_Kahn2 graph_kahn2 = new Graph_Kahn2(dGraph);
		graph_kahn2.topoSortByKahn();
		graph_kahn2.topoSortByKahn02();
	}
}
