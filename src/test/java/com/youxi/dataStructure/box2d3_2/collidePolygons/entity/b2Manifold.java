package com.youxi.dataStructure.box2d3_2.collidePolygons.entity;

import com.youxi.dataStructure.box2d3_2.entity.b2Vec2;

public class b2Manifold {
    /// The unit normal vector in world space, points from shape A to bodyB
    // 世界空间中的单位法向量，从形状A指向物体b
    b2Vec2 normal;

    /// Angular impulse applied for rolling resistance. N * m * s = kg * m^2 / s
    // 用于滚动阻力的角冲量。N * m * s = kg * m^2 / s
    float rollingImpulse;

    /// The manifold points, up to two are possible in 2D
    // 流形点，最多两个在二维中是可能的  流形点：两个碰撞物体之间的有效接触点集合
    b2ManifoldPoint points[] = new b2ManifoldPoint[2];

    /// The number of contacts points, will be 0, 1, or 2
    // 接触点的数量，将是0、1或2
    int pointCount;
}

