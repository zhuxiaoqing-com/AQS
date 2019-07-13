package com.example.demo1.util.excel;

/**
 * 角色资源获得途径
 *
 * @author ZhangHuan Created on 2017/11/16.
 */
public enum GainType {

    GM(1, "GM命令", false),

    QUEST(2, "任务奖励", true),

    RECHARGE(3, "充值", false),

    BUY_GOODS(4, "购买商品", false),

    VIP(5, "VIP特权礼包", true),

    MAIL(6, "邮件附件", false),

    PICKUP(7, "拾取", true),

    USE_ITEM(8, "使用道具", false),

    SELL_ITEM(9, "出售道具", true),

    SIGN(10, "签到", true),

    PRIVILEGE(11, "特权奖励", true),

    FINISHED_INSTANCE(12, "成功通关副本", true),

    SWEEP_INSTANCE(13, "魔神扫荡副本", true),

    ITEM_COMPOSE(14, "道具合成", false),

    EXORCISM(15, "除魔", true),

    GUILD_CONTRIBUTION_ITEM(16, "公会捐献道具", true),

    GUILD_PRAY(17, "公会祈福", true),

    GUILD_STORE_MOVE(18, "公会仓库兑换", false),

    TRADE_REFUND(19, "交易行退款", false),

    TRADE_SELL(20, "交易行寄售成交", true),

    FRIEND_GIVE(21, "好友赠送金币", true),

    ARENA_RANKING_REWARD(22, "竞技场奖励", true),

    GET_DAILY_RECHARGE_REWARDS(23, "领取每日充值奖励", true),

    GET_FIRST_RECHARGE_REWARDS(24, "领取首充奖励", true),

    OA_MYSTERIOUS_SHOP(25, "神秘商店", false),

    GET_CUMULATIVE_LOGIN_REWARDS(26, "领取七日登录奖励", true),

    OA_ONLINE_REWARD(27, "在线奖励", true),

    OA_KEY_CODE(28, "激活码", false),

    OA_BACK_RESOURCE(29, "资源找回", true),

    BACK_TO_ITEM_BAG(30, "脱装备", false),

    GEM_UPLOAD(31, "装备位宝石卸下", false),

    USE_ITEM_GIFT(32, "道具礼包", true),

    WANTED_INTEGRAL_REWARD(33, "通缉积分奖励领取", true),

    WANTED_FINISH_QUEST(34, "通缉完成任务奖励", true),

    ADVANCE_PASS_INSTANCE(35, "进阶线通关奖励", true),

    EXP_QUEST_REWARD(36, "经验任务奖励", true),

    RED_EQUIP_THROUGH_REWARDS(37, "红装通关奖励", true),

    DEVIL_BOSS_REWARDS(38, "魔神BOSS奖励", true),

    GUILD_CONTRIBUTION(39, "公会贡献", true),

    GUILD_RED_PACKET(40, "领取公会红包", true),

    WORLD_BOSS_RANK_REWARDS(41, "世界BOSS排名奖励", true),

    WORLD_BOSS_TREASURE(42, "世界BOSS探宝", true),

    EQUIP_BOSS_JOIN(43, "装备BOSS参与奖", true),

    WORLD_BOSS_LAST_KILL_REWARDS(44, "世界BOSS最后一击奖励", true),

    GUILD_WAR_OCUUPY_REWARD(45, "公会战占领城池奖励", true),

    ACTIVATE_REPEAT_DRESS(46, "激活重复装扮", true),

    ACTIVATE_VALHALLA(47, "激活英灵殿", true),

    VALHALLA_UNLOAD(48, "英灵殿摘除", false),

    VALHALLA_EXTRACT(49, "英灵殿抽取", true),

    INSTANCE_ACTIVITY(50, "副本活动奖励", true),

    INVEST(51, "投资计划返利", true),

    TEAM_SECRET_INSTANCE(52, "组队秘境奖励", true),

    LOGIN_WELFARE(53, "登录福利", true),

    CUMULATIVE_AIMS(54, "七日目标奖励", true),

    GOLD_WELFARE(55, "金币福利", true),

    DAILY_ONE_RECHARGE_REWARDS(56, "每日一元领取奖励", true),

    DAILY_TREASURE_REWARDS(57, "一元夺宝奖励", true),

    VIP_DAILY_REWARDS(58, "vip每日奖励", true),

    USE_APPOINT_GIFT(59, "领取礼包指定奖励", true),

    RESOLVE_EQUIP(60, "装备分解", true),

    XUN_BAO(61, "寻宝奖励", true),

    CAREER_GIFT(62, "职业礼包奖励", true),

    AUTO_BUY_GOODS(63, "自动购买商品", false),

    XUN_BAO_GLORY(64, "寻宝荣耀币奖励", true),

    KILL_MONSTER_ADD_EXP(65, "大世界杀怪获得经验", true),

    GUILD_WAR_ADD_EXP(66, "公会战获得经验", true),

    DUNGEON_ADD_EXP(67, "地牢获得经验", true),

    EXP_INSTANCE_ADD_EXP(68, "经验副本获得经验", true),

    EXP_QUEST_ADD_EXP(69, "经验任务获得经验", true),

    WANTED_ADD_EXP(70, "通缉任务获得经验", true),

    UNION_WELFARE_ADD_EXP(71, "公会福利获得经验", true),

    OFFLINE_REWARD(72, "离线挂机奖励", true),

    TLD_COST_COIN(73, "限时活动-限时消费奖励", false),

    TLD_TOTAL_RECHARGE(74, "限时活动-限时累充奖励", false),

    TLD_DAY_RECHARGE(75, "限时活动-限时连充奖励", false),

    TIME_LIMITED_EXCHANGE(76, "限时兑换", false),

    TIME_LIMITED_RANK(77, "限时排行", false),

    DAY_WELFARE(78, "夜间福利", false),

    WEEK_WELFARE(79, "周一福利", false),

    DIRECT_PURCHASE(80, "直购礼包", false),

    ACCUMULATE_RECHARGE_REWARD(81, "领取累充礼包奖励", false),

    DIAMOND_DIAL(82, "钻石转盘", false),

    BUY_MYSTERYSTORE_GOODS(83, "购买神秘商店物品", false),

    RECHARGE_GIVE_COIN(84, "充值赠送奖励", false),

    TRADE_BUY(85, "交易行购买", true),

    TRADE_CANCEL_SELL(86, "交易行取消寄售", true),

    BORDER_CRAFT_EXP(87, "边境争霸", true),
    ;

    /**
     * 来源Id
     */
    private int id;

    /**
     * 类型描述
     */
    private String desc;

    /**
     * 防沉迷  true 防沉迷状态收益减半
     */
    private boolean needFCM;

    GainType(int id, String desc, boolean needFCM) {
        this.id = id;
        this.desc = desc;
        this.needFCM = needFCM;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isNeedFCM() {
        return needFCM;
    }

    public void setNeedFCM(boolean needFCM) {
        this.needFCM = needFCM;
    }
}
