package com.youxi.AI.fsm.player;

import com.sh.game.core.ai.AbstractFSMState;
import com.sh.game.system.GameManager;
import com.sh.game.system.buff.BuffManager;
import com.sh.game.system.fight.PassiveSkillManager;
import com.sh.game.system.fight.constant.FightConst;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.obj.Player;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.system.role.PlayerManager;

/**
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2018/1/22.
 */
public abstract class PlayerState extends AbstractFSMState<PerformerActionState, Player> {

    private int deltaTime = 0;

    private long evilTime = 0;

    public PlayerState(PerformerActionState type, Player performer) {
        super(type, performer);
    }


    @Override
    public void update(GameMap map, int delta) {
        //每秒计时
        deltaTime += delta;
        evilTime += delta;
        if (deltaTime >= 1000) {
            if (evilTime >= 3600000) { //一小时
                evilTime = 0;
                PlayerManager.getInstance().costEvilValue(performer, 1);
            }
            deltaTime = 0;
            /*if (!performer.isDead()) {
                PlayerManager.getInstance().autoUseDrug(performer);
            }*/
            /*int dodgeValue = performer.getDodgeValue();
            //恢复闪避值
            if (dodgeValue < 120) {
                performer.setDodgeValue(dodgeValue + 1);
                performer.sendAttrUpdateMsg(ConstAttribute.dodgeValue, performer.getDodgeValue());
            }*/
            // 血量变更触发被动技能
            GameManager.getInstance().get(PassiveSkillManager.class).triggerPassive(map, performer, performer, FightConst.PassiveType.HP, 0);
        }
        //更新角色buff
        BuffManager.getInstance().updateBuffer(map, performer, delta);

    }
}
