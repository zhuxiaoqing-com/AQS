package com.example.demo1.util.lock;

import org.junit.jupiter.api.Test;

public class Test1 {

    @Test
    public void test01() {
        ChainLock lock = LockUtils.getLock(11, 22, 11);
        lock.lock();
        lock.unlock();
    }
}
