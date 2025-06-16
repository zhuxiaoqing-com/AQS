package com.youxi.dataStructure.box2d3_2.jgk;

import com.youxi.math.box2dGJK.shape.B2Vec2;

public class b2SimplexVertex {
    B2Vec2 wA;
    /// < support point in proxyA
    B2Vec2 wB;
    /// < support point in proxyB
    B2Vec2 w;
    /// < wB - wA
    float a;
    /// < barycentric coordinate for closest point
    int indexA;
    /// < wA index
    int indexB; ///< wB index
}
