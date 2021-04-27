package com.youxi.AI.entity;

import com.youxi.AI.fsm.FSMMachine;
import com.youxi.AI.fsm.monster.MonsterBornState;
import com.youxi.AI.fsm.monster.MonsterState;
import com.youxi.AI.fsm.state.PerformerActionState;
import com.youxi.sssh.entity.IMapObject;
import com.youxi.sssh.fsm.Vector3f;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 怪物
 *
 * @author zhangli
 * 2017年6月6日 下午9:54:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Monster extends Performer {


    /**
     * 怪物状态机
     */
	private FSMMachine<MonsterState> actionMachine = new FSMMachine<>(new ArrayList<>(), new MonsterBornState(PerformerActionState.born, this));


/*
	*/
/**
     * 技能状态机
     *//*

    private FSMMachine<SkillState> skillMachine = new FSMMachine<>(new ArrayList<>(), new SkillAwaitState(PerformerSkillState.await, this));
*/

    /**
     * AI类型 </br>
     * 1、怪物 怪物的AI类型   0、主动怪，1、被动怪  .... </br>
     * 2、宠物 0 保护 1战斗  2  跟随 3停止
     */
    private int aiType = 1;

    /**
     * 距离攻击目标,警戒范围
     */
    private float toAttackArea = 10;

    /**
     * 追击范围
     */
    private double followRadius;

    /**
     * 巡逻范围
     */
    private float patrolRadius;

    /**
     * 攻击间隔
     */
    private int attackInterval = 2000;

    /**
     * 巡逻路点
     */
    private List<Float> patrolPaths;

    /**
     * 周围警戒的反应速度
     */
    private int heart = 3;

    /**
     * 睡眠时间（默认30秒）
     */
    private int reliveDelay = 30000;

    /**
     * 死亡尸体停留时间
     */
    private int dieDelay;

    /**
     * 出生时间
     */
    private int bornDelay;

    /**
     * 复活类型
     * 1. 固定时间
     * 2. 每天定点复活
     */
    private int reliveType = 0;

    /**
     * 出生坐标
     */
    private Vector3f bornVector = new Vector3f();

    /**
     * 出生方向
     */
    private float bornDir;

    /**
     * 最近的可攻击对象
     */
    private long nearestObjectId;

    /**
     * 使用移动速度类型
     */
    private int speedType;

    /**
     * 寻路路径
     */
    private List<Vector3f> nextPathVectors = new ArrayList<>();

    /**
     * 巡逻点
     */
    private Vector3f patrolVector = new Vector3f();

    /**
     * 是否刚刚初始化
     */
    private boolean init;

    /**
     * 固定1点血
     */
    private boolean fixHurt = false;

    /**
     * 怪物类型
     */
    private int monsterType;

    /**
     * 技能列表
     */
    protected List<Integer> skills = new ArrayList<>();

    private int tempSkill;


    private int career;

    /**
     * 隶属于哪个服务器(用于边境BOSS)
     */
    private int serverId;

    //基于那个怪物分裂出来的
    private int fissionMId;

    /**
     * 延时出生
     */
    private long delayedBorn;

    /**
     * 存活时间
     */
    private long survivalTime;

    /**
     * 分组id
     */
    private int groupId;

    /**
     * 路径怪目标节点下标
     */
    private int routeIndex;

    /**
     * 路径怪转换节点时间
     */
    private long routeWaitTime;

    /**
     * 路径怪目标点
     */
    private Vector3f routeVector = new Vector3f();

    /**
     * 只对嘲讽效果增加仇恨
     */
    private boolean onlyTaunt;

    // 参与攻击玩家列表
    private Set<Long> hurtPlayers = new HashSet<>();

    // 触发事件
    //private List<TriggerEvent> triggerEvent = new ArrayList<>();

    // 玩家伤害列表
    private Map<Long, Hurt> threatMap = new HashMap<>();

    /**
     * 战斗巡逻
     */
    private boolean fightPatrol;

    /**
     * 时间控血怪每秒掉血值
     */
    private long secondReduceHp;

    /**
     * 上次受伤时间
     */
    private long lastHurtTime;

    /**
     * 时间控血怪(不论有没有受击，按时掉血)
     */
    private boolean continueReduceHp;

    /**
     * 无视仇恨值，不进入战斗状态
     */
    private boolean ignoreThreat;

    /**
     * 达到最低血量
     */
    private boolean hadLowestHp;

    /**
     * 最低血量
     */
    private long lowestHp;

    public Vector3f getBornVector() {
        return bornVector;
    }

    public void setBornVector(Vector3f bornVector) {
        this.bornVector.setVector(bornVector);
    }

    public Vector3f getRouteVector() {
        return routeVector;
    }

    public void setRouteVector(Vector3f routeVector) {
        this.routeVector.setVector(routeVector);
    }

    public Vector3f getPatrolVector() {
        return patrolVector;
    }

    public void setPatrolVector(Vector3f patrolVector) {
        this.patrolVector.setVector(patrolVector);
    }

    public FSMMachine getActionMachine() {
        return actionMachine;
    }
    /* -----------------继承方法-------------------- */

    @Override
    public int getType() {
        return MapObjectType.MONSTER;
    }

    @Override
    protected boolean isSamePkSide(GameMap map, IMapObject obj) {
        return false;
    }

    @Override
    protected boolean isTeam(GameMap map, IMapObject obj) {
        return false;
    }

    public Set<Long> getHurtPlayers() {
        return hurtPlayers;
    }

    public void setHurtPlayers(Set<Long> hurtPlayers) {
        this.hurtPlayers = hurtPlayers;
    }

    public Map<Long, Hurt> getThreatMap() {
        return threatMap;
    }

    public void setThreatMap(Map<Long, Hurt> threatMap) {
        this.threatMap = threatMap;
    }
    /* -----------------自定义方法-------------------- */


    /**
     * 初始化怪物触发事件
     *
     * @param triggerStr 需要解析的字符串
     */
    public void initTrigger(String triggerStr) {
        this.triggerEvent.clear();
        if (StringUtils.isBlank(triggerStr)) {
            return;
        }
        String[] strArray = triggerStr.split(SplitSymbolConst.YuHao);
        //遍历参数对
        for (String res : strArray) {
            if (StringUtils.isBlank(res)) {
                continue;
            }
            String param[] = res.split(SplitSymbolConst.ShuXian);//分割参数，分隔符“|”
            if (param.length < 3) {
                continue;
            }
            //次数
            int count = Integer.parseInt(param[0]);
            //类型
            int type = Integer.parseInt(param[1]);
            //值
            int value = Integer.parseInt(param[2]);
            TriggerEvent event = new TriggerEvent();
            event.setCount(count);
            event.setRecord(0);
            event.setType(type);
            event.setValue(value);
            //事件参数
            for (int i = 3; i < param.length; i++) {
                event.getParams().add(Integer.parseInt(param[i]));
            }
            this.triggerEvent.add(event);
        }
    }

    /**
     * 重置触发次数
     */
    public void resetTrigger() {
        for (TriggerEvent event : triggerEvent) {
            event.setRecord(0);
        }
    }

    public void update(GameMap map, int delta) {

    }

    public CfgMonster getConfig() {
        return ConfigDataManager.getInstance().getById(CfgMonster.class, this.getConfigId());
    }

    public void initSkill(CfgMonster cfgMonster) {
        for (int skillId : cfgMonster.getSkillList()) {
            skills.add(skillId);
        }
    }

    @Override
    public long findHatredId() {
        return groupId > 0 ? groupId : id;
    }

    public int getMoveInterval() {
        return 100;
    }
}
