package com.youxi.AI.fsm.pet;

import com.sh.game.constant.ConstAttribute;
import com.sh.game.system.fight.constant.FightConst;
import com.sh.game.system.map.constant.AIConst;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.obj.*;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.util.TimeUtil;

public class PetDieState extends PetState {


    public PetDieState(PerformerActionState type, Conjure performer) {
        super(type, performer);
    }

    @Override
    public void enter(GameMap map) {
        int dieDelay = performer.getDieDelay() < AIConst.delay ? AIConst.delay : performer.getDieDelay();
        performer.getActionMachine().getAiData().setDieTime(TimeUtil.getNowOfMills() + dieDelay);
        //发送改变 同步消息
        performer.sendAttrUpdateMsg(ConstAttribute.lifeType, FightConst.LifeType.LIFE_DEAD);
        //处理侍从死亡
        if (performer.getType() == MapObjectType.PET) {
            Player master = (Player) performer.getMaster();
            master.setLastDiePetId(performer.getConfigId());
        }
        //处理假玩家死亡
        if (performer.getType() == MapObjectType.MONSTER) {
            map.onMonsterDie(performer);
        }
    }

    @Override
    public void exit(GameMap map) {

        //处理召唤怪死亡
        if (performer.getType() == MapObjectType.CONJURE_UNIT) {
            Performer master = performer.getMaster();
            if (master instanceof Monster) {
                ConjureUnit conjure = (ConjureUnit) performer;
                int skillId = conjure.getSourceId();
                if (skillId > 0) {
                    int num = master.getCreationNum().getOrDefault(skillId, 0);
                    int creationNum = num > 1 ? num - 1 : 0;
                    master.getCreationNum().put(skillId, creationNum);
                }
            }
            map.remove(performer);
        } else if (performer.getType() == MapObjectType.MONSTER) {
            map.remove(performer);
        }
    }

    @Override
    public PerformerActionState checkTransition(GameMap map) {
        if (TimeUtil.getNowOfMills() >= performer.getActionMachine().getAiData().getDieTime()) {
            return PerformerActionState.sleep;
        }
        return PerformerActionState.die;
    }
}
