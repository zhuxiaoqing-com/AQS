package com.youxi.building.entity;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/13 20:02
 * @Description:
 */
public class PosWallInfo {
    /**
     * 建筑唯一Id
     */
    private long buildingSrl;

    /**
     * 建筑所在的坐标(一个建筑可能占好几个坐标，这个坐标只表示其中一个 例子：一个地板占4个坐标，这个坐标只是其中的一个)
     */
    private int position;

    /**
     * 墙朝向
     */
    private long dir;

    /**
     * 该坐标位置放置的墙  是否是对立面类型的墙 (墙建筑分两面 实际占据的位置是一面，和实际占据的位置的对立面瑟是一面)
     */
    private boolean oppositePos;


    public PosWallInfo(long buildingSrl, int position, long dir, boolean oppositePos) {
        this.buildingSrl = buildingSrl;
        this.position = position;
        this.dir = dir;
        this.oppositePos = oppositePos;
    }

    public long getBuildingSrl() {
        return buildingSrl;
    }

    public void setBuildingSrl(long buildingSrl) {
        this.buildingSrl = buildingSrl;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isOppositePos() {
        return oppositePos;
    }

    public void setOppositePos(boolean oppositePos) {
        this.oppositePos = oppositePos;
    }

    public long getDir() {
        return dir;
    }

    public void setDir(long dir) {
        this.dir = dir;
    }
}
