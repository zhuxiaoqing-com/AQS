package com.youxi.math.skill;

/**
 * @author zhuxiaoqing
 * @Description: TokenBucket
 * @Date 2026/5/29 13:07
 **/


/**
 * 技能限流器，解决那种技能cd小于1秒的技能;
 * 因为网络破洞，客户端消息堆积，服务器消息堆积，两边GC都有可能导致技能一下子全部过来;
 * 对于cd过小的技能，建议使用技能限流器，也就是令牌桶，允许短时间突发，但整体平均受控
 *
 * 可以用令牌桶和滑动窗口：
 * 区别 令牌桶是每次都计算当前能恢复多少个，只看令牌;
 * 滑动窗口是看历史记录，当前到前1000ms有没有突破多少个;更严格;
 *
 * 比如1秒限时100次：
 * 滑动窗口 0ms的时候进来了100个; 等500ms的时候再来50个，就不行了，因为500ms到-500ms已经有100个了;
 * 令牌桶   0ms的时候进来了100个; 等500ms的时候再来50个，是可以的，因为已经过去500ms，可以再生成50个令牌;
 *
 * 相对来说 令牌桶面对短时间突发效果更好
 *
 * TokenBucket  是令牌桶
 *
 *
 *
 */

public final class TokenBucket {

    /**
     * 每毫秒恢复多少令牌
     * 例如 100/s => 0.1/ms
     */
    private final double refillPerMs;

    /**
     * 最大令牌容量
     * 例如 100/s，20%冗余 => 120
     */
    private final double capacity;

    /**
     * 当前令牌数量
     */
    private double tokens;

    /**
     * 上次恢复令牌的时间
     */
    private long lastRefillMs;


    /**
     *
     * 次数限流用：例如 100/s，burstRatio=1.2，容量就是 120。
     *
     * @param permitsPerSecond 每秒多少次
     * @param burstRatio 冗余, 比如1.2就是冗余百分之20
     * @param nowMs 当前毫秒
     */
    public TokenBucket(int permitsPerSecond, double burstRatio, long nowMs) {
        if (permitsPerSecond <= 0) {
            throw new IllegalArgumentException("permitsPerSecond must > 0");
        }
        if (burstRatio < 1.0) {
            throw new IllegalArgumentException("burstRatio must >= 1.0");
        }

        this.refillPerMs = permitsPerSecond / 1000.0;
        this.capacity = permitsPerSecond * burstRatio;
        this.tokens = this.capacity;
        this.lastRefillMs = nowMs;
    }

    /**
     * 通用限流用：例如移动误差桶。
     *
     * @param refillPerSecond 每秒恢复多少令牌。移动里可以理解为每秒恢复多少米误差额度。
     * @param capacity 最大容量。移动里可以理解为最多累计允许多少米误差。
     */
    public TokenBucket(double refillPerSecond, double capacity, long nowMs) {
        if (refillPerSecond < 0) {
            throw new IllegalArgumentException("refillPerSecond must >= 0");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must > 0");
        }

        this.refillPerMs = refillPerSecond / 1000.0;
        this.capacity = capacity;
        this.tokens = capacity;
        this.lastRefillMs = nowMs;
    }

    public boolean tryAcquire(long nowMs) {
        return tryAcquire(nowMs, 1);
    }

    public boolean tryAcquire(long nowMs, int cost) {
        if (cost <= 0) {
            throw new IllegalArgumentException("cost must > 0");
        }

        refill(nowMs);

        if (tokens < cost) {
            return false;
        }

        tokens -= cost;
        return true;
    }

    public boolean tryAcquire(long nowMs, double cost) {
        if (cost <= 0) {
            throw new IllegalArgumentException("cost must > 0");
        }

        refill(nowMs);

        if (tokens < cost) {
            return false;
        }

        tokens -= cost;
        return true;
    }

    private void refill(long nowMs) {
        if (nowMs <= lastRefillMs) {
            return;
        }

        long deltaMs = nowMs - lastRefillMs;
        tokens = Math.min(capacity, tokens + deltaMs * refillPerMs);
        lastRefillMs = nowMs;
    }

    public double getTokens(long nowMs) {
        refill(nowMs);
        return tokens;
    }

    public double getCapacity() {
        return capacity;
    }
}
