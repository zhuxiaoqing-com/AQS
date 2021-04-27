package com.youxi.AI.fsm.player;

import com.sh.game.constant.ConstAttribute;
import com.sh.game.system.fight.constant.FightConst;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.obj.Player;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.util.TimeUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 人物复活状态机
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2017/9/11.
 */
public class ReviveState extends PlayerState {

    public ReviveState(PerformerActionState type, Player performer) {
        super(type, performer);
    }

    @Override
    public void enter(GameMap map) {

        //出生状态下，无敌
        performer.setInvincible(true);

        //状态改为出生状态
        performer.sendAttrUpdateMsg(ConstAttribute.lifeType, FightConst.LifeType.LIFE_BORN);

        //进入复活复活后操作，
        performer.getRevive().afterRevive(map, performer);
        //清楚复活操作
        performer.setRevive(null);

        int bornDelay = performer.getBornDelay() < 1000 ? 1000 : performer.getBornDelay();
        performer.getActionMachine().getAiData().setBornTime(TimeUtil.getNow_long() + bornDelay);
    }


    @Override
    public void exit(GameMap map) {
        //修改状态为存活状态
        Map<Integer, Long> attrs = new HashMap<>();
        attrs.put(ConstAttribute.lifeType, (long) FightConst.LifeType.LIFE_ALIVE);
        attrs.put(ConstAttribute.mp, performer.getMp());
        attrs.put(ConstAttribute.hp, performer.getHp());
        performer.sendAttrUpdateMsg(attrs);
        //取消无敌
        performer.setInvincible(false);

        // 主角复活，加入侍从对象
        // AttendantManager.getInstance().enterBattlePet(map, performer);
    }

    @Override
    public PerformerActionState checkTransition(GameMap map) {
        if (TimeUtil.getNowOfMills() >= performer.getActionMachine().getAiData().getBornTime()) {
            return PerformerActionState.active;
        }
        return PerformerActionState.born;
    }
}
