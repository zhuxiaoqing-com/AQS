package com.youxi.AI.fsm.monster;

import com.sh.game.system.map.fsm.monster.ai.MonsterAI;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.obj.Monster;
import com.sh.game.system.map.obj.Pitfall;
import com.sh.game.system.map.scene.GameMap;

public class MonsterFightState extends MonsterState {


    public MonsterFightState(PerformerActionState type, Monster performer) {
        super(type, performer);
    }

    @Override
    public void enter(GameMap map) {
        MonsterAI.AI[performer.getAiType()].battleEnter(map, performer);
    }

    @Override
    public void exit(GameMap map) {
        MonsterAI.AI[performer.getAiType()].battleExit(map, performer);
    }

    @Override
    public void update(GameMap map, int delta) {
        super.update(map, delta);
        if (performer instanceof Pitfall) {
            performer.setInvincible(true);
        }
        MonsterAI.AI[performer.getAiType()].battleUpdate(map, performer, delta);
    }

    @Override
    public PerformerActionState checkTransition(GameMap map) {
        if (performer.isDead()) {
            return PerformerActionState.die;
        }
        //追击是否超过追击范围，如果超过则回家
        double distance = performer.getVector3().distance(performer.getBornVector());
        if (distance > performer.getFollowRadius()) {
            return PerformerActionState.back;
        }
        if (performer.findThreatMap().isEmpty()) {
            MonsterAI.AI[performer.getAiType()].searchEnemy(map, performer);
            if (performer.findThreatMap().isEmpty()) {
                return PerformerActionState.back;
            }
        }
        return PerformerActionState.fight;
    }

}
