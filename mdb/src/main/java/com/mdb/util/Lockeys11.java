package com.mdb.util;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Lockeys11 {
    private static final long GET_EXPIRE_TIME = TimeUtils.getMinuteConst() * 4;
    private static final long TICK_EXPIRE_TIME = TimeUtils.getMinuteConst() * 5;

    private Lockeys11() {

    }

    private static Lockeys11 instance = new Lockeys11();

    private static Lockeys11 getInstance() {
        return instance;
    }

    private Map<String, Map<Object, Lock>> lockeys = new ConcurrentHashMap<>();


    /**
     * 怎么才能在回收的时候 通过时间戳判断是否可以回收;
     * 这个时间戳要在什么时候判断才没问题;
     * 有一种方法 get的时候如果已经超过4分钟就直接 new新的覆盖;
     * tick回收的时候超过5分钟回收;
     * <p>
     * 这样就不会有get和tick临界值的问题了;
     */
    public static Lock getLockey(String name, Object key) {
        long currTime = TimeUtils.getTime();
        Map<Object, Lock> objectLockMap = instance.lockeys.computeIfAbsent(name, a -> new ConcurrentHashMap<>());
        // ConcurrentHashMap 当key存在时候 只会更新node 不会new Node
        /*Lock lock = objectLockMap.compute(key, (k, old) -> {
            // 为空 或者过期了
            if (old == null || currTime - old.getTimeStamp() > GET_EXPIRE_TIME) {
                return new Lock(name, k);
            }

            return old;
        });
        // 设置访问时间
        lock.setTimeStamp(currTime);

        */

        // 换一种方式
        Lock lock = objectLockMap.compute(key, (k, old) -> {
            // 不存在new新的
            if (old == null) {
                return new Lock(name, k);
            }
            // 存在更新时间戳
            old.setTimeStamp(currTime);
            return old;
        });

        return lock;
    }


    public void tick() {
        Cache<Object, Object> build = Caffeine.newBuilder().build();

        long currTime = TimeUtils.getTime();

        ArrayList<Lock> removeLockList = new ArrayList<>();
        for (Map<Object, Lock> value : lockeys.values()) {
            removeLockList.clear();
            for (Lock lock : value.values()) {
                if (currTime - lock.getTimeStamp() > TICK_EXPIRE_TIME) {
                    removeLockList.add(lock);
                }
            }

            for (Lock lock : removeLockList) {
                // 删除过期的key 如果过期就删除
                value.computeIfPresent(lock.getKey(), (k, old) -> {
                    if (currTime - lock.getTimeStamp() > TICK_EXPIRE_TIME) {
                        return null;
                    }
                    return old;
                });
            }
        }

    }

}
