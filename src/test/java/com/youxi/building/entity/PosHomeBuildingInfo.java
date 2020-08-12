package com.youxi.building.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/13 20:02
 * @Description:
 */
public class PosHomeBuildingInfo {
    /**
     * key: position
     * value: 当前坐标的信息
     */
    private Map<Integer, PosPositionInfo> positionInfoMap = new HashMap<>();

    public Map<Integer, PosPositionInfo> getPositionInfoMap() {
        return positionInfoMap;
    }

    public void setPositionInfoMap(Map<Integer, PosPositionInfo> positionInfoMap) {
        this.positionInfoMap = positionInfoMap;
    }
}
