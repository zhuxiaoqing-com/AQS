package com.youxi.math.box2dGJK;


import com.youxi.math.box2dGJK.shape.B2Rot;
import com.youxi.math.box2dGJK.shape.B2Transform;
import com.youxi.math.box2dGJK.shape.B2Vec2;

public class B2Math {
    public static float b2_epsilon = 0.0000001f;
    public static float b2_polygonRadius = 2.0f * 0.005f;
    public static float b2_linearSlop = 0.005f;

    public static B2Vec2 b2Mul(B2Transform T, B2Vec2 v) {
        float x = (T.q.c * v.x - T.q.s * v.y) + T.p.x;
        float y = (T.q.s * v.x + T.q.c * v.y) + T.p.y;
        return new B2Vec2(x, y);
    }


    public static B2Vec2 b2Mul(B2Rot q, B2Vec2 v) {
        return new B2Vec2(q.c * v.x - q.s * v.y, q.s * v.x + q.c * v.y);
    }

    /// Inverse rotate a vector
    public static B2Vec2 b2MulT(B2Rot q, B2Vec2 v) {
        return new B2Vec2(q.c * v.x + q.s * v.y, -q.s * v.x + q.c * v.y);
    }

    protected static float b2Distance(B2Vec2 a, B2Vec2 b) {
        B2Vec2 c = a.sub(b);
        return c.length();
    }

    /**
     * 设置1我们将返回的向量视为逆时针旋转了90°，或者将 s 设置为-1时视为顺时针旋转了90°
     */
    public static B2Vec2 b2Cross(B2Vec2 a, float s) {
        return new B2Vec2(s * a.y, -s * a.x);
    }

    public static float b2Cross(B2Vec2 a, B2Vec2 b) {
        return a.cross(b);
    }

    /**
     * 设置1我们将返回的向量视为顺时针旋转了90°，或者将 s 设置为-1时视为逆时针旋转了90°
     */
    public static B2Vec2 b2Cross(float s, B2Vec2 a) {
        return new B2Vec2(-s * a.y, s * a.x);
    }

    public static float b2Dot(B2Vec2 a, B2Vec2 b) {
        return a.dot(b);
    }

    public static B2Vec2 add(B2Vec2 a, B2Vec2 B2Vec2) {
        return new B2Vec2(a.x + B2Vec2.x, a.y + B2Vec2.y);
    }

    public static B2Vec2 add(B2Vec2 a, B2Vec2 B2Vec2, B2Vec2 c) {
        return new B2Vec2(a.x + B2Vec2.x + c.x, a.y + B2Vec2.y + c.y);
    }

    public static B2Vec2 sub(B2Vec2 a, B2Vec2 B2Vec2) {
        return new B2Vec2(a.x - B2Vec2.x, a.y - B2Vec2.y);
    }

    public static B2Vec2 multiply(B2Vec2 a, float v) {
        return new B2Vec2(a.x * v, a.y * v);
    }

    public static B2Vec2 multiply(float v, B2Vec2 a) {
        return new B2Vec2(a.x * v, a.y * v);
    }

    public static B2Vec2 b2Min(B2Vec2 a, B2Vec2 b) {
        return new B2Vec2(Math.min(a.x, b.x), Math.min(a.y, b.y));
    }

    public static B2Vec2 b2Max(B2Vec2 a, B2Vec2 b) {
        return new B2Vec2(Math.max(a.x, b.x), Math.max(a.y, b.y));
    }

    public static B2Vec2 b2Abs(B2Vec2 a)
    {
        return new B2Vec2(Math.abs(a.x), Math.abs(a.y));
    }

}
