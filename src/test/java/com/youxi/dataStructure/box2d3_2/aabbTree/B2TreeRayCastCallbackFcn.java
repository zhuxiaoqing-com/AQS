package com.youxi.dataStructure.box2d3_2.aabbTree;

@FunctionalInterface
public interface B2TreeRayCastCallbackFcn {
    float callback(b2RayCastInput input, int proxyId, long userData, Object context);
}
