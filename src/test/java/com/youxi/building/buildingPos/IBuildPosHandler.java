package com.youxi.building.buildingPos;

import com.youxi.building.entity.PosPositionInfo;

import java.util.Map;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/13 17:15
 * @Description:
 */
public interface IBuildPosHandler {

    /**
     * 检测是否可以建筑
     */
    boolean checkIsCanBuilding(long playerId, int buildingId, int pos, int dir);

    /**
     * 添加建筑
     */
    void addBuilding(long playerId, Map<Integer, PosPositionInfo> positionInfoMap, long buildingSrl, int buildingId, int pos, int dir);

    /**
     * 删除建筑
     */
    void removeBuilding(Map<Integer, PosPositionInfo> positionInfoMap, long buildingSrl, int buildingId, int pos, int dir);
}
