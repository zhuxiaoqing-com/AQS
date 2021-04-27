package com.youxi.AI.fsm.monster;

import com.example.demo1.demo.TimeUtil;
import com.youxi.AI.entity.GameMap;
import com.youxi.AI.entity.Monster;
import com.youxi.AI.fsm.monster.ai.MonsterAI;
import com.youxi.AI.fsm.state.PerformerActionState;

/**
 * 怪物出生状态
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2017/9/11.
 */
public class MonsterBornState extends MonsterState {


    public MonsterBornState(PerformerActionState type, Monster performer) {
        super(type, performer);
    }

    @Override
    public void enter(GameMap map) {
        MonsterAI.AI[performer.getAiType()].bornEnter(map, performer);
    }

    @Override
    public void exit(GameMap map) {
        MonsterAI.AI[performer.getAiType()].bornExit(map, performer);
    }

    @Override
    public void update(GameMap map, int delta) {
        super.update(map, delta);
        MonsterAI.AI[performer.getAiType()].bornUpdate(map, performer, delta);
    }

    @Override
    public PerformerActionState checkTransition(GameMap map) {
        if (performer.isInit()) { // 刚初始化的对象
            if (TimeUtil.getNowOfMills() >= performer.getDelayedBorn()) {
                performer.setInit(false);
                enter(map);
            }
            return PerformerActionState.born;
        }
        if (TimeUtil.getNowOfMills() >= performer.getActionMachine().getAiData().getBornTime()) {
            return PerformerActionState.active;
        }
        return PerformerActionState.born;
    }

}
