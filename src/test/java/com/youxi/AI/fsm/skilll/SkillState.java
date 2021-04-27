package com.youxi.AI.fsm.skilll;

import com.sh.game.core.ai.AbstractFSMState;
import com.sh.game.system.map.fsm.AIData;
import com.sh.game.system.map.fsm.state.PerformerSkillState;
import com.sh.game.system.map.obj.Performer;

/**
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2018/1/9.
 */
public abstract class SkillState extends AbstractFSMState<PerformerSkillState,Performer> {

    public SkillState(PerformerSkillState type, Performer performer) {
        super(type, performer);
    }

    //技能的AIData
    AIData findAiData() {
        return performer.getSkillMachine().getAiData();
    }


}
