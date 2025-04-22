package com.youxi.dataStructure.aabbTree.bean;

public interface RayCastCallback {

    float rayCastCallback(B2RayCastInput subInput, int nodeId);
}
