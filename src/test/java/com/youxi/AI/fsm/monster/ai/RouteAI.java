package com.youxi.AI.fsm.monster.ai;


import com.sh.engine.config.ConfigDataManager;
import com.sh.game.config.model.CfgAIRoute;
import com.sh.game.constant.ConstState;
import com.sh.game.system.buff.BuffManager;
import com.sh.game.system.guildAdventure.constant.ConstGuildAdventure;
import com.sh.game.system.map.obj.AdventureDart;
import com.sh.game.system.map.obj.Monster;
import com.sh.game.system.map.obj.Player;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.system.map.util.MoveUtil;
import com.sh.game.system.map.vo.Vector3f;
import com.sh.game.util.TimeUtil;

import java.util.List;

/**
 * 寻路主动怪AI
 *
 * @author zhangli
 * 2017年6月6日 下午9:56:45
 */
public class RouteAI extends ActiveAI {

    @Override
    public void patrol(GameMap map, Monster monster, boolean fight) {
        CfgAIRoute cfgAIRoute = ConfigDataManager.getInstance().getById(CfgAIRoute.class, monster.getConfigId());
        if (cfgAIRoute == null) {
            return;
        }

        //等待中
        if (checkWait(monster, cfgAIRoute)) {
            return;
        }
        List<Vector3f> routes = cfgAIRoute.getRoute();
        int routeIndex = monster.getRouteIndex();
        int routeCount = routes.size();
        if (routeIndex >= routeCount) {
            return;
        }
        Vector3f vector3f = routes.get(routeIndex);
        monster.setRouteVector(vector3f);
        //巡逻停留点
        if (monster.getVector3().equals(vector3f)) {
            //终点
            if (routeCount == routeIndex + 1) {
                //巡逻结束
                patrolEnd(monster, cfgAIRoute);
            } else {
                //巡逻等待
                patrolWait(monster, cfgAIRoute);
            }
        } else {
            //巡逻
            patrolMove(map, monster, vector3f);
        }
    }

    /**
     * 巡逻移动
     *
     * @param map      地图
     * @param monster  怪物
     * @param vector3f 坐标
     */
    private void patrolMove(GameMap map, Monster monster, Vector3f vector3f) {
        if (monster instanceof AdventureDart) {
            AdventureDart adventureDart = (AdventureDart) monster;
            Player player = map.getPlayerMap().get(adventureDart.getOwnerId());
            if (player == null || player.getVector3().distance(adventureDart.getVector3()) > adventureDart.getStopDistance()) {
                adventureDart.setState(ConstGuildAdventure.AdventureDartState.stop);
                return;
            }
            adventureDart.setState(ConstGuildAdventure.AdventureDartState.move);
        }

        MoveUtil.moveByPath(map, monster, vector3f);
    }

    /**
     * 巡逻结束
     * * @param monster    怪物
     *
     * @param cfgAIRoute 寻路AI配置
     */
    private void patrolEnd(Monster monster, CfgAIRoute cfgAIRoute) {
        if (cfgAIRoute.getEndIsRemove() != ConstState.TRUE) {
            return;
        }
        monster.death();

        if (monster instanceof AdventureDart) {
            ((AdventureDart) monster).setSuccess(true);
        }
    }

    /**
     * 巡逻等待
     *
     * @param monster    怪物
     * @param cfgAIRoute 寻路AI配置
     */
    private void patrolWait(Monster monster, CfgAIRoute cfgAIRoute) {
        int routeIndex = monster.getRouteIndex() + 1;
        monster.setRouteIndex(routeIndex);
        monster.setBornVector(monster.getRouteVector());
        //该点不属于停留点
        if (!cfgAIRoute.getWaitPoint().contains(routeIndex)) {
            return;
        }
        //最低血量是否停留
        if (monster.isHadLowestHp() && cfgAIRoute.getLowestHpIsWait() != ConstState.TRUE) {
            return;
        }
        monster.setRouteWaitTime(TimeUtil.getNowOfMills() + cfgAIRoute.getWaitTime());
        BuffManager.getInstance().addBuffs(monster, cfgAIRoute.getWaitAddBuff());

        if (!(monster instanceof AdventureDart)) {
            return;
        }
        AdventureDart adventureDart = (AdventureDart) monster;
        adventureDart.setState(ConstGuildAdventure.AdventureDartState.addHp);
    }

    /**
     * 检查等待状态
     *
     * @param monster    怪物
     * @param cfgAIRoute 寻路AI配置
     * @return 是否等待
     */
    private boolean checkWait(Monster monster, CfgAIRoute cfgAIRoute) {
        long now = TimeUtil.getNowOfMills();
        if (now < monster.getRouteWaitTime()) {
            return true;
        }
        //等待时间到了，开始移动，清空等待时间和buff
        if (monster.getRouteWaitTime() > 0) {
            monster.setRouteWaitTime(0);
            for (Integer buffId : cfgAIRoute.getWaitAddBuff()) {
                BuffManager.getInstance().removeBuff(monster, buffId);
            }
        }
        return false;
    }
}
