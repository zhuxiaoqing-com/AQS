package com.youxi.dataStructure.box2d3_2.BHVaabbTree;

@FunctionalInterface
public interface B2TreeRayCastCallbackFcn {
    float callback(b2RayCastInput input, int proxyId, Object userData, Object context);
}
