package com.youxi.math.skill;

/**
 * @author zhuxiaoqing
 * @Description: MoveCheck
 * @Date 2026/5/29 13:26
 **/
public class MoveCheck {
    /**
     * 检测移动伪代码;
     *  移动误差限流器
     *  double speed = 5.0;
     *
     * // 最多累计容忍 200ms 的移动误差 = 1m
     * double capacity = speed * 0.2;
     *
     * // 每秒恢复 50ms 的移动误差 = 0.25m/s
     * double refillPerSecond = speed * 0.05;
     * TokenBucket moveErrorBucket = new TokenBucket(refillPerSecond, capacity, nowMs);
     *
     *
     */
   /* public MoveCheckResult checkMove(Player player, Vec2 clientPos, long clientTime, long serverNow) {
        MoveState move = player.getMoveState();

        long dtMs = serverNow - move.lastServerTime;
        if (dtMs <= 0) {
            return MoveCheckResult.accept();
        }

        double dtSec = dtMs / 1000.0;

        Vec2 predictPos = move.lastServerPos.add(move.dir.mul(move.speed * dtSec));

        double error = predictPos.distance(clientPos);

        double normalTolerance = 0.3; // 普通误差
        double hardTolerance = 1.5;   // 硬上限

        if (error <= normalTolerance) {
            move.lastServerPos = clientPos;
            move.lastServerTime = serverNow;
            return MoveCheckResult.accept();
        }

        if (error > hardTolerance) {
            // 瞬移级别，不走令牌桶，直接拉回
            return MoveCheckResult.rollback(predictPos);
        }

        double over = error - normalTolerance;

        if (player.moveErrorBucket.tryConsume(over, serverNow)) {
            // 小范围异常，暂时容忍
            move.lastServerPos = clientPos;
            move.lastServerTime = serverNow;
            return MoveCheckResult.acceptSuspicious();
        }

        // 长期累计异常
        return MoveCheckResult.rollback(predictPos);
    }*/


    /**
     *
     *
     * 因为移动速度会变化，其实还可以用 固定米数，不跟速度变;
     *
     * 简单的实用方案：桶用固定米数，不跟速度变
     * capacity = 1.0;          // 最多累计容忍 1 米误差
     * refillPerSecond = 0.25;  // 每秒恢复 0.25 米误差
     *
     * 不管玩家速度是 3m/s、5m/s、10m/s，桶都一样。
     *
     * 优点是简单。
     * 缺点是：
     * 低速玩家可能太宽，高速玩家可能太严。
     * 普通 MMO 跑图，这个其实能用。
     *
     *
     * 还可以
     *  桶按“时间误差”设计，再转换成距离
     *
     *别把桶理解成“米”，而是理解成：
     * 玩家最多允许累计占 200ms 的便宜
     * 每秒恢复 50ms 的便宜额度
     * 也就是桶里存的是时间误差额度，单位是秒或者毫秒
     *
     * capacityMs = 200;  // 最多允许累计 200ms 速度误差
     * refillMsPerSecond = 50; // 每秒恢复 50ms 误差额度
     *
     * 每次检测时：
     * double overDist = realDist - allowDist - normalTolerance;
     * double overTimeMs = overDist / currentSpeed * 1000.0;
     *
     * 然后扣：
     * bucket.tryAcquire(nowMs, overTimeMs);
     *
     * 可以：
     * normalToleranceMs = 50;   // 免费误差 50ms
     * hardToleranceMs = 250;    // 单次硬上限 250ms
     * bucketCapacityMs = 200;   // 累计容忍 200ms
     * bucketRefillMsPerSecond = 50; // 每秒恢复 50ms
     *
     * 速度变化时 也有问题 服务器可能100ms的时候变化了，300ms才通知到客户端
     * 这个不管;速度变化后，直接记录旧的速度，如果新的速度不满足，如果同步时间在变化时的300ms内，就使用旧的速度再校验一次;
     */







}
