package com.youxi.dataStructure.box2d3_2.aabbTree;

import com.youxi.math.box2dGJK.shape.B2Vec2;

public class b2ShapeCastInput {
    /// A generic shape
    public b2ShapeProxy proxy;

    /// The translation of the shape cast
    public B2Vec2 translation;

    /// The maximum fraction of the translation to consider, typically 1
    public float maxFraction;

    /// Allow shape cast to encroach when initially touching. This only works if the radius is greater than zero.
    // 当初始接触时允许形状轻微侵入其他物体。注意该功能需形状半径大于零才生效
    public boolean canEncroach;
}
