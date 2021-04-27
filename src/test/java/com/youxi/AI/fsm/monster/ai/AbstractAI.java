package com.youxi.AI.fsm.monster.ai;


import com.sh.game.constant.ConstAttribute;
import com.sh.game.core.event.EventCommandModular;
import com.sh.game.core.event.gameEvent.scene.MonsterDieEvent;
import com.sh.game.system.GameManager;
import com.sh.game.system.buff.BuffManager;
import com.sh.game.system.fight.constant.FightConst;
import com.sh.game.system.map.ThreatManager;
import com.sh.game.system.map.constant.AIConst;
import com.sh.game.system.map.constant.MonstConst;
import com.sh.game.system.map.fsm.AIData;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.monster.MonsterManager;
import com.sh.game.system.map.obj.Monster;
import com.sh.game.system.map.obj.Performer;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.system.map.scene.instance.extra.mapRank.MultiAssistRanker;
import com.sh.game.system.map.util.MonsterAIUtil;
import com.sh.game.system.map.util.MoveUtil;
import com.sh.game.system.move.MoveManager;
import com.sh.game.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * 抽象的怪物ai，主要是提供了一些简单的设想，比如说更新血量，检查时间等等
 *
 * @author zhangli
 * 2017年6月6日 下午9:56:45
 */
public abstract class AbstractAI implements MonsterAI {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractAI.class);

    @Override
    public boolean activeEnter(GameMap map, Monster monster) {
        //清空寻路路径点
        monster.getNextPathVectors().clear();
        //取消无敌
        monster.setInvincible(false);
        //修改状态为存活状态
        monster.sendAttrUpdateMsg(ConstAttribute.lifeType, FightConst.LifeType.LIFE_ALIVE);
        MultiAssistRanker mapAssistRanker = map.getMultiAssistRanker();
        if (mapAssistRanker != null) {
            mapAssistRanker.monsterBackHome(monster);
        }
        return true;
    }

    @Override
    public boolean activeUpdate(GameMap map, Monster monster, int dt) {
        AIData aiData = monster.getActionMachine().getAiData();
        // 活动状态更新计时
        aiData.updateActiveAITime(dt);
        MonsterAIUtil.recoverHp(monster, AIConst.RecoverInterval.HP);
        return aiData.getSleepTime() <= 0;
    }

    @Override
    public boolean activeExit(GameMap map, Monster monster) {
        monster.getNextPathVectors().clear();
        return true;
    }

    @Override
    public boolean battleEnter(GameMap map, Monster monster) {
        monster.setFightState(true);
        return true;
    }

    @Override
    public boolean battleUpdate(GameMap map, Monster monster, int dt) {
        if (monster.isDead()) {
            return false;
        }
        AIData aiData = monster.getActionMachine().getAiData();
        aiData.updateFightAITime(dt);

        if (aiData.getSleepTime() > 0) {
            return false;
        }
        // 寻找仇恨最高的目标对象
        Performer target = MonsterAIUtil.findHighestThreatPerformer(map, monster);
        return target != null;
    }

    @Override
    public boolean battleExit(GameMap map, Monster monster) {
        monster.setFightState(false);
        return true;
    }

    @Override
    public boolean dieEnter(GameMap map, Monster monster) {
        int dieDelay = monster.getDieDelay() < AIConst.delay ? AIConst.delay : monster.getDieDelay();
        monster.getActionMachine().getAiData().setDieTime(TimeUtil.getNowOfMills() + dieDelay);
        //死亡不可见
        monster.setVisible(false);
        //发送死亡状态
        monster.sendAttrUpdateMsg(ConstAttribute.lifeType, FightConst.LifeType.LIFE_DEAD);
        //清除buff
        BuffManager.getInstance().removeAllBuff(monster, true);

        //发送怪物死亡消息
        EventCommandModular.getInstance().publishEvent(new MonsterDieEvent(monster, map));

        //怪物死亡事件
        map.onMonsterDie(monster);
        return true;
    }

    @Override
    public boolean dieUpdate(GameMap map, Monster monster, int dt) {
        return true;
    }

    @Override
    public boolean dieExit(GameMap map, Monster monster) {
        //清除仇恨值
        clearThreat(map, monster);

        //如果是分裂出来的怪物则不能分裂
        /*if (monster.getFissionMId() != 0) {
            return true;
        }
        //检查是否能分裂
        CfgMonster cfgMonster = ConfigDataManager.getInstance().getById(CfgMonster.class, monster.getConfigId());
        if (cfgMonster != null && cfgMonster.getFission() != null && cfgMonster.getFission().length > 0) {
            for (int id : cfgMonster.getFission()) {
                Monster fissionMonster = GameManager.getInstance().get(MonsterManager.class).createMonster(map, id, monster.getVector3(), monster.getDir(), monster.getGroupId());
                if (fissionMonster != null) {
                    fissionMonster.setFissionMId(monster.getConfigId());
                }
            }
        }*/
        return true;
    }

    @Override
    public boolean sleepEnter(GameMap map, Monster monster) {
        //复活类型
        int reliveType = monster.getReliveType();
        // 不需要复活
        if (monster.getReliveType() == MonstConst.ReliveType.NO) {
            map.remove(monster);
            return true;
        }

        if (reliveType == MonstConst.ReliveType.FIX_TIME) {
            int reliveDelay = monster.getReliveDelay() < AIConst.delay ? AIConst.delay : monster.getReliveDelay();
            monster.getActionMachine().getAiData().setReliveTime(TimeUtil.getNowOfMills() + reliveDelay);
        }
        map.removeView(monster);
        return true;
    }

    @Override
    public boolean sleepUpdate(GameMap map, Monster monster, int dt) {
        return true;
    }

    @Override
    public boolean sleepExit(GameMap map, Monster monster) {
        GameManager.getInstance().get(MonsterManager.class).relive(map, monster);
        return true;
    }

    @Override
    public boolean bornEnter(GameMap map, Monster monster) {
        //出生状态下，无敌
        monster.setInvincible(true);
        //重置击杀者
        monster.setKillerId(0);
        monster.setDead(false);
        monster.setVisible(true);
        map.addView(monster, monster.getVector3().findV2());
        //发送出生状态
        monster.sendAttrUpdateMsg(ConstAttribute.lifeType, FightConst.LifeType.LIFE_BORN);
        int bornDelay = monster.getBornDelay() < AIConst.delay ? AIConst.delay : monster.getBornDelay();
        monster.getActionMachine().getAiData().setBornTime(TimeUtil.getNowOfMills() + bornDelay);
        return true;
    }

    @Override
    public boolean bornUpdate(GameMap map, Monster monster, int dt) {
        return true;
    }

    @Override
    public boolean bornExit(GameMap map, Monster monster) {
        return true;
    }

    @Override
    public boolean backEnter(GameMap map, Monster monster) {
        //回家路上，无敌
        monster.setInvincible(true);
        //清除仇恨
        clearThreat(map, monster);
        //清楚原来路径节点，重新寻路
        monster.getNextPathVectors().clear();
        return true;
    }

    @Override
    public boolean backUpdate(GameMap map, Monster monster, int dt) {
        MoveUtil.moveByPath(map, monster, monster.getBornVector());
        monster.getActionMachine().getAiData().setSleepTime(monster.getMoveInterval());
        return true;
    }

    @Override
    public boolean backExit(GameMap map, Monster monster) {
        monster.setInvincible(false);
        return true;
    }

    @Override
    public boolean forceHome(GameMap map, Monster monster) {
        MoveManager.getInstance().changePos(map, monster, monster.getBornVector(), monster.getDir());
        return true;
    }

    /**
     * 清除仇恨值
     *
     * @param map
     * @param monster
     */
    protected void clearThreat(GameMap map, Monster monster) {
        //清除敌对仇恨
        ThreatManager.getInstance().removeEnemyThreat(monster);

        //清除仇恨值
        if (monster.getGroupId() != 0) {
            //如果超过30秒没有更新仇恨值，清除仇恨
            if (TimeUtil.getNowOfMills() - monster.findThreatTime() < 30000) {
                //怪物组需要所有组都死亡才可以死亡。
                Collection<Monster> mapMonster = map.getMonsterMap().values();
                long liveGroupCount = mapMonster.stream().filter(m -> m.getGroupId() == monster.getGroupId() && m.getActionMachine().getCurrentState() == PerformerActionState.fight).count();
                if (liveGroupCount > 0) {
                    return;
                }
            }
        }
        ThreatManager.getInstance().clear(monster);

    }

    /**
     * 索敌
     *
     * @param map
     * @param monster
     * @return
     */
    public void searchEnemy(GameMap map, Monster monster) {
        Performer nearestPerformer = MonsterAIUtil.findNearestFightTargetPerformer(map, monster);
        if (nearestPerformer != null) {
            ThreatManager.getInstance().addEnemyThreat(monster, nearestPerformer.getId(), AIConst.ThreatValue.View);
        }
    }

}
