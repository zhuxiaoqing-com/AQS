package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LbPlayerFaction extends warrior.mdb.XBean {
    private long playerId; 
    private long lastDailyFresh;  // 上次每日刷新
    private long lastWeekFresh;  // 上次每周刷新
    private long lastMonthFresh;  // 上次每月刷新
    private long factionSeasonDailyScore;  // 阵营战每日个人积分
    private long factionSeasonWeekScore;  // 阵营战每周个人积分解锁制造奖励
    private long factionSeasonMonthScore;  // 阵营战每月个人积分
    private long factionSeasonTotalScore;  // 阵营战累计个人积分
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> composeTechDonate;  // 制造科技捐献
    private int dailyMakeBuffNum;  // 当天已经添加的生产buff数量
    private int makingItem;  // 正在知道的道具id
    private long singleMakeStartMill;  // 这件道具制造的开始时间
    private long singleMakeNeedMill;  // 这件道具制造需要多久
    private int makeFinishItemId;  // 制造完成道具id
    private int makeFinishItemNum;  // 制造完成道具件数
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> makingCost;  // 制造消耗 打断时返还
    private long assistCarrierMakeSrl;  // 协助的载具制造srl
    private int assistCarrierMakeCfgId;  // 协助的载具制造配置id(载具配置id)
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> composeDonateTotal;  // 为装备研发投入的道具累计
    private java.util.concurrent.CopyOnWriteArrayList<Long> factionSeasonScore7Days;  // 最近7天的阵营战积分
    private long lastSeasonSummary;  // 上次阵营战结算时间
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> techDonate;  // 普通科技捐献累计
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> dailyMobiDonate;  // 今日动员募捐
    private int dailyTechMobiDonate;  // 今日动员和科技总募捐
    private java.util.concurrent.CopyOnWriteArrayList<PlayerBattleLog> playerBattleLogMap;  // 阵营战个人数据
    private java.util.concurrent.CopyOnWriteArraySet<Integer> dailyTakeDonateReward;  // 今日已领取募捐奖励
    private java.util.concurrent.CopyOnWriteArraySet<Integer> grantTakeDonateReward;  // 已领取累计奖励
    private int totalTechMobiDonate;  // 累计动员和科技总募捐
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> dailyDonateCount;  // 今日捐献次数
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> talentDepotCD;  // 阵营领取cd
    private long makeTimeTotal;  // 阵营累计制造时长
    private java.util.concurrent.CopyOnWriteArrayList<Long> weekLogisticScore;  // 7天后勤数据
    private long logisticScore;  // 后勤总积分
    private long collectScore;  // 采集分
    private long allBattleScore;  // 战斗总积分(演习积分,阵营战积分,跨服战积分 综合)
    private xbean.lobby.PlayerAreaFlagDonate areaDonate;  // 地区捐献数据
    private long dailyConstructScore; 
    private long constructScore; 
    private int crossScore;  // 跨服战累计积分
    private int dailyMobiCost;  // 今日动员募捐总白银
    private int weeklyMobiCost;  // 周动员募捐总白银
    private int monthlyMobiCost;  // 月动员募捐总白银
    private int totalMobiCost;  // 动员募捐总白银
    private java.util.concurrent.ConcurrentHashMap<Integer, TalentReceiveRemain> talentReceiveRemainMap;  // 上次领取中未领取成功的道具信息
    private long totalComposeScore;  // 装备研发积分
    private java.util.concurrent.CopyOnWriteArrayList<Long> weekConstructScore;  // 7天建设数据
    private java.util.concurrent.CopyOnWriteArraySet<Integer> receiveFactionTargetSet;  // 已经领取过奖励的阵营目标idSet
    private java.util.concurrent.CopyOnWriteArrayList<Integer> latelyMakeItemId;  // 最近制造的列表
    private int logisticsHonor;  // 后勤荣誉
    private int battleHonor;  // 战斗荣誉
    private int transportHonor;  // 运输荣
    private int mobiHonor; 
    private java.util.concurrent.CopyOnWriteArrayList<Long> weekLogisticHonor;  // 7天后勤数据
    private java.util.concurrent.CopyOnWriteArrayList<Long> weekBattleHonor;  // 7天后勤数据
    private long teleportDeclareTime;  // 记录部队宣战时间;在传送时记录;用于判断是否已经推送过传送

    LbPlayerFaction(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.composeTechDonate = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.makingCost = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.composeDonateTotal = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.factionSeasonScore7Days = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		this.techDonate = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.dailyMobiDonate = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.playerBattleLogMap = new java.util.concurrent.CopyOnWriteArrayList<PlayerBattleLog>();
		this.dailyTakeDonateReward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.grantTakeDonateReward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.dailyDonateCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.talentDepotCD = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		this.weekLogisticScore = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		this.areaDonate = new PlayerAreaFlagDonate(this);
		this.talentReceiveRemainMap = new java.util.concurrent.ConcurrentHashMap<Integer, TalentReceiveRemain>();
		this.weekConstructScore = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		this.receiveFactionTargetSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.latelyMakeItemId = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		this.weekLogisticHonor = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		this.weekBattleHonor = new java.util.concurrent.CopyOnWriteArrayList<Long>();
	}

	public LbPlayerFaction() {
		this((warrior.mdb.XBean)null);
	}

	public LbPlayerFaction(LbPlayerFaction _o_) {
		this(_o_, null);
	}

	LbPlayerFaction(LbPlayerFaction _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.playerId = _o_.playerId;
		this.lastDailyFresh = _o_.lastDailyFresh;
		this.lastWeekFresh = _o_.lastWeekFresh;
		this.lastMonthFresh = _o_.lastMonthFresh;
		this.factionSeasonDailyScore = _o_.factionSeasonDailyScore;
		this.factionSeasonWeekScore = _o_.factionSeasonWeekScore;
		this.factionSeasonMonthScore = _o_.factionSeasonMonthScore;
		this.factionSeasonTotalScore = _o_.factionSeasonTotalScore;
		this.composeTechDonate = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.composeTechDonate.forEach((_k_, _v_) -> this.composeTechDonate.put(_k_, _v_));
		this.dailyMakeBuffNum = _o_.dailyMakeBuffNum;
		this.makingItem = _o_.makingItem;
		this.singleMakeStartMill = _o_.singleMakeStartMill;
		this.singleMakeNeedMill = _o_.singleMakeNeedMill;
		this.makeFinishItemId = _o_.makeFinishItemId;
		this.makeFinishItemNum = _o_.makeFinishItemNum;
		this.makingCost = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.makingCost.forEach((_k_, _v_) -> this.makingCost.put(_k_, _v_));
		this.assistCarrierMakeSrl = _o_.assistCarrierMakeSrl;
		this.assistCarrierMakeCfgId = _o_.assistCarrierMakeCfgId;
		this.composeDonateTotal = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.composeDonateTotal.forEach((_k_, _v_) -> this.composeDonateTotal.put(_k_, _v_));
		this.factionSeasonScore7Days = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.factionSeasonScore7Days.forEach(_v_ -> this.factionSeasonScore7Days.add(_v_));
		this.lastSeasonSummary = _o_.lastSeasonSummary;
		this.techDonate = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.techDonate.forEach((_k_, _v_) -> this.techDonate.put(_k_, _v_));
		this.dailyMobiDonate = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyMobiDonate.forEach((_k_, _v_) -> this.dailyMobiDonate.put(_k_, _v_));
		this.dailyTechMobiDonate = _o_.dailyTechMobiDonate;
		this.playerBattleLogMap = new java.util.concurrent.CopyOnWriteArrayList<PlayerBattleLog>();
		_o_.playerBattleLogMap.forEach(_v_ -> this.playerBattleLogMap.add(new PlayerBattleLog(_v_)));
		this.dailyTakeDonateReward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.dailyTakeDonateReward.forEach(_v_ -> this.dailyTakeDonateReward.add(_v_));
		this.grantTakeDonateReward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.grantTakeDonateReward.forEach(_v_ -> this.grantTakeDonateReward.add(_v_));
		this.totalTechMobiDonate = _o_.totalTechMobiDonate;
		this.dailyDonateCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyDonateCount.forEach((_k_, _v_) -> this.dailyDonateCount.put(_k_, _v_));
		this.talentDepotCD = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.talentDepotCD.forEach((_k_, _v_) -> this.talentDepotCD.put(_k_, _v_));
		this.makeTimeTotal = _o_.makeTimeTotal;
		this.weekLogisticScore = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.weekLogisticScore.forEach(_v_ -> this.weekLogisticScore.add(_v_));
		this.logisticScore = _o_.logisticScore;
		this.collectScore = _o_.collectScore;
		this.allBattleScore = _o_.allBattleScore;
		this.areaDonate = new PlayerAreaFlagDonate(_o_.areaDonate, this);
		this.dailyConstructScore = _o_.dailyConstructScore;
		this.constructScore = _o_.constructScore;
		this.crossScore = _o_.crossScore;
		this.dailyMobiCost = _o_.dailyMobiCost;
		this.weeklyMobiCost = _o_.weeklyMobiCost;
		this.monthlyMobiCost = _o_.monthlyMobiCost;
		this.totalMobiCost = _o_.totalMobiCost;
		this.talentReceiveRemainMap = new java.util.concurrent.ConcurrentHashMap<Integer, TalentReceiveRemain>();
		_o_.talentReceiveRemainMap.forEach((_k_, _v_) -> this.talentReceiveRemainMap.put(_k_, new TalentReceiveRemain(_v_)));
		this.totalComposeScore = _o_.totalComposeScore;
		this.weekConstructScore = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.weekConstructScore.forEach(_v_ -> this.weekConstructScore.add(_v_));
		this.receiveFactionTargetSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.receiveFactionTargetSet.forEach(_v_ -> this.receiveFactionTargetSet.add(_v_));
		this.latelyMakeItemId = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.latelyMakeItemId.forEach(_v_ -> this.latelyMakeItemId.add(_v_));
		this.logisticsHonor = _o_.logisticsHonor;
		this.battleHonor = _o_.battleHonor;
		this.transportHonor = _o_.transportHonor;
		this.mobiHonor = _o_.mobiHonor;
		this.weekLogisticHonor = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.weekLogisticHonor.forEach(_v_ -> this.weekLogisticHonor.add(_v_));
		this.weekBattleHonor = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.weekBattleHonor.forEach(_v_ -> this.weekBattleHonor.add(_v_));
		this.teleportDeclareTime = _o_.teleportDeclareTime;
	}

	public void copyFrom(LbPlayerFaction _o_) {
        this.playerId = _o_.playerId;
        this.lastDailyFresh = _o_.lastDailyFresh;
        this.lastWeekFresh = _o_.lastWeekFresh;
        this.lastMonthFresh = _o_.lastMonthFresh;
        this.factionSeasonDailyScore = _o_.factionSeasonDailyScore;
        this.factionSeasonWeekScore = _o_.factionSeasonWeekScore;
        this.factionSeasonMonthScore = _o_.factionSeasonMonthScore;
        this.factionSeasonTotalScore = _o_.factionSeasonTotalScore;
        this.composeTechDonate = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.composeTechDonate.forEach((_k_, _v_) -> this.composeTechDonate.put(_k_, _v_));
        this.dailyMakeBuffNum = _o_.dailyMakeBuffNum;
        this.makingItem = _o_.makingItem;
        this.singleMakeStartMill = _o_.singleMakeStartMill;
        this.singleMakeNeedMill = _o_.singleMakeNeedMill;
        this.makeFinishItemId = _o_.makeFinishItemId;
        this.makeFinishItemNum = _o_.makeFinishItemNum;
        this.makingCost = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.makingCost.forEach((_k_, _v_) -> this.makingCost.put(_k_, _v_));
        this.assistCarrierMakeSrl = _o_.assistCarrierMakeSrl;
        this.assistCarrierMakeCfgId = _o_.assistCarrierMakeCfgId;
        this.composeDonateTotal = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.composeDonateTotal.forEach((_k_, _v_) -> this.composeDonateTotal.put(_k_, _v_));
        this.factionSeasonScore7Days = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.factionSeasonScore7Days.forEach(_v_ -> this.factionSeasonScore7Days.add(_v_));
        this.lastSeasonSummary = _o_.lastSeasonSummary;
        this.techDonate = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.techDonate.forEach((_k_, _v_) -> this.techDonate.put(_k_, _v_));
        this.dailyMobiDonate = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyMobiDonate.forEach((_k_, _v_) -> this.dailyMobiDonate.put(_k_, _v_));
        this.dailyTechMobiDonate = _o_.dailyTechMobiDonate;
        this.playerBattleLogMap = new java.util.concurrent.CopyOnWriteArrayList<PlayerBattleLog>();
		_o_.playerBattleLogMap.forEach(_v_ -> this.playerBattleLogMap.add(new PlayerBattleLog(_v_)));
        this.dailyTakeDonateReward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.dailyTakeDonateReward.forEach(_v_ -> this.dailyTakeDonateReward.add(_v_));
        this.grantTakeDonateReward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.grantTakeDonateReward.forEach(_v_ -> this.grantTakeDonateReward.add(_v_));
        this.totalTechMobiDonate = _o_.totalTechMobiDonate;
        this.dailyDonateCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyDonateCount.forEach((_k_, _v_) -> this.dailyDonateCount.put(_k_, _v_));
        this.talentDepotCD = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.talentDepotCD.forEach((_k_, _v_) -> this.talentDepotCD.put(_k_, _v_));
        this.makeTimeTotal = _o_.makeTimeTotal;
        this.weekLogisticScore = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.weekLogisticScore.forEach(_v_ -> this.weekLogisticScore.add(_v_));
        this.logisticScore = _o_.logisticScore;
        this.collectScore = _o_.collectScore;
        this.allBattleScore = _o_.allBattleScore;
        this.areaDonate = new PlayerAreaFlagDonate(_o_.areaDonate, this);
        this.dailyConstructScore = _o_.dailyConstructScore;
        this.constructScore = _o_.constructScore;
        this.crossScore = _o_.crossScore;
        this.dailyMobiCost = _o_.dailyMobiCost;
        this.weeklyMobiCost = _o_.weeklyMobiCost;
        this.monthlyMobiCost = _o_.monthlyMobiCost;
        this.totalMobiCost = _o_.totalMobiCost;
        this.talentReceiveRemainMap = new java.util.concurrent.ConcurrentHashMap<Integer, TalentReceiveRemain>();
		_o_.talentReceiveRemainMap.forEach((_k_, _v_) -> this.talentReceiveRemainMap.put(_k_, new TalentReceiveRemain(_v_)));
        this.totalComposeScore = _o_.totalComposeScore;
        this.weekConstructScore = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.weekConstructScore.forEach(_v_ -> this.weekConstructScore.add(_v_));
        this.receiveFactionTargetSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.receiveFactionTargetSet.forEach(_v_ -> this.receiveFactionTargetSet.add(_v_));
        this.latelyMakeItemId = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.latelyMakeItemId.forEach(_v_ -> this.latelyMakeItemId.add(_v_));
        this.logisticsHonor = _o_.logisticsHonor;
        this.battleHonor = _o_.battleHonor;
        this.transportHonor = _o_.transportHonor;
        this.mobiHonor = _o_.mobiHonor;
        this.weekLogisticHonor = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.weekLogisticHonor.forEach(_v_ -> this.weekLogisticHonor.add(_v_));
        this.weekBattleHonor = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.weekBattleHonor.forEach(_v_ -> this.weekBattleHonor.add(_v_));
        this.teleportDeclareTime = _o_.teleportDeclareTime;
	}


	@JSONField(name = "1")
	public long getPlayerId(){
		return this.playerId;
	}

	@JSONField(name = "2")
	public long getLastDailyFresh(){
		return this.lastDailyFresh;
	}

	@JSONField(name = "3")
	public long getLastWeekFresh(){
		return this.lastWeekFresh;
	}

	@JSONField(name = "4")
	public long getLastMonthFresh(){
		return this.lastMonthFresh;
	}

	@JSONField(name = "5")
	public long getFactionSeasonDailyScore(){
		return this.factionSeasonDailyScore;
	}

	@JSONField(name = "6")
	public long getFactionSeasonWeekScore(){
		return this.factionSeasonWeekScore;
	}

	@JSONField(name = "7")
	public long getFactionSeasonMonthScore(){
		return this.factionSeasonMonthScore;
	}

	@JSONField(name = "8")
	public long getFactionSeasonTotalScore(){
		return this.factionSeasonTotalScore;
	}

	@JSONField(name = "9")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getComposeTechDonate(){
		return this.composeTechDonate;
	}

	@JSONField(name = "10")
	public int getDailyMakeBuffNum(){
		return this.dailyMakeBuffNum;
	}

	@JSONField(name = "11")
	public int getMakingItem(){
		return this.makingItem;
	}

	@JSONField(name = "12")
	public long getSingleMakeStartMill(){
		return this.singleMakeStartMill;
	}

	@JSONField(name = "13")
	public long getSingleMakeNeedMill(){
		return this.singleMakeNeedMill;
	}

	@JSONField(name = "15")
	public int getMakeFinishItemId(){
		return this.makeFinishItemId;
	}

	@JSONField(name = "16")
	public int getMakeFinishItemNum(){
		return this.makeFinishItemNum;
	}

	@JSONField(name = "17")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getMakingCost(){
		return this.makingCost;
	}

	@JSONField(name = "19")
	public long getAssistCarrierMakeSrl(){
		return this.assistCarrierMakeSrl;
	}

	@JSONField(name = "20")
	public int getAssistCarrierMakeCfgId(){
		return this.assistCarrierMakeCfgId;
	}

	@JSONField(name = "21")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getComposeDonateTotal(){
		return this.composeDonateTotal;
	}

	@JSONField(name = "22")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getFactionSeasonScore7Days(){
		return this.factionSeasonScore7Days;
	}

	@JSONField(name = "23")
	public long getLastSeasonSummary(){
		return this.lastSeasonSummary;
	}

	@JSONField(name = "24")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getTechDonate(){
		return this.techDonate;
	}

	@JSONField(name = "25")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDailyMobiDonate(){
		return this.dailyMobiDonate;
	}

	@JSONField(name = "26")
	public int getDailyTechMobiDonate(){
		return this.dailyTechMobiDonate;
	}

	@JSONField(name = "27")
	public java.util.concurrent.CopyOnWriteArrayList<PlayerBattleLog> getPlayerBattleLogMap(){
		return this.playerBattleLogMap;
	}

	@JSONField(name = "28")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getDailyTakeDonateReward(){
		return this.dailyTakeDonateReward;
	}

	@JSONField(name = "29")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getGrantTakeDonateReward(){
		return this.grantTakeDonateReward;
	}

	@JSONField(name = "30")
	public int getTotalTechMobiDonate(){
		return this.totalTechMobiDonate;
	}

	@JSONField(name = "31")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDailyDonateCount(){
		return this.dailyDonateCount;
	}

	@JSONField(name = "33")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getTalentDepotCD(){
		return this.talentDepotCD;
	}

	@JSONField(name = "34")
	public long getMakeTimeTotal(){
		return this.makeTimeTotal;
	}

	@JSONField(name = "35")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getWeekLogisticScore(){
		return this.weekLogisticScore;
	}

	@JSONField(name = "36")
	public long getLogisticScore(){
		return this.logisticScore;
	}

	@JSONField(name = "37")
	public long getCollectScore(){
		return this.collectScore;
	}

	@JSONField(name = "38")
	public long getAllBattleScore(){
		return this.allBattleScore;
	}

	@JSONField(name = "39")
	public xbean.lobby.PlayerAreaFlagDonate getAreaDonate(){
		return this.areaDonate;
	}

	@JSONField(name = "41")
	public long getDailyConstructScore(){
		return this.dailyConstructScore;
	}

	@JSONField(name = "42")
	public long getConstructScore(){
		return this.constructScore;
	}

	@JSONField(name = "43")
	public int getCrossScore(){
		return this.crossScore;
	}

	@JSONField(name = "44")
	public int getDailyMobiCost(){
		return this.dailyMobiCost;
	}

	@JSONField(name = "45")
	public int getWeeklyMobiCost(){
		return this.weeklyMobiCost;
	}

	@JSONField(name = "46")
	public int getMonthlyMobiCost(){
		return this.monthlyMobiCost;
	}

	@JSONField(name = "47")
	public int getTotalMobiCost(){
		return this.totalMobiCost;
	}

	@JSONField(name = "48")
	public java.util.concurrent.ConcurrentHashMap<Integer, TalentReceiveRemain> getTalentReceiveRemainMap(){
		return this.talentReceiveRemainMap;
	}

	@JSONField(name = "49")
	public long getTotalComposeScore(){
		return this.totalComposeScore;
	}

	@JSONField(name = "50")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getWeekConstructScore(){
		return this.weekConstructScore;
	}

	@JSONField(name = "51")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getReceiveFactionTargetSet(){
		return this.receiveFactionTargetSet;
	}

	@JSONField(name = "40")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getLatelyMakeItemId(){
		return this.latelyMakeItemId;
	}

	@JSONField(name = "54")
	public int getLogisticsHonor(){
		return this.logisticsHonor;
	}

	@JSONField(name = "55")
	public int getBattleHonor(){
		return this.battleHonor;
	}

	@JSONField(name = "56")
	public int getTransportHonor(){
		return this.transportHonor;
	}

	@JSONField(name = "57")
	public int getMobiHonor(){
		return this.mobiHonor;
	}

	@JSONField(name = "58")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getWeekLogisticHonor(){
		return this.weekLogisticHonor;
	}

	@JSONField(name = "59")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getWeekBattleHonor(){
		return this.weekBattleHonor;
	}

	@JSONField(name = "61")
	public long getTeleportDeclareTime(){
		return this.teleportDeclareTime;
	}

	@JSONField(name = "1")
	public void setPlayerId(long _v_){
		this.playerId = _v_;
	}

	@JSONField(name = "2")
	public void setLastDailyFresh(long _v_){
		this.lastDailyFresh = _v_;
	}

	@JSONField(name = "3")
	public void setLastWeekFresh(long _v_){
		this.lastWeekFresh = _v_;
	}

	@JSONField(name = "4")
	public void setLastMonthFresh(long _v_){
		this.lastMonthFresh = _v_;
	}

	@JSONField(name = "5")
	public void setFactionSeasonDailyScore(long _v_){
		this.factionSeasonDailyScore = _v_;
	}

	@JSONField(name = "6")
	public void setFactionSeasonWeekScore(long _v_){
		this.factionSeasonWeekScore = _v_;
	}

	@JSONField(name = "7")
	public void setFactionSeasonMonthScore(long _v_){
		this.factionSeasonMonthScore = _v_;
	}

	@JSONField(name = "8")
	public void setFactionSeasonTotalScore(long _v_){
		this.factionSeasonTotalScore = _v_;
	}

	@JSONField(name = "9")
	public void setComposeTechDonate(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.composeTechDonate = _v_;
	}

	@JSONField(name = "10")
	public void setDailyMakeBuffNum(int _v_){
		this.dailyMakeBuffNum = _v_;
	}

	@JSONField(name = "11")
	public void setMakingItem(int _v_){
		this.makingItem = _v_;
	}

	@JSONField(name = "12")
	public void setSingleMakeStartMill(long _v_){
		this.singleMakeStartMill = _v_;
	}

	@JSONField(name = "13")
	public void setSingleMakeNeedMill(long _v_){
		this.singleMakeNeedMill = _v_;
	}

	@JSONField(name = "15")
	public void setMakeFinishItemId(int _v_){
		this.makeFinishItemId = _v_;
	}

	@JSONField(name = "16")
	public void setMakeFinishItemNum(int _v_){
		this.makeFinishItemNum = _v_;
	}

	@JSONField(name = "17")
	public void setMakingCost(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.makingCost = _v_;
	}

	@JSONField(name = "19")
	public void setAssistCarrierMakeSrl(long _v_){
		this.assistCarrierMakeSrl = _v_;
	}

	@JSONField(name = "20")
	public void setAssistCarrierMakeCfgId(int _v_){
		this.assistCarrierMakeCfgId = _v_;
	}

	@JSONField(name = "21")
	public void setComposeDonateTotal(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.composeDonateTotal = _v_;
	}

	@JSONField(name = "22")
	public void setFactionSeasonScore7Days(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.factionSeasonScore7Days = _v_;
	}

	@JSONField(name = "23")
	public void setLastSeasonSummary(long _v_){
		this.lastSeasonSummary = _v_;
	}

	@JSONField(name = "24")
	public void setTechDonate(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.techDonate = _v_;
	}

	@JSONField(name = "25")
	public void setDailyMobiDonate(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.dailyMobiDonate = _v_;
	}

	@JSONField(name = "26")
	public void setDailyTechMobiDonate(int _v_){
		this.dailyTechMobiDonate = _v_;
	}

	@JSONField(name = "27")
	public void setPlayerBattleLogMap(java.util.concurrent.CopyOnWriteArrayList<PlayerBattleLog> _v_){
		this.playerBattleLogMap = _v_;
	}

	@JSONField(name = "28")
	public void setDailyTakeDonateReward(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.dailyTakeDonateReward = _v_;
	}

	@JSONField(name = "29")
	public void setGrantTakeDonateReward(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.grantTakeDonateReward = _v_;
	}

	@JSONField(name = "30")
	public void setTotalTechMobiDonate(int _v_){
		this.totalTechMobiDonate = _v_;
	}

	@JSONField(name = "31")
	public void setDailyDonateCount(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.dailyDonateCount = _v_;
	}

	@JSONField(name = "33")
	public void setTalentDepotCD(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.talentDepotCD = _v_;
	}

	@JSONField(name = "34")
	public void setMakeTimeTotal(long _v_){
		this.makeTimeTotal = _v_;
	}

	@JSONField(name = "35")
	public void setWeekLogisticScore(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.weekLogisticScore = _v_;
	}

	@JSONField(name = "36")
	public void setLogisticScore(long _v_){
		this.logisticScore = _v_;
	}

	@JSONField(name = "37")
	public void setCollectScore(long _v_){
		this.collectScore = _v_;
	}

	@JSONField(name = "38")
	public void setAllBattleScore(long _v_){
		this.allBattleScore = _v_;
	}

	@JSONField(name = "39")
	public void setAreaDonate(xbean.lobby.PlayerAreaFlagDonate _v_){
		this.areaDonate = _v_;
	}

	@JSONField(name = "41")
	public void setDailyConstructScore(long _v_){
		this.dailyConstructScore = _v_;
	}

	@JSONField(name = "42")
	public void setConstructScore(long _v_){
		this.constructScore = _v_;
	}

	@JSONField(name = "43")
	public void setCrossScore(int _v_){
		this.crossScore = _v_;
	}

	@JSONField(name = "44")
	public void setDailyMobiCost(int _v_){
		this.dailyMobiCost = _v_;
	}

	@JSONField(name = "45")
	public void setWeeklyMobiCost(int _v_){
		this.weeklyMobiCost = _v_;
	}

	@JSONField(name = "46")
	public void setMonthlyMobiCost(int _v_){
		this.monthlyMobiCost = _v_;
	}

	@JSONField(name = "47")
	public void setTotalMobiCost(int _v_){
		this.totalMobiCost = _v_;
	}

	@JSONField(name = "48")
	public void setTalentReceiveRemainMap(java.util.concurrent.ConcurrentHashMap<Integer, TalentReceiveRemain> _v_){
		this.talentReceiveRemainMap = _v_;
	}

	@JSONField(name = "49")
	public void setTotalComposeScore(long _v_){
		this.totalComposeScore = _v_;
	}

	@JSONField(name = "50")
	public void setWeekConstructScore(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.weekConstructScore = _v_;
	}

	@JSONField(name = "51")
	public void setReceiveFactionTargetSet(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.receiveFactionTargetSet = _v_;
	}

	@JSONField(name = "40")
	public void setLatelyMakeItemId(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.latelyMakeItemId = _v_;
	}

	@JSONField(name = "54")
	public void setLogisticsHonor(int _v_){
		this.logisticsHonor = _v_;
	}

	@JSONField(name = "55")
	public void setBattleHonor(int _v_){
		this.battleHonor = _v_;
	}

	@JSONField(name = "56")
	public void setTransportHonor(int _v_){
		this.transportHonor = _v_;
	}

	@JSONField(name = "57")
	public void setMobiHonor(int _v_){
		this.mobiHonor = _v_;
	}

	@JSONField(name = "58")
	public void setWeekLogisticHonor(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.weekLogisticHonor = _v_;
	}

	@JSONField(name = "59")
	public void setWeekBattleHonor(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.weekBattleHonor = _v_;
	}

	@JSONField(name = "61")
	public void setTeleportDeclareTime(long _v_){
		this.teleportDeclareTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LbPlayerFaction) {
			LbPlayerFaction _o_ = (LbPlayerFaction)_o1_;
			if (this.playerId != _o_.playerId) return false;
			if (this.lastDailyFresh != _o_.lastDailyFresh) return false;
			if (this.lastWeekFresh != _o_.lastWeekFresh) return false;
			if (this.lastMonthFresh != _o_.lastMonthFresh) return false;
			if (this.factionSeasonDailyScore != _o_.factionSeasonDailyScore) return false;
			if (this.factionSeasonWeekScore != _o_.factionSeasonWeekScore) return false;
			if (this.factionSeasonMonthScore != _o_.factionSeasonMonthScore) return false;
			if (this.factionSeasonTotalScore != _o_.factionSeasonTotalScore) return false;
			if (!this.composeTechDonate.equals(_o_.composeTechDonate)) return false;
			if (this.dailyMakeBuffNum != _o_.dailyMakeBuffNum) return false;
			if (this.makingItem != _o_.makingItem) return false;
			if (this.singleMakeStartMill != _o_.singleMakeStartMill) return false;
			if (this.singleMakeNeedMill != _o_.singleMakeNeedMill) return false;
			if (this.makeFinishItemId != _o_.makeFinishItemId) return false;
			if (this.makeFinishItemNum != _o_.makeFinishItemNum) return false;
			if (!this.makingCost.equals(_o_.makingCost)) return false;
			if (this.assistCarrierMakeSrl != _o_.assistCarrierMakeSrl) return false;
			if (this.assistCarrierMakeCfgId != _o_.assistCarrierMakeCfgId) return false;
			if (!this.composeDonateTotal.equals(_o_.composeDonateTotal)) return false;
			if (!this.factionSeasonScore7Days.equals(_o_.factionSeasonScore7Days)) return false;
			if (this.lastSeasonSummary != _o_.lastSeasonSummary) return false;
			if (!this.techDonate.equals(_o_.techDonate)) return false;
			if (!this.dailyMobiDonate.equals(_o_.dailyMobiDonate)) return false;
			if (this.dailyTechMobiDonate != _o_.dailyTechMobiDonate) return false;
			if (!this.playerBattleLogMap.equals(_o_.playerBattleLogMap)) return false;
			if (!this.dailyTakeDonateReward.equals(_o_.dailyTakeDonateReward)) return false;
			if (!this.grantTakeDonateReward.equals(_o_.grantTakeDonateReward)) return false;
			if (this.totalTechMobiDonate != _o_.totalTechMobiDonate) return false;
			if (!this.dailyDonateCount.equals(_o_.dailyDonateCount)) return false;
			if (!this.talentDepotCD.equals(_o_.talentDepotCD)) return false;
			if (this.makeTimeTotal != _o_.makeTimeTotal) return false;
			if (!this.weekLogisticScore.equals(_o_.weekLogisticScore)) return false;
			if (this.logisticScore != _o_.logisticScore) return false;
			if (this.collectScore != _o_.collectScore) return false;
			if (this.allBattleScore != _o_.allBattleScore) return false;
			if (!this.areaDonate.equals(_o_.areaDonate)) return false;
			if (this.dailyConstructScore != _o_.dailyConstructScore) return false;
			if (this.constructScore != _o_.constructScore) return false;
			if (this.crossScore != _o_.crossScore) return false;
			if (this.dailyMobiCost != _o_.dailyMobiCost) return false;
			if (this.weeklyMobiCost != _o_.weeklyMobiCost) return false;
			if (this.monthlyMobiCost != _o_.monthlyMobiCost) return false;
			if (this.totalMobiCost != _o_.totalMobiCost) return false;
			if (!this.talentReceiveRemainMap.equals(_o_.talentReceiveRemainMap)) return false;
			if (this.totalComposeScore != _o_.totalComposeScore) return false;
			if (!this.weekConstructScore.equals(_o_.weekConstructScore)) return false;
			if (!this.receiveFactionTargetSet.equals(_o_.receiveFactionTargetSet)) return false;
			if (!this.latelyMakeItemId.equals(_o_.latelyMakeItemId)) return false;
			if (this.logisticsHonor != _o_.logisticsHonor) return false;
			if (this.battleHonor != _o_.battleHonor) return false;
			if (this.transportHonor != _o_.transportHonor) return false;
			if (this.mobiHonor != _o_.mobiHonor) return false;
			if (!this.weekLogisticHonor.equals(_o_.weekLogisticHonor)) return false;
			if (!this.weekBattleHonor.equals(_o_.weekBattleHonor)) return false;
			if (this.teleportDeclareTime != _o_.teleportDeclareTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(playerId ^ (playerId >>> 32));
		_h_ += _h_ * 31 + (int)(lastDailyFresh ^ (lastDailyFresh >>> 32));
		_h_ += _h_ * 31 + (int)(lastWeekFresh ^ (lastWeekFresh >>> 32));
		_h_ += _h_ * 31 + (int)(lastMonthFresh ^ (lastMonthFresh >>> 32));
		_h_ += _h_ * 31 + (int)(factionSeasonDailyScore ^ (factionSeasonDailyScore >>> 32));
		_h_ += _h_ * 31 + (int)(factionSeasonWeekScore ^ (factionSeasonWeekScore >>> 32));
		_h_ += _h_ * 31 + (int)(factionSeasonMonthScore ^ (factionSeasonMonthScore >>> 32));
		_h_ += _h_ * 31 + (int)(factionSeasonTotalScore ^ (factionSeasonTotalScore >>> 32));
		_h_ += _h_ * 31 + this.composeTechDonate.hashCode();
		_h_ += _h_ * 31 + (int)(this.dailyMakeBuffNum);
		_h_ += _h_ * 31 + (int)(this.makingItem);
		_h_ += _h_ * 31 + (int)(singleMakeStartMill ^ (singleMakeStartMill >>> 32));
		_h_ += _h_ * 31 + (int)(singleMakeNeedMill ^ (singleMakeNeedMill >>> 32));
		_h_ += _h_ * 31 + (int)(this.makeFinishItemId);
		_h_ += _h_ * 31 + (int)(this.makeFinishItemNum);
		_h_ += _h_ * 31 + this.makingCost.hashCode();
		_h_ += _h_ * 31 + (int)(assistCarrierMakeSrl ^ (assistCarrierMakeSrl >>> 32));
		_h_ += _h_ * 31 + (int)(this.assistCarrierMakeCfgId);
		_h_ += _h_ * 31 + this.composeDonateTotal.hashCode();
		_h_ += _h_ * 31 + this.factionSeasonScore7Days.hashCode();
		_h_ += _h_ * 31 + (int)(lastSeasonSummary ^ (lastSeasonSummary >>> 32));
		_h_ += _h_ * 31 + this.techDonate.hashCode();
		_h_ += _h_ * 31 + this.dailyMobiDonate.hashCode();
		_h_ += _h_ * 31 + (int)(this.dailyTechMobiDonate);
		_h_ += _h_ * 31 + this.playerBattleLogMap.hashCode();
		_h_ += _h_ * 31 + this.dailyTakeDonateReward.hashCode();
		_h_ += _h_ * 31 + this.grantTakeDonateReward.hashCode();
		_h_ += _h_ * 31 + (int)(this.totalTechMobiDonate);
		_h_ += _h_ * 31 + this.dailyDonateCount.hashCode();
		_h_ += _h_ * 31 + this.talentDepotCD.hashCode();
		_h_ += _h_ * 31 + (int)(makeTimeTotal ^ (makeTimeTotal >>> 32));
		_h_ += _h_ * 31 + this.weekLogisticScore.hashCode();
		_h_ += _h_ * 31 + (int)(logisticScore ^ (logisticScore >>> 32));
		_h_ += _h_ * 31 + (int)(collectScore ^ (collectScore >>> 32));
		_h_ += _h_ * 31 + (int)(allBattleScore ^ (allBattleScore >>> 32));
		_h_ += _h_ * 31 + this.areaDonate.hashCode();
		_h_ += _h_ * 31 + (int)(dailyConstructScore ^ (dailyConstructScore >>> 32));
		_h_ += _h_ * 31 + (int)(constructScore ^ (constructScore >>> 32));
		_h_ += _h_ * 31 + (int)(this.crossScore);
		_h_ += _h_ * 31 + (int)(this.dailyMobiCost);
		_h_ += _h_ * 31 + (int)(this.weeklyMobiCost);
		_h_ += _h_ * 31 + (int)(this.monthlyMobiCost);
		_h_ += _h_ * 31 + (int)(this.totalMobiCost);
		_h_ += _h_ * 31 + this.talentReceiveRemainMap.hashCode();
		_h_ += _h_ * 31 + (int)(totalComposeScore ^ (totalComposeScore >>> 32));
		_h_ += _h_ * 31 + this.weekConstructScore.hashCode();
		_h_ += _h_ * 31 + this.receiveFactionTargetSet.hashCode();
		_h_ += _h_ * 31 + this.latelyMakeItemId.hashCode();
		_h_ += _h_ * 31 + (int)(this.logisticsHonor);
		_h_ += _h_ * 31 + (int)(this.battleHonor);
		_h_ += _h_ * 31 + (int)(this.transportHonor);
		_h_ += _h_ * 31 + (int)(this.mobiHonor);
		_h_ += _h_ * 31 + this.weekLogisticHonor.hashCode();
		_h_ += _h_ * 31 + this.weekBattleHonor.hashCode();
		_h_ += _h_ * 31 + (int)(teleportDeclareTime ^ (teleportDeclareTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(playerId).append(",");
		_sb_.append(lastDailyFresh).append(",");
		_sb_.append(lastWeekFresh).append(",");
		_sb_.append(lastMonthFresh).append(",");
		_sb_.append(factionSeasonDailyScore).append(",");
		_sb_.append(factionSeasonWeekScore).append(",");
		_sb_.append(factionSeasonMonthScore).append(",");
		_sb_.append(factionSeasonTotalScore).append(",");
		_sb_.append(composeTechDonate).append(",");
		_sb_.append(dailyMakeBuffNum).append(",");
		_sb_.append(makingItem).append(",");
		_sb_.append(singleMakeStartMill).append(",");
		_sb_.append(singleMakeNeedMill).append(",");
		_sb_.append(makeFinishItemId).append(",");
		_sb_.append(makeFinishItemNum).append(",");
		_sb_.append(makingCost).append(",");
		_sb_.append(assistCarrierMakeSrl).append(",");
		_sb_.append(assistCarrierMakeCfgId).append(",");
		_sb_.append(composeDonateTotal).append(",");
		_sb_.append(factionSeasonScore7Days).append(",");
		_sb_.append(lastSeasonSummary).append(",");
		_sb_.append(techDonate).append(",");
		_sb_.append(dailyMobiDonate).append(",");
		_sb_.append(dailyTechMobiDonate).append(",");
		_sb_.append(playerBattleLogMap).append(",");
		_sb_.append(dailyTakeDonateReward).append(",");
		_sb_.append(grantTakeDonateReward).append(",");
		_sb_.append(totalTechMobiDonate).append(",");
		_sb_.append(dailyDonateCount).append(",");
		_sb_.append(talentDepotCD).append(",");
		_sb_.append(makeTimeTotal).append(",");
		_sb_.append(weekLogisticScore).append(",");
		_sb_.append(logisticScore).append(",");
		_sb_.append(collectScore).append(",");
		_sb_.append(allBattleScore).append(",");
		_sb_.append(areaDonate).append(",");
		_sb_.append(dailyConstructScore).append(",");
		_sb_.append(constructScore).append(",");
		_sb_.append(crossScore).append(",");
		_sb_.append(dailyMobiCost).append(",");
		_sb_.append(weeklyMobiCost).append(",");
		_sb_.append(monthlyMobiCost).append(",");
		_sb_.append(totalMobiCost).append(",");
		_sb_.append(talentReceiveRemainMap).append(",");
		_sb_.append(totalComposeScore).append(",");
		_sb_.append(weekConstructScore).append(",");
		_sb_.append(receiveFactionTargetSet).append(",");
		_sb_.append(latelyMakeItemId).append(",");
		_sb_.append(logisticsHonor).append(",");
		_sb_.append(battleHonor).append(",");
		_sb_.append(transportHonor).append(",");
		_sb_.append(mobiHonor).append(",");
		_sb_.append(weekLogisticHonor).append(",");
		_sb_.append(weekBattleHonor).append(",");
		_sb_.append(teleportDeclareTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
