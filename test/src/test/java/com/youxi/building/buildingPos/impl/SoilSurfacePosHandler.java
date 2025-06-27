package com.youxi.building.buildingPos.impl;

import com.youxi.building.buildingPos.BaseBuildPosHandler;
import com.youxi.building.entity.PosPositionInfo;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 地表可以放在任何东西上面，也可以重复放置在地表上，但是如果和已经放置的地表完全重合了，需把旧的地表删除
 *
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/11 15:50
 * @Description: 地表处理类
 */
public class SoilSurfacePosHandler extends BaseBuildPosHandler {

    /**
     * 检测是否可以建筑
     *
     * @param playerId   玩家id
     * @param allPosList 建筑占据的格子坐标
     * @param dir        方向
     */
    @Override
    protected boolean checkIsCanBuilding(long playerId, Set<Integer> allPosList, int dir) {
        // 地表肯定可以建筑
        return true;
    }


    @Override
    public void addBuilding(long playerId, Map<Integer, PosPositionInfo> positionInfoMap, long buildingSrl, int buildingId, int pos, int dir) {
        Set<Integer> allPos = findBuildingOccupyPos(buildingId, pos, dir);
        // 删除被新的地表完全覆盖的旧的地表
        overwrittenOldBuildings(playerId, positionInfoMap, allPos);
        for (int position : allPos) {
            PosPositionInfo posPositionInfo = positionInfoMap.computeIfAbsent(position, a -> new PosPositionInfo(position));
            posPositionInfo.getSoilSurfaceSrl().add(buildingSrl);
        }
    }


    @Override
    public void removeBuilding(Map<Integer, PosPositionInfo> positionInfoMap, long buildingSrl, int buildingId, int pos, int dir) {
        Set<Integer> allPos = findBuildingOccupyPos(buildingId, pos, dir);
        for (int position : allPos) {
            PosPositionInfo posPositionInfo = positionInfoMap.get(position);
            if (posPositionInfo != null) {
                posPositionInfo.getSoilSurfaceSrl().remove(buildingSrl);
            }
        }
    }

    /**
     * 删除被新的地表完全覆盖的旧的地表
     */
    private void overwrittenOldBuildings(long playerId, Map<Integer, PosPositionInfo> positionInfoMap, Set<Integer> allPosList) {
        //  key: 建筑的唯一Id
        Set<Long> beOverwrittenBuildings = new HashSet<>();
        for (int pos : allPosList) {
            PosPositionInfo positionInfo = positionInfoMap.get(pos);
            if (positionInfo == null) {
                continue;
            }
            beOverwrittenBuildings.addAll(positionInfo.getSoilSurfaceSrl());
        }


      /*  PlayerHomeProxy homeProxy = new PlayerHomeProxy(playerId);
        Map<Long, HomeBuilding> homeBuilding = homeProxy.getHome().getBuild();

        for (long soilSurfaceSrl : beOverwrittenBuildings) {
            HomeBuilding building = homeBuilding.get(soilSurfaceSrl);
            Set<Integer> oldBuildingOccupyPos = findBuildingOccupyPos(building.getBid(), building.getPos(), building.getDir());

            // 如果新建的地表 比 旧地表小 就覆盖不了旧的地表
            if (allPosList.size() < oldBuildingOccupyPos.size()) {
                continue;
            }

            // 如果新地表完全覆盖了旧地表
            if (allPosList.containsAll(oldBuildingOccupyPos)) {
                // 移除被覆盖的地表
                homeProxy.removeBuilding(soilSurfaceSrl);
            }
        }*/
    }
}
