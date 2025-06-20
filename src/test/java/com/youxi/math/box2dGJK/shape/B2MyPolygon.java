package com.youxi.math.box2dGJK.shape;

import java.util.Arrays;

import static com.youxi.math.box2dGJK.B2Math.b2Cross;
import static com.youxi.math.box2dGJK.B2Math.b2Mul;

/**
 * polygon
 *
 * @author Eric.Ma
 */
public class B2MyPolygon {
    float b2_polygonRadius = 0.005f * 1.0f * 2.0f;

    // 顶点坐标数组
    private B2Vec2[] m_vertices = new B2Vec2[8];
    // 顶点数量
    int m_vertexCount;


    public B2MyPolygon(B2Vec2[] m_vertices) {
        this.m_vertices = m_vertices;
        this.m_vertexCount = m_vertices.length;
    }

    public B2MyPolygon(float hx, float hy, B2Vec2 center, float deg) {
        float angle = (float) Math.toRadians(deg);
        angle = deg;
        //初始化盒子顶点坐标
        m_vertexCount = 4;
        m_vertices = new B2Vec2[m_vertexCount];
        m_vertices[0] = new B2Vec2(-hx, -hy);
        m_vertices[1] = new B2Vec2(hx, -hy);
        m_vertices[2] = new B2Vec2(hx, hy);
        m_vertices[3] = new B2Vec2(-hx, hy);

        //创建一个变换
        B2Transform xf = new B2Transform();
        xf.p = center;
        xf.q.Set(angle);
        // 变换顶点和法线
        for (int i = 0; i < m_vertexCount; ++i) {
            m_vertices[i] = b2Mul(xf, m_vertices[i]);
        }
    }

    public B2Vec2[] getM_vertices() {
        return m_vertices;
    }

    public void setM_vertices(B2Vec2[] m_vertices) {
        this.m_vertices = m_vertices;
    }

    public int getM_vertexCount() {
        return m_vertexCount;
    }

    public void setM_vertexCount(int m_vertexCount) {
        this.m_vertexCount = m_vertexCount;
    }

    public float getB2_polygonRadius() {
        return b2_polygonRadius;
    }

    public void setB2_polygonRadius(float b2_polygonRadius) {
        this.b2_polygonRadius = b2_polygonRadius;
    }

    @Override
    public String toString() {
        return "{" + Arrays.toString(m_vertices) + '}';
    }
}
