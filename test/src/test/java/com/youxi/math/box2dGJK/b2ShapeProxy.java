package com.youxi.math.box2dGJK;

import com.youxi.math.box2dGJK.shape.*;

import static com.youxi.math.box2dGJK.B2Math.b2Dot;

//https://blog.csdn.net/cg0206/article/details/8352227
public class b2ShapeProxy {
	public B2Vec2[] points;
	public int count;
	public float radius;


	public b2ShapeProxy(b2ShapeProxy proxy) {
		points = new B2Vec2[proxy.points.length];
		for (int i = 0; i < proxy.points.length; i++) {
			points[i] = proxy.points[i];
		}
		count = proxy.count;
		radius = proxy.radius;
	}

	public b2ShapeProxy(B2Circle circle) {
		points = new B2Vec2[]{circle.getCenter()};
		count = 1;
		radius = circle.getRegionRadius();
		init();
	}

	public b2ShapeProxy(B2ConvexPolygon polygon) {
		points = polygon.getM_vertices();
		count = polygon.getM_vertexCount();
		radius = polygon.getB2_polygonRadius();
		init();
	}

	public b2ShapeProxy(B2MyPolygon polygon) {
		points = polygon.getM_vertices();
		count = polygon.getM_vertexCount();
		radius = polygon.getB2_polygonRadius();
		init();
	}

	public b2ShapeProxy(B2OBB polygon) {
		points = polygon.getPoints().toArray(new B2Vec2[0]);
		count = polygon.getPoints().size();
		radius = polygon.getRadius();
		init();
	}


	public void Set(B2Vec2[] vertices, int count, float radius) {
		points = vertices;
		this.count = count;
		this.radius = radius;
		init();
	}

	public void init() {
		B2Vec2[] tempPoints = new B2Vec2[this.points.length];
		// 将 points里面的B2Vec2 新弄个对象
		for (int i = 0; i < points.length; i++) {
			tempPoints[i] = new B2Vec2(points[i].x, points[i].y);
		}

		this.points = tempPoints;
	}


	//
//获取顶点个数
	public int GetVertexCount() {
		return count;
	}
//根据索引获得顶点
	public B2Vec2 GetVertex(int index){
		return points[index];
	}
	//根据指定的方向获得支撑点索引，用于构建单纯形
	public int GetSupport(B2Vec2 d) {
		int bestIndex = 0;
		// 获取最远的点，用于产生的单纯形包含最大的空间区域
		float bestValue = b2Dot(points[0], d);
		//遍历所有的顶点，获取最远点的索引
		for (int i = 1; i < count; ++i)
		{
			float value = b2Dot(points[i], d);
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
		float bestValue = b2Dot(points[0], d);
		//遍历所有的顶点，获得远的点
		for (int i = 1; i < count; ++i)
		{
			float value = b2Dot(points[i], d);
			if (value > bestValue)
			{
				bestIndex = i;
				bestValue = value;
			}
		}
		//返回顶点
		return points[bestIndex];
	}


}
