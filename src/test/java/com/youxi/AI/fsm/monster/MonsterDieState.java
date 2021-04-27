package com.youxi.AI.fsm.monster;

import com.sh.game.system.map.fsm.monster.ai.MonsterAI;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.obj.Monster;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.util.TimeUtil;

public class MonsterDieState extends MonsterState {


    public MonsterDieState(PerformerActionState type, Monster performer) {
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
    public void update(GameMap map, int delta) {
        super.update(map, delta);
        MonsterAI.AI[performer.getAiType()].dieUpdate(map, performer, delta);
    }

    @Override
    public PerformerActionState checkTransition(GameMap map) {
        if (TimeUtil.getNowOfMills() >= performer.getActionMachine().getAiData().getDieTime()) {
            return PerformerActionState.sleep;
        }
        return PerformerActionState.die;
    }

}
