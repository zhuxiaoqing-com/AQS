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
	int BAG_TAKE_ALL = 5006; // 背包物品全部拿走
	int POLISH_COST = 5007; // 装备打磨消耗
	int MARK_POS_FIGHTER_COST = 5008; // 标记装备槽位战斗消耗
	int GENS_DONATION_COST = 5009; // 公会捐赠消耗
	int GENS_CREATE_COST = 5010; // 创建公会消耗
	int GENS_DEPOT_RECEIVE_COST = 5011; // 公会仓库领取消耗
	int GENS_IMPEACH_COST = 5012; // 公会弹劾消耗
	int SKIN_ACTIVATION_COST = 5013; // 时装激活消耗
	int MAP_REVIVE = 5014; //地图复活
	int WAR_WAKES_SHOP = 5015; //战令商店购买
	int WAR_WAKES_UPGRADE = 5016; //战令升级
	int WAR_WAKES_BUY_LEVEL = 5017; //战令购买等级
	int TELEPORT_AREA = 5018; //传送区域消耗
	int BUILD_HOME = 5019; //绑定家园消耗
	int EQUIP_REPAIR_COST = 5020; //装备修理消耗
	int EQUIP_MET_REPAIR_COST = 5021; //装备精细修理消耗
	int BAG_MOVE_MULTI_ITEM = 5022; // 背包移动多个道具
	int INLAY_PENDANT = 5023; // 镶嵌挂件
	int MOVE_ITEM = 5024; // 移动道具
	int TAKE_OFF_EQUIP = 5025; // 脱装备
	int WEAR_EQUIP = 5026; // 穿装备
	int ROLE_DEATH_DROP = 5027; // 玩家死亡掉落
	int ITEM_THROW = 5028; // 丢弃道具
	int COMPOSE_COST = 5029; // 合成消耗
	int TALENT_LEVEL_UP = 5030; // 天赋等级提升
	int SHOP_BUY = 5031; // 商城购买
	int GENS_SAVE_RESOURCE = 5032; // 公会资源上交


}