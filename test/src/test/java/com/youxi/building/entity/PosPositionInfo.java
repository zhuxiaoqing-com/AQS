package com.youxi.building.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/13 20:02
 * @Description: 建筑分为四类：墙 地表 地板 其他摆件
 */
public class PosPositionInfo {
    /**
     * 坐标
     */
    private int position;

    // 其他摆件 不能放在其他摆件上面;  不能和墙交叉放
    private long otherBuildingSrl;
    // 一个坐标上面可以放多个墙, 不能和其他摆件和墙交叉放
    private Map<Long, PosWallInfo> wallInfoMap = new HashMap<>();
    // 地板 不能放在其他地板 其他摆件上面
    private long floorSrl;
    // 地表 放：任何东西都可以放在地表上，被放：地表可以放在任何东西上面;   新地表如果和旧地表冲突就直接覆盖
    private Set<Long> soilSurfaceSrl = new HashSet<>();

    public PosPositionInfo(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public long getOtherBuildingSrl() {
        return otherBuildingSrl;
    }

    public void setOtherBuildingSrl(long otherBuildingSrl) {
        this.otherBuildingSrl = otherBuildingSrl;
    }

    public Map<Long, PosWallInfo> getWallInfoMap() {
        return wallInfoMap;
    }

    public void setWallInfoMap(Map<Long, PosWallInfo> wallInfoMap) {
        this.wallInfoMap = wallInfoMap;
    }

    public long getFloorSrl() {
        return floorSrl;
    }

    public void setFloorSrl(long floorSrl) {
        this.floorSrl = floorSrl;
    }

    public Set<Long> getSoilSurfaceSrl() {
        return soilSurfaceSrl;
    }

    public void setSoilSurfaceSrl(Set<Long> soilSurfaceSrl) {
        this.soilSurfaceSrl = soilSurfaceSrl;
    }

    /**
     * 判断该数据类 是否为空
     *
     * @return true: 为空  false: 不为空
     */
    public boolean isEmpty() {
        return otherBuildingSrl == 0 && wallInfoMap.isEmpty() && floorSrl == 0 && soilSurfaceSrl.isEmpty();
    }

    /**
     * 是否是空地
     * 如果该数据类只有墙的话就是空地
     *
     * @return true: 为空  false: 不为空
     */
    public boolean isSpaceArea() {
        return otherBuildingSrl == 0 && floorSrl == 0 && soilSurfaceSrl.isEmpty();
    }
}
