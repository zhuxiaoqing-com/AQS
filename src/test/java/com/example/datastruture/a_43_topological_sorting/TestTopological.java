package com.example.datastruture.a_43_topological_sorting;


import org.junit.Test;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/29 10:45
 * @Description:
 */
public class TestTopological {
	DirectionGraph dGraph = null;
	Graph_KahnObj dGraphObj = null;
	{
/*		dGraph = new DirectionGraph(8);
		dGraph.addEdge(0, 1);
		dGraph.addEdge(0, 3);
		dGraph.addEdge(1, 2);
		dGraph.addEdge(1, 4);
		dGraph.addEdge(3, 4);
		dGraph.addEdge(4, 5);
		dGraph.addEdge(4, 6);
		dGraph.addEdge(5, 7);
		dGraph.addEdge(6, 7);*/

		//dGraph = new DirectionGraph(8);
		dGraphObj = new Graph_KahnObj();
		//extracted(dGraph);
		extracted(dGraphObj);
	}

	private void extracted(DirectionGraph dGraph) {
		dGraph.addEdge(0, 0);
		dGraph.addEdge(5, 6);
		dGraph.addEdge(6, 33);
		dGraph.addEdge(7, 7);
		dGraph.addEdge(7, 5);

		dGraph.addEdge(1, 2);
		dGraph.addEdge(2, 3);
		dGraph.addEdge(3, 1);

		dGraph.addEdge(5, 12);
		dGraph.addEdge(12, 5);

		dGraph.addEdge(120, 111);
		dGraph.addEdge(1111, 333);
		dGraph.addEdge(44, 555);


	}

	@Test
	public void test01(){
		/*Graph_Kahn2 graph_kahn2 = new Graph_Kahn2(dGraph);
		graph_kahn2.topoSortByKahn02();*/

		//dGraphObj.topoSortByKahn02();

			dGraphObj.check();
	}
}
