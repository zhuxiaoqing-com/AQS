package com.youxi.AI.fsm.player;

import com.sh.game.system.GameManager;
import com.sh.game.system.fight.PassiveSkillManager;
import com.sh.game.system.fight.constant.FightConst;
import com.sh.game.system.map.ThreatManager;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.obj.Player;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.util.TimeUtil;

public class FightState extends PlayerState {

    /**
     * 被攻击的怪物脱离战斗或者死亡
     * 可以配置时间后，脱离战斗
     */
    private boolean readLeaveFight = false;
    /**
     * 被攻击的怪物脱离战斗或者死亡
     * <p>
     * 等待脱离战斗时间
     */
    private long readLeaveTime = 0;

    public FightState(PerformerActionState type, Player performer) {
        super(type, performer);
    }


    @Override
    public void enter(GameMap map) {
        performer.setFightState(true);

        //重置等待时间与标志位
        readLeaveTime = 0;
        readLeaveFight = false;

        performer.getActionMachine().getAiData().setFightTime(0);
        GameManager.getInstance().get(PassiveSkillManager.class).triggerPassive(map, performer, performer, FightConst.PassiveType.FIGHT, 0);
    }

    @Override
    public void exit(GameMap map) {
        performer.setFightState(false);

        performer.getActionMachine().getAiData().setFightTime(0);
        performer.getActionMachine().getAiData().setWhoMyTarget(0);
        ThreatManager.getInstance().clear(performer);
    }

    @Override
    public void update(GameMap map, int delta) {
        super.update(map, delta);

        if (this.performer.isDead()) {
            return;
        }

        //更新进入状态
        if (readLeaveFight) {
            readLeaveTime += delta;
        }

        performer.getActionMachine().getAiData().updateFightTime(delta);
    }

    @Override
    public PerformerActionState checkTransition(GameMap map) {
        if (performer.isDead()) {// 瞬秒（还没来得及进入战斗状态）
            return PerformerActionState.die;
        }

        //如果在安全区 立马脱战
        if (map.isCanPk() && performer.isSafeArea()) {
            return PerformerActionState.active;
        }
        if (performer.isStopFight()) {
            performer.setStopFight(false);
            return PerformerActionState.active;
        }

        //3s没有被攻击也没有攻击别人
        if (TimeUtil.getNowOfMills() - performer.findThreatTime() > 3000 && !performer.isSkillState()) {
            //可以开始倒计时了
            readLeaveFight = true;
        } else {
            readLeaveFight = false;
            readLeaveTime = 0;
        }

        if (readLeaveTime > 0) {
            return PerformerActionState.active;
        }
        return PerformerActionState.fight;
    }

}
