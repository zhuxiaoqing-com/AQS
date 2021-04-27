package com.youxi.AI.fsm.monster.ai;

import com.sh.engine.config.ConfigDataManager;
import com.sh.game.config.model.CfgSkillGeneral;
import com.sh.game.system.fight.FightManager;
import com.sh.game.system.fight.FightUtil;
import com.sh.game.system.map.fsm.AIData;
import com.sh.game.system.map.obj.Monster;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.system.map.util.MonsterAIUtil;
import com.sh.game.util.TimeUtil;

/**
 * 站立定身怪AI，创建进入战斗状态
 *
 * @author zhangli
 * 2017年6月6日 下午9:56:45
 */
public class StandAI extends AbstractAI {

    @Override
    public boolean activeEnter(GameMap map, Monster monster) {
        return super.activeEnter(map, monster);
    }

    @Override
    public boolean activeUpdate(GameMap map, Monster monster, int dt) {
        monster.findThreatMap().put(0L, 1L);//添加仇恨值，进入战斗状态
        return super.activeUpdate(map, monster, dt);
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

        int sleepTime = monster.getMoveInterval();
        //获取可以释放的技能
        int skillId = MonsterAIUtil.findSkill(monster);
        if (skillId != 0) {
            CfgSkillGeneral cfgSkillGeneral = ConfigDataManager.getInstance().getById(CfgSkillGeneral.class, skillId);
            if (cfgSkillGeneral != null) {
                long instId = FightUtil.skillInstId(skillId);
                // 释放技能
                boolean result = FightManager.getInstance().castSkill(map, monster, cfgSkillGeneral, instId, 0, TimeUtil.getNowOfMills(), monster.getVector3());
                if (result && skillId == monster.getTempSkill()) {
                    monster.setTempSkill(0);
                }
                //成功攻击，睡眠时间为攻击速度
                sleepTime = monster.getAttackInterval();
            }
        }
        monster.getActionMachine().getAiData().setSleepTime(sleepTime);
        return true;
    }
}
