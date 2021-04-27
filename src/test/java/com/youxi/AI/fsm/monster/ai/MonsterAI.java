package com.youxi.AI.fsm.monster.ai;

import com.youxi.AI.entity.GameMap;
import com.youxi.AI.entity.Monster;

/**
 * 怪物AI接口， 用于做不同的怪物AI，所有这些方法都在怪物状态机中调用
 *
 * @author zhangli
 * 2017年6月6日 下午9:55:45
 */
public interface MonsterAI {

    /**
     * 0：主动怪，1：被动怪，2：定身怪，3：寻路主动怪，4：寻路被动怪，5：巡逻怪, 6:假人
     */
    MonsterAI[] AI = {new ActiveAI(), new PassiveAI(), new StandAI(), new RouteAI(), new GuardAI(), new PatrolAI(), new DummyAI()};

    boolean activeEnter(GameMap map, Monster monster);

    boolean activeUpdate(GameMap map, Monster monster, int dt);

    boolean activeExit(GameMap map, Monster monster);


    boolean battleEnter(GameMap map, Monster monster);

    boolean battleUpdate(GameMap map, Monster monster, int dt);

    boolean battleExit(GameMap map, Monster monster);


    boolean dieEnter(GameMap map, Monster monster);

    boolean dieUpdate(GameMap map, Monster monster, int dt);

    boolean dieExit(GameMap map, Monster monster);


    boolean sleepEnter(GameMap map, Monster monster);

    boolean sleepUpdate(GameMap map, Monster monster, int dt);

    boolean sleepExit(GameMap map, Monster monster);


    boolean bornEnter(GameMap map, Monster monster);

    boolean bornUpdate(GameMap map, Monster monster, int dt);

    boolean bornExit(GameMap map, Monster monster);


    boolean backEnter(GameMap map, Monster monster);

    boolean backUpdate(GameMap map, Monster monster, int dt);

    boolean backExit(GameMap map, Monster monster);


    /**
     * 强制回家
     *
     * @param map
     * @param monster
     * @return
     */
    boolean forceHome(GameMap map, Monster monster);

    void searchEnemy(GameMap map, Monster monster);

}
