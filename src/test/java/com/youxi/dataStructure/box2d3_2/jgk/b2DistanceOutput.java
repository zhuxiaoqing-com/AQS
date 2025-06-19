package com.youxi.dataStructure.box2d3_2.jgk;

import com.youxi.math.box2dGJK.shape.B2Vec2;

// b2Distance的输出
public class b2DistanceOutput {
    B2Vec2 pointA = new B2Vec2(0, 0);        //shapeA上最近的点 如果碰撞了 pointA和pointB相等
    B2Vec2 pointB = new B2Vec2(0, 0);        //shapeB上最近的点
    B2Vec2 normal = new B2Vec2(0, 0);
    /// < Normal vector that points from A to B. Invalid if distance is zero.
    float distance;
    /// < The final distance, zero if overlapped
    int iterations;
    /// < Number of GJK iterations used
    int simplexCount; ///< The number of simplexes stored in the simplex array
}
