package com.youxi.building.entity;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/10 11:14
 * @Description: 存储家园可建筑块的数据
 */
public class HomeBuildingChunkBean {
    private int cfgId;

    private int minChunkX; // x轴的最小格子 id
    private int maxChunkX; // x轴的最大格子 id

    private int minChunkZ;// z轴的最小格子 id
    private int maxChunkZ;// z轴的最大格子 id

    public HomeBuildingChunkBean(int cfgId, int minChunkX, int maxChunkX, int minChunkZ, int maxChunkZ) {
        this.cfgId = cfgId;
        this.minChunkX = minChunkX;
        this.maxChunkX = maxChunkX;
        this.minChunkZ = minChunkZ;
        this.maxChunkZ = maxChunkZ;
    }


    public int getCfgId() {
        return cfgId;
    }

    public void setCfgId(int cfgId) {
        this.cfgId = cfgId;
    }

    public int getMinChunkX() {
        return minChunkX;
    }

    public void setMinChunkX(int minChunkX) {
        this.minChunkX = minChunkX;
    }

    public int getMaxChunkX() {
        return maxChunkX;
    }

    public void setMaxChunkX(int maxChunkX) {
        this.maxChunkX = maxChunkX;
    }

    public int getMinChunkZ() {
        return minChunkZ;
    }

    public void setMinChunkZ(int minChunkZ) {
        this.minChunkZ = minChunkZ;
    }

    public int getMaxChunkZ() {
        return maxChunkZ;
    }

    public void setMaxChunkZ(int maxChunkZ) {
        this.maxChunkZ = maxChunkZ;
    }
}
