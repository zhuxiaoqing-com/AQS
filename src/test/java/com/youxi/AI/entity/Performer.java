package com.youxi.AI.entity;

import com.sh.engine.config.ConfigDataManager;
import com.sh.game.config.model.CfgCampRelation;
import com.sh.game.constant.ConstAttribute;
import com.sh.game.constant.ConstPkMode;
import com.sh.game.constant.ConstRelation;
import com.sh.game.core.ai.FSMMachine;
import com.sh.game.system.attr.Attribute;
import com.sh.game.system.buff.entity.AbstractBuff;
import com.sh.game.system.buff.entity.attr.ShieldHpBuff;
import com.sh.game.system.cd.entity.CD;
import com.sh.game.system.fight.constant.FightConst;
import com.sh.game.system.map.AttrUtils;
import com.sh.game.system.map.ThreatManager;
import com.sh.game.system.map.scene.IAttackHurt;
import com.sh.game.util.TimeUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangHuan Created on 2019/1/10.
 */
@Setter
@Getter
public abstract class Performer{

    /**
     * HP
     */
    protected long hp;

    /**
     * MP
     */
    protected long mp;

    /**
     * 等级
     */
    protected int level;

    /**
     * 对象体型大小
     */
    protected float bodySize;

    /**
     * 战斗力
     */
    protected long power;

    /**
     * 是否死亡
     */
    protected boolean dead;

    /**
     * 死亡时间
     */
    protected long deadTime;

    /**
     * 击杀者Id
     */
    protected long killerId;

    /**
     * 对象是否无敌
     */
    protected boolean invincible;

    /**
     * 是否正在释放技能
     */
    protected boolean skillState;

    /**
     * 是否在战斗状态
     */
    protected boolean fightState;

    /**
     * 上次释放技能Id
     */
    protected int lastCastSkillId;

    /**
     * 是否可移动（默认可移动）
     */
    protected boolean canMove = true;

    /**
     * 对象阵营
     */
    protected int camp;

    /**
     * 当前属性值
     */
    protected Map<Integer, Long> finalAttr = new HashMap<>(100);

    /**
     * 对象基础属性
     */
  //  protected Attribute attribute = new Attribute();

    /**
     * 对象身上的buff
     */
    // protected List<AbstractBuff> bufferLists = new ArrayList<>();

    /**
     * 对象免疫buff列表
     * key:buff实例Id
     * value:免疫时间
     */
    protected Map<Integer, Long> immuneBuffMap = new HashMap<>();

    /**
     * 技能CD
     */
    protected Map<Long, CD> cds = new HashMap<>();

    /**
     * 召唤怪数量
     * key：技能Id
     * value：召唤怪数量
     */
    protected Map<Integer, Integer> creationNum = new HashMap<>();

