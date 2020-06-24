package com.example.redis.jedis.funnellimitflow;

import java.util.HashMap;
import java.util.Map;

/**
 * 漏斗限流
 */
public class FunnelLimitFlow {
    static class Funnel {
        // 漏斗容量
        int capacity;
        // 漏嘴流水速率 (可以以 xx/毫秒  比如 100/1秒 的话, 就100/1000毫秒 就是 流水速率了)
        float leakingRate;
        // 漏斗剩余空间
        int leftQuota;
        // 上一次漏水时间
        long leakingTs;

        public Funnel(int capacity, float leakingRate) {
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = capacity;
            this.leakingTs = System.currentTimeMillis();
        }

        void makeSpace() {
            long nowTs = System.currentTimeMillis();
            // 距离上一次漏水过去了多久
            long deltaTs = nowTs - leakingTs;
            // 又可以腾出不少空间了
            int deltaQuota = (int) (deltaTs * leakingRate);
            if (deltaQuota < 0) { // 间隔时间太长，整数数字过大溢出
                this.leftQuota = capacity;
                this.leakingTs = nowTs;
                return;
            }
            // 腾的空间太少，那就等下次吧
            if (deltaQuota < 1) { // 腾出空间太小，最小单位是1
                return;
            }
            this.leftQuota += deltaQuota;
            this.leakingTs = nowTs;
            // 剩余空间不得高于容量
            if (this.leftQuota > this.capacity) {
                this.leftQuota = this.capacity;
            }
        }

        boolean watering(int quota) {
            makeSpace();
            // 判断剩余空间是否足够
            if (this.leftQuota >= quota) {
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }
    }
    // 所有的漏斗
    private Map<String, Funnel> funnels = new HashMap<>();

    /**
     *
     * @param userId
     * @param actionKey
     * @param capacity  漏斗容量
     * @param leakingRate 漏水流水速率 quota/ms
     * @return
     */
    public boolean isActionAllowed(String userId, String actionKey, int capacity, float leakingRate) {
        String key = String.format("%s:%s", userId, actionKey);
        Funnel funnel = funnels.get(key);
        if (funnel == null) {
            funnel = new Funnel(capacity, leakingRate);
            funnels.put(key, funnel);
        }
        return funnel.watering(1); // 需要1个quota
    }

    public void test01() {
    }
}
