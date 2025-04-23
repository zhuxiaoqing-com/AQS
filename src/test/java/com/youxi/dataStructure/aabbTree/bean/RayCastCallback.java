package com.youxi.dataStructure.aabbTree.bean;

@FunctionalInterface
public interface RayCastCallback {

    float rayCastCallback(B2RayCastInput subInput, int nodeId);
}
