package com.example.demo1.util.excel;

/**
 * 角色资源消费类型
 *
 * @author ZhangHuan Created on 2017/11/16.
 */
public enum CostType {

    BAG_USE(2001, "背包使用"),

    AUTO_DRUG(2002, "自动吃药"),

    BUY_GOODS(2003, "购买商品"),

    AUTO_BUY(2004, "自动购买"),

    GIVE_GOODS(2005, "赠送商品"),

    UNLOCK_BAG(2006, "解锁背包"),

    UP_GEM(2007, "升级宝石"),

    TALENT_RESET(2008, "天赋重置"),

    TALENT_UNLEARN(2009, "天赋洗点"),

    EQUIP_INDEX_INTENSIFY(2010, "强化装备位"),

    ITEM_APPRAISE(2011, "道具鉴定"),

    EQUIP_IMPROVE(2012, "装备提品"),

    EQUIP_POLISH(2013, "装备洗练"),

    ARTIFACT_UP(2014, "升级神器"),

    MOUNT_UP(2015, "升级坐骑"),

    WING_ACTIVE(2016, "激活神翼"),

    WING_UP(2017, "升级神翼"),

    SIGN_IN(2019, "补签"),

    GM(2020, "GM命令"),

    CREATE_GUILD(2021, "创建公会"),

    GUILD_RENAME(2022, "公会改名"),

    GUILD_PRAY(2023, "公会祈福"),

    GUILD_MERGE(2024, "申请公会合并"),

    GUILD_SKILL_UPGRADE(2025, "升级公会技能"),

    ROLE_RENAME(2026, "角色改名"),

    FLY_SHOES_TELEPORT(2027, "小飞鞋传送"),

    ROLE_REVIVE(2028, "原地复活"),

    ITEM_COMPOSE(2029, "道具合成"),

    GEM_ATTR_INLAY(2030, "属性宝石镶嵌"),

    GEM_ATTR_PUNCH(2031, "打属性宝石孔"),

    EXORCISM_FLUSH(2032, "除魔星级刷新"),

    BUY_EXORCISM_TIMES(2033, "购买除魔次数"),

    QUICK_COMPLETED_EXORCISM(2034, "快速完成除魔任务"),

    ENTER_INSTANCE(2035, "进入副本"),

    BUY_INSTANCE_COUNT(2036, "购买副本次数"),

    DISCARD_ITEM(2037, "丢弃道具"),

    SELL_ITEM(2038, "出售道具"),

    GUILD_EXCHANGE_ITEM(2039, "公会兑换道具"),

    BUBBLE_HOTSPRING_MASSAGE(2040, "泡温泉按摩"),

    BUBBLE_HOTSPRING_PUSHOIL(2041, "泡温泉推油"),

    TRADE_SELL(2042, "交易行交易", false),

    TRADE_BUY(2043, "交易行购买", false),

    ENTER_BOSS_HOME(2044, "进入BOSS之家"),

    MOVE_TO_STORE(2045, "移动到角色仓库"),

    MOVE_TO_GUILD(2046, "贡献到工会仓库"),

    RECYCLE_ITEM(2047, "回收道具"),

    SKILL_INLAY(2048, "技能镶嵌"),

    CONSIGN_ITEM(2049, "寄售道具"),

    DAILY_BLESS(2050, "日常祈福"),

    GOD_POSITION_UPGRADE(2051, "升级神职"),

    GOD_PRESTIGE_UPGRADE(2052, "升级名望"),

    GOD_PRESTIGE_EXCHANGE(2053, "兑换名望"),

    GOD_EQUIP_FORGE(2054, "打造名望装备"),

    BUY_AWAKE_ITEM(2055, "购买觉醒精华道具"),

    GOD_PRESTIGE_RECYCLE(2056, "每天回收名望"),

    GOD_SEAL_DROP(2057, "神印掉落"),

    UPGRADE_ATTENDANT(2058, "升级侍从"),

    SUMMON_ATTENDANT_FIGHT(2059, "召唤侍从出战"),

    TOTEMS_LEVEL_UP(2060, "公会图腾升级"),

