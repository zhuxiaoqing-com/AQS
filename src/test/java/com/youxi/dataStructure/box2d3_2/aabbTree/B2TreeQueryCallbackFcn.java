package com.youxi.dataStructure.box2d3_2.aabbTree;

@FunctionalInterface
public interface B2TreeQueryCallbackFcn {
    boolean callback(int proxyId, long userData, Object context);
}
