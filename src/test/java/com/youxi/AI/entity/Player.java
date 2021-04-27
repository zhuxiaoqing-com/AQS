package com.youxi.AI.entity;

import com.sh.game.constant.ConstAttribute;
import com.sh.game.constant.ConstPkMode;
import com.sh.game.core.ai.FSMMachine;
import com.sh.game.system.map.fsm.player.PlayerState;
import com.sh.game.system.map.fsm.player.ReviveState;
import com.sh.game.system.map.fsm.skilll.SkillAwaitState;
import com.sh.game.system.map.fsm.skilll.SkillState;
import com.sh.game.system.map.fsm.state.PerformerActionState;
import com.sh.game.system.map.fsm.state.PerformerSkillState;
import com.sh.game.system.role.AbsRevive;
import com.sh.game.system.role.constant.RoleConst;
import com.sh.game.system.team.TeamManager;
import com.sh.game.util.LinkedFIFOSet;
import com.sh.game.util.TimeUtil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 玩家
 *
 * @author zhangli
 * 2017年6月6日 下午9:55:04
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Player extends Performer {
    /**
     * 用户名
     */
    private long uid;

    /**
     * 服务器id
     */
    private int sid;

    /**
     * 服务器名称
     */
    private String serverName = "";

    /**
     * 是否已下线
     */
    private boolean offline;

    /**
     * 回城复活时间
     */
    private int reliveHomeTime;

    /**
     * 人物复活对象
     */
    private AbsRevive revive;

    /**
     * 出生时间
     */
    private int bornDelay;

    /**
     * 玩家状态机
     */
    private FSMMachine<PlayerState> actionMachine = new FSMMachine<>(new ArrayList<>(), new ReviveState(PerformerActionState.born, this));

    /**
     * 玩家技能状态
     */
    private FSMMachine<SkillState> skillMachine = new FSMMachine<>(new ArrayList<>(), new SkillAwaitState(PerformerSkillState.await, this));

    /**
     * 是否已进入地图（进入地图时判断重复进入）
     */
    private boolean enterMap;

    /**
     * 组队自动匹配的目标
     */
    private int autoMatchDestination;

    /**
     * 正在拾取的道具
     */
    private long pickUpId;

    /**
     * 是否挂机中
     */
    private boolean autoFight;

    /**
     * 安全区 ，标识玩家是否在安全区
     * 默认true,不能pk
     */
    private boolean safeArea = false;

    /**
     * 祷告区
     */
    private boolean prayArea;

    /**
     * 是否在祷告状态
     */
    private boolean prayState;

    /**
     * 泡温泉区
     */
    private boolean hotSpringArea;

    /**
     * 是否在泡温泉
     */
    private boolean bubbleHotSpring;

    /**
     * 释放技能是否无cd
     */
    private boolean skillNoCd;

    /**
     * 出战侍从
     */
    private Pet pet;

    /**
     * 当前出战侍从
     */
    private int battlePetId;

    /**
     * 上次死亡侍从Id
     */
    private int lastDiePetId;

    /**
     * 闪避值
     */
    protected int dodgeValue = 60;

    /**
     * 必杀值
     */
    protected int uniqueValue;

    /**
     * 连击数
     */
    private int combo;

    /**
     * 最后连击的时间
     */
    private long lastComboTime;

    /**
     * 神威值
     */
    private long martialValue;

    /**
     * 威慑值
     */
    private long deterValue;


    /**
     * 恢复护盾值时间
     */
    private long recoveryShieldTime;

    /**
     * 设定血量回复百分比
     */
    private int hpPercent;

    /**
     * 设定魔量回复百分比
     */
    private int mpPercent;

    /**
     * 自动使用药物
     */
    private boolean autoUseMedicine;

    /**
     * 公会Id
     */
    private long guildId;

    private String guildName = "";

    private int career;

    private long exp;


    //攻击我的人
    private LinkedFIFOSet<Long> attackMeEnemies = new LinkedFIFOSet<>(10);
    //治疗我的人
    private LinkedFIFOSet<Long> careMeFriends = new LinkedFIFOSet<>(10);

    /**
     * 无伤
     */
    private boolean withoutHurt;
    /**
     * 变身状态时长
     */
    private int shapeShiftTime;

    private int sex;

    private boolean login;

    private int newbieGoalId;

    /**
     * 今日在线时间
     */
    private long todayOnlineTime;

    /**
     * 今日在线更新时间
     */
    private long todayOnlineUpdateTime;


    /**
     * 远程服务器
     */
    //是否在远程服务器
    private boolean remote;

    /**
     * 传送脱战
     */
    private boolean stopFight;

    /**
     * 今日复活次数
     */
    private int reborn;

    /**
     * 上次原地复活
     */
    private long lastRebornTime;

    /**
     * 善恶值
     */
    private int evilValue;

    /**
     * 可传送时间
     */
    private long transportTime;

    /* -----------------继承方法-------------------- */

    @Override
    public int getType() {
        return MapObjectType.PLAYER;
    }

    @Override
    protected boolean isSamePkSide(GameMap map, IMapObject obj) {
        int pkMode = (int) findAttr(ConstAttribute.pkMode);
        if (obj.getType() == MapObjectType.PLAYER) {
            //PK地图
            if (map.isCanPk()) {
                Player target = (Player) obj;
                //如果自己或者目标在安全区
                if (!safeArea && !target.isSafeArea()) {
                    switch (pkMode) {
                        case ConstPkMode.TEAM:
                            List<Long> members = TeamManager.getInstance().findTeamMemberIds(getId());
                            return members.contains(target.getId());
                        case ConstPkMode.GUILD:
                            return guildId == target.getGuildId() && guildId != 0;
                        /*case ConstPkMode.FORCES:
                            return this.getCamp() != target.getCamp();*/
                        case ConstPkMode.ALLY:
                            //组队+公会
                            List<Long> teamMemberIds = TeamManager.getInstance().findTeamMemberIds(getId());
                            boolean isTeam = teamMemberIds.contains(target.getId());
                            boolean isSameGuild = guildId == target.getGuildId() && guildId != 0;
                            return isTeam || isSameGuild;
                        /*case ConstPkMode.EVIL:
                            return target.findAttr(ConstAttribute.evilState) > 0;*/
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected boolean isTeam(GameMap map, IMapObject obj) {
        if (obj.getType() != MapObjectType.PLAYER) {
            return false;
        }
        Player target = (Player) obj;
        List<Long> members = TeamManager.getInstance().findTeamMemberIds(getId());
        return members.contains(target.getId());
    }

    @Override
    public int findCombo() {
        return combo;
    }

    public void addAttackMeId(Performer performer) {
        if (performer.getId() == this.getId()) {
            return;
        }
        if (performer instanceof Player) {
            this.attackMeEnemies.add(performer.getId());
        }
    }

    public void addCureMeId(Performer performer) {
        if (performer.getId() == this.getId()) {
            return;
        }
        if (performer instanceof Player) {
            this.careMeFriends.add(performer.getId());
        }
    }

    @Override
    public long addHp(long sourceId, long hp) {
        if (withoutHurt && hp <= 0) {
            return 0;
        }
        return super.addHp(sourceId, hp);
    }

    public void updateShowAttr(Map<Integer, Long> attrs) {
        attrs.forEach((type, value) -> finalAttr.put(type, value));
    }

    /**
     * 获取今日在线时长
     */
    public long findTodayOnlineTime() {
        long now = TimeUtil.getNowOfMills();
        if (TimeUtil.isSameDay(now, todayOnlineUpdateTime)) {
            todayOnlineTime += now - todayOnlineUpdateTime;
        } else {//新的一天，取到零点的时间
            todayOnlineTime = now - TimeUtil.getZeroClockTime(now);
        }
        todayOnlineUpdateTime = now;
        return todayOnlineTime;
    }

    /**
     * 设置善恶红名
     *
     * @param map
     * @param evilState
     */
    public void changeEvilState(GameMap map, long evilState) {
        //int pkMode = (int) findAttr(ConstAttribute.pkMode);
        //if (!map.isRationalPkMode(pkMode)) {
        int evil = (int) findAttr(ConstAttribute.evilState);
        if (evil < evilState) {
            sendAttrUpdateMsg(ConstAttribute.evilState, evilState);
        }
        //}
    }

    public void clearEvilState() {
        sendAttrUpdateMsg(ConstAttribute.evilState, 0);
    }

    public int findEvilState() {
        return evilValue > 0 ? RoleConst.EvilState.danger : 0;
    }

}
