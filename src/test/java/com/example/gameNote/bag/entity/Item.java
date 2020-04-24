package com.example.gameNote.bag.entity;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/4/24 15:10
 * @Description:
 */
public class Item {
    /**
     * 唯一id
     */
    private long id;
    /**
     * 配置表id
     */
    private int cfgId;
    /**
     * 数量
     */
    private int count;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCfgId() {
        return cfgId;
    }

    public void setCfgId(int cfgId) {
        this.cfgId = cfgId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
