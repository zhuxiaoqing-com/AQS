package com.youxi.math.box2dGJK.shape;

import com.youxi.math.box2dGJK.B2Math;

import java.util.ArrayList;
import java.util.List;

/**
 * 右手系‌	‌逆时针 (CCW)	连续边向量叉积 > 0  P0(0,0) → P1(1,0) → P2(1,1) → P3(0,1)
 * ‌左手系‌	‌顺时针 (CW)	连续边向量叉积 < 0  P0(0,0) → P1(0,1) → P2(1,1) → P3(1,0)
 * <p>
 * 右手系‌	‌最左下角点‌	最小化XY值（min(x+y)）	        (0, 0)
 * ‌左手系‌	‌最左上角点‌	x最小 + y最大（min(x), max(y)）	(0, 1)
 */
public class B2OBB {
    float b2_polygonRadius = 0.005f * 1.0f * 2.0f;

    public B2Vec2 center;
    public B2Vec2 halfSize;
    public float deg;
    public List<B2Vec2> points = new ArrayList<B2Vec2>();

    public B2OBB(B2Vec2 center, B2Vec2 halfSize, float deg) {
        this.center = center;
        this.halfSize = halfSize;
        this.deg = deg;

        calcPoint();
    }

    private void calcPoint() {
        points.clear();
        // 求points
        B2Vec2 p0 = B2Math.b2Add(center, new B2Vec2(-halfSize.x, -halfSize.y));
        p0 = B2Math.unityRotate(p0, deg);
        points.add(p0);

        p0 = B2Math.b2Add(center, new B2Vec2(-halfSize.x, halfSize.y));
        p0 = B2Math.unityRotate(p0, deg);
        points.add(p0);

        p0 = B2Math.b2Add(center, new B2Vec2(halfSize.x, halfSize.y));
        p0 = B2Math.unityRotate(p0, deg);
        points.add(p0);

        p0 = B2Math.b2Add(center, new B2Vec2(halfSize.x, -halfSize.y));
        p0 = B2Math.unityRotate(p0, deg);
        points.add(p0);
    }

    public B2Vec2 getCenter() {
        return center;
    }

    public B2Vec2 getHalfSize() {
        return halfSize;
    }

    public float getDeg() {
        return deg;
    }

    public List<B2Vec2> getPoints() {
        return points;
    }

    public float getRadius() {
        return b2_polygonRadius;
    }
}
