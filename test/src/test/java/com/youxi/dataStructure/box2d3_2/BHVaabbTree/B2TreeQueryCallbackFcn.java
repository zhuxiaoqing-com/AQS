package com.youxi.dataStructure.box2d3_2.BHVaabbTree;

import com.youxi.math.box2dGJK.shape.B2AABB;

@FunctionalInterface
public interface B2TreeQueryCallbackFcn {
    boolean callback(B2AABB b2AABB, int proxyId, Object userData, Object context);
}
