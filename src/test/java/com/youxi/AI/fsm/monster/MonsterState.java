package com.youxi.AI.fsm.monster;


import com.example.demo1.demo.TimeUtil;
import com.youxi.AI.entity.GameMap;
import com.youxi.AI.entity.Hurt;
import com.youxi.AI.entity.Monster;
import com.youxi.AI.fsm.AbstractFSMState;
import com.youxi.AI.fsm.state.PerformerActionState;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 怪物状态机
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2018/4/8.
 */
public abstract class MonsterState extends AbstractFSMState<PerformerActionState, Monster> {

    private static long ownTime = 0;
    private int deltaTime = 0;

    static {
        //ownTime = ConfigDataManager.getInstance().getById(CfgGameSetting.class, 1).getOwnTime();
    }

    public MonsterState(PerformerActionState type, Monster performer) {
        super(type, performer);
    }

    @Override
    public void update(GameMap map, int delta) {
        //怪物存活时间结束
        if (performer.getSurvivalTime() > 0 && TimeUtil.getNowOfMills() >= performer.getSurvivalTime()) {
            performer.setHp(0);
            performer.setDead(true);
        }
        //每秒计时
        deltaTime += delta;
        if (deltaTime >= 1000) {
            deltaTime = 0;
            if (!performer.isDead()) {
                // 血量变更触发被动技能
                //GameManager.getInstance().get(PassiveSkillManager.class).triggerPassive(map, performer, performer, FightConst.PassiveType.HP, 0);
            }
        }
        //更新怪物buff
        //BuffManager.getInstance().updateBuffer(map, performer, delta);
/*
        for (TriggerEvent event : performer.getTriggerEvent()) {
            event.trigger(map, performer, delta);
        }*/

        removeOverTimeOwner();
    }

    /**
     * 怪物归属-超过归属时间删除玩家
     */
    private void removeOverTimeOwner() {
        // 仇恨列表过期删除
        Map<Long, Hurt> threatMap = performer.getThreatMap();
        if (threatMap == null || threatMap.isEmpty()) {
            return;
        }
        long now = System.currentTimeMillis();
        Set<Long> removeRids = null;
        for (Map.Entry<Long, Hurt> entry : threatMap.entrySet()) {
            Long rid = entry.getKey();
            Hurt hurt = entry.getValue();
            // 检测是否已经过期
            if (/*TimeUtil.getTimeDuration(TimeUnit.MILLISECONDS, hurt.getAttackTime(), now)*/ 1 >= ownTime) {
                if (removeRids == null) {
                    removeRids = new HashSet<>();
                }
                removeRids.add(rid);
            }
        }

        if (removeRids != null) {
            for (Long rid : removeRids) {
                threatMap.remove(rid);
            }
        }
    }

}