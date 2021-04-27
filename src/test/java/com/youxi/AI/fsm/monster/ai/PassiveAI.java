package com.youxi.AI.fsm.monster.ai;

import com.sh.engine.config.ConfigDataManager;
import com.sh.game.config.model.CfgMonster;
import com.sh.game.config.model.CfgSkillGeneral;
import com.sh.game.system.fight.FightManager;
import com.sh.game.system.fight.FightUtil;
import com.sh.game.system.fight.constant.FightConst;
import com.sh.game.system.map.fsm.AIData;
import com.sh.game.system.map.navigation.navmesh.RecastNavManager;
import com.sh.game.system.map.obj.Conjure;
import com.sh.game.system.map.obj.MapObjectType;
import com.sh.game.system.map.obj.Monster;
import com.sh.game.system.map.obj.Performer;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.system.map.util.MonsterAIUtil;
import com.sh.game.system.map.util.MoveUtil;
import com.sh.game.system.map.vo.Vector3f;
import com.sh.game.util.RandomUtil;
import com.sh.game.util.TimeUtil;
import com.youxi.AI.entity.GameMap;
import com.youxi.AI.entity.Monster;

/**
 * 被动怪AI
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2017/11/7.
 */
public class PassiveAI extends AbstractAI {

    private int sid = 0;

    private int chaseTime = 0;

    @Override
    public boolean battleUpdate(GameMap map, Monster monster, int dt) {
        boolean flag = super.battleUpdate(map, monster, dt);
        if (!flag) {
            return false;
        }
        long targetId = monster.getActionMachine().getAiData().getWhoMyTarget();
        Performer target = (Performer) map.findObject(targetId);
        if (target == null) {
            return false;
        }
        if (target instanceof Conjure) {
            if (target.getType() != MapObjectType.MONSTER) {
                targetId = ((Conjure) target).getMaster().getId();
            }
        }
        target = (Performer) map.findObject(targetId);
        if (target == null) {
            return false;
        }

        int sleepTime = 0;
        //获取可以释放的技能
        int skillId = MonsterAIUtil.findSkill(monster);
        if (skillId != 0) {
            CfgSkillGeneral cfgSkillGeneral = ConfigDataManager.getInstance().getById(CfgSkillGeneral.class, skillId);
            if (cfgSkillGeneral != null) {
                if (cfgSkillGeneral.getTgtType() == FightConst.TgtType.SELF) {
                    //自身
                    target = monster;
                }
                //距离太远
                double distance = monster.getVector3().distance(target.getVector3()) - target.getBodySize();
                distance = distance < 0 ? 0 : distance; //矫正距离
                double chaseDis = cfgSkillGeneral.getRangeMax();
                if (sid != skillId) {
                    sid = skillId;
                    double randomMin = chaseDis * 0.75f;
                    chaseDis = RandomUtil.random(randomMin, chaseDis);
                }
                if (distance > chaseDis) {
                    //太远了，追击
                    if (chaseTime > 0) {
                        chaseTime -= dt;
                        MoveUtil.chaseWalk(map, monster, target);
                    } else {
                        chaseTime = 10000;
                        CfgMonster cfgMonster = ConfigDataManager.getInstance().getById(CfgMonster.class, monster.getConfigId());
                        if (cfgMonster != null) {
                            int[] advanceTime = cfgMonster.getAdvanceTime();
                            if (advanceTime.length > 1) {
                                chaseTime = RandomUtil.random(advanceTime[0], advanceTime[1]);
                            }
                            int[] advanceTimeCD = cfgMonster.getAdvanceTimeCD();
                            if (advanceTimeCD.length > 1) {
                                sleepTime = RandomUtil.random(advanceTimeCD[0], advanceTimeCD[1]);
                            }
                        }
                    }
                } else if (cfgSkillGeneral.getRangeMin() - distance > 1) {
                    //太近了
                    double backDistance = MoveUtil.calcMoveDistance(monster);
                    if (cfgSkillGeneral.getRangeMin() - distance < backDistance) {
                        backDistance = cfgSkillGeneral.getRangeMin() - distance;
                    }
                    //后退
                    MonsterAIUtil.backWalk(map, monster, target, backDistance);
                } else {
                    long instId = FightUtil.skillInstId(skillId);
                    // 释放技能
                    boolean result = FightManager.getInstance().castSkill(map, monster, cfgSkillGeneral, instId, target.getId(), TimeUtil.getNowOfMills(), monster.getVector3());
                    if (result && skillId == monster.getTempSkill()) {
                        monster.setTempSkill(0);
                    }
                    //成功攻击，睡眠时间为攻击速度
                    sleepTime = monster.getAttackInterval();
                }
            } else {
                patrol(map, monster, true);
            }
        } else {
            if (monster.isFightPatrol()) {
                patrol(map, monster, true);
            }
        }
        if (sleepTime > 0) {
            monster.getActionMachine().getAiData().setSleepTime(sleepTime);
        }
        return true;
    }

    /**
     * 巡逻
     *
     * @param map
     * @param monster
     * @param fight   是否在战斗状态
     * @return
     */
    protected void patrol(GameMap map, Monster monster, boolean fight) {
        Vector3f patrolVector = monster.getPatrolVector();
        AIData aiData = monster.getActionMachine().getAiData();
        //重新改变寻路目标
        if (aiData.getRandomMoveTime() <= 0) {
            int randomTime = RandomUtil.random(3000, 8000);

            double randomDir = RandomUtil.random(0, 360); //获取随机方向
            float dis = monster.getPatrolRadius();
            Vector3f startV3 = monster.getBornVector();
            //巡逻范围
            if (fight) {
                randomDir = RandomUtil.random(0, 270) + monster.getDir() + 45; //获取随机方向
                dis = 2; //战斗巡逻范围
                startV3 = monster.getVector3();
                randomTime = RandomUtil.random(1000, 3000);
            }
            //下一次随机移动时间
            aiData.setRandomMoveTime(randomTime);

            Vector3f endV3 = MoveUtil.randomCircular(startV3, randomDir, dis);
            endV3 = RecastNavManager.getInstance().findRaycast(map, startV3, endV3);
            patrolVector.setVector(endV3);
        }
        MoveUtil.moveByPath(map, monster, patrolVector);
    }

}
