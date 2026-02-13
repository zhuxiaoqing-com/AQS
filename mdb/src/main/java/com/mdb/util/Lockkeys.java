package com.mdb.util;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class Lockkeys {
    private static final long GET_EXPIRE_TIME = TimeUtils.getMinuteConst() * 4;
    private static final long TICK_EXPIRE_TIME = TimeUtils.getMinuteConst() * 5;

    private Lockkeys() {
    }

    private static Lockkeys instance = new Lockkeys();

    private static Lockkeys getInstance() {
        return instance;
    }


    Cache<LockKey, Lock> cache = Caffeine.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES).build();

    public static Lock getLockey(String name, Object key) {
        return instance.cache.get(new LockKey(name, key), a -> new Lock(name, key));
    }


}
