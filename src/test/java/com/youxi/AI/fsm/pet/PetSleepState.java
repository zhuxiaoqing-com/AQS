package com.youxi.AI.fsm.pet;

import com.sh.game.system.map.constant.AIConst;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.obj.Conjure;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.util.TimeUtil;

/**
 * 宠物出生,CD时间
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2017/9/11.
 */
public class PetSleepState extends PetState {


    public PetSleepState(PerformerActionState type, Conjure performer) {
        super(type, performer);
    }

    @Override
    public void enter(GameMap map) {
        int reliveDelay = performer.getReliveDelay() < AIConst.delay ? AIConst.delay : performer.getReliveDelay();
        performer.getActionMachine().getAiData().setReliveTime(TimeUtil.getNowOfMills() + reliveDelay);
    }

    @Override
    public void exit(GameMap map) {
    }

    @Override
    public void update(GameMap map, int delta) {
        super.update(map, delta);
    }

    @Override
    public PerformerActionState checkTransition(GameMap map) {
        if (TimeUtil.getNowOfMills() >= performer.getActionMachine().getAiData().getReliveTime()) {
            return PerformerActionState.born;
        }
        return PerformerActionState.sleep;
    }
}
