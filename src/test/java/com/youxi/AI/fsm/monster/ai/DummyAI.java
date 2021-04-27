package com.youxi.AI.fsm.monster.ai;


import com.sh.game.constant.ConstAttribute;
import com.sh.game.system.fight.constant.FightConst;
import com.sh.game.system.map.constant.AIConst;
import com.sh.game.system.map.obj.Monster;
import com.sh.game.system.map.scene.GameMap;
import com.sh.game.util.TimeUtil;

/**
 * 假玩家AI
 *
 * @author zhangli
 * 2017年6月6日 下午9:56:45
 */
public class DummyAI extends PassiveAI {

    @Override
    public boolean dieEnter(GameMap map, Monster monster) {
        int dieDelay = monster.getDieDelay() < AIConst.delay ? AIConst.delay : monster.getDieDelay();
        monster.getActionMachine().getAiData().setDieTime(TimeUtil.getNowOfMills() + dieDelay);
        //发送死亡状态
        monster.sendAttrUpdateMsg(ConstAttribute.lifeType, FightConst.LifeType.LIFE_DEAD);
        //怪物死亡事件
        map.onMonsterDie(monster);
        return true;
    }

    @Override
    public boolean activeUpdate(GameMap map, Monster monster, int dt) {
        boolean flag = super.activeUpdate(map, monster, dt);
        if (!flag) {
            return false;
        }
        searchEnemy(map, monster);
        return true;
    }

    @Override
    public boolean dieExit(GameMap map, Monster monster) {
        super.dieExit(map,monster);
        //重置击杀者
        monster.setKillerId(0);
        monster.setDead(false);
        monster.setHp(monster.getMhp());
        monster.setMp(monster.getMmp());
        //发送出生状态
        monster.sendAttrUpdateMsg(ConstAttribute.lifeType, FightConst.LifeType.LIFE_BORN);
        return true;
    }

}
