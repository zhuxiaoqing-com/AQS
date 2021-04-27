package com.youxi.AI.fsm.monster;

import com.sh.game.system.map.fsm.monster.ai.MonsterAI;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.obj.Monster;
import com.sh.game.system.map.scene.GameMap;

/**
 * 怪物回家状态
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2017/11/4.
 */
public class MonsterBackState extends MonsterState {

    //回家超时时间，超时强制拉回家
    private final static int maxTime = 10000;

    //回家用时
    private int backTime;

    public MonsterBackState(PerformerActionState type, Monster performer) {
        super(type, performer);
    }


    @Override
    public void enter(GameMap map) {
        //回家用时
        backTime = 0;
        MonsterAI.AI[performer.getAiType()].backEnter(map, performer);
    }

    @Override
    public void exit(GameMap map) {
        MonsterAI.AI[performer.getAiType()].backExit(map, performer);
    }

    @Override
    public void update(GameMap map, int delta) {
        super.update(map, delta);
        MonsterAI.AI[performer.getAiType()].backUpdate(map, performer, delta);
        backTime += delta;
    }

    @Override
    public PerformerActionState checkTransition(GameMap map) {
        if (performer.isDead()) {
            return PerformerActionState.die;
        }
        if (performer.getVector3().equals(performer.getBornVector())) {
            return PerformerActionState.active;
        }
        if (backTime >= maxTime) {
            MonsterAI.AI[performer.getAiType()].forceHome(map, performer);
            return PerformerActionState.active;
        }
        return PerformerActionState.back;
    }

}
