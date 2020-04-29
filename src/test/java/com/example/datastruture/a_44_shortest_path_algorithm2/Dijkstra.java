package com.example.datastruture.a_44_shortest_path_algorithm2;


import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 单源最短路径算法(从一个顶点到一个顶点)
 */
public class Dijkstra {
	GraphDijkstra graph;
	// 邻接表
	private LinkedList<Edge> adj[];
	// 顶点个数
	private int v;

	public Dijkstra(GraphDijkstra graph) {
		this.graph = graph;
		this.adj = graph.getAdj();
		this.v = graph.getV();
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

	/*// 因为 java 提供的优先级队列，没有暴露更新数据的接口，所以我们需要重新实现一个
	private class PriorityQueue {
		// 根据 vertex.dist 构建小顶堆
		private Vertex[] nodes;
		private int count;

		public PriorityQueue(int v) {
			this.nodes = new Vertex[v + 1];
			this.count = v;
		}

		public Vertex poll() {
			//todo 空实现
			return nodes[0];
		}

		public void add(Vertex vertex) {
			//todo 空实现
		}


		// 更新结点的值，并且从下往上推化，重新符合堆的定义 时间复杂度 O(logn)
		public void update(Vertex vertex) {

		}

		public boolean isEmpty() {
			return true;
		}
	}*/

	/**
	 * 时间复杂度是 E * logV
	 *
	 *
	 * 1、while(){for} 循环 这个嵌套循环最多循环的次数不会超过 E 条边; 这个while(){for}就是最多每条边都遍历过一次
	 * 2、其次就是 堆的 add update remove  这个的复杂度是 logV ; 因为堆里面的元素最多不会超过 V 个;
	 *
	 * 相乘 就是 E * logV
	 *
	 *
	 * @param s
	 * @param t
	 */
	// 从顶点 s 到顶点 t 的最短路径
	public void dijkstra02(int s, int t) {
		Vertex[] vertexes = new Vertex[this.v];
		for (int i = 0; i < this.v; ++i) {
			vertexes[i] = new Vertex(i, Integer.MAX_VALUE);
		}

		int[] prevPath = new int[this.v];
		// 为啥要填充 -1 因为我们的图有 0 节点的关系;
		Arrays.fill(prevPath, -1);

		// 已经进入队列的点
		boolean[] inQueue = new boolean[this.v];

		PriorityQueue<Vertex> queue = new PriorityQueue<>(this.v, Comparator.comparing(Vertex::getDist));
		// 将起始点的距离设置为 0
		vertexes[s].dist = 0;
		queue.add(vertexes[s]);

		while (!queue.isEmpty()) {
			Vertex poll = queue.poll();
			LinkedList<Edge> edges = adj[poll.id];
			// 如果所有的线路里面最短的是 t
			if (poll.id == t) {
				break;
			}
			for (Edge edge : edges) {
				// 如果这条线路更长的话
				if (poll.dist + edge.w > vertexes[edge.tid].dist) {
					continue;
				}
				prevPath[edge.tid] = edge.sid;
				vertexes[edge.tid].dist = poll.dist + edge.w;
				if (inQueue[edge.tid]) {
					update(queue, vertexes[edge.tid]);
				} else {
					queue.add(vertexes[edge.tid]);
					inQueue[edge.tid] = true;
				}
			}
		}

		// 打印路径
		int lastVertex = t;
		LinkedList<Integer> path = new LinkedList<>();
		path.add(t);
		while (lastVertex != s) {
			int i = prevPath[lastVertex];
			path.addFirst(i);
			lastVertex = i;
		}

		// 打印路径
		System.out.println(path);
	}

	private void update(java.util.PriorityQueue<Vertex> queue, Vertex vertex) {
		queue.remove(vertex);
		queue.add(vertex);
	}


	/**
	 * Dijkstra 是在终点出队列的时候才结束,因为当终点出队列的时候，终点的 dist 值是优先级队列中所有定点的最小值，
	 * 即便再运行下去，终点的 dist 值也不会在被更新了
	 * <p>
	 * 优先级队列中出来的那个定点一定是最小的了 距离起点。因为是优先级队列的最小的出来的
	 *
	 * @param s
	 * @param t
	 */
	// 从顶点 s 到顶点 t 的最短路径
	public void dijkstra(int s, int t) {
		// 用来还原最短路径
		int[] predecessor = new int[this.v];
		Vertex[] vertexes = new Vertex[this.v];
		// 存着顶点从 s 到 t 的路径总长度
		for (int i = 0; i < this.v; ++i) {
			vertexes[i] = new Vertex(i, Integer.MAX_VALUE);
		}
		// 存着即将扫描的数据
		PriorityQueue<Vertex> queue = new PriorityQueue<>(this.v, Comparator.comparing(Vertex::getDist));
		// 标记是否进入过队列
		boolean[] inqueue = new boolean[this.v];
		// 从 s 开始扫描  应该是广度搜索吧
		vertexes[s].dist = 0;
		queue.add(vertexes[s]);
		inqueue[s] = true;
		while (!queue.isEmpty()) {
			// 取堆顶元素并删除
			Vertex minVertex = queue.poll();
			// 最短路径产生了
			if (minVertex.id == t) break;
			for (int i = 0; i < adj[minVertex.id].size(); i++) {
				Edge edge = adj[minVertex.id].get(i);
				Vertex vertex = vertexes[edge.tid];
				if (minVertex.dist + edge.w < vertex.dist) {
					vertex.dist = minVertex.dist + edge.w;
					predecessor[vertex.id] = minVertex.id;
					if (inqueue[vertex.id]) {
						update(queue, vertex);
					} else {
						inqueue[vertex.id] = true;
						queue.add(vertex);
					}
				}
			}
		}
		System.out.print(s);
		print(s, t, predecessor);
		System.out.println();
	}

	public void print(int s, int t, int[] predecessor) {
		if (t == s) {
			return;
		}
		print(s, predecessor[t], predecessor);
		System.out.print("-> " + t);
	}

}
