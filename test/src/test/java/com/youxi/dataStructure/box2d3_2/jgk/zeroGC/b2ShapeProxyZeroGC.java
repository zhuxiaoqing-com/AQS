package com.youxi.dataStructure.box2d3_2.jgk.zeroGC;

import com.youxi.math.box2dGJK.B2Math;
import com.youxi.math.box2dGJK.b2ShapeProxy;
import com.youxi.math.box2dGJK.shape.*;

import static com.youxi.math.box2dGJK.B2Math.b2Dot;

public class b2ShapeProxyZeroGC {
    public B2Vec2[] points = new B2Vec2[10];
    public int count;
    public float radius;

    {
        for (int i = 0; i < points.length; i++) {
            points[i] = new B2Vec2();
        }
    }




    public void reset(B2Circle circle) {
        points[0].setXY(circle.getCenter());
        count = 1;
        radius = circle.getRegionRadius();
    }

    public void reset(B2ConvexPolygon polygon) {
        for (int i = 0; i < polygon.getM_vertices().length; i++) {
            points[i].setXY(polygon.getM_vertices()[i]);
        }
        count = polygon.getM_vertexCount();
        radius = polygon.getB2_polygonRadius();
    }

    public void reset(B2MyPolygon polygon) {
        points = polygon.getM_vertices();
        count = polygon.getM_vertexCount();
        radius = polygon.getB2_polygonRadius();
    }

    public void reset(B2OBB polygon) {
        points = polygon.getPoints().toArray(new B2Vec2[0]);
        count = polygon.getPoints().size();
        radius = polygon.getRadius();
    }


    public void reset(b2ShapeProxy proxy) {
        for (int i = 0; i < proxy.GetVertexCount(); i++) {
            points[i].setXY(proxy.points[i]);
        }
        count = proxy.count;
        radius = proxy.radius;
    }

    public void reset(b2ShapeProxyZeroGC proxy) {
        for (int i = 0; i < proxy.GetVertexCount(); i++) {
            points[i].setXY(proxy.points[i]);
        }
        count = proxy.count;
        radius = proxy.radius;
    }
    public void reset(b2ShapeProxyZeroGC proxy, float finalFraction, B2Vec2 delta2) {
        for (int i = 0; i < proxy.GetVertexCount(); i++) {
            B2Math.b2MulAddZeroGC(proxy.points[i], finalFraction, delta2, points[i]);
        }
        count = proxy.count;
        radius = proxy.radius;
    }

    //
//获取顶点个数
    public int GetVertexCount() {
        return count;
    }

    //根据索引获得顶点
    public B2Vec2 GetVertex(int index) {
        return points[index];
    }

    //根据指定的方向获得支撑点索引，用于构建单纯形
    public int GetSupport(B2Vec2 d) {
        int bestIndex = 0;
        // 获取最远的点，用于产生的单纯形包含最大的空间区域
        float bestValue = b2Dot(points[0], d);
        //遍历所有的顶点，获取最远点的索引
        for (int i = 1; i < count; ++i) {
            float value = b2Dot(points[i], d);
            if (value > bestValue) {
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
        for (int i = 1; i < count; ++i) {
            float value = b2Dot(points[i], d);
            if (value > bestValue) {
                bestIndex = i;
                bestValue = value;
            }
        }
        //返回顶点
        return points[bestIndex];
    }


}
