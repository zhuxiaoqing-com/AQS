package com.youxi.AI.fsm.skilll;

import com.sh.engine.config.ConfigDataManager;
import com.sh.game.config.model.CfgSkillMovement;
import com.sh.game.system.fight.FightSkillData;
import com.sh.game.system.fight.constant.FightConst;
import com.sh.game.system.fight.constant.MoveConst;
import com.sh.game.system.fight.handler.SkillMoveUtil;
import com.sh.game.system.map.fsm.state.PerformerSkillState;
import com.sh.game.system.map.obj.Performer;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.system.map.vo.Vector3f;
import com.sh.game.system.move.MoveManager;

/**
 * 技能吟唱状态
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2018/1/8.
 */
public class SkillSpellState extends SkillState {

    private int spellTime = 0;

    private int skillId = 0;

    public SkillSpellState(PerformerSkillState type, Performer performer) {
        super(type, performer);
    }


    @Override
    public void enter(GameMap map) {
        FightSkillData skillData = findAiData().getFightSkill();
        if (skillData == null) {
            return;
        }
        skillData.setNewState(false);

        int skillSpellTime = skillData.getSpellTime();
        //吟唱时间为0 自己退出
        if (skillSpellTime <= 0) {
            return;
        } else {
            spellTime = skillSpellTime;
        }

        skillId = skillData.getSkillId();
    }

    @Override
    public void exit(GameMap map) {
        FightSkillData skillData = findAiData().getFightSkill();
        if (skillData == null) {
            return;
        }
        int moveId = skillData.getMoveId();
        int moveType = skillData.getMoveType();
        if (moveId > 0 && moveType == FightConst.MoveType.MOVE_START) {
            long targetId = skillData.getTargetId();
            CfgSkillMovement cfgMove = ConfigDataManager.getInstance().getById(CfgSkillMovement.class, moveId);
            if (cfgMove != null && cfgMove.getMoveUnit() == MoveConst.MoveUnit.self) {
                Performer target = (Performer) map.findObject(targetId);
                Vector3f v3 = SkillMoveUtil.calcMovePosition(map, performer, target, cfgMove);
                float dir = performer.getDir();
                MoveManager.getInstance().skillMove(map, performer, v3, dir);
            }
        }
    }

    @Override
    public void update(GameMap map, int delta) {
        spellTime -= delta;
        FightSkillData skillData = findAiData().getFightSkill();
        if (skillData == null) {
            return;
        }
        //如果技能被打断
        if (skillData.isInterrupted()) {
            //终止技能释放
            findAiData().setFightSkill(null);
        }
    }

    @Override
    public PerformerSkillState checkTransition(GameMap map) {
        if (performer.isDead()) {
            findAiData().setFightSkill(null);
            return PerformerSkillState.await;
        }
        FightSkillData skillData = findAiData().getFightSkill();
        if (skillData == null) {
            return PerformerSkillState.await;
        }
        if (skillData.isNewState()) {
            //非吟唱技能直接释放
            if (skillData.getSpellTime() <= 0) {
                return PerformerSkillState.cast;
            } else {
                //重新进入spell状态
                enter(map);
                return PerformerSkillState.spell;
            }
        }
        if (skillId != findAiData().getFightSkill().getSkillId()) {
            return PerformerSkillState.cast;
        }
        if (spellTime <= 0) {
            return PerformerSkillState.cast;
        }
        return PerformerSkillState.spell;
    }
}
