package com.youxi.AI.fsm.monster.ai;

import com.sh.game.system.map.obj.Monster;
import com.sh.game.system.map.scene.GameMap;

/**
 * 主动怪AI
 *
 * @author zhangli
 * 2017年6月6日 下午9:56:17
 */
public class ActiveAI extends PatrolAI {

    @Override
    public boolean activeUpdate(GameMap map, Monster monster, int dt) {
        boolean flag = super.activeUpdate(map, monster, dt);
        if (!flag) {
            return false;
        }
        searchEnemy(map, monster);
        return true;
    }
}
