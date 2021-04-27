package com.youxi.AI.fsm.monster;

import com.sh.game.system.map.fsm.monster.ai.MonsterAI;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.obj.Monster;
import com.sh.game.system.map.scene.GameMap;

/**
 * 怪物活动状态 (巡逻状态)
 */
public class MonsterActiveState extends MonsterState {


    public MonsterActiveState(PerformerActionState type, Monster performer) {
        super(type, performer);
    }

    @Override
    public void enter(GameMap map) {
        MonsterAI.AI[performer.getAiType()].activeEnter(map, performer);
    }

    @Override
    public void exit(GameMap map) {
        MonsterAI.AI[performer.getAiType()].activeExit(map, performer);
    }

    @Override
    public void update(GameMap map, int delta) {
        super.update(map, delta);
        MonsterAI.AI[performer.getAiType()].activeUpdate(map, performer, delta);
    }

    @Override
    public PerformerActionState checkTransition(GameMap map) {
        if (performer.isDead()) {// 瞬秒（还没来得及进入战斗状态）
            return PerformerActionState.fight;
        }
        if (!performer.findThreatMap().isEmpty() && !performer.isIgnoreThreat()) {
            return PerformerActionState.fight;
        }
        return PerformerActionState.active;
    }

}
