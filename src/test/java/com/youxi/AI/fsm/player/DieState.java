package com.youxi.AI.fsm.player;

import com.sh.engine.config.ConfigDataManager;
import com.sh.game.config.model.CfgScene;
import com.sh.game.constant.ConstAttribute;
import com.sh.game.constant.ConstResetTime;
import com.sh.game.core.event.EventCommandModular;
import com.sh.game.core.event.commandEvent.common.CommonUpdateTeamInfo;
import com.sh.game.core.event.commandEvent.role.RoleKilledEvent;
import com.sh.game.core.event.commandEvent.role.RoleMountRideStateEvent;
import com.sh.game.system.buff.BuffManager;
import com.sh.game.system.fight.constant.FightConst;
import com.sh.game.system.map.ThreatManager;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.msg.RspReviveInfo;
import com.sh.game.system.map.obj.MapObject;
import com.sh.game.system.map.obj.Player;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.system.map.vo.Vector4f;
import com.sh.game.system.role.PlayerManager;
import com.sh.game.system.role.constant.RoleConst;
import com.sh.game.util.MessageUtil;
import com.sh.game.util.TimeUtil;

public class DieState extends PlayerState {

    //复活倒计时
    private int reviveTime;

    public DieState(PerformerActionState type, Player performer) {
        super(type, performer);
    }

    @Override
    public void enter(GameMap map) {
        if (TimeUtil.isReset(ConstResetTime.RESURGENCE_RESET, performer.getLastRebornTime())) {
            //隔天重置死亡次数
            performer.setReborn(0);
        }
        EventCommandModular.getInstance().publishEvent(new RoleMountRideStateEvent(performer.getId(), false));
        performer.setDeadTime(System.currentTimeMillis());
        //状态改为死亡状态
        performer.sendAttrUpdateMsg(ConstAttribute.lifeType, FightConst.LifeType.LIFE_DEAD);
        map.onPlayerDie(performer);
        //复活倒计时
        reviveTime = map.playerReviveTime(performer);
        //发送玩家已复活次数
        MessageUtil.sendMsg(new RspReviveInfo(0, performer.getReborn(), reviveTime), performer.getId());
        reviveTime += 5000;
        BuffManager.getInstance().removeAllBuff(performer, true);
        //清除仇恨
        ThreatManager.getInstance().clear(performer);
        //设置红名xx
        long attackerId = performer.getKillerId();
        MapObject attacker = map.getObjectMap().get(attackerId);
        if (attacker instanceof Player && attackerId != performer.getId()) {
            if (performer.getEvilValue() == 0) { //白名被杀
                PlayerManager.getInstance().addEvilValue(map, (Player) attacker);
            }
        }
        EventCommandModular.getInstance().publishEvent(new CommonUpdateTeamInfo(performer.getId()));
        EventCommandModular.getInstance().publishEvent(new RoleKilledEvent(performer.getId(), attackerId, attacker == null ? "" : attacker.getName(), map.getCfgId(), new Vector4f(performer.getVector3(), performer.getDir())));
    }

    @Override
    public void exit(GameMap map) {
        //进入复活操作
        performer.getRevive().revive(performer);
    }

    @Override
    public void update(GameMap map, int delta) {
        super.update(map, delta);
        reviveTime -= delta;
        if (reviveTime <= 0) {
            CfgScene cfgScene = ConfigDataManager.getInstance().getById(CfgScene.class, map.getCfgId());
            int reviveId = cfgScene != null ? cfgScene.getReviveType() : 0;
            PlayerManager.getInstance().chooseRelive(performer, RoleConst.ReliveType.SCENE, reviveId);
        }
    }

    @Override
    public PerformerActionState checkTransition(GameMap map) {

        if (!this.performer.isDead()) {
            return PerformerActionState.born;
        }

        if (performer.getRevive() != null) {
            return PerformerActionState.born;
        }

        return PerformerActionState.die;

    }

}
