package com.youxi.AI.fsm.player;

import com.sh.game.constant.ConstAttribute;
import com.sh.game.system.map.ThreatManager;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.obj.Performer;
import com.sh.game.system.map.obj.Player;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.system.role.PlayerManager;

public class ActiveState extends PlayerState {

    private int recoveryHpMpInterval = 1000;

    public ActiveState(PerformerActionState type, Player performer) {
        super(type, performer);
    }


    @Override
    public void enter(GameMap map) {
        ThreatManager.getInstance().clear(performer);
    }

    @Override
    public void exit(GameMap map) {
    }

    @Override
    public void update(GameMap map, int delta) {
        super.update(map, delta);

        //自动恢复hp and mp
        recoveryHpMpInterval -= delta;
        if (recoveryHpMpInterval <= 0) {
            recoveryHpMpInterval = 1000;
            PlayerManager.getInstance().autoRecoveryHpAndMp(performer);
        }

    }

    @Override
    public PerformerActionState checkTransition(GameMap map) {
        if (this.performer.isDead()) {
            return PerformerActionState.die;
        }
        if (performer.findThreatTime() > 0) {
            //否则进入战斗状态
            if (checkRideState(performer)) {
                return PerformerActionState.active;
            }
            return PerformerActionState.fight;
        }
        //仇恨列表为空进入活跃状态
        return PerformerActionState.active;
    }

    /**
     * 检查角色骑乘状态
     *
     * @param performer
     * @return
     */
    private boolean checkRideState(Performer performer) {
        if (performer instanceof Player) {
            Player player = (Player) performer;
            return player.findAttr(ConstAttribute.isRide) == 1;
        }
        return false;
    }

}
