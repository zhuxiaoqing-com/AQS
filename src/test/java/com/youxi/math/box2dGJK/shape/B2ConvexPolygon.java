package com.youxi.math.box2dGJK.shape;

import static com.youxi.math.box2dGJK.B2Math.b2Cross;
import static com.youxi.math.box2dGJK.B2Math.b2Mul;

/**
 * polygon
 *
 * @author Eric.Ma
 */
public class B2ConvexPolygon extends B2Shape {
	float b2_polygonRadius = 0.005f * 1.0f * 2.0f;

	// 顶点坐标数组
	private B2Vec2[] m_vertices = new B2Vec2[8];
	// 法线数组
	private B2Vec2[] m_normals = new B2Vec2[8];
	// 重心坐标
	B2Vec2 m_centroid;
	// 顶点数量
	int m_vertexCount;

	public B2ConvexPolygon() {
		super(new B2Vec2(0, 0));
		for (int i = 0; i < m_vertices.length; i++) {
			m_vertices[i] = new B2Vec2(0, 0);
		}

		for (int i = 0; i < m_normals.length; i++) {
			m_normals[i] = new B2Vec2(0, 0);
		}
		m_centroid = new B2Vec2(0,0);
	}

	public float getB2_polygonRadius() {
		return b2_polygonRadius;
	}

	public void setB2_polygonRadius(float b2_polygonRadius) {
		this.b2_polygonRadius = b2_polygonRadius;
	}

	public B2Vec2[] getM_vertices() {
		return m_vertices;
	}

	public void setM_vertices(B2Vec2[] m_vertices) {
		this.m_vertices = m_vertices;
	}

	public B2Vec2[] getM_normals() {
		return m_normals;
	}

	public void setM_normals(B2Vec2[] m_normals) {
		this.m_normals = m_normals;
	}

	public B2Vec2 getM_centroid() {
		return m_centroid;
	}

	public void setM_centroid(B2Vec2 m_centroid) {
		this.m_centroid = m_centroid;
	}

	public int getM_vertexCount() {
		return m_vertexCount;
	}

	public void setM_vertexCount(int m_vertexCount) {
		this.m_vertexCount = m_vertexCount;
	}

	//构建一个包围着顶点轴对齐盒子
	public void SetAsBox(float hx, float hy) {
		//初始化盒子顶点坐标
		m_vertexCount = 4;
		m_vertices[0].Set(-hx, -hy);
		m_vertices[1].Set(hx, -hy);
		m_vertices[2].Set(hx, hy);
		m_vertices[3].Set(-hx, hy);
		m_normals[0].Set(0.0f, -1.0f);
		m_normals[1].Set(1.0f, 0.0f);
		m_normals[2].Set(0.0f, 1.0f);
		m_normals[3].Set(-1.0f, 0.0f);
		m_centroid.SetZero();
	}

	//构建一个包围着顶点确定方位的轴对齐盒子
	public void SetAsBox(float hx, float hy, B2Vec2 center, float angle) {
		//初始化盒子顶点坐标
		m_vertexCount = 4;
		m_vertices[0].Set(-hx, -hy);
		m_vertices[1].Set(hx, -hy);
		m_vertices[2].Set(hx, hy);
		m_vertices[3].Set(-hx, hy);
		m_normals[0].Set(0.0f, -1.0f);
		m_normals[1].Set(1.0f, 0.0f);
		m_normals[2].Set(0.0f, 1.0f);
		m_normals[3].Set(-1.0f, 0.0f);
		m_centroid = center;
		//创建一个变换
		B2Transform xf = new B2Transform();
		xf.p = center;
		xf.q.Set(angle);
		// 变换顶点和法线
		for (int i = 0; i < m_vertexCount; ++i) {
			m_vertices[i] = b2Mul(xf, m_vertices[i]);
			m_normals[i] = b2Mul(xf.q, m_normals[i]);
		}
	}

	//获取孩子形状个数，你可以使用它去创建形状
	public int GetChildCount() {
		return 1;
	}

	/**************************************************************************
	 * 功能描述：计算重心坐标
	 参见 http://www.cnblogs.com/jun930123/archive/2012/09/10/2678305.html
	 * 参数说明： vs   :顶点数组
	 count:顶点数
	 * 返 回 值： 重心坐标
	 ***************************************************************************/
	static B2Vec2 ComputeCentroid(B2Vec2[] vs, int count) {
		//b2Assert(count >= 3);
		if (count < 3) {
			return null;
		}
		B2Vec2 c = new B2Vec2(0.0f, 0.0f);
		float area = 0.0f;
		//三角形的一个参考点
		//它的位置并不能改变结构(除了舍入误差)
		B2Vec2 s = vs[0];

	 float inv3 = 1.0f / 3.0f;

		for (int i = 0; i < count; ++i) {
			// 三角形顶点
			B2Vec2 p1 = vs[0].sub(s);
			B2Vec2 p2 = vs[i];
			B2Vec2 p3 = i + 1 < count ? vs[i + 1] : vs[0];
			//三角形两边的向量
			B2Vec2 e1 = p2.sub(p1);
			B2Vec2 e2 = p3.sub(p1);
			//计算三角形面积
			// S = 1/2 * b2Cross(e1,e2)
			float D = e1.cross(e2);
			float triangleArea = 0.5f * D;
			//获取面积之后
			area += triangleArea;
			// 面积加权重心
			//c += triangleArea * inv3 * (p1 + p2 + p3);
			c = p1.add(p2).add(p3).multiply(inv3 * triangleArea).add(c);
		}
		//重心
		//c *= 1.0f / area;
		c.multiply(1.0f / area);
		return c;
	}

	//复制顶点
	public void Set(B2Vec2[] vertices, int count) {
		//验证顶点的个数
		m_vertexCount = count;

		//拷贝顶点
		for (int i = 0; i < m_vertexCount; ++i) {
			m_vertices[i] = vertices[i];
		}
		// 计算法线，确保每条边的长度都不为0
		for (int i = 0; i < m_vertexCount; ++i) {
			//获取两个相邻点的索引
			int i1 = i;
			int i2 = i + 1 < m_vertexCount ? i + 1 : 0;
			//获得边向量
			B2Vec2 edge = m_vertices[i2].sub(m_vertices[i1]);
			//计算法线，并标准化
			m_normals[i] = b2Cross(edge, 1.0f);
			m_normals[i] = m_normals[i].Normalize();
		}

		//计算重心
		m_centroid = ComputeCentroid(m_vertices, m_vertexCount);
	}


}
