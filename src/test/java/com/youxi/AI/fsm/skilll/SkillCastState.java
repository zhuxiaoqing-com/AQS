package com.youxi.AI.fsm.skilll;

import com.sh.engine.config.ConfigDataManager;
import com.sh.game.config.model.CfgSkillMovement;
import com.sh.game.system.fight.FightManager;
import com.sh.game.system.fight.FightSkillData;
import com.sh.game.system.fight.SkillSegment;
import com.sh.game.system.fight.constant.FightConst;
import com.sh.game.system.fight.constant.MoveConst;
import com.sh.game.system.fight.handler.SkillMoveUtil;
import com.sh.game.system.map.fsm.state.PerformerSkillState;
import com.sh.game.system.map.obj.MapObject;
import com.sh.game.system.map.obj.Monster;
import com.sh.game.system.map.obj.Performer;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.system.map.util.MoveUtil;
import com.sh.game.system.map.vo.Vector3f;
import com.sh.game.system.move.MoveManager;
import com.sh.game.util.TimeUtil;

/**
 * 技能释放状态
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2018/1/8.
 */
public class SkillCastState extends SkillState {

    //技能下一段释放时间，主要用于判断释放间隔
    private long nextCastTime = 0;

    //技能计算总次数
    private int castTotal = 0;

    //当前释放段数
    private int currentSegment = 0;

    public SkillCastState(PerformerSkillState type, Performer performer) {
        super(type, performer);
    }

    @Override
    public void enter(GameMap map) {
        FightSkillData skillData = findAiData().getFightSkill();
        if (skillData == null) {
            return;
        }
        skillData.setNewState(false);
        //初始化技能段数
        currentSegment = 0;
        castTotal = skillData.getSegmentMap().size();
        //设置第一段释放时间
        SkillSegment segment = skillData.getSegmentMap().get(1);
        if (segment != null) {
            nextCastTime = TimeUtil.getNowOfMills() + segment.getCaseTime();
        }
    }

    @Override
    public void exit(GameMap map) {
        //退出技能释放
        FightSkillData skillData = findAiData().getFightSkill();
        if (skillData != null) {
            findAiData().setFightSkill(null);
        }
    }

    @Override
    public void update(GameMap map, int delta) {

        FightSkillData skillData = findAiData().getFightSkill();
        if (skillData == null) {
            return;
        }
        //如果技能被打断
        if (skillData.isInterrupted()) {
            //终止技能释放
            findAiData().setFightSkill(null);
            return;
        }
        //是否位移
        if (performer instanceof Monster) {
            int moveId = skillData.getMoveId();
            int moveType = skillData.getMoveType();
            if (moveType == FightConst.MoveType.MOVE_CHASE) {
                MapObject object = map.findObject(skillData.getTargetId());
                if (object instanceof Performer) {
                    Performer target = (Performer) object;
                    MoveUtil.chaseWalk(map, (Monster) performer, target);
                }
            } else if (moveType == FightConst.MoveType.MOVE_STRAIGHT) {
                long targetId = skillData.getTargetId();
                CfgSkillMovement cfgMove = ConfigDataManager.getInstance().getById(CfgSkillMovement.class, moveId);
                if (cfgMove != null && cfgMove.getMoveUnit() == MoveConst.MoveUnit.self) {
                    Performer target = (Performer) map.findObject(targetId);
                    Vector3f v3 = SkillMoveUtil.calcMovePosition(map, performer, target, cfgMove);
                    MoveManager.getInstance().move(map, performer, v3);
                }
            }
        }

        castTotal = skillData.getSegmentMap().size();
        //段数释放完毕
        if (currentSegment >= castTotal) {
            return;
        }
        //未到技能释放时间
        if (TimeUtil.getNowOfMills() < nextCastTime) {
            return;
        }
        currentSegment++; //计算当前释放第几段
        if (!performer.isDead()) {
            FightManager.getInstance().releaseSkill(map, performer, skillData, skillData.findEffects(currentSegment));
        }
        SkillSegment nextSegment = skillData.getSegmentMap().get(currentSegment + 1);
        if (nextSegment == null) {
            return;
        }
        //下一段释放时间
        nextCastTime = TimeUtil.getNowOfMills() + nextSegment.getCaseTime();
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
                //重新进入cast状态
                enter(map);
                return PerformerSkillState.cast;
            } else {
                return PerformerSkillState.spell;
            }
        }
        if (currentSegment >= castTotal && TimeUtil.getNowOfMills() >= findAiData().getFightSkill().getEndTime()) {
            return PerformerSkillState.await;
        }
        return PerformerSkillState.cast;
    }
}