    OA_RUSH_BUY(2061, "限时抢购"),

    OA_MYSTERIOUS_SHOP(2062, "神秘商店购买"),

    OA_MYSTERIOUS_SHOP_REFRESH(2063, "神秘商店刷新"),

    OA_LIMIT_EXCHANGE(2064, "限时兑换"),

    OA_EXPERIENCE_REFINE(2065, "经验炼制消耗"),

    OA_CONCE_BUY_GIFT(2066, "运营活动-购买特惠礼包"),

    OA_TURNTABLE(2067, "幸运转盘", false),

    ARENA_DARE_BUY_COUNT(2068, "竞技场-购买挑战次数"),

    ACTIVE_ATTENDANT(2069, "激活武神"),

    UPGRADE_YUANSHEN(2070, "升级元神"),

    ONE_KEY_UPGRADE_YUAN_SOUL(2071, "一键升级元魂"),

    OA_BACK_RESOURCE(2072, "资源找回"),

    CASTING_UPRATE(2073, "铸造提升升级成功率消耗"),

    CASTING_UPLEVEL(2074, "铸造提升等级消耗"),

    CASTING_TRANSFER(2075, "铸造强化转移消耗"),

    OA_BACK_COUNT(2076, "次数找回"),

    CASTING_RESOLVE(2077, "铸造分解消耗"),

    CASTING_RESTORE(2078, "铸造强化还原消耗"),

    CASTING_ARTIFICE(2079, "铸造炼化消耗"),

    CASTING_REFINE(2080, "铸造精炼消耗"),

    CASTING_REFINE_TRANSFER(2081, "铸造精炼转移消耗"),

    CASTING_REFINE_RESTORE(2082, "铸造精炼还原消耗"),

    RED_PACKET(2083, "全服红包", false),

    MOVE_TO_COLLECTION(2084, "移动到藏品阁"),

    RESOLVE_COLLECTION(2085, "藏品分解"),

    PLAYER_DIE_DROP(2086, "玩家死亡掉落"),

    TALENT_ACTIVE(2087, "天赋激活"),

    SKILL_LEVEL_UP(2088, "技能升级"),

    ENCHANTMENT_ACTIVE(2089, "结界激活消耗"),

    ENCHANTMENT_UPGRADE(2090, "结界阵法升级消耗"),

    GEM_INLAY(2091, "宝石镶嵌"),

    GOD_WEAPON_UP(2092, "神兵阶数提升"),

    GOD_WEAPON_SKILL_UP(2093, "神兵技能升级"),

    GOD_CROWN_UP(2094, "神冠阶数提升"),

    GOD_CROWN_SKILL_UP(2095, "神冠技能升级"),

    EQUIP_SLOT_INTENSIFY(2096, "装备位强化"),

    EQUIP_SLOT_STAR(2097, "装备位升星"),

    GOD_APPLIANCE_UP(2098, "神器阶数提升"),

    GOD_APPLIANCE_SKILL_UP(2099, "神器技能升级"),

    MOUNTS_UPGRADE(2100, "坐骑升级消耗"),

    MOUNTS_PUTON_EQUIP(2101, "坐骑穿装备"),

    MOUNTS_SKILL_CROWN_UP(2102, "坐骑技能升级消耗"),

    MOUNTS_ACTIVE_SKIN(2103, "坐骑激活皮肤"),

    EQUIP_FORGE(2104, "装备打造"),

    WINGS_UPGRADE(2105, "神翼升级消耗"),

    WINGS_ACTIVE_SKIN(2106, "神翼激活皮肤"),

    GOD_ARM_ACTIVATE(2107, "神臂激活"),

    GOD_ARM_UPGRADE_RANK(2108, "神臂升级"),

    GOD_ARM_UPGRADE_SKILL(2109, "神臂升级技能"),

    WARFLAG_UPGRADE(2110, "战旗升级消耗"),

    WARFLAG_PUTON_EQUIP(2111, "战旗穿装备"),

    WARFLAG_SKILL_CROWN_UP(2112, "战旗技能升级消耗"),

    GOD_APPLIANCE_EQUIP(2113, "神器装备"),

