package com.youxi.dataStructure.box2d3_2.jgk;

// 低级光线投射或形状投射输出数据。在初始重叠的情况下，返回零分数和法线。

import com.youxi.math.box2dGJK.shape.B2Vec2;

/// Low level ray cast or shape-cast output data. Returns a zero fraction and normal in the case of initial overlap.
public class B2CCDOutput {
    /// The surface normal at the hit point 碰撞法线
    public B2Vec2 normal = new B2Vec2();

    /// The surface hit point
    public B2Vec2 point = new B2Vec2(); // 碰撞点

    /// The fraction of the input translation at collision
    public float fraction;

    /// The number of iterations used
    public int iterations;

    /// Did the cast hit?
    public boolean hit;
}
