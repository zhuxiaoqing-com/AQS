package com.youxi.AI.fsm;

import com.sh.game.system.fight.FightSkillData;
import lombok.Data;

@Data
public class AIData {

    /**
     * 已经睡眠的时间，整个AI系统都要用，主要是用于战斗和移动
     */
    private int sleepTime = 0;

    /**
     * 上次寻找周围怪物的时间
     */
    private int findNearestFightTargetTime = 0;

    /**
     * 随机走动的参数 (1w-2w之间)
     */
    private int randomMoveTime = 0;

    /**
     * 仇恨清除时间
     */
    private int clearThreatTime = 3000;

    /**
     * 更新攻击目标时间
     */
    private int updateFightTarget = 5000;

    /**
     * 血条和蓝条恢复间隔时间（默认一秒）
     */
    private int recoverHpAndMpTime = 1000;

    /**
     * 死亡时间
     */
    private long dieTime = 0;

    /**
     * 出生时间
     */
    private long bornTime = 0;

    /**
     * 复活时间
     */
    private long reliveTime = 0;

    /**
     * 攻击我的对象
     */
    private long whoAttackMe;

    /**
     * 被攻击的时间
     */
    private long whoAttackMeTime;

    /**
     * 我的攻击目标
     */
    private long whoMyTarget;

    /**
     * 攻击的时间
     */
    private long whoMyTargetTime;

    /**
     * 释放的技能信息
     */
    private FightSkillData fightSkill = null;

    /**
     * 上次攻击造成的伤害值
     */
    private int lastHurtValue;

    /**
     * 攻击次数
     */
    private int hitNum;

    /**
     * 进入战斗状态时长
     */
    private long fightTime;

    public void updateFightAITime(int dt) {
        this.sleepTime -= dt;
        this.recoverHpAndMpTime -= dt;
        this.clearThreatTime -= dt;
        this.updateFightTarget -= dt;
        this.findNearestFightTargetTime -= dt;
        this.randomMoveTime -= dt;
    }

    public void updateActiveAITime(int dt) {
        this.sleepTime -= dt;
        this.recoverHpAndMpTime -= dt;
        this.findNearestFightTargetTime -= dt;
        this.randomMoveTime -= dt;
    }


    public void updatePetAITime(int dt) {
        this.sleepTime -= dt;
        this.recoverHpAndMpTime -= dt;
    }

    public void updateFightTime(int dt) {
        this.fightTime += dt;
    }
}
