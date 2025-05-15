package com.youxi.dataStructure.box2d3_2.aabbTree;

import com.youxi.dataStructure.box2d3_2.entity.b2Vec2;

public class b2RayCastInput {
    /// Start point of the ray cast
    b2Vec2 origin;

    /// Translation of the ray cast
    b2Vec2 translation;

    /// The maximum fraction of the translation to consider, typically 1
    float maxFraction;
}
