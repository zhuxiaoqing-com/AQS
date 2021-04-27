package com.youxi.AI.fsm.dummy;

import com.sh.game.system.map.fsm.monster.MonsterDieState;
import com.sh.game.system.map.fsm.monster.ai.MonsterAI;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.obj.Monster;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.util.TimeUtil;

public class DummyDieState extends MonsterDieState {


    public DummyDieState(PerformerActionState type, Monster performer) {
        super(type, performer);
    }

    @Override
    public void enter(GameMap map) {
        MonsterAI.AI[performer.getAiType()].dieEnter(map, performer);
    }

    @Override
    public void exit(GameMap map) {
        MonsterAI.AI[performer.getAiType()].dieExit(map, performer);
    }

    @Override
    public PerformerActionState checkTransition(GameMap map) {
        if (TimeUtil.getNowOfMills() >= performer.getActionMachine().getAiData().getDieTime()) {
            return PerformerActionState.born;
        }
        return PerformerActionState.die;
    }

}
