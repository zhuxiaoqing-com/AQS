package com.youxi.dataStructure.aabbTree.bean;

@FunctionalInterface
public interface QueryCallback {
    /**
     *
     * @param nodeId
     * @param aabb
     * @return 返回true 继续  返回false 直接结束
     */
    boolean queryCallback(int nodeId,B2AABB aabb);
}
