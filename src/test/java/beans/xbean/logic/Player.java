package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class Player extends warrior.mdb.XBean {
    private long pid;  // 玩家角色ID
    private String uid;  // userId
    private String name;  // 角色名
    private int sex;  // 性别
    private int nation;  // 国家
    private int hair;  // 头部配置ID
    private int bodyid;  // 模型配置ID
    private long gold;  // 金币
    private long diamond;  // 钻石
    private long honor;  // 当有拥有荣誉值
    private long talent;  // 天赋点数
    private long ihonor;  // 累计获得荣誉值
    private long point;  // 战术点
    private long warMakes;  // 战令币
    private int exp;  // 当前经验
    private long token;  // 验证
    private long enterd;  // 进入游戏时间
    private long cread;  // 创建时间
    private long messd;  // 被禁言结束时间
    private long outd;  // 上次登出时间
    private int wpos;  // 当前使用装备的位置(用主手还是副手)
    private int supplyPos;  // 当前使用的补给位置
    private int bagLoad;  // 人物背包总负重
    private int skilledHurt;  // 玩家受伤获取防具熟练度(每累计受伤x伤害就获取x熟练度)
    private boolean init;  // 玩家是否初始化
    private long lastRefresh;  // 上一次刷新的时间戳
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> buyMakeCount;  // 今日购买制造次数
    private long platinum;  // 白金币
    private int loginDay;  // 累计登陆天数
    private int loginContinueDay;  // 累计连续登陆天数
    private long loginCount;  // 累计登陆次数
    private int rmbSum;  // 总充
    private int rmbCount;  // 总充次数
    private long firstOrderTime;  // 首充时间
    private String firstOrderId;  // 首充订单号
    private long lastOrderTime;  // 上一次付费时间
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> dailyHonorMap;  // 每日野外击杀荣誉点
    private long nextCanTeleportTime;  // 下一次可以传送的毫秒值
    private long gentesCoin;  // 军团币
    private long defenseItemSrl;  // 进入工事前被顶下来的武器srl
    private int defenseWpos;  // 进入工事前持有的wpos
    private int nameChangeCount;  // 创角后改名次数
    private int avatar;  // 头像
    private int lastTeleNpc;  // 上一次进入的传送门serviceId
    private long lastExceptionTime;  // 上一次脱离卡死的时间
    private long lastBackHomeTime;  // 上一次回城的时间
    private long techPoint;  // 科技点
    private long mentorPoint;  // 名师值
    private java.util.concurrent.ConcurrentHashMap<Long, GensInviteInfo> inviteGens;  // 收到的军团邀请
    private long suppliesCoin;  // 军需代币
    private long exploit;  // 功勋货币
    private long heroMedal;  // 战斗英雄奖章货币
    private int faction;  // 阵营
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> dailyUseCommanderCount;  // 使用指挥官技能计数
    private long totalOnline;  // 累计在线时长
    private int newSupplyCount;  // 已领取新手补给次数
    private long newSupplyLastTime;  // 上次领取新手补给时间
    private long perfectTime;  // 完善玩家数据时间
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> resCoinMap;  // 资源货币
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> historyCoinMap;  // 历史获取过的货币
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> commanderSkillNextTime;  // 下一次使用指挥官技能的时间
    private long usedTalent;  // 已消耗的天赋点数
    private long energy;  // 精力
    private int dailyTrumpCount;  // 今日小喇叭使用次数
    private int wildDeathCount;  // 野外死亡次数
    private long lastWildDeathTime;  // 上一次野外死亡时间
    private String qq; 
    private boolean showFaction;  // 是否阵营可见
    private boolean showFriend;  // 是否好友可见
    private boolean showCompany;  // 是否连队可见
    private int honorCure;  // 荣誉治疗量;治疗多少算x点荣誉
    private boolean randomFaction;  // 是否是随机阵营
    private int selectWeaponId;  // 创号选择的武器id
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> dailyCommanderSkillUseReward;  // 当天使用指挥官技能获得的奖励
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> talentRecvEndTime; 
    private long lastUpdateHold;  // 上次更新拥有武装到lb
    private long adjustCoin;  // 调剂值
    private long luckyCoin;  // 幸运币
    private int transportTargetArea; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> fragCoinMap;  // 碎片货币
    private long autoModeUseMill;  // 自动模式使用时长
    private long twoRockerModeUseMill;  // 双摇杆模式使用时长

    Player(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.uid = "";
		this.name = "";
		this.buyMakeCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.firstOrderId = "";
		this.dailyHonorMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		this.inviteGens = new java.util.concurrent.ConcurrentHashMap<Long, GensInviteInfo>();
		this.dailyUseCommanderCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.resCoinMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		this.historyCoinMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		this.commanderSkillNextTime = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		this.qq = "";
		this.dailyCommanderSkillUseReward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.talentRecvEndTime = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		this.fragCoinMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
	}

	public Player() {
		this((warrior.mdb.XBean)null);
	}

	public Player(Player _o_) {
		this(_o_, null);
	}

	Player(Player _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.pid = _o_.pid;
		this.uid = _o_.uid;
		this.name = _o_.name;
		this.sex = _o_.sex;
		this.nation = _o_.nation;
		this.hair = _o_.hair;
		this.bodyid = _o_.bodyid;
		this.gold = _o_.gold;
		this.diamond = _o_.diamond;
		this.honor = _o_.honor;
		this.talent = _o_.talent;
		this.ihonor = _o_.ihonor;
		this.point = _o_.point;
		this.warMakes = _o_.warMakes;
		this.exp = _o_.exp;
		this.token = _o_.token;
		this.enterd = _o_.enterd;
		this.cread = _o_.cread;
		this.messd = _o_.messd;
		this.outd = _o_.outd;
		this.wpos = _o_.wpos;
		this.supplyPos = _o_.supplyPos;
		this.bagLoad = _o_.bagLoad;
		this.skilledHurt = _o_.skilledHurt;
		this.init = _o_.init;
		this.lastRefresh = _o_.lastRefresh;
		this.buyMakeCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.buyMakeCount.forEach((_k_, _v_) -> this.buyMakeCount.put(_k_, _v_));
		this.platinum = _o_.platinum;
		this.loginDay = _o_.loginDay;
		this.loginContinueDay = _o_.loginContinueDay;
		this.loginCount = _o_.loginCount;
		this.rmbSum = _o_.rmbSum;
		this.rmbCount = _o_.rmbCount;
		this.firstOrderTime = _o_.firstOrderTime;
		this.firstOrderId = _o_.firstOrderId;
		this.lastOrderTime = _o_.lastOrderTime;
		this.dailyHonorMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.dailyHonorMap.forEach((_k_, _v_) -> this.dailyHonorMap.put(_k_, _v_));
		this.nextCanTeleportTime = _o_.nextCanTeleportTime;
		this.gentesCoin = _o_.gentesCoin;
		this.defenseItemSrl = _o_.defenseItemSrl;
		this.defenseWpos = _o_.defenseWpos;
		this.nameChangeCount = _o_.nameChangeCount;
		this.avatar = _o_.avatar;
		this.lastTeleNpc = _o_.lastTeleNpc;
		this.lastExceptionTime = _o_.lastExceptionTime;
		this.lastBackHomeTime = _o_.lastBackHomeTime;
		this.techPoint = _o_.techPoint;
		this.mentorPoint = _o_.mentorPoint;
		this.inviteGens = new java.util.concurrent.ConcurrentHashMap<Long, GensInviteInfo>();
		_o_.inviteGens.forEach((_k_, _v_) -> this.inviteGens.put(_k_, new GensInviteInfo(_v_)));
		this.suppliesCoin = _o_.suppliesCoin;
		this.exploit = _o_.exploit;
		this.heroMedal = _o_.heroMedal;
		this.faction = _o_.faction;
		this.dailyUseCommanderCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyUseCommanderCount.forEach((_k_, _v_) -> this.dailyUseCommanderCount.put(_k_, _v_));
		this.totalOnline = _o_.totalOnline;
		this.newSupplyCount = _o_.newSupplyCount;
		this.newSupplyLastTime = _o_.newSupplyLastTime;
		this.perfectTime = _o_.perfectTime;
		this.resCoinMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.resCoinMap.forEach((_k_, _v_) -> this.resCoinMap.put(_k_, _v_));
		this.historyCoinMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.historyCoinMap.forEach((_k_, _v_) -> this.historyCoinMap.put(_k_, _v_));
		this.commanderSkillNextTime = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.commanderSkillNextTime.forEach((_k_, _v_) -> this.commanderSkillNextTime.put(_k_, _v_));
		this.usedTalent = _o_.usedTalent;
		this.energy = _o_.energy;
		this.dailyTrumpCount = _o_.dailyTrumpCount;
		this.wildDeathCount = _o_.wildDeathCount;
		this.lastWildDeathTime = _o_.lastWildDeathTime;
		this.qq = _o_.qq;
		this.showFaction = _o_.showFaction;
		this.showFriend = _o_.showFriend;
		this.showCompany = _o_.showCompany;
		this.honorCure = _o_.honorCure;
		this.randomFaction = _o_.randomFaction;
		this.selectWeaponId = _o_.selectWeaponId;
		this.dailyCommanderSkillUseReward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyCommanderSkillUseReward.forEach((_k_, _v_) -> this.dailyCommanderSkillUseReward.put(_k_, _v_));
		this.talentRecvEndTime = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.talentRecvEndTime.forEach((_k_, _v_) -> this.talentRecvEndTime.put(_k_, _v_));
		this.lastUpdateHold = _o_.lastUpdateHold;
		this.adjustCoin = _o_.adjustCoin;
		this.luckyCoin = _o_.luckyCoin;
		this.transportTargetArea = _o_.transportTargetArea;
		this.fragCoinMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.fragCoinMap.forEach((_k_, _v_) -> this.fragCoinMap.put(_k_, _v_));
		this.autoModeUseMill = _o_.autoModeUseMill;
		this.twoRockerModeUseMill = _o_.twoRockerModeUseMill;
	}

	public void copyFrom(Player _o_) {
        this.pid = _o_.pid;
        this.uid = _o_.uid;
        this.name = _o_.name;
        this.sex = _o_.sex;
        this.nation = _o_.nation;
        this.hair = _o_.hair;
        this.bodyid = _o_.bodyid;
        this.gold = _o_.gold;
        this.diamond = _o_.diamond;
        this.honor = _o_.honor;
        this.talent = _o_.talent;
        this.ihonor = _o_.ihonor;
        this.point = _o_.point;
        this.warMakes = _o_.warMakes;
        this.exp = _o_.exp;
        this.token = _o_.token;
        this.enterd = _o_.enterd;
        this.cread = _o_.cread;
        this.messd = _o_.messd;
        this.outd = _o_.outd;
        this.wpos = _o_.wpos;
        this.supplyPos = _o_.supplyPos;
        this.bagLoad = _o_.bagLoad;
        this.skilledHurt = _o_.skilledHurt;
        this.init = _o_.init;
        this.lastRefresh = _o_.lastRefresh;
        this.buyMakeCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.buyMakeCount.forEach((_k_, _v_) -> this.buyMakeCount.put(_k_, _v_));
        this.platinum = _o_.platinum;
        this.loginDay = _o_.loginDay;
        this.loginContinueDay = _o_.loginContinueDay;
        this.loginCount = _o_.loginCount;
        this.rmbSum = _o_.rmbSum;
        this.rmbCount = _o_.rmbCount;
        this.firstOrderTime = _o_.firstOrderTime;
        this.firstOrderId = _o_.firstOrderId;
        this.lastOrderTime = _o_.lastOrderTime;
        this.dailyHonorMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.dailyHonorMap.forEach((_k_, _v_) -> this.dailyHonorMap.put(_k_, _v_));
        this.nextCanTeleportTime = _o_.nextCanTeleportTime;
        this.gentesCoin = _o_.gentesCoin;
        this.defenseItemSrl = _o_.defenseItemSrl;
        this.defenseWpos = _o_.defenseWpos;
        this.nameChangeCount = _o_.nameChangeCount;
        this.avatar = _o_.avatar;
        this.lastTeleNpc = _o_.lastTeleNpc;
        this.lastExceptionTime = _o_.lastExceptionTime;
        this.lastBackHomeTime = _o_.lastBackHomeTime;
        this.techPoint = _o_.techPoint;
        this.mentorPoint = _o_.mentorPoint;
        this.inviteGens = new java.util.concurrent.ConcurrentHashMap<Long, GensInviteInfo>();
		_o_.inviteGens.forEach((_k_, _v_) -> this.inviteGens.put(_k_, new GensInviteInfo(_v_)));
        this.suppliesCoin = _o_.suppliesCoin;
        this.exploit = _o_.exploit;
        this.heroMedal = _o_.heroMedal;
        this.faction = _o_.faction;
        this.dailyUseCommanderCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyUseCommanderCount.forEach((_k_, _v_) -> this.dailyUseCommanderCount.put(_k_, _v_));
        this.totalOnline = _o_.totalOnline;
        this.newSupplyCount = _o_.newSupplyCount;
        this.newSupplyLastTime = _o_.newSupplyLastTime;
        this.perfectTime = _o_.perfectTime;
        this.resCoinMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.resCoinMap.forEach((_k_, _v_) -> this.resCoinMap.put(_k_, _v_));
        this.historyCoinMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.historyCoinMap.forEach((_k_, _v_) -> this.historyCoinMap.put(_k_, _v_));
        this.commanderSkillNextTime = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.commanderSkillNextTime.forEach((_k_, _v_) -> this.commanderSkillNextTime.put(_k_, _v_));
        this.usedTalent = _o_.usedTalent;
        this.energy = _o_.energy;
        this.dailyTrumpCount = _o_.dailyTrumpCount;
        this.wildDeathCount = _o_.wildDeathCount;
        this.lastWildDeathTime = _o_.lastWildDeathTime;
        this.qq = _o_.qq;
        this.showFaction = _o_.showFaction;
        this.showFriend = _o_.showFriend;
        this.showCompany = _o_.showCompany;
        this.honorCure = _o_.honorCure;
        this.randomFaction = _o_.randomFaction;
        this.selectWeaponId = _o_.selectWeaponId;
        this.dailyCommanderSkillUseReward = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyCommanderSkillUseReward.forEach((_k_, _v_) -> this.dailyCommanderSkillUseReward.put(_k_, _v_));
        this.talentRecvEndTime = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.talentRecvEndTime.forEach((_k_, _v_) -> this.talentRecvEndTime.put(_k_, _v_));
        this.lastUpdateHold = _o_.lastUpdateHold;
        this.adjustCoin = _o_.adjustCoin;
        this.luckyCoin = _o_.luckyCoin;
        this.transportTargetArea = _o_.transportTargetArea;
        this.fragCoinMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.fragCoinMap.forEach((_k_, _v_) -> this.fragCoinMap.put(_k_, _v_));
        this.autoModeUseMill = _o_.autoModeUseMill;
        this.twoRockerModeUseMill = _o_.twoRockerModeUseMill;
	}


	@JSONField(name = "1")
	public long getPid(){
		return this.pid;
	}

	@JSONField(name = "2")
	public String getUid(){
		return this.uid;
	}

	@JSONField(name = "3")
	public String getName(){
		return this.name;
	}

	@JSONField(name = "4")
	public int getSex(){
		return this.sex;
	}

	@JSONField(name = "5")
	public int getNation(){
		return this.nation;
	}

	@JSONField(name = "6")
	public int getHair(){
		return this.hair;
	}

	@JSONField(name = "7")
	public int getBodyid(){
		return this.bodyid;
	}

	@JSONField(name = "8")
	public long getGold(){
		return this.gold;
	}

	@JSONField(name = "9")
	public long getDiamond(){
		return this.diamond;
	}

	@JSONField(name = "10")
	public long getHonor(){
		return this.honor;
	}

	@JSONField(name = "11")
	public long getTalent(){
		return this.talent;
	}

	@JSONField(name = "12")
	public long getIhonor(){
		return this.ihonor;
	}

	@JSONField(name = "13")
	public long getPoint(){
		return this.point;
	}

	@JSONField(name = "14")
	public long getWarMakes(){
		return this.warMakes;
	}

	@JSONField(name = "15")
	public int getExp(){
		return this.exp;
	}

	@JSONField(name = "17")
	public long getToken(){
		return this.token;
	}

	@JSONField(name = "18")
	public long getEnterd(){
		return this.enterd;
	}

	@JSONField(name = "19")
	public long getCread(){
		return this.cread;
	}

	@JSONField(name = "21")
	public long getMessd(){
		return this.messd;
	}

	@JSONField(name = "22")
	public long getOutd(){
		return this.outd;
	}

	@JSONField(name = "23")
	public int getWpos(){
		return this.wpos;
	}

	@JSONField(name = "24")
	public int getSupplyPos(){
		return this.supplyPos;
	}

	@JSONField(name = "25")
	public int getBagLoad(){
		return this.bagLoad;
	}

	@JSONField(name = "26")
	public int getSkilledHurt(){
		return this.skilledHurt;
	}

	@JSONField(name = "27")
	public boolean getInit(){
		return this.init;
	}

	@JSONField(name = "30")
	public long getLastRefresh(){
		return this.lastRefresh;
	}

	@JSONField(name = "31")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getBuyMakeCount(){
		return this.buyMakeCount;
	}

	@JSONField(name = "33")
	public long getPlatinum(){
		return this.platinum;
	}

	@JSONField(name = "34")
	public int getLoginDay(){
		return this.loginDay;
	}

	@JSONField(name = "35")
	public int getLoginContinueDay(){
		return this.loginContinueDay;
	}

	@JSONField(name = "36")
	public long getLoginCount(){
		return this.loginCount;
	}

	@JSONField(name = "37")
	public int getRmbSum(){
		return this.rmbSum;
	}

	@JSONField(name = "38")
	public int getRmbCount(){
		return this.rmbCount;
	}

	@JSONField(name = "39")
	public long getFirstOrderTime(){
		return this.firstOrderTime;
	}

	@JSONField(name = "40")
	public String getFirstOrderId(){
		return this.firstOrderId;
	}

	@JSONField(name = "41")
	public long getLastOrderTime(){
		return this.lastOrderTime;
	}

	@JSONField(name = "44")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getDailyHonorMap(){
		return this.dailyHonorMap;
	}

	@JSONField(name = "43")
	public long getNextCanTeleportTime(){
		return this.nextCanTeleportTime;
	}

	@JSONField(name = "46")
	public long getGentesCoin(){
		return this.gentesCoin;
	}

	@JSONField(name = "47")
	public long getDefenseItemSrl(){
		return this.defenseItemSrl;
	}

	@JSONField(name = "48")
	public int getDefenseWpos(){
		return this.defenseWpos;
	}

	@JSONField(name = "49")
	public int getNameChangeCount(){
		return this.nameChangeCount;
	}

	@JSONField(name = "50")
	public int getAvatar(){
		return this.avatar;
	}

	@JSONField(name = "51")
	public int getLastTeleNpc(){
		return this.lastTeleNpc;
	}

	@JSONField(name = "52")
	public long getLastExceptionTime(){
		return this.lastExceptionTime;
	}

	@JSONField(name = "53")
	public long getLastBackHomeTime(){
		return this.lastBackHomeTime;
	}

	@JSONField(name = "54")
	public long getTechPoint(){
		return this.techPoint;
	}

	@JSONField(name = "55")
	public long getMentorPoint(){
		return this.mentorPoint;
	}

	@JSONField(name = "56")
	public java.util.concurrent.ConcurrentHashMap<Long, GensInviteInfo> getInviteGens(){
		return this.inviteGens;
	}

	@JSONField(name = "57")
	public long getSuppliesCoin(){
		return this.suppliesCoin;
	}

	@JSONField(name = "60")
	public long getExploit(){
		return this.exploit;
	}

	@JSONField(name = "61")
	public long getHeroMedal(){
		return this.heroMedal;
	}

	@JSONField(name = "62")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "63")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDailyUseCommanderCount(){
		return this.dailyUseCommanderCount;
	}

	@JSONField(name = "64")
	public long getTotalOnline(){
		return this.totalOnline;
	}

	@JSONField(name = "65")
	public int getNewSupplyCount(){
		return this.newSupplyCount;
	}

	@JSONField(name = "66")
	public long getNewSupplyLastTime(){
		return this.newSupplyLastTime;
	}

	@JSONField(name = "67")
	public long getPerfectTime(){
		return this.perfectTime;
	}

	@JSONField(name = "68")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getResCoinMap(){
		return this.resCoinMap;
	}

	@JSONField(name = "71")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getHistoryCoinMap(){
		return this.historyCoinMap;
	}

	@JSONField(name = "69")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getCommanderSkillNextTime(){
		return this.commanderSkillNextTime;
	}

	@JSONField(name = "70")
	public long getUsedTalent(){
		return this.usedTalent;
	}

	@JSONField(name = "75")
	public long getEnergy(){
		return this.energy;
	}

	@JSONField(name = "76")
	public int getDailyTrumpCount(){
		return this.dailyTrumpCount;
	}

	@JSONField(name = "77")
	public int getWildDeathCount(){
		return this.wildDeathCount;
	}

	@JSONField(name = "78")
	public long getLastWildDeathTime(){
		return this.lastWildDeathTime;
	}

	@JSONField(name = "79")
	public String getQq(){
		return this.qq;
	}

	@JSONField(name = "80")
	public boolean getShowFaction(){
		return this.showFaction;
	}

	@JSONField(name = "81")
	public boolean getShowFriend(){
		return this.showFriend;
	}

	@JSONField(name = "82")
	public boolean getShowCompany(){
		return this.showCompany;
	}

	@JSONField(name = "83")
	public int getHonorCure(){
		return this.honorCure;
	}

	@JSONField(name = "84")
	public boolean getRandomFaction(){
		return this.randomFaction;
	}

	@JSONField(name = "85")
	public int getSelectWeaponId(){
		return this.selectWeaponId;
	}

	@JSONField(name = "86")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDailyCommanderSkillUseReward(){
		return this.dailyCommanderSkillUseReward;
	}

	@JSONField(name = "87")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getTalentRecvEndTime(){
		return this.talentRecvEndTime;
	}

	@JSONField(name = "88")
	public long getLastUpdateHold(){
		return this.lastUpdateHold;
	}

	@JSONField(name = "90")
	public long getAdjustCoin(){
		return this.adjustCoin;
	}

	@JSONField(name = "91")
	public long getLuckyCoin(){
		return this.luckyCoin;
	}

	@JSONField(name = "93")
	public int getTransportTargetArea(){
		return this.transportTargetArea;
	}

	@JSONField(name = "94")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getFragCoinMap(){
		return this.fragCoinMap;
	}

	@JSONField(name = "97")
	public long getAutoModeUseMill(){
		return this.autoModeUseMill;
	}

	@JSONField(name = "98")
	public long getTwoRockerModeUseMill(){
		return this.twoRockerModeUseMill;
	}

	@JSONField(name = "1")
	public void setPid(long _v_){
		this.pid = _v_;
	}

	@JSONField(name = "2")
	public void setUid(String _v_){
		this.uid = _v_;
	}

	@JSONField(name = "3")
	public void setName(String _v_){
		this.name = _v_;
	}

	@JSONField(name = "4")
	public void setSex(int _v_){
		this.sex = _v_;
	}

	@JSONField(name = "5")
	public void setNation(int _v_){
		this.nation = _v_;
	}

	@JSONField(name = "6")
	public void setHair(int _v_){
		this.hair = _v_;
	}

	@JSONField(name = "7")
	public void setBodyid(int _v_){
		this.bodyid = _v_;
	}

	@JSONField(name = "8")
	public void setGold(long _v_){
		this.gold = _v_;
	}

	@JSONField(name = "9")
	public void setDiamond(long _v_){
		this.diamond = _v_;
	}

	@JSONField(name = "10")
	public void setHonor(long _v_){
		this.honor = _v_;
	}

	@JSONField(name = "11")
	public void setTalent(long _v_){
		this.talent = _v_;
	}

	@JSONField(name = "12")
	public void setIhonor(long _v_){
		this.ihonor = _v_;
	}

	@JSONField(name = "13")
	public void setPoint(long _v_){
		this.point = _v_;
	}

	@JSONField(name = "14")
	public void setWarMakes(long _v_){
		this.warMakes = _v_;
	}

	@JSONField(name = "15")
	public void setExp(int _v_){
		this.exp = _v_;
	}

	@JSONField(name = "17")
	public void setToken(long _v_){
		this.token = _v_;
	}

	@JSONField(name = "18")
	public void setEnterd(long _v_){
		this.enterd = _v_;
	}

	@JSONField(name = "19")
	public void setCread(long _v_){
		this.cread = _v_;
	}

	@JSONField(name = "21")
	public void setMessd(long _v_){
		this.messd = _v_;
	}

	@JSONField(name = "22")
	public void setOutd(long _v_){
		this.outd = _v_;
	}

	@JSONField(name = "23")
	public void setWpos(int _v_){
		this.wpos = _v_;
	}

	@JSONField(name = "24")
	public void setSupplyPos(int _v_){
		this.supplyPos = _v_;
	}

	@JSONField(name = "25")
	public void setBagLoad(int _v_){
		this.bagLoad = _v_;
	}

	@JSONField(name = "26")
	public void setSkilledHurt(int _v_){
		this.skilledHurt = _v_;
	}

	@JSONField(name = "27")
	public void setInit(boolean _v_){
		this.init = _v_;
	}

	@JSONField(name = "30")
	public void setLastRefresh(long _v_){
		this.lastRefresh = _v_;
	}

	@JSONField(name = "31")
	public void setBuyMakeCount(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.buyMakeCount = _v_;
	}

	@JSONField(name = "33")
	public void setPlatinum(long _v_){
		this.platinum = _v_;
	}

	@JSONField(name = "34")
	public void setLoginDay(int _v_){
		this.loginDay = _v_;
	}

	@JSONField(name = "35")
	public void setLoginContinueDay(int _v_){
		this.loginContinueDay = _v_;
	}

	@JSONField(name = "36")
	public void setLoginCount(long _v_){
		this.loginCount = _v_;
	}

	@JSONField(name = "37")
	public void setRmbSum(int _v_){
		this.rmbSum = _v_;
	}

	@JSONField(name = "38")
	public void setRmbCount(int _v_){
		this.rmbCount = _v_;
	}

	@JSONField(name = "39")
	public void setFirstOrderTime(long _v_){
		this.firstOrderTime = _v_;
	}

	@JSONField(name = "40")
	public void setFirstOrderId(String _v_){
		this.firstOrderId = _v_;
	}

	@JSONField(name = "41")
	public void setLastOrderTime(long _v_){
		this.lastOrderTime = _v_;
	}

	@JSONField(name = "44")
	public void setDailyHonorMap(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.dailyHonorMap = _v_;
	}

	@JSONField(name = "43")
	public void setNextCanTeleportTime(long _v_){
		this.nextCanTeleportTime = _v_;
	}

	@JSONField(name = "46")
	public void setGentesCoin(long _v_){
		this.gentesCoin = _v_;
	}

	@JSONField(name = "47")
	public void setDefenseItemSrl(long _v_){
		this.defenseItemSrl = _v_;
	}

	@JSONField(name = "48")
	public void setDefenseWpos(int _v_){
		this.defenseWpos = _v_;
	}

	@JSONField(name = "49")
	public void setNameChangeCount(int _v_){
		this.nameChangeCount = _v_;
	}

	@JSONField(name = "50")
	public void setAvatar(int _v_){
		this.avatar = _v_;
	}

	@JSONField(name = "51")
	public void setLastTeleNpc(int _v_){
		this.lastTeleNpc = _v_;
	}

	@JSONField(name = "52")
	public void setLastExceptionTime(long _v_){
		this.lastExceptionTime = _v_;
	}

	@JSONField(name = "53")
	public void setLastBackHomeTime(long _v_){
		this.lastBackHomeTime = _v_;
	}

	@JSONField(name = "54")
	public void setTechPoint(long _v_){
		this.techPoint = _v_;
	}

	@JSONField(name = "55")
	public void setMentorPoint(long _v_){
		this.mentorPoint = _v_;
	}

	@JSONField(name = "56")
	public void setInviteGens(java.util.concurrent.ConcurrentHashMap<Long, GensInviteInfo> _v_){
		this.inviteGens = _v_;
	}

	@JSONField(name = "57")
	public void setSuppliesCoin(long _v_){
		this.suppliesCoin = _v_;
	}

	@JSONField(name = "60")
	public void setExploit(long _v_){
		this.exploit = _v_;
	}

	@JSONField(name = "61")
	public void setHeroMedal(long _v_){
		this.heroMedal = _v_;
	}

	@JSONField(name = "62")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "63")
	public void setDailyUseCommanderCount(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.dailyUseCommanderCount = _v_;
	}

	@JSONField(name = "64")
	public void setTotalOnline(long _v_){
		this.totalOnline = _v_;
	}

	@JSONField(name = "65")
	public void setNewSupplyCount(int _v_){
		this.newSupplyCount = _v_;
	}

	@JSONField(name = "66")
	public void setNewSupplyLastTime(long _v_){
		this.newSupplyLastTime = _v_;
	}

	@JSONField(name = "67")
	public void setPerfectTime(long _v_){
		this.perfectTime = _v_;
	}

	@JSONField(name = "68")
	public void setResCoinMap(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.resCoinMap = _v_;
	}

	@JSONField(name = "71")
	public void setHistoryCoinMap(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.historyCoinMap = _v_;
	}

	@JSONField(name = "69")
	public void setCommanderSkillNextTime(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.commanderSkillNextTime = _v_;
	}

	@JSONField(name = "70")
	public void setUsedTalent(long _v_){
		this.usedTalent = _v_;
	}

	@JSONField(name = "75")
	public void setEnergy(long _v_){
		this.energy = _v_;
	}

	@JSONField(name = "76")
	public void setDailyTrumpCount(int _v_){
		this.dailyTrumpCount = _v_;
	}

	@JSONField(name = "77")
	public void setWildDeathCount(int _v_){
		this.wildDeathCount = _v_;
	}

	@JSONField(name = "78")
	public void setLastWildDeathTime(long _v_){
		this.lastWildDeathTime = _v_;
	}

	@JSONField(name = "79")
	public void setQq(String _v_){
		this.qq = _v_;
	}

	@JSONField(name = "80")
	public void setShowFaction(boolean _v_){
		this.showFaction = _v_;
	}

	@JSONField(name = "81")
	public void setShowFriend(boolean _v_){
		this.showFriend = _v_;
	}

	@JSONField(name = "82")
	public void setShowCompany(boolean _v_){
		this.showCompany = _v_;
	}

	@JSONField(name = "83")
	public void setHonorCure(int _v_){
		this.honorCure = _v_;
	}

	@JSONField(name = "84")
	public void setRandomFaction(boolean _v_){
		this.randomFaction = _v_;
	}

	@JSONField(name = "85")
	public void setSelectWeaponId(int _v_){
		this.selectWeaponId = _v_;
	}

	@JSONField(name = "86")
	public void setDailyCommanderSkillUseReward(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.dailyCommanderSkillUseReward = _v_;
	}

	@JSONField(name = "87")
	public void setTalentRecvEndTime(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.talentRecvEndTime = _v_;
	}

	@JSONField(name = "88")
	public void setLastUpdateHold(long _v_){
		this.lastUpdateHold = _v_;
	}

	@JSONField(name = "90")
	public void setAdjustCoin(long _v_){
		this.adjustCoin = _v_;
	}

	@JSONField(name = "91")
	public void setLuckyCoin(long _v_){
		this.luckyCoin = _v_;
	}

	@JSONField(name = "93")
	public void setTransportTargetArea(int _v_){
		this.transportTargetArea = _v_;
	}

	@JSONField(name = "94")
	public void setFragCoinMap(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.fragCoinMap = _v_;
	}

	@JSONField(name = "97")
	public void setAutoModeUseMill(long _v_){
		this.autoModeUseMill = _v_;
	}

	@JSONField(name = "98")
	public void setTwoRockerModeUseMill(long _v_){
		this.twoRockerModeUseMill = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Player) {
			Player _o_ = (Player)_o1_;
			if (this.pid != _o_.pid) return false;
			if (!this.uid.equals(_o_.uid)) return false;
			if (!this.name.equals(_o_.name)) return false;
			if (this.sex != _o_.sex) return false;
			if (this.nation != _o_.nation) return false;
			if (this.hair != _o_.hair) return false;
			if (this.bodyid != _o_.bodyid) return false;
			if (this.gold != _o_.gold) return false;
			if (this.diamond != _o_.diamond) return false;
			if (this.honor != _o_.honor) return false;
			if (this.talent != _o_.talent) return false;
			if (this.ihonor != _o_.ihonor) return false;
			if (this.point != _o_.point) return false;
			if (this.warMakes != _o_.warMakes) return false;
			if (this.exp != _o_.exp) return false;
			if (this.token != _o_.token) return false;
			if (this.enterd != _o_.enterd) return false;
			if (this.cread != _o_.cread) return false;
			if (this.messd != _o_.messd) return false;
			if (this.outd != _o_.outd) return false;
			if (this.wpos != _o_.wpos) return false;
			if (this.supplyPos != _o_.supplyPos) return false;
			if (this.bagLoad != _o_.bagLoad) return false;
			if (this.skilledHurt != _o_.skilledHurt) return false;
			if (this.init != _o_.init) return false;
			if (this.lastRefresh != _o_.lastRefresh) return false;
			if (!this.buyMakeCount.equals(_o_.buyMakeCount)) return false;
			if (this.platinum != _o_.platinum) return false;
			if (this.loginDay != _o_.loginDay) return false;
			if (this.loginContinueDay != _o_.loginContinueDay) return false;
			if (this.loginCount != _o_.loginCount) return false;
			if (this.rmbSum != _o_.rmbSum) return false;
			if (this.rmbCount != _o_.rmbCount) return false;
			if (this.firstOrderTime != _o_.firstOrderTime) return false;
			if (!this.firstOrderId.equals(_o_.firstOrderId)) return false;
			if (this.lastOrderTime != _o_.lastOrderTime) return false;
			if (!this.dailyHonorMap.equals(_o_.dailyHonorMap)) return false;
			if (this.nextCanTeleportTime != _o_.nextCanTeleportTime) return false;
			if (this.gentesCoin != _o_.gentesCoin) return false;
			if (this.defenseItemSrl != _o_.defenseItemSrl) return false;
			if (this.defenseWpos != _o_.defenseWpos) return false;
			if (this.nameChangeCount != _o_.nameChangeCount) return false;
			if (this.avatar != _o_.avatar) return false;
			if (this.lastTeleNpc != _o_.lastTeleNpc) return false;
			if (this.lastExceptionTime != _o_.lastExceptionTime) return false;
			if (this.lastBackHomeTime != _o_.lastBackHomeTime) return false;
			if (this.techPoint != _o_.techPoint) return false;
			if (this.mentorPoint != _o_.mentorPoint) return false;
			if (!this.inviteGens.equals(_o_.inviteGens)) return false;
			if (this.suppliesCoin != _o_.suppliesCoin) return false;
			if (this.exploit != _o_.exploit) return false;
			if (this.heroMedal != _o_.heroMedal) return false;
			if (this.faction != _o_.faction) return false;
			if (!this.dailyUseCommanderCount.equals(_o_.dailyUseCommanderCount)) return false;
			if (this.totalOnline != _o_.totalOnline) return false;
			if (this.newSupplyCount != _o_.newSupplyCount) return false;
			if (this.newSupplyLastTime != _o_.newSupplyLastTime) return false;
			if (this.perfectTime != _o_.perfectTime) return false;
			if (!this.resCoinMap.equals(_o_.resCoinMap)) return false;
			if (!this.historyCoinMap.equals(_o_.historyCoinMap)) return false;
			if (!this.commanderSkillNextTime.equals(_o_.commanderSkillNextTime)) return false;
			if (this.usedTalent != _o_.usedTalent) return false;
			if (this.energy != _o_.energy) return false;
			if (this.dailyTrumpCount != _o_.dailyTrumpCount) return false;
			if (this.wildDeathCount != _o_.wildDeathCount) return false;
			if (this.lastWildDeathTime != _o_.lastWildDeathTime) return false;
			if (!this.qq.equals(_o_.qq)) return false;
			if (this.showFaction != _o_.showFaction) return false;
			if (this.showFriend != _o_.showFriend) return false;
			if (this.showCompany != _o_.showCompany) return false;
			if (this.honorCure != _o_.honorCure) return false;
			if (this.randomFaction != _o_.randomFaction) return false;
			if (this.selectWeaponId != _o_.selectWeaponId) return false;
			if (!this.dailyCommanderSkillUseReward.equals(_o_.dailyCommanderSkillUseReward)) return false;
			if (!this.talentRecvEndTime.equals(_o_.talentRecvEndTime)) return false;
			if (this.lastUpdateHold != _o_.lastUpdateHold) return false;
			if (this.adjustCoin != _o_.adjustCoin) return false;
			if (this.luckyCoin != _o_.luckyCoin) return false;
			if (this.transportTargetArea != _o_.transportTargetArea) return false;
			if (!this.fragCoinMap.equals(_o_.fragCoinMap)) return false;
			if (this.autoModeUseMill != _o_.autoModeUseMill) return false;
			if (this.twoRockerModeUseMill != _o_.twoRockerModeUseMill) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(pid ^ (pid >>> 32));
		_h_ += _h_ * 31 + this.uid.hashCode();
		_h_ += _h_ * 31 + this.name.hashCode();
		_h_ += _h_ * 31 + (int)(this.sex);
		_h_ += _h_ * 31 + (int)(this.nation);
		_h_ += _h_ * 31 + (int)(this.hair);
		_h_ += _h_ * 31 + (int)(this.bodyid);
		_h_ += _h_ * 31 + (int)(gold ^ (gold >>> 32));
		_h_ += _h_ * 31 + (int)(diamond ^ (diamond >>> 32));
		_h_ += _h_ * 31 + (int)(honor ^ (honor >>> 32));
		_h_ += _h_ * 31 + (int)(talent ^ (talent >>> 32));
		_h_ += _h_ * 31 + (int)(ihonor ^ (ihonor >>> 32));
		_h_ += _h_ * 31 + (int)(point ^ (point >>> 32));
		_h_ += _h_ * 31 + (int)(warMakes ^ (warMakes >>> 32));
		_h_ += _h_ * 31 + (int)(this.exp);
		_h_ += _h_ * 31 + (int)(token ^ (token >>> 32));
		_h_ += _h_ * 31 + (int)(enterd ^ (enterd >>> 32));
		_h_ += _h_ * 31 + (int)(cread ^ (cread >>> 32));
		_h_ += _h_ * 31 + (int)(messd ^ (messd >>> 32));
		_h_ += _h_ * 31 + (int)(outd ^ (outd >>> 32));
		_h_ += _h_ * 31 + (int)(this.wpos);
		_h_ += _h_ * 31 + (int)(this.supplyPos);
		_h_ += _h_ * 31 + (int)(this.bagLoad);
		_h_ += _h_ * 31 + (int)(this.skilledHurt);
		_h_ += _h_ * 31 + (this.init ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(lastRefresh ^ (lastRefresh >>> 32));
		_h_ += _h_ * 31 + this.buyMakeCount.hashCode();
		_h_ += _h_ * 31 + (int)(platinum ^ (platinum >>> 32));
		_h_ += _h_ * 31 + (int)(this.loginDay);
		_h_ += _h_ * 31 + (int)(this.loginContinueDay);
		_h_ += _h_ * 31 + (int)(loginCount ^ (loginCount >>> 32));
		_h_ += _h_ * 31 + (int)(this.rmbSum);
		_h_ += _h_ * 31 + (int)(this.rmbCount);
		_h_ += _h_ * 31 + (int)(firstOrderTime ^ (firstOrderTime >>> 32));
		_h_ += _h_ * 31 + this.firstOrderId.hashCode();
		_h_ += _h_ * 31 + (int)(lastOrderTime ^ (lastOrderTime >>> 32));
		_h_ += _h_ * 31 + this.dailyHonorMap.hashCode();
		_h_ += _h_ * 31 + (int)(nextCanTeleportTime ^ (nextCanTeleportTime >>> 32));
		_h_ += _h_ * 31 + (int)(gentesCoin ^ (gentesCoin >>> 32));
		_h_ += _h_ * 31 + (int)(defenseItemSrl ^ (defenseItemSrl >>> 32));
		_h_ += _h_ * 31 + (int)(this.defenseWpos);
		_h_ += _h_ * 31 + (int)(this.nameChangeCount);
		_h_ += _h_ * 31 + (int)(this.avatar);
		_h_ += _h_ * 31 + (int)(this.lastTeleNpc);
		_h_ += _h_ * 31 + (int)(lastExceptionTime ^ (lastExceptionTime >>> 32));
		_h_ += _h_ * 31 + (int)(lastBackHomeTime ^ (lastBackHomeTime >>> 32));
		_h_ += _h_ * 31 + (int)(techPoint ^ (techPoint >>> 32));
		_h_ += _h_ * 31 + (int)(mentorPoint ^ (mentorPoint >>> 32));
		_h_ += _h_ * 31 + this.inviteGens.hashCode();
		_h_ += _h_ * 31 + (int)(suppliesCoin ^ (suppliesCoin >>> 32));
		_h_ += _h_ * 31 + (int)(exploit ^ (exploit >>> 32));
		_h_ += _h_ * 31 + (int)(heroMedal ^ (heroMedal >>> 32));
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + this.dailyUseCommanderCount.hashCode();
		_h_ += _h_ * 31 + (int)(totalOnline ^ (totalOnline >>> 32));
		_h_ += _h_ * 31 + (int)(this.newSupplyCount);
		_h_ += _h_ * 31 + (int)(newSupplyLastTime ^ (newSupplyLastTime >>> 32));
		_h_ += _h_ * 31 + (int)(perfectTime ^ (perfectTime >>> 32));
		_h_ += _h_ * 31 + this.resCoinMap.hashCode();
		_h_ += _h_ * 31 + this.historyCoinMap.hashCode();
		_h_ += _h_ * 31 + this.commanderSkillNextTime.hashCode();
		_h_ += _h_ * 31 + (int)(usedTalent ^ (usedTalent >>> 32));
		_h_ += _h_ * 31 + (int)(energy ^ (energy >>> 32));
		_h_ += _h_ * 31 + (int)(this.dailyTrumpCount);
		_h_ += _h_ * 31 + (int)(this.wildDeathCount);
		_h_ += _h_ * 31 + (int)(lastWildDeathTime ^ (lastWildDeathTime >>> 32));
		_h_ += _h_ * 31 + this.qq.hashCode();
		_h_ += _h_ * 31 + (this.showFaction ? 1231 : 1237);
		_h_ += _h_ * 31 + (this.showFriend ? 1231 : 1237);
		_h_ += _h_ * 31 + (this.showCompany ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.honorCure);
		_h_ += _h_ * 31 + (this.randomFaction ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.selectWeaponId);
		_h_ += _h_ * 31 + this.dailyCommanderSkillUseReward.hashCode();
		_h_ += _h_ * 31 + this.talentRecvEndTime.hashCode();
		_h_ += _h_ * 31 + (int)(lastUpdateHold ^ (lastUpdateHold >>> 32));
		_h_ += _h_ * 31 + (int)(adjustCoin ^ (adjustCoin >>> 32));
		_h_ += _h_ * 31 + (int)(luckyCoin ^ (luckyCoin >>> 32));
		_h_ += _h_ * 31 + (int)(this.transportTargetArea);
		_h_ += _h_ * 31 + this.fragCoinMap.hashCode();
		_h_ += _h_ * 31 + (int)(autoModeUseMill ^ (autoModeUseMill >>> 32));
		_h_ += _h_ * 31 + (int)(twoRockerModeUseMill ^ (twoRockerModeUseMill >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(pid).append(",");
		_sb_.append("T").append(uid.length()).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(sex).append(",");
		_sb_.append(nation).append(",");
		_sb_.append(hair).append(",");
		_sb_.append(bodyid).append(",");
		_sb_.append(gold).append(",");
		_sb_.append(diamond).append(",");
		_sb_.append(honor).append(",");
		_sb_.append(talent).append(",");
		_sb_.append(ihonor).append(",");
		_sb_.append(point).append(",");
		_sb_.append(warMakes).append(",");
		_sb_.append(exp).append(",");
		_sb_.append(token).append(",");
		_sb_.append(enterd).append(",");
		_sb_.append(cread).append(",");
		_sb_.append(messd).append(",");
		_sb_.append(outd).append(",");
		_sb_.append(wpos).append(",");
		_sb_.append(supplyPos).append(",");
		_sb_.append(bagLoad).append(",");
		_sb_.append(skilledHurt).append(",");
		_sb_.append(init).append(",");
		_sb_.append(lastRefresh).append(",");
		_sb_.append(buyMakeCount).append(",");
		_sb_.append(platinum).append(",");
		_sb_.append(loginDay).append(",");
		_sb_.append(loginContinueDay).append(",");
		_sb_.append(loginCount).append(",");
		_sb_.append(rmbSum).append(",");
		_sb_.append(rmbCount).append(",");
		_sb_.append(firstOrderTime).append(",");
		_sb_.append("T").append(firstOrderId.length()).append(",");
		_sb_.append(lastOrderTime).append(",");
		_sb_.append(dailyHonorMap).append(",");
		_sb_.append(nextCanTeleportTime).append(",");
		_sb_.append(gentesCoin).append(",");
		_sb_.append(defenseItemSrl).append(",");
		_sb_.append(defenseWpos).append(",");
		_sb_.append(nameChangeCount).append(",");
		_sb_.append(avatar).append(",");
		_sb_.append(lastTeleNpc).append(",");
		_sb_.append(lastExceptionTime).append(",");
		_sb_.append(lastBackHomeTime).append(",");
		_sb_.append(techPoint).append(",");
		_sb_.append(mentorPoint).append(",");
		_sb_.append(inviteGens).append(",");
		_sb_.append(suppliesCoin).append(",");
		_sb_.append(exploit).append(",");
		_sb_.append(heroMedal).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(dailyUseCommanderCount).append(",");
		_sb_.append(totalOnline).append(",");
		_sb_.append(newSupplyCount).append(",");
		_sb_.append(newSupplyLastTime).append(",");
		_sb_.append(perfectTime).append(",");
		_sb_.append(resCoinMap).append(",");
		_sb_.append(historyCoinMap).append(",");
		_sb_.append(commanderSkillNextTime).append(",");
		_sb_.append(usedTalent).append(",");
		_sb_.append(energy).append(",");
		_sb_.append(dailyTrumpCount).append(",");
		_sb_.append(wildDeathCount).append(",");
		_sb_.append(lastWildDeathTime).append(",");
		_sb_.append("T").append(qq.length()).append(",");
		_sb_.append(showFaction).append(",");
		_sb_.append(showFriend).append(",");
		_sb_.append(showCompany).append(",");
		_sb_.append(honorCure).append(",");
		_sb_.append(randomFaction).append(",");
		_sb_.append(selectWeaponId).append(",");
		_sb_.append(dailyCommanderSkillUseReward).append(",");
		_sb_.append(talentRecvEndTime).append(",");
		_sb_.append(lastUpdateHold).append(",");
		_sb_.append(adjustCoin).append(",");
		_sb_.append(luckyCoin).append(",");
		_sb_.append(transportTargetArea).append(",");
		_sb_.append(fragCoinMap).append(",");
		_sb_.append(autoModeUseMill).append(",");
		_sb_.append(twoRockerModeUseMill).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
