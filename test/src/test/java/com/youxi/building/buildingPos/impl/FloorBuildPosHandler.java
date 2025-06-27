package com.youxi.building.buildingPos.impl;

import com.youxi.building.buildingPos.BaseBuildPosHandler;
import com.youxi.building.entity.PosPositionInfo;

import java.util.Map;
import java.util.Set;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/11 15:50
 * @Description: 地板处理类
 */
public class FloorBuildPosHandler extends BaseBuildPosHandler {

    /**
     * 检测是否可以建筑
     *
     * @param playerId   玩家id
     * @param allPosList 建筑占据的格子坐标
     * @param dir        方向
     */
    @Override
    protected boolean checkIsCanBuilding(long playerId, Set<Integer> allPosList, int dir) {
        Map<Integer, PosPositionInfo> buildingInfos = findBuildingInfoByPosList(playerId, allPosList);

        for (PosPositionInfo info : buildingInfos.values()) {
            // 地板不可以放在地板 和 其他建筑上面
            if (info.getFloorSrl() != 0 || info.getOtherBuildingSrl() != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addBuilding(long playerId, Map<Integer, PosPositionInfo> positionInfoMap, long buildingSrl, int buildingId, int pos, int dir) {
        Set<Integer> allPos = findBuildingOccupyPos(buildingId, pos, dir);
        for (int position : allPos) {
            PosPositionInfo posPositionInfo = positionInfoMap.computeIfAbsent(position, a -> new PosPositionInfo(position));
            if (posPositionInfo.getFloorSrl() != 0) {
                logger.error("......................addFloorBuilding, building: {} 不为空！！！ position:{} x:{} y{}  dir:{}" + buildingId, pos, 0,0/*Misc.getIntHigh(pos), Misc.getIntLow(pos)*/, dir);
            }
            posPositionInfo.setFloorSrl(buildingSrl);
        }
    }


    @Override
    public void removeBuilding(Map<Integer, PosPositionInfo> positionInfoMap, long buildingSrl, int buildingId, int pos, int dir) {
        Set<Integer> allPos = findBuildingOccupyPos(buildingId, pos, dir);
        for (int position : allPos) {
            PosPositionInfo posPositionInfo = positionInfoMap.get(position);
            if (posPositionInfo != null) {
                posPositionInfo.setFloorSrl(0);
            }
        }
    }
}