    ONE_KEY_INTENSIFY(2114, "一键装备位强化"),

    ONE_KEY_STAR(2115, "一键装备位升星"),

    GENIUS_UP(2116, "天赋升级"),

    HERO_EVOLVE(2117, "武神进化"),

    HERO_QUALITY(2118, "武神提品"),

    HERO_GROW(2119, "武神成长"),

    WANTED_IMPROVE(2120, "通缉怪物提品"),

    WANTED_BUY_COUNT(2121, "购买通缉任务次数"),

    ENTER_REDEQUIP(2122, "红装副本消耗"),

    ENTER_DEVIL(2123, "魔神副本消耗"),

    GUILD_AID_UPGRADE(2124, "公会加持升级"),

    GUILD_AID_POLISH(2125, "公会加持洗练"),

    ANTIQUE_SUBMIT(2126, "藏品提交"),

    GUILD_CONTRIBUTION(2127, "公会贡献"),

    EXP_INSTANCE_DARE_COUNT(2128, "经验任务挑战次数购买"),

    DEVIL_INSTANCE_BUY_TIME(2129, "神魔副本购买副本时间"),

    DEVIL_INSTANCE_BUY_BUFF(2129, "神魔副本购买BUFF"),

    GUILD_RED_PACKET(2130, "公会红包", false),

    EQUIP_BOSS_ITEM_COST(2131, "装备BOSS副本次数不足道具消耗"),

    EQUIP_ELITE_INS_BUY_COUNT(2132, "装备BOSS精英副本 特权购买次数"),

    WORLD_BOSS_TREASURE(2133, "世界BOSS探宝 "),

    QUIT_GUILD_CLEAR(2134, "退会清空"),

    DRESS_ACTIVATE(2135, "激活装扮消耗"),

    DRESS_ACTIVATE_REPEAT(2136, "激活重复时装消耗"),

    REFRESH_BIAOCHE(2136, "刷新镖车"),

    HIGHEST_BIAOCHE(2137, "一键至尊镖车"),

    VALHALLA_UPGRADE(2138, "英灵殿升级消耗"),

    VALHALLA_INLAY(2139, "英灵殿镶嵌消耗"),

    VALHALLA_EXTRACT(2140, "英灵殿抽取消耗"),

    WAR_GOD_ADVANCE(2141, "战神套装进阶"),

    GOLD_WELFARE(2142, "金币福利领取"),

    DAILY_JOIN_TREASURE(2143, "每日一元参与夺宝消耗"),

    INSTANCE_DOUBLE(2144, "副本奖励翻倍"),

    RESOLVE_EQUIP(2145, "装备分解"),

    XUN_BAO(2146, "装备分解"),

    EXP_INSTANCE_TICKET(2147, "经验副本门票"),

    EQUIP_DEVIL_ACTIVE(2148, "装备魔神激活"),

    EQUIP_DEVIL_INTENSIFY(2149, "装备魔神强化"),

    EQUIP_SLOT_FUSION(2150, "装备位融合消耗"),

    EQUIP_SLOT_MAD(2151, "装备位融合狂化"),

    TIME_LIMITED_EXCHANGE(2152, "限时兑换"),

    DEVILPET_ACTIVE(2153, "宠物召唤"),

    DEVILPET_STAR(2154, "宠物升星"),

    DIAMOND_DIAL(2155, "钻石转盘"),

    BUY_MYSTERYSTORE_GOODS(2156, "购买神秘商店物品"),

    REFRESH_MYSTERYSTORE_GOODS(2157, "刷新神秘商店物品"),;

    /**
     * 消费类型Id
     */
    private int id;

    /**
     * 类型描述
     */
    private String desc;

    /**
     * 是否正常消费
     */
    private boolean consume;

    CostType(int id, String desc) {
        this.id = id;
        this.desc = desc;
        this.consume = true;
    }

    CostType(int id, String desc, boolean consume) {
        this.id = id;
        this.desc = desc;
        this.consume = consume;
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

    public boolean isConsume() {
        return consume;
    }

    public void setConsume(boolean consume) {
        this.consume = consume;
    }
}
