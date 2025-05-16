package com.youxi.dataStructure.box2d3_2.aabbTree;

@FunctionalInterface
public interface B2TreeShapeCastCallbackFcn {
    float callback(b2ShapeCastInput input, int proxyId, long userData, Object context);
}
