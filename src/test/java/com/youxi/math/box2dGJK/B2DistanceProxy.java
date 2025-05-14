package com.youxi.math.box2dGJK;

import com.youxi.math.box2dGJK.shape.*;

import static com.youxi.math.box2dGJK.B2Math.b2Dot;

//https://blog.csdn.net/cg0206/article/details/8352227
public class B2DistanceProxy {
	B2Vec2[] m_buffer = new B2Vec2[2];
	B2Vec2[] m_vertices;
	int m_count;
	float m_radius;

	public B2DistanceProxy() {
	}

	public B2DistanceProxy(B2Circle circle) {
		m_vertices = new B2Vec2[]{circle.getCenter()};
		m_count = 1;
		m_radius = circle.getRegionRadius();
	}

	public B2DistanceProxy(B2ConvexPolygon polygon) {
		m_vertices = polygon.getM_vertices();
		m_count = polygon.getM_vertexCount();
		m_radius = polygon.getB2_polygonRadius();
	}

	public B2DistanceProxy(B2MyPolygon polygon) {
		m_vertices = polygon.getM_vertices();
		m_count = polygon.getM_vertexCount();
		m_radius = polygon.getB2_polygonRadius();
	}


	void Set(B2Vec2[] vertices, int count, float radius) {
		m_vertices = vertices;
		m_count = count;
		m_radius = radius;
	}



	//
//获取顶点个数
	public int GetVertexCount() {
		return m_count;
	}
//根据索引获得顶点
	public B2Vec2 GetVertex(int index){
		return m_vertices[index];
	}
	//根据指定的方向获得支撑点索引，用于构建单纯形
	public int GetSupport(B2Vec2 d) {
		int bestIndex = 0;
		// 获取最远的点，用于产生的单纯形包含最大的空间区域
		float bestValue = b2Dot(m_vertices[0], d);
		//遍历所有的顶点，获取最远点的索引
		for (int i = 1; i < m_count; ++i)
		{
			float value = b2Dot(m_vertices[i], d);
			if (value > bestValue)
			{
				bestIndex = i;
				bestValue = value;
			}
		}
		//返回索引
		return bestIndex;
	}
//根据指定的方向向量获得支撑点，用于构建单纯形
	public B2Vec2 B2Vec2GetSupportVertex(B2Vec2 d) {
		int bestIndex = 0;
		float bestValue = b2Dot(m_vertices[0], d);
		//遍历所有的顶点，获得远的点
		for (int i = 1; i < m_count; ++i)
		{
			float value = b2Dot(m_vertices[i], d);
			if (value > bestValue)
			{
				bestIndex = i;
				bestValue = value;
			}
		}
		//返回顶点
		return m_vertices[bestIndex];
	}


}
