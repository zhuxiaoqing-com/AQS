package com.youxi.AI.fsm.monster.ai;

import com.sh.game.system.map.fsm.AIData;
import com.sh.game.system.map.obj.Monster;
import com.sh.game.system.map.scene.GameMap;

/**
 * 巡逻怪AI
 *
 * @author zhangli
 * 2017年6月6日 下午9:56:17
 */
public class PatrolAI extends PassiveAI {

    @Override
    public boolean activeUpdate(GameMap map, Monster monster, int dt) {
        boolean flag = super.activeUpdate(map, monster, dt);
        if (!flag) {
            return false;
        }

        //巡逻 or 回到出生地
        patrol(map, monster, false);
        //更新下一次动作
        AIData aiData = monster.getActionMachine().getAiData();
        aiData.setSleepTime(monster.getMoveInterval());

        return true;
    }
}
