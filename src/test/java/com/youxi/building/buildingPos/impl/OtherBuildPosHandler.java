package com.youxi.building.buildingPos.impl;

import com.youxi.building.buildingPos.BaseBuildPosHandler;
import com.youxi.building.entity.PosPositionInfo;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 其他普通摆件，可以放在地板，地表上面，然后不能和墙交叉。
 *
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/11 15:50
 * @Description:
 */
public class OtherBuildPosHandler extends BaseBuildPosHandler {

    /**
     * 检测是否可以建筑
     *
     * @param playerId   玩家id
     * @param allPosList 建筑占据的格子坐标
     * @param dir        方向
     */
    @Override
    public boolean checkIsCanBuilding(long playerId, Set<Integer> allPosList, int dir) {
        Map<Integer, PosPositionInfo> buildingInfoByPoss = findBuildingInfoByPosList(playerId, allPosList);

        // 不是对立面类型的墙
        Set<Long> wallSet = new HashSet<>();
        // 对立面类型的墙
        Set<Long> oppositeSet = new HashSet<>();

        for (PosPositionInfo info : buildingInfoByPoss.values()) {
            // 其他摆件 不能放在其他摆件上面
            if (info.getOtherBuildingSrl() != 0) {
                return false;
            }
            // 将墙分类 在后面用来判断 该墙是否与将要其他摆件交叉
            info.getWallInfoMap().values().forEach(wallInfo -> {
                Set<Long> set = wallInfo.isOppositePos() ? oppositeSet : wallSet;
                set.add(wallInfo.getBuildingSrl());
            });
        }

        // 判断是否有墙和将要建筑的摆件交叉
        for (Long buildingSrl : wallSet) {
            // 如果该摆件占用了 墙的建立边和对面边; 说明与墙交叉了
            if (oppositeSet.contains(buildingSrl)) {
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
            if (posPositionInfo.getOtherBuildingSrl() != 0) {
                logger.error("......................addOtherBuilding, building: {} 不为空！！！ position:{} x:{} y:{}  dir:{}" + buildingId, pos,/* Misc.getIntHigh(pos)*/pos, /*Misc.getIntLow(pos)*/pos, dir);
            }
            posPositionInfo.setOtherBuildingSrl(buildingSrl);
        }
    }


    @Override
    public void removeBuilding(Map<Integer, PosPositionInfo> positionInfoMap, long buildingSrl, int buildingId, int pos, int dir) {
        Set<Integer> allPos = findBuildingOccupyPos(buildingId, pos, dir);
        for (int position : allPos) {
            PosPositionInfo posPositionInfo = positionInfoMap.get(position);
            if (posPositionInfo != null) {
                posPositionInfo.setOtherBuildingSrl(0);
            }
        }
    }
}
