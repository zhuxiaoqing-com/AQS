package com.youxi.dataStructure.box2d3_2.entity;

public class b2Math {

    /// Get the center of the AABB.
    public static b2Vec2 b2AABB_Center(b2AABB a) {
        return new b2Vec2(0.5f * (a.lowerBound.x + a.upperBound.x), 0.5f * (a.lowerBound.y + a.upperBound.y));
    }

    public static float b2Perimeter(b2AABB a) {
        float wx = a.upperBound.x - a.lowerBound.x;
        float wy = a.upperBound.y - a.lowerBound.y;
        return 2.0f * (wx + wy);
    }

    /// Union of two AABBs
    public static b2AABB b2AABB_Union(b2AABB a, b2AABB b) {
        b2AABB c = new b2AABB();
        c.lowerBound.x = b2MinFloat(a.lowerBound.x, b.lowerBound.x);
        c.lowerBound.y = b2MinFloat(a.lowerBound.y, b.lowerBound.y);
        c.upperBound.x = b2MaxFloat(a.upperBound.x, b.upperBound.x);
        c.upperBound.y = b2MaxFloat(a.upperBound.y, b.upperBound.y);
        return c;
    }

    public static float b2MinFloat(float a, float b) {
        return a < b ? a : b;
    }

    /// @return the maximum of two floats
    public static float b2MaxFloat(float a, float b) {
        return a > b ? a : b;
    }

    public static b2Vec2 b2Add(b2Vec2 a, b2Vec2 b) {
        return new b2Vec2(a.x + b.x, a.y + b.y);
    }

    /// Vector subtraction
    public static b2Vec2 b2Sub(b2Vec2 a, b2Vec2 b) {
        return new b2Vec2(a.x - b.x, a.y - b.y);
    }

    /// Vector negation
    public static b2Vec2 b2Neg(b2Vec2 a) {
        return new b2Vec2(-a.x, -a.y);
    }

    public static float b2LengthSquared(b2Vec2 v) {
        return v.x * v.x + v.y * v.y;
    }

}
