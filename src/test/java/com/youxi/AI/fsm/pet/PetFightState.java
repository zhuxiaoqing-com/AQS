package com.youxi.AI.fsm.pet;

import com.sh.engine.config.ConfigDataManager;
import com.sh.game.config.model.CfgSkillGeneral;
import com.sh.game.system.fight.FightManager;
import com.sh.game.system.fight.FightUtil;
import com.sh.game.system.fight.constant.FightConst;
import com.sh.game.system.map.constant.AIConst;
import com.sh.game.system.map.fsm.AIData;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.obj.Conjure;
import com.sh.game.system.map.obj.ConjureUnit;
import com.sh.game.system.map.obj.Performer;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.system.map.util.MonsterAIUtil;
import com.sh.game.system.map.util.MoveUtil;
import com.sh.game.system.map.util.PetAiUtil;
import com.sh.game.util.TimeUtil;

public class PetFightState extends PetState {


    public PetFightState(PerformerActionState type, Conjure performer) {
        super(type, performer);
    }

    @Override
    public void enter(GameMap map) {
        performer.setFightState(true);
    }

    @Override
    public void exit(GameMap map) {
        performer.setFightState(false);
    }

    @Override
    public void update(GameMap map, int delta) {
        super.update(map, delta);
        if (performer.isDead()) {
            return;
        }

        AIData aiData = performer.getActionMachine().getAiData();
        aiData.updatePetAITime(delta);

        if (aiData.getSleepTime() > 0) {
            return;
        }
        //设置更新间隔时长
        aiData.setSleepTime(performer.getMoveInterval());
        //召唤者
        Performer master = performer.getMaster();
        //攻击目标
        long targetId = aiData.getWhoMyTarget();
        //如果为守护模式，优先攻击主角攻击的目标
        if (performer.getAiType() == AIConst.PetAI.GUARD) {
            targetId = master.getActionMachine().getAiData().getWhoMyTarget();
        }
        if (targetId == performer.getId()) {
            targetId = 0;
        }

        //检查当前攻击的对象
        Performer target = (Performer) map.findObject(targetId);
        if (target == null || target.isDead() || target.getVector3().distance(performer.getVector3()) > performer.getToAttackArea()) {
            //重新寻找目标
            aiData.setWhoMyTarget(0);
            target = PetAiUtil.findMonster(map, master, performer);
        }
        //没有新目标
        if (target == null) {
            follow(map, master);
            return;
        }
        //设置当前攻击目标
        aiData.setWhoMyTarget(target.getId());

        //找到可使用技能
        int skillId = MonsterAIUtil.findSkill(performer);
        CfgSkillGeneral cfgSkillGeneral = ConfigDataManager.getInstance().getById(CfgSkillGeneral.class, skillId);
        if (cfgSkillGeneral != null) {
            if (cfgSkillGeneral.getTgtType() == FightConst.TgtType.SELF) {
                //自身
                target = performer;
            }
            //检查距离
            double distance = performer.getVector3().distance(target.getVector3()) - target.getBodySize();
            distance = distance < 0 ? 0 : distance; //矫正距离
            if (distance > cfgSkillGeneral.getRangeMax()) {
                //追击
                MoveUtil.chaseWalk(map, performer, target);
            } else if (cfgSkillGeneral.getRangeMin() - distance > 1) {
                //太近了
                double backDistance = MoveUtil.calcMoveDistance(performer);
                if (cfgSkillGeneral.getRangeMin() - distance < backDistance) {
                    backDistance = cfgSkillGeneral.getRangeMin() - distance;
                }
                //后退
                MonsterAIUtil.backWalk(map, performer, target, backDistance);
            } else {
                //释放技能
                long instId = FightUtil.skillInstId(skillId);
                FightManager.getInstance().castSkill(map, performer, cfgSkillGeneral, instId, target.getId(), TimeUtil.getNowOfMills(), performer.getVector3());
                aiData.setSleepTime(performer.getAttackInterval());
            }
        }
    }

    @Override
    public PerformerActionState checkTransition(GameMap map) {
        if (performer.isDead()) {
            return PerformerActionState.die;
        }
        if (performer instanceof ConjureUnit) {
            ConjureUnit conjure = (ConjureUnit) performer;
            if (conjure.isBolsterNpc()) {
                return PerformerActionState.fight;
            }
        }
        Performer master = performer.getMaster();
        double dis = performer.getVector3().distance(master.getVector3());
        if (master.getActionMachine().getCurrentState() != PerformerActionState.fight || dis > performer.followMaxRange()) {
            return PerformerActionState.active;
        }
        return PerformerActionState.fight;
    }
}
