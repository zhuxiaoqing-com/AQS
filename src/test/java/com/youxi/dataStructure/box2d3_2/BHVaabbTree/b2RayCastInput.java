package com.youxi.dataStructure.box2d3_2.BHVaabbTree;

import com.youxi.math.box2dGJK.shape.B2Vec2;

public class b2RayCastInput {
    /// Start point of the ray cast
    B2Vec2 origin;

    /// Translation of the ray cast
    B2Vec2 translation;

    /// The maximum fraction of the translation to consider, typically 1
    float maxFraction;
}
