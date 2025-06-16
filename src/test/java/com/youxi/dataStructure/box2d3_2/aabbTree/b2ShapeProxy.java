package com.youxi.dataStructure.box2d3_2.aabbTree;

import com.youxi.math.box2dGJK.shape.B2Vec2;

public class b2ShapeProxy {
    /// The point cloud
    B2Vec2 points[];

    /// The number of points. Must be greater than 0.
    int count;

    /// The external radius of the point cloud. May be zero.
    float radius;
}
