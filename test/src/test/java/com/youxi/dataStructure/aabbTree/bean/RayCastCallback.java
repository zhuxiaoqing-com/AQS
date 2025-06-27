package com.youxi.dataStructure.aabbTree.bean;

@FunctionalInterface
public interface RayCastCallback {

    /**
     *
     * @return return 0 结束 返回<0 不处理 返回>0 修改射线长度为返回的值
     */
    float rayCastCallback(B2RayCastInput subInput, int nodeId);
}
