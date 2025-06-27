package com.youxi.building.buildingPos.impl;

import com.youxi.building.Misc;
import com.youxi.building.buildingPos.BaseBuildPosHandler;
import com.youxi.building.entity.PosPositionInfo;
import com.youxi.building.entity.PosWallInfo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 墙不能和其他物品交叉放   但是可以放在地板和地表上面
 *
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/11 15:50
 * @Description:
 */
public class WallBuildPosHandler extends BaseBuildPosHandler {

    /**
     * 检测是否可以建筑
     *
     * @param playerId 玩家id
     * @param wallPos  墙真正占据的坐标
     * @param dir      方向
     */
    @Override
    public boolean checkIsCanBuilding(long playerId, Set<Integer> wallPos, int dir) {
        // 将墙的占据坐标分组，分为墙真正占据的坐标 和墙对面的坐标
        Set<Integer> oppositePos = findOppositePos(wallPos, dir);

        Set<Integer> list = new HashSet<>();
        list.addAll(wallPos);
        list.addAll(oppositePos);

        Map<Integer, PosPositionInfo> buildingInfos = findBuildingInfoByPosList(playerId, list);


        // 不是对立面类型的墙  key: 唯一id   value:position
        Map<Long, Set<PosWallInfo>> oldWalls = new HashMap<>();
        // 对立面类型的墙  key: 唯一id   value:position
        Map<Long, Set<PosWallInfo>> oldOpposites = new HashMap<>();
        // 其他摆件 key: 唯一id   value:position
        Map<Long, Set<Integer>> oldOtherBuildings = new HashMap<>();

        for (PosPositionInfo info : buildingInfos.values()) {
            if (info.getOtherBuildingSrl() != 0) {
                oldOtherBuildings.computeIfAbsent(info.getOtherBuildingSrl(), a -> new HashSet<>()).add(info.getPosition());
            }

            info.getWallInfoMap().values().forEach(wallInfo -> {
                Map<Long, Set<PosWallInfo>> map = wallInfo.isOppositePos() ? oldOpposites : oldWalls;
                map.computeIfAbsent(wallInfo.getBuildingSrl(), a -> new HashSet<>()).add(wallInfo);
            });
        }

        // 判断普通建筑是否和将要建立的墙交叉
        for (Set<Integer> positionSet : oldOtherBuildings.values()) {
            long wallCount = wallPos.stream().filter(positionSet::contains).count();
            if (wallCount == 0) {
                continue;
            }
            long oppositeCount = oppositePos.stream().filter(positionSet::contains).count();
            if (oppositeCount == 0) {
                continue;
            }
            return false;
        }


        // 判断新的墙和旧的墙 是否交叉
        for (Map.Entry<Long, Set<PosWallInfo>> entry : oldWalls.entrySet()) {
            long buildingSrl = entry.getKey();
            Set<PosWallInfo> wallPosSet = entry.getValue();
            if (oldOpposites.containsKey(buildingSrl)) {
                PosWallInfo next = wallPosSet.iterator().next();
                // 如果新墙在旧墙的两边 并且新墙和旧墙的方向相同或者相反(判断重合)
                if (((next.getDir() + dir) & 1) == 0) {
                    return false;
                }
                Set<PosWallInfo> oppositesPosSet = oldOpposites.get(buildingSrl);
                // 如果新的墙在旧的墙的两边并且都占了二格以上(判断交叉)
                if (wallPosSet.size() >= 2 && oppositesPosSet.size() >= 2) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void addBuilding(long playerId, Map<Integer, PosPositionInfo> positionInfoMap, long buildingSrl, int buildingId, int pos, int dir) {
        Set<Integer> wallPos = findBuildingOccupyPos(buildingId, pos, dir);
        Set<Integer> oppositePos = findOppositePos(wallPos, dir);

        for (int position : wallPos) {
            PosPositionInfo posPositionInfo = positionInfoMap.computeIfAbsent(position, a -> new PosPositionInfo(position));
            posPositionInfo.getWallInfoMap().put(buildingSrl, new PosWallInfo(buildingSrl, position, dir, false));
        }
        for (int position : oppositePos) {
            PosPositionInfo posPositionInfo = positionInfoMap.computeIfAbsent(position, a -> new PosPositionInfo(position));
            posPositionInfo.getWallInfoMap().put(buildingSrl, new PosWallInfo(buildingSrl, position, dir, true));
        }
    }

    @Override
    public void removeBuilding(Map<Integer, PosPositionInfo> positionInfoMap, long buildingSrl, int buildingId, int pos, int dir) {
        Set<Integer> wallPos = findBuildingOccupyPos(buildingId, pos, dir);
        wallPos.addAll(findOppositePos(wallPos, dir));

        for (int position : wallPos) {
            PosPositionInfo posPositionInfo = positionInfoMap.get(position);
            if (posPositionInfo != null) {
                posPositionInfo.getWallInfoMap().remove(buildingSrl);
            }
        }
    }


    /**
     * 获取墙的另一面坐标
     *
     * @param pos pos
     * @param dir dir
     */
    private Set<Integer> findOppositePos(Set<Integer> pos, int dir) {
        Set<Integer> posSet = new HashSet<>();
        switch (dir) {
            case 1: {
                for (Integer p : pos) {
                    short x = Misc.getIntHigh(p);
                    short y = Misc.getIntLow(p);
                    posSet.add(Misc.getIntIndexByXY(x, (short) (y - 1)));
                }
                break;
            }
            case 2: {
                for (Integer p : pos) {
                    short x = Misc.getIntHigh(p);
                    short y = Misc.getIntLow(p);
                    posSet.add(Misc.getIntIndexByXY((short) (x - 1), y));
                }
                break;
            }
            case 3: {
                for (Integer p : pos) {
                    short x = Misc.getIntHigh(p);
                    short y = Misc.getIntLow(p);
                    posSet.add(Misc.getIntIndexByXY(x, (short) (y + 1)));
                }
                break;
            }
            case 0: {
                for (Integer p : pos) {
                    short x = Misc.getIntHigh(p);
                    short y = Misc.getIntLow(p);
                    posSet.add(Misc.getIntIndexByXY((short) (x + 1), y));
                }
                break;
            }
            default: {
                throw new RuntimeException("获取墙的另一面坐标 dir不存在：" + dir);
            }
        }

        // 筛选出合法的坐标
        return posSet.stream().filter(this::checkPosIsLegal).collect(Collectors.toSet());
    }
}