    /**
     * 对象被动技能
     */
    protected Map<Integer, Integer> passiveSkillMap = new HashMap<>();


    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
        finalAttr.putAll(attribute.getFinalAttrs());
    }

    public void setFightState(boolean fightState) {
        this.fightState = fightState;
        sendFightStateMsg();
    }

    //获取行为状态机
    public abstract FSMMachine getActionMachine();

    public abstract FSMMachine getSkillMachine();

    /**
     * 获得最大血量
     */
    public long getMhp() {
        return findAttr(ConstAttribute.mhp);
    }

    /**
     * 获得最大蓝量
     */
    public long getMmp() {
        return findAttr(ConstAttribute.mmp);
    }

    /**
     * 最大护盾
     */
    public long findMaxShield() {
        long maxShield = 0;
        for (AbstractBuff buff : bufferLists) {
            if (buff instanceof ShieldHpBuff) {
                ShieldHpBuff shieldHpBuff = (ShieldHpBuff) buff;
                int shieldHp = shieldHpBuff.getMaxShield();
                //有血量护盾
                if (shieldHp > maxShield) {
                    maxShield = shieldHp;
                }
            }
        }
        return maxShield;
    }

    /**
     * 当前护盾值
     *
     * @return
     */
    public long findCurShieldValue() {
        long curShield = 0;
        for (AbstractBuff buff : bufferLists) {
            if (buff instanceof ShieldHpBuff) {
                ShieldHpBuff shieldHpBuff = (ShieldHpBuff) buff;
                int shieldHp = shieldHpBuff.getAttrValue();
                //有血量护盾
                if (shieldHp > curShield) {
                    curShield = shieldHp;
                }
            }
        }
        return curShield;
    }

    /**
     * 获得对象是否战斗状态
     */
    public int findFightState() {
        return fightState ? 1 : 0;
    }

    /**
     * 获得移动速度
     */
    public long findMoveSpeed() {
        return fightState ? findAttr(ConstAttribute.battleSpeed) : findAttr(ConstAttribute.moveSpeed);
    }

    /**
     * 获得属性值
     *
     * @param type
     */
    public long findAttr(int type) {
        if (type == ConstAttribute.hp) {
            return hp;
        } else if (type == ConstAttribute.mp) {
            return mp;
        } else {
            long value = finalAttr.getOrDefault(type, 0L);
            if (value == 0 && type == ConstAttribute.pkMode) {
                value = ConstPkMode.PEACE;
            }
            return value;
        }
    }

    /**
     * 加血
     *
     * @param value
     */
    public long addHp(long sourceId, long value) {
        long result = value <= 0 ? subChangeHp(Math.abs(value)) : addChangeHp(value);
        if (result > 0) {
            AttrUtils.sendCommonDamageMsg(sourceId, this, value, hp);
        }
        return result;
    }

    /**
     * buff加血扣血方法
     *
     * @param sourceId
     * @param value
     */
    public void addBuffHp(long sourceId, long value) {
        boolean isTimeControlHp = false;
        if (this instanceof Monster) {
            Monster monster = (Monster) this;
            isTimeControlHp = monster.getSecondReduceHp() > 0;
        }
        long realValue;
        if (isTimeControlHp) {
            realValue = Math.abs(value);
        } else {
            realValue = value <= 0 ? subChangeHp(Math.abs(value)) : addChangeHp(value);
        }
        if (value < 0) {
            GameMap map = this.findMap();
            if (map instanceof IAttackHurt) {
                ((IAttackHurt) map).onAttackHurt(sourceId, this.getId(), realValue);
            }
        }
        AttrUtils.sendCommonDamageMsg(sourceId, this, value, hp);
    }

    /**
     * 减血
     *
     * @param value
     */
    public long subHp(long sourceId, long value) {
        //Player的buff飘字效果用addHp
        return addHp(sourceId, -value);
    }

    /**
     * 加蓝
     *
     * @param value
     */
    public boolean addMp(long value) {
        if (value <= 0) {
            return subMp(Math.abs(value));
        }
        //MP已满
        long maxMp = this.getMmp();
        if (mp >= maxMp) {
            return false;
        }
        long objMp = mp + value;
        if (objMp > maxMp) {
            objMp = maxMp;
        }
        mp = objMp;
        //发送属性变化
        sendAttrUpdateMsg(ConstAttribute.mp, mp);
        return true;
    }

    /**
     * 减蓝
     *
     * @param value
     */
    public boolean subMp(long value) {
        if (value <= 0) {
            return false;
        }
        //MP已经最少
        if (mp <= 0) {
            return false;
        }
        long objMp = mp - value;
        if (objMp < 0) {
            objMp = 0;
        }
        mp = objMp;
        //发送属性变化
        sendAttrUpdateMsg(ConstAttribute.mp, mp);
        return true;
    }

    /**
     * 增加属性
     *
     * @param type
     * @param value
     */
    public boolean addAttr(int type, long value) {
        if (value <= 0) {
            return subAttr(type, Math.abs(value));
        }
        if (type == ConstAttribute.hp) {
            addHp(id, value);
            return true;
        }
        if (type == ConstAttribute.mp) {
            return addMp(value);
        }
        return addFinalAttr(type, value);
    }

    /**
     * 减少属性
     *
     * @param type
     * @param value
     */
    public boolean subAttr(int type, long value) {
        if (value == 0) {
            return false;
        }
        if (value < 0) {
            return addAttr(type, Math.abs(value));
        }
        if (type == ConstAttribute.hp) {
            subHp(id, value);
            return true;
        }
        if (type == ConstAttribute.mp) {
            return subMp(value);
        }
        boolean result = subtractFinalAttr(type, value);
        if (result) {
            if (type == ConstAttribute.mhp) {
                long mhp = this.getMhp();
                if (hp > mhp) {
                    hp = mhp;
                    sendAttrUpdateMsg(ConstAttribute.hp, hp);
                }
            } else if (type == ConstAttribute.mmp) {
                long mmp = this.getMmp();
                if (mp > mmp) {
                    mp = mmp;
                    sendAttrUpdateMsg(ConstAttribute.mp, mp);
                }
            }
        }
        return result;
    }

    /**
     * 死亡
     */
    public void death() {
        fightDeath();
        sendAttrUpdateMsg(ConstAttribute.hp, hp);
        sendAttrUpdateMsg(ConstAttribute.lifeType, FightConst.LifeType.LIFE_DEAD);
    }

    /**
     * 战斗死亡
     */
    public void fightDeath() {
        hp = 0;
        dead = true;
        deadTime = TimeUtil.getNowOfMills();
    }

    /**
     * 连击数
     */
    public int findCombo() {
        return 0;
    }

    public long findAtkValue() {
        long atkMax = findAttr(ConstAttribute.atkMax);
        long satkMax = findAttr(ConstAttribute.satkMax);
        return atkMax > satkMax ? atkMax : satkMax;
    }

    public void addBuff(AbstractBuff buff) {
        bufferLists.add(buff);
    }

    public void removeBuff(int buffId, long ownId) {
        bufferLists.removeIf(buff -> buff.getBuffId() == buffId && ownId == buff.getOwnId());
    }

    public AbstractBuff findBuff(int buffId, long ownId) {
        for (AbstractBuff buff : bufferLists) {
            if (buffId == buff.getBuffId() && ownId == buff.getOwnId()) {
                return buff;
            }
        }
        return null;
    }

    public AbstractBuff findBuffByType(int buffType, long ownId) {
        for (AbstractBuff buff : bufferLists) {
            if (buffType == buff.getType() && ownId == buff.getOwnId()) {
                return buff;
            }
        }
        return null;
    }


    public List<AbstractBuff> findBuffsByType(int buffType, long ownId) {
        ArrayList<AbstractBuff> results = new ArrayList<>();
        for (AbstractBuff buff : bufferLists) {
            if (buffType == buff.getType() && ownId == buff.getOwnId()) {
                results.add(buff);
            }
        }
        return results;
    }

    public List<AbstractBuff> findBuffByBuffId(int buffId) {
        ArrayList<AbstractBuff> results = new ArrayList<>();
        for (AbstractBuff buff : bufferLists) {
            if (buffId == buff.getBuffId()) {
                results.add(buff);
            }
        }
        return results;
    }

    public boolean isHavaBuff(int buffId) {
        for (AbstractBuff buff : bufferLists) {
            if (buffId == buff.getBuffId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获得仇恨Id
     */
    public long findHatredId() {
        return id;
    }

    /**
     * 是否只受到嘲讽仇恨
     */
    public boolean isOnlyTaunt() {
        return false;
    }

    /**
     * 获得对象仇恨
     */
    public Map<Long, Long> findThreatMap() {
        long hatredId = findHatredId();
        return ThreatManager.getInstance().findThreatMap(hatredId);
    }

    /**
     * 获得仇恨更新时间
     */
    public long findThreatTime() {
        long hatredId = findHatredId();
        return ThreatManager.getInstance().findThreatTime(hatredId);
    }

    /**
     * 发送战斗状态消息
     */
    private void sendFightStateMsg() {
        Map<Integer, Long> attrMap = new HashMap<>();
        attrMap.put(ConstAttribute.inCombat, (long) this.findFightState());
        attrMap.put(ConstAttribute.moveSpeed, this.findMoveSpeed());
        sendAttrUpdateMsg(attrMap);
    }

    /**
     * 增加血量
     *
     * @param value
     * @return
     */
    private long addChangeHp(long value) {
        if (value <= 0) {
            return subChangeHp(Math.abs(value));
        }
        //血量已满
        long maxHp = this.getMhp();
        if (hp >= maxHp) {
            return 0;
        }
        long objHp = hp + value;
        if (objHp > maxHp) {
            value = maxHp - hp;
            objHp = maxHp;
        }
        hp = objHp;
        //发送属性变化
        sendAttrUpdateMsg(ConstAttribute.hp, hp);
        return value;
    }

    /**
     * 减少血量
     *
     * @param value
     */
    private long subChangeHp(long value) {
        if (value <= 0) {
            return 0;
        }
        //血量已经最少
        if (hp <= 0) {
            return 0;
        }
        long objHp = hp - value;
        if (objHp <= 0) {
            value = hp;
            objHp = 0;
            //剩余血量0，对象死亡
            this.death();
        }
        hp = objHp;
        //发送属性变化
        sendAttrUpdateMsg(ConstAttribute.hp, hp);
        return value;
    }

    private boolean addFinalAttr(int type, long value) {
        long attrValue = finalAttr.getOrDefault(type, 0L) + value;
        long maxValue = attribute.findFinalAttrByType(type);
        attrValue = attrValue < 0 ? 0 : attrValue;
        long finalValue = attrValue > maxValue ? maxValue : attrValue;
        finalAttr.put(type, finalValue);
        AttrUtils.sendAttrUpdateMsg(this, type, finalValue);
        return true;
    }

    private boolean subtractFinalAttr(int type, long value) {
        long attrValue = finalAttr.getOrDefault(type, 0L) - value;
        long maxValue = attribute.findFinalAttrByType(type);
        attrValue = attrValue > maxValue ? maxValue : attrValue;
        long finalValue = attrValue > 0 ? attrValue : type == ConstAttribute.mhp ? 1 : 0;
        finalAttr.put(type, finalValue);
        AttrUtils.sendAttrUpdateMsg(this, type, finalValue);
        return true;
    }

    public void sendAttrUpdateMsg(int type, long value) {
        if (type != ConstAttribute.hp && type != ConstAttribute.mp && type != ConstAttribute.inCombat && type != ConstAttribute.moveSpeed) {
            finalAttr.put(type, value);
        }
        AttrUtils.sendAttrUpdateMsg(this, type, value);
    }

    public void sendAttrUpdateMsg(Map<Integer, Long> attrs) {
        attrs.forEach((type, value) -> {
            if (type != ConstAttribute.hp && type != ConstAttribute.mp && type != ConstAttribute.inCombat && type != ConstAttribute.moveSpeed) {
                finalAttr.put(type, value);
            }
        });
        AttrUtils.sendAttrUpdateMsg(this, attrs);
    }

    public void changeAttribute(Attribute subtractAttr, Attribute addAttr) {
        AttrUtils.swapAttributeChange(this, subtractAttr, addAttr);
    }

    protected int findRelation(GameMap map, IMapObject obj) {
        int objCampId = obj.getCamp();
        if (camp == objCampId) {
            return ConstRelation.PARTNER;
        }
        //默认中立
        int relation = ConstRelation.NEUTRAL;
        CfgCampRelation cfg = ConfigDataManager.getInstance().getById(CfgCampRelation.class, camp);
        if (cfg != null) {
            if (cfg.getEnemy().contains(objCampId)) {
                //敌方
                relation = ConstRelation.ENEMY;
            } else if (cfg.getPartner().contains(objCampId)) {
                //友方
                relation = ConstRelation.PARTNER;
            }
        }
        return relation;
    }

    protected abstract boolean isSamePkSide(GameMap map, IMapObject obj);

    protected abstract boolean isTeam(GameMap map, IMapObject obj);

    @Override
    public boolean isEnemy(GameMap map, IMapObject obj) {
        if (obj == this) {
            return false;
        }
        if (isInSafeArea(obj) || isInSafeArea(this)) {
            return false;
        }
        if (isRedPlayer(obj)) {
            return true;
        }
        int pkMode = (int) findAttr(ConstAttribute.pkMode);
        if (pkMode == ConstPkMode.ALL) {
            //全体模式可以任意攻击
            return true;
        }
        int relation = findRelation(map, obj);
        if (relation == ConstRelation.NEUTRAL) {
            //中立关系不可攻击
            return false;
        }
        if (pkMode == ConstPkMode.PEACE) {
            //和平模式只可攻击敌对关系
            return relation == ConstRelation.ENEMY;
        }
        //其他PK阵营模式
        boolean samePkSide = isSamePkSide(map, obj);
        //同阵营的友好和敌对关系都是友军，不可攻击
        //不同阵营的友好和敌对关系都是敌军，可攻击
        return !samePkSide;
    }

    @Override
    public boolean isFriend(GameMap map, IMapObject obj) {
        return !isEnemy(map, obj);
    }

    @Override
    public boolean isTeamAndSelf(GameMap map, IMapObject obj) {
        if (this == obj){
            return true;
        }
        return isTeam(map, obj);
    }

    private boolean isRedPlayer(IMapObject obj) {
        if (obj instanceof Player) {
            return ((Player) obj).getEvilValue() > 0;
        }
        return false;
    }

    protected boolean isInSafeArea(IMapObject obj) {
        if (obj instanceof Player) {
            return ((Player) obj).isSafeArea();
        } else if (obj instanceof Conjure) {
            Performer master = ((Conjure) obj).getMaster();
            if (master instanceof Player) {
                return ((Player) master).isSafeArea();
            }
        }
        return false;
    }

    public void setCamp(int camp) {
        this.camp = camp;
        sendAttrUpdateMsg(ConstAttribute.camp, camp);
    }

    public int getCamp() {
        return camp;
    }

}
