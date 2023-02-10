package com.example.demo4;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/11/19 20:18
 * @Description: 奖励获得类型
 */
public interface GainType {

	int ARRANGEMENT = 1; //整理背包

	int SPLIT = 2; //拆分

	int GM = 3; //GM获取

	int QUEST = 4; //任务奖励

	int WARFARE = 5; //战役奖励

	int MONSTER_DROP = 6; //怪物掉落

	int BUY_SHOP = 7; //商城购买

	int EQUIP_EXCHANGE = 8; //装备交换

	int EMAIL_GET = 9; //邮件领取

	int ITEM_COMPOSE = 10; //道具合成

	int ACHIEVE_UNLOCK = 11; //成就解锁奖励

	int ACHIEVE_STAGE = 12; //成就阶段性奖励

	int MILITARY_LEVEL = 13; //军衔解锁奖励

	int COLLECT = 14; //采集奖励

	int GIFT_REWARD = 15; //礼包奖励

	int RANDOM_GIFT = 16; //随机礼包

	int WORKER_COLLECT = 17; // 工人采集获得

	int BAG_TAKE_ALL = 18; // 背包物品全部拿走

	int POLISH_REWARD = 19; // 打磨奖励

	int GENS_WEEKLY_REWARD = 20; // 公会每周奖励

	int GENS_DEPOT_RECEIVE = 21; // 公会仓库领取

	int SEVEN_DAYS_LOGIN_AWARD = 22; // 七日登陆奖励

	int NEW_HAND_TARGET_AWARD = 23; // 新手目标奖励

	int ACT_RANK_AWARD = 24; // 活动排行榜奖励

	int BATTLE_DROP_PICK = 25; // 战场掉落拾取

	int WAR_MAKES_SHOP = 26; // 战令商城购买

	int WAR_MAKES_LEVEL_REWARD = 27; // 战令等级奖励

	int KILL_ROLE = 28; // 击杀玩家获得奖励

	int WAR_WAKES_UPGRADE = 29; // 战令类型提升奖励

	int CARRIER_CREW_REMOVE = 30; // 载具乘员遣散获得奖励

	int BAG_MOVE_MULTI_ITEM = 31; // 背包移动多个道具

	int INLAY_PENDANT = 32; // 镶嵌挂件

	int UNLOAD_PENDANT = 33; // 卸下挂件

	int RETURN_BULLET = 34; // 返还子弹

	int MOVE_ITEM = 35; // 移动道具

	int WEAR_EQUIP = 36; // 穿装备

	int TAKE_OFF_EQUIP = 37; // 脱装备

	int TEMP_CARRIER_REMOVE = 38; // 临时载具删除返还道具

	int GVGS_SUMMARY = 39; // gvg结算奖励

	int NEW_HAND_SUMMARY = 40; // 新手关卡奖励

	int PVP_SUMMARY = 41; // pvp结算奖励

	int EAT_CHICKEN_SUMMARY = 42; // 吃鸡结算奖励

	int DEFEND_ZOMBIE = 43; // 防守僵尸

	int EXPLORE_PASS = 44; // 探索结算奖励

	int CARRIER_CREATE_INIT_ITEM = 45; // 创建载具初始道具

	int TALENT_RESET = 46; // 重置天赋点

	int GENTES_WILD_NPC_REWARD = 47; // 公会野外占领奖励

	int CALLBACK_REFUND = 48; // 召回载具失败退还

	int SWITCH_WEAPON = 49; // 切换武器返还子弹

	int UP_REWARD = 50;    //UP活动抽奖奖励

	int GENS_IMPEACH_COST_REFUND = 51; // 公会弹劾消耗返还

	int WAR_MAKES_BAR_REWARD = 52; // 战令进度条奖励

	int WILD_BOSS_REWARD = 53;	//世界boss活动奖励

	int OPEN_SELECT_GIFT = 54; //打开可选择礼包

	int DISASSEMBLE_CARRIER = 55;	//拆解载具返还

	int MILITARY_DAILY = 56; //军衔每日奖励

	/**
	 * 该 gainType 是不是不会导致背包道具数量变化
	 *
	 */
	static boolean isNoItemNumChange(int gainType) {
		switch (gainType){
			case ARRANGEMENT:
			case SPLIT:
				return true;
		}
		return false;
	}
}