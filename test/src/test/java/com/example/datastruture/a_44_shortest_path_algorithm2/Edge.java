package com.example.datastruture.a_44_shortest_path_algorithm2;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/29 17:40
 * @Description:
 */
public class Edge {

	// 边的起始顶点编号
	public int sid;
	// 边的终止顶点编号
	public int tid;
	// 权重
	public int w;

	public Edge(int sid, int tid, int w) {
		this.sid = sid;
		this.tid = tid;
		this.w = w;
	}

}
