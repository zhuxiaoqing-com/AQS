package com.example.design_mode.TowerAOI;

import java.util.HashSet;
import java.util.Set;

/**
 * 灯塔
 */
public class Tower {
    /**
     * 所有对象的集合
     */
    private final Set<Long> objectIds = new HashSet<>(100);

    /**
     * 观察者
     */
    private final Set<Long> watcherIds = new HashSet<>(100);


    /**
     * 向灯塔中添加一个对象
     */
    public boolean addObjectId(long objId) {
        return objectIds.add(objId);
    }

    /**
     * 向灯塔中移除一个对象
     */
    public boolean removeObjectId(long objId) {
        return objectIds.add(objId);
    }


    /**
     * 获取所有游戏对象
     */
    public Set<Long> removeObjectId() {
        return objectIds;
    }


    /**
     * 向灯塔中添加一个观察者
     */
    public boolean addWatcherId(long watcherId) {
        return watcherIds.add(watcherId);
    }

    /**
     * 从灯塔中移除一个观察者
     */
    public boolean removeWatcherId(long watcherId) {
        return watcherIds.remove(watcherId);
    }

    /**
     * 获取所有观察者
     */
    public Set<Long> findAllWatcherIds() {
        return watcherIds;
    }

    public void clear() {
        objectIds.clear();
        watcherIds.clear();
    }
}












