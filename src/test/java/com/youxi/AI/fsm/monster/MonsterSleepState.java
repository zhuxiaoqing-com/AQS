package com.youxi.AI.fsm.monster;


import com.sh.game.system.map.constant.MonstConst;
import com.sh.game.system.map.fsm.monster.ai.MonsterAI;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.obj.Monster;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.util.TimeUtil;

public class MonsterSleepState extends MonsterState {


    public MonsterSleepState(PerformerActionState type, Monster performer) {
        super(type, performer);
    }

    @Override
    public void enter(GameMap map) {
        MonsterAI.AI[performer.getAiType()].sleepEnter(map, performer);
    }

    @Override
    public void exit(GameMap map) {
        MonsterAI.AI[performer.getAiType()].sleepExit(map, performer);
    }

    @Override
    public void update(GameMap map, int delta) {
        super.update(map, delta);
        MonsterAI.AI[performer.getAiType()].sleepUpdate(map, performer, delta);
    }

    @Override
    public PerformerActionState checkTransition(GameMap map) {
        //复活类型
        int reliveType = performer.getReliveType();
        //固定间隔时间复活
        if (reliveType == MonstConst.ReliveType.FIX_TIME) {
            if (TimeUtil.getNowOfMills() >= performer.getActionMachine().getAiData().getReliveTime()) {
                return PerformerActionState.born;
            }
        }
        return PerformerActionState.sleep;
    }

}
