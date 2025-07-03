package com.mdb.utils;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class Lockeys {
    private static final long GET_EXPIRE_TIME = TimeUtils.getMinuteConst() * 4;
    private static final long TICK_EXPIRE_TIME = TimeUtils.getMinuteConst() * 5;

    private Lockeys() {
    }

    private static Lockeys instance = new Lockeys();

    private static Lockeys getInstance() {
        return instance;
    }


    Cache<LockKey, Lock> cache = Caffeine.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES).build();

    public static Lock getLockey(String name, Object key) {
        return instance.cache.get(new LockKey(name, key), a -> new Lock(name, key));
    }


}
