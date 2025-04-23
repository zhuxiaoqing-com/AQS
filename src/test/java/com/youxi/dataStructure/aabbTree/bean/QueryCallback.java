package com.youxi.dataStructure.aabbTree.bean;

@FunctionalInterface
public interface QueryCallback {
    boolean queryCallback(int nodeId);
}
