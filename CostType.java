package Game.ServerCore.logic.module.bean.item;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/12/08 20:30
 * @Description:
 */
public interface CostType {

	int SPLIT = 5000; // 道具切分
	int NPC_SERVICE_OPEN_DOOR = 5001; // npc服务开门消耗道具
	int CARRIER_DESTROY_REPAIR = 5002; // 载具损毁修理
	int WARFARE_COUNT_MONEY = 5003; // 战役购买次数
	int FIGHT_COST = 5004; // 战斗消耗
	int BAG_EXPAND = 5005; // 背包扩充
	int BAG_TAKE_ALL = 5004; // 背包物品全部拿走
	int POLISH_COST = 5005; // 装备打磨消耗
	int MARK_POS_FIGHTER_COST = 5006; // 标记装备槽位战斗消耗
	int GENS_DONATION_COST = 5007; // 公会捐赠消耗
	int GENS_CREATE_COST = 5007; // 创建公会消耗
	int GENS_DEPOT_RECEIVE_COST = 5008; // 公会仓库领取消耗
	int GENS_IMPEACH_COST = 5009; // 公会弹劾消耗
	int SKIN_ACTIVATION_COST = 5010; // 时装激活消耗
	int MAP_REVIVE = 5020; //地图复活
	int WAR_WAKES_SHOP = 5021; //战令商店购买
	int WAR_WAKES_UPGRADE = 5022; //战令升级
	int WAR_WAKES_BUY_LEVEL = 5023; //战令购买等级
	int TELEPORT_AREA = 5024; //传送区域消耗
	int BUILD_HOME = 5025; //绑定家园消耗
	int EQUIP_REPAIR_COST = 5026; //装备修理消耗
	int EQUIP_MET_REPAIR_COST = 5027; //装备精细修理消耗
	int BAG_MOVE_MULTI_ITEM = 5028; // 背包移动多个道具
	int INLAY_PENDANT = 5029; // 镶嵌挂件
	int MOVE_ITEM = 5030; // 移动道具
	int TAKE_OFF_EQUIP = 5031; // 脱装备
	int WEAR_EQUIP = 5032; // 穿装备
	int ROLE_DEATH_DROP = 5033; // 玩家死亡掉落
	int ITEM_THROW = 5035; // 丢弃道具
	int COMPOSE_COST = 5036; // 合成消耗
	int TALENT_LEVEL_UP = 5037; // 天赋等级提升
	int SHOP_BUY = 5038; // 商城购买
	int GENS_SAVE_RESOURCE = 5039; // 公会资源上交


}
