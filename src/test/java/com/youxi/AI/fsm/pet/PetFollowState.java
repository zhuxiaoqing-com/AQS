package com.youxi.AI.fsm.pet;

import com.sh.game.constant.ConstAttribute;
import com.sh.game.system.fight.constant.FightConst;
import com.sh.game.system.map.constant.AIConst;
import com.sh.game.system.map.fsm.AIData;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.obj.Conjure;
import com.sh.game.system.map.obj.Performer;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.system.map.util.MonsterAIUtil;

public class PetFollowState extends PetState {


    public PetFollowState(PerformerActionState type, Conjure performer) {
        super(type, performer);
    }

    @Override
    public void enter(GameMap map) {
        //发送改变 同步消息
        performer.sendAttrUpdateMsg(ConstAttribute.lifeType, FightConst.LifeType.LIFE_ALIVE);
    }

    @Override
    public void exit(GameMap map) {
    }

    @Override
    public void update(GameMap map, int delta) {
        super.update(map, delta);
        //对象死亡
        if (performer.isDead()) {
            return;
        }

        AIData aiData = performer.getActionMachine().getAiData();
        //更新AI对象时间
        aiData.updatePetAITime(delta);
        //回复血量和蓝量
        MonsterAIUtil.recoverHp(performer, AIConst.RecoverInterval.HP);

        //没到更新时间
        if (aiData.getSleepTime() > 0) {
            return;
        }
        //召唤者
        Performer master = performer.getMaster();
        //主人和宠物不在同一个地图
        if (master.getMapId() != performer.getMapId() && master.getLine() != performer.getLine()) {
            return;
        }

        follow(map, master);
    }

    @Override
    public PerformerActionState checkTransition(GameMap map) {
        if (performer.isDead()) {
            return PerformerActionState.die;
        }
        Performer master = performer.getMaster();
        double dis = performer.getVector3().distance(master.getVector3());
        if (master.getActionMachine().getCurrentState() == PerformerActionState.fight && dis <= performer.followMaxRange()) {
            return PerformerActionState.fight;
        }
        return PerformerActionState.active;
    }
}




