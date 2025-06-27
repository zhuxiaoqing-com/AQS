package com.youxi.dataStructure.aabbTree.bean;

import com.youxi.math.box2dGJK.shape.B2Vec2;

public class B2RayCastInput {
    public B2Vec2 p1; // 射线起点
    public B2Vec2  p2; // 是方向参考点
    public float maxFraction; // 控制射线延伸比例。
    //实际终点是 p1 + maxFraction*(p2 - p1)
}
