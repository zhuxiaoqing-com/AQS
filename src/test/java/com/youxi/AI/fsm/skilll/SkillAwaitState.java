package com.youxi.AI.fsm.skilll;

import com.sh.game.system.fight.FightSkillData;
import com.sh.game.system.map.fsm.state.PerformerSkillState;
import com.sh.game.system.map.obj.Performer;
import com.sh.game.system.map.scene.GameMap;
import com.youxi.AI.entity.Performer;
import com.youxi.AI.fsm.state.PerformerSkillState;

/**
 * 技能待命状态
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2018/1/9.
 */
public class SkillAwaitState extends SkillState {

    public SkillAwaitState(PerformerSkillState type, Performer performer) {
        super(type, performer);
    }

    @Override
    public void enter(GameMap map) {
        performer.setCanMove(true);
        performer.setSkillState(false);
        //performer.setLastThreatTime(TimeUtil.getNow_long());
    }

    @Override
    public void exit(GameMap map) {
        FightSkillData skillData = findAiData().getFightSkill();
        boolean canMove = skillData == null || skillData.isCanMove();
        performer.setCanMove(canMove);
        performer.setSkillState(true);
    }

    @Override
    public void update(GameMap map, int delta) {

    }

    @Override
    public PerformerSkillState checkTransition(GameMap map) {

        //技能释放不为0,转为吟唱阶段
        FightSkillData skillData = findAiData().getFightSkill();
        if (skillData != null) {
            skillData.setNewState(false);
            //非吟唱技能直接释放
            if (skillData.getSpellTime() <= 0) {
                return PerformerSkillState.cast;
            } else {
                return PerformerSkillState.spell;
            }
        }

        return PerformerSkillState.await;
    }

}
