package com.youxi.dataStructure.box2d3_2.BHVaabbTree;

@FunctionalInterface
public interface B2TreeShapeCastCallbackFcn {
    float callback(b2ShapeCastInput input, int proxyId, Object userData, Object context);
}
