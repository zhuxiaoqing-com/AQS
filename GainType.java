package Game.ServerCore.logic.module.bean.item;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/11/19 20:18
 * @Description: 奖励获得类型
 */
public interface GainType {

	int ARRANGEMENT = 1; //整理背包

	int SPLIT = 2; //拆分

	int GM = 3; //GM获取

	int QUEST = 30; //任务奖励

	int WARFARE = 31; //战役奖励

	int MONSTER_DROP = 32; //怪物掉落

	int BUY_SHOP = 33; //商城购买

	int EQUIP_EXCHANGE = 34; //装备交换

	int EMAIL_GET = 35; //邮件领取

	int ITEM_COMPOSE = 36; //道具合成

	int ACHIEVE_UNLOCK = 37; //成就解锁奖励

	int ACHIEVE_STAGE = 38; //成就阶段性奖励

	int MILITARY_LEVEL = 39; //军衔解锁奖励

	int COLLECT = 40; //采集奖励

	int GIFT_REWARD = 41; //礼包奖励

	int RANDOM_GIFT = 43; //随机礼包

	int WORKER_COLLECT = 44; // 工人采集获得

	int BAG_TAKE_ALL = 45; // 背包物品全部拿走

	int POLISH_REWARD = 46; // 打磨奖励

	int GENS_WEEKLY_REWARD = 47; // 公会每周奖励

	int GENS_DEPOT_RECEIVE = 48; // 公会仓库领取

	int SEVEN_DAYS_LOGIN_AWARD = 50; // 七日登陆奖励

	int NEW_HAND_TARGET_AWARD = 51; // 新手目标奖励

	int ACT_RANK_AWARD = 52; // 活动排行榜奖励

	int BATTLE_DROP_PICK = 53; // 战场掉落拾取

	int WAR_MAKES_SHOP = 54; // 战令商城购买

	int WAR_MAKES_LEVEL_REWARD = 55; // 战令等级奖励

	int PVP_KILL_ROLE = 56; // pvp击杀玩家获得奖励

	int WAR_WAKES_UPGRADE = 57; // 战令类型提升奖励

	int CARRIER_CREW_REMOVE = 58; // 载具乘员遣散获得奖励

	int BAG_MOVE_MULTI_ITEM = 59; // 背包移动多个道具

	int INLAY_PENDANT = 60; // 镶嵌挂件

	int UNLOAD_PENDANT = 61; // 卸下挂件

	int RETURN_BULLET = 62; // 返还子弹

	int MOVE_ITEM = 63; // 移动道具

	int WEAR_EQUIP = 64; // 穿装备

	int TAKE_OFF_EQUIP = 65; // 脱装备

	int TEMP_CARRIER_REMOVE = 66; // 临时载具删除返还道具

	int GVGS_SUMMARY = 67; // gvg结算奖励

	int NEW_HAND_SUMMARY = 68; // 新手关卡奖励

	int PVP_SUMMARY = 69; // pvp结算奖励

	int EAT_CHICKEN_SUMMARY = 70; // 吃鸡结算奖励

	int DEFEND_ZOMBIE = 71; // 防守僵尸

	int EXPLORE_PASS = 72; // 探索结算奖励

	int CARRIER_CREATE_INIT_ITEM = 72; // 创建载具初始道具

	int TALENT_RESET = 73; // 重置天赋点

	int GENTES_WILD_NPC_REWARD = 74; // 公会野外占领奖励


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
