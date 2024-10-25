package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LgPlayerInfo extends warrior.mdb.XBean {
    private long pid;  // 玩家Id
    private String name;  // 玩家名称
    private int sex;  // 性别
    private int nation;  // 国家
    private int hair;  // 头部配置ID
    private int bodyid;  // 模型配置ID
    private int mlv;  // 本国军衔等级
    private String userId;  // userId
    private long gcd;  // 工会CD时间
    private int contribute;  // 贡献值
    private int dailyContrib;  // 每天公会贡献
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> dayDepotNum;  // 每天公会仓库已领取次数
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> weeklyReceived;  // 已经领取过的每周公会奖励Id和次数
    private long weeklyLastRefresh;  // 每周公会贡献值刷新时间
    private long dayLastRefresh;  // 每天公会仓库领取次数刷新
    private int avatar;  // 头像
    private java.util.concurrent.CopyOnWriteArrayList<Integer> takenGensQuestId;  // 已领取过的军团任务id
    private int dayReceiveSupplyNum;  // 每天领取军团补给的次数
    private int maxTalentType;  // 最高天赋类别
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> wildRewardTime;  // 上次领取区域奖励时间
    private boolean isCheckInDaily;  // 今天是否领取打卡奖励
    private int talentActiveType;  // 激活天赋类别
    private int faction;  // 阵营
    private long lastTrumpetTime;  // 上一次喇叭发送时间
    private int trumpetCombo;  // 喇叭连击数
    private int achieveNum;  // 成就次数
    private long lastFactionSalaryTime;  // 上一次领取阵营薪资时间
    private long honor;  // 荣誉
    private String qq; 
    private boolean showFaction;  // 是否阵营可见
    private boolean showFriend;  // 是否好友可见
    private boolean showCompany;  // 是否连队可见
    private long offline;  // 下线时间
    private java.util.concurrent.CopyOnWriteArraySet<Integer> monthlyCheckInInfo;  // 本月签到总数
    private java.util.concurrent.CopyOnWriteArraySet<Integer> monthlyTakenCheckInReward;  // 本月已领取签到奖励id
    private long monthlyLastRefresh; 
    private int maxTroopLead; 
    private int talentTotalLevel; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> holdSum;  // 武装持有量
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> carrierTypeNum;  // 载具持有类型量
    private boolean offlineAndLeaveScene;  // 是否已经离线并且离开地图了
    private boolean createInOrientationAct;  // 是否再迎新活动内创角
    private long create;  // 创角时间
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> talentLevelMap;  // 职业对应天赋的总等级
    private long troopImmureEndTime;  // 部队监禁结束时间
    private String troopImmureOperator;  // 部队监禁操作人

    LgPlayerInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.name = "";
		this.userId = "";
		this.dayDepotNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.weeklyReceived = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.takenGensQuestId = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		this.wildRewardTime = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		this.qq = "";
		this.monthlyCheckInInfo = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.monthlyTakenCheckInReward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.holdSum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.carrierTypeNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.talentLevelMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.troopImmureOperator = "";
	}

	public LgPlayerInfo() {
		this((warrior.mdb.XBean)null);
	}

	public LgPlayerInfo(LgPlayerInfo _o_) {
		this(_o_, null);
	}

	LgPlayerInfo(LgPlayerInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.pid = _o_.pid;
		this.name = _o_.name;
		this.sex = _o_.sex;
		this.nation = _o_.nation;
		this.hair = _o_.hair;
		this.bodyid = _o_.bodyid;
		this.mlv = _o_.mlv;
		this.userId = _o_.userId;
		this.gcd = _o_.gcd;
		this.contribute = _o_.contribute;
		this.dailyContrib = _o_.dailyContrib;
		this.dayDepotNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dayDepotNum.forEach((_k_, _v_) -> this.dayDepotNum.put(_k_, _v_));
		this.weeklyReceived = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.weeklyReceived.forEach((_k_, _v_) -> this.weeklyReceived.put(_k_, _v_));
		this.weeklyLastRefresh = _o_.weeklyLastRefresh;
		this.dayLastRefresh = _o_.dayLastRefresh;
		this.avatar = _o_.avatar;
		this.takenGensQuestId = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.takenGensQuestId.forEach(_v_ -> this.takenGensQuestId.add(_v_));
		this.dayReceiveSupplyNum = _o_.dayReceiveSupplyNum;
		this.maxTalentType = _o_.maxTalentType;
		this.wildRewardTime = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.wildRewardTime.forEach((_k_, _v_) -> this.wildRewardTime.put(_k_, _v_));
		this.isCheckInDaily = _o_.isCheckInDaily;
		this.talentActiveType = _o_.talentActiveType;
		this.faction = _o_.faction;
		this.lastTrumpetTime = _o_.lastTrumpetTime;
		this.trumpetCombo = _o_.trumpetCombo;
		this.achieveNum = _o_.achieveNum;
		this.lastFactionSalaryTime = _o_.lastFactionSalaryTime;
		this.honor = _o_.honor;
		this.qq = _o_.qq;
		this.showFaction = _o_.showFaction;
		this.showFriend = _o_.showFriend;
		this.showCompany = _o_.showCompany;
		this.offline = _o_.offline;
		this.monthlyCheckInInfo = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.monthlyCheckInInfo.forEach(_v_ -> this.monthlyCheckInInfo.add(_v_));
		this.monthlyTakenCheckInReward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.monthlyTakenCheckInReward.forEach(_v_ -> this.monthlyTakenCheckInReward.add(_v_));
		this.monthlyLastRefresh = _o_.monthlyLastRefresh;
		this.maxTroopLead = _o_.maxTroopLead;
		this.talentTotalLevel = _o_.talentTotalLevel;
		this.holdSum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.holdSum.forEach((_k_, _v_) -> this.holdSum.put(_k_, _v_));
		this.carrierTypeNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.carrierTypeNum.forEach((_k_, _v_) -> this.carrierTypeNum.put(_k_, _v_));
		this.offlineAndLeaveScene = _o_.offlineAndLeaveScene;
		this.createInOrientationAct = _o_.createInOrientationAct;
		this.create = _o_.create;
		this.talentLevelMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.talentLevelMap.forEach((_k_, _v_) -> this.talentLevelMap.put(_k_, _v_));
		this.troopImmureEndTime = _o_.troopImmureEndTime;
		this.troopImmureOperator = _o_.troopImmureOperator;
	}

	public void copyFrom(LgPlayerInfo _o_) {
        this.pid = _o_.pid;
        this.name = _o_.name;
        this.sex = _o_.sex;
        this.nation = _o_.nation;
        this.hair = _o_.hair;
        this.bodyid = _o_.bodyid;
        this.mlv = _o_.mlv;
        this.userId = _o_.userId;
        this.gcd = _o_.gcd;
        this.contribute = _o_.contribute;
        this.dailyContrib = _o_.dailyContrib;
        this.dayDepotNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dayDepotNum.forEach((_k_, _v_) -> this.dayDepotNum.put(_k_, _v_));
        this.weeklyReceived = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.weeklyReceived.forEach((_k_, _v_) -> this.weeklyReceived.put(_k_, _v_));
        this.weeklyLastRefresh = _o_.weeklyLastRefresh;
        this.dayLastRefresh = _o_.dayLastRefresh;
        this.avatar = _o_.avatar;
        this.takenGensQuestId = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.takenGensQuestId.forEach(_v_ -> this.takenGensQuestId.add(_v_));
        this.dayReceiveSupplyNum = _o_.dayReceiveSupplyNum;
        this.maxTalentType = _o_.maxTalentType;
        this.wildRewardTime = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.wildRewardTime.forEach((_k_, _v_) -> this.wildRewardTime.put(_k_, _v_));
        this.isCheckInDaily = _o_.isCheckInDaily;
        this.talentActiveType = _o_.talentActiveType;
        this.faction = _o_.faction;
        this.lastTrumpetTime = _o_.lastTrumpetTime;
        this.trumpetCombo = _o_.trumpetCombo;
        this.achieveNum = _o_.achieveNum;
        this.lastFactionSalaryTime = _o_.lastFactionSalaryTime;
        this.honor = _o_.honor;
        this.qq = _o_.qq;
        this.showFaction = _o_.showFaction;
        this.showFriend = _o_.showFriend;
        this.showCompany = _o_.showCompany;
        this.offline = _o_.offline;
        this.monthlyCheckInInfo = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.monthlyCheckInInfo.forEach(_v_ -> this.monthlyCheckInInfo.add(_v_));
        this.monthlyTakenCheckInReward = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.monthlyTakenCheckInReward.forEach(_v_ -> this.monthlyTakenCheckInReward.add(_v_));
        this.monthlyLastRefresh = _o_.monthlyLastRefresh;
        this.maxTroopLead = _o_.maxTroopLead;
        this.talentTotalLevel = _o_.talentTotalLevel;
        this.holdSum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.holdSum.forEach((_k_, _v_) -> this.holdSum.put(_k_, _v_));
        this.carrierTypeNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.carrierTypeNum.forEach((_k_, _v_) -> this.carrierTypeNum.put(_k_, _v_));
        this.offlineAndLeaveScene = _o_.offlineAndLeaveScene;
        this.createInOrientationAct = _o_.createInOrientationAct;
        this.create = _o_.create;
        this.talentLevelMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.talentLevelMap.forEach((_k_, _v_) -> this.talentLevelMap.put(_k_, _v_));
        this.troopImmureEndTime = _o_.troopImmureEndTime;
        this.troopImmureOperator = _o_.troopImmureOperator;
	}


	@JSONField(name = "1")
	public long getPid(){
		return this.pid;
	}

	@JSONField(name = "2")
	public String getName(){
		return this.name;
	}

	@JSONField(name = "3")
	public int getSex(){
		return this.sex;
	}

	@JSONField(name = "4")
	public int getNation(){
		return this.nation;
	}

	@JSONField(name = "5")
	public int getHair(){
		return this.hair;
	}

	@JSONField(name = "6")
	public int getBodyid(){
		return this.bodyid;
	}

	@JSONField(name = "7")
	public int getMlv(){
		return this.mlv;
	}

	@JSONField(name = "8")
	public String getUserId(){
		return this.userId;
	}

	@JSONField(name = "9")
	public long getGcd(){
		return this.gcd;
	}

	@JSONField(name = "10")
	public int getContribute(){
		return this.contribute;
	}

	@JSONField(name = "11")
	public int getDailyContrib(){
		return this.dailyContrib;
	}

	@JSONField(name = "12")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDayDepotNum(){
		return this.dayDepotNum;
	}

	@JSONField(name = "13")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getWeeklyReceived(){
		return this.weeklyReceived;
	}

	@JSONField(name = "14")
	public long getWeeklyLastRefresh(){
		return this.weeklyLastRefresh;
	}

	@JSONField(name = "15")
	public long getDayLastRefresh(){
		return this.dayLastRefresh;
	}

	@JSONField(name = "16")
	public int getAvatar(){
		return this.avatar;
	}

	@JSONField(name = "17")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getTakenGensQuestId(){
		return this.takenGensQuestId;
	}

	@JSONField(name = "18")
	public int getDayReceiveSupplyNum(){
		return this.dayReceiveSupplyNum;
	}

	@JSONField(name = "19")
	public int getMaxTalentType(){
		return this.maxTalentType;
	}

	@JSONField(name = "20")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getWildRewardTime(){
		return this.wildRewardTime;
	}

	@JSONField(name = "21")
	public boolean getIsCheckInDaily(){
		return this.isCheckInDaily;
	}

	@JSONField(name = "22")
	public int getTalentActiveType(){
		return this.talentActiveType;
	}

	@JSONField(name = "23")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "24")
	public long getLastTrumpetTime(){
		return this.lastTrumpetTime;
	}

	@JSONField(name = "25")
	public int getTrumpetCombo(){
		return this.trumpetCombo;
	}

	@JSONField(name = "26")
	public int getAchieveNum(){
		return this.achieveNum;
	}

	@JSONField(name = "28")
	public long getLastFactionSalaryTime(){
		return this.lastFactionSalaryTime;
	}

	@JSONField(name = "29")
	public long getHonor(){
		return this.honor;
	}

	@JSONField(name = "30")
	public String getQq(){
		return this.qq;
	}

	@JSONField(name = "31")
	public boolean getShowFaction(){
		return this.showFaction;
	}

	@JSONField(name = "32")
	public boolean getShowFriend(){
		return this.showFriend;
	}

	@JSONField(name = "33")
	public boolean getShowCompany(){
		return this.showCompany;
	}

	@JSONField(name = "34")
	public long getOffline(){
		return this.offline;
	}

	@JSONField(name = "35")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getMonthlyCheckInInfo(){
		return this.monthlyCheckInInfo;
	}

	@JSONField(name = "36")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getMonthlyTakenCheckInReward(){
		return this.monthlyTakenCheckInReward;
	}

	@JSONField(name = "37")
	public long getMonthlyLastRefresh(){
		return this.monthlyLastRefresh;
	}

	@JSONField(name = "38")
	public int getMaxTroopLead(){
		return this.maxTroopLead;
	}

	@JSONField(name = "39")
	public int getTalentTotalLevel(){
		return this.talentTotalLevel;
	}

	@JSONField(name = "40")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getHoldSum(){
		return this.holdSum;
	}

	@JSONField(name = "41")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getCarrierTypeNum(){
		return this.carrierTypeNum;
	}

	@JSONField(name = "43")
	public boolean getOfflineAndLeaveScene(){
		return this.offlineAndLeaveScene;
	}

	@JSONField(name = "44")
	public boolean getCreateInOrientationAct(){
		return this.createInOrientationAct;
	}

	@JSONField(name = "45")
	public long getCreate(){
		return this.create;
	}

	@JSONField(name = "46")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getTalentLevelMap(){
		return this.talentLevelMap;
	}

	@JSONField(name = "47")
	public long getTroopImmureEndTime(){
		return this.troopImmureEndTime;
	}

	@JSONField(name = "48")
	public String getTroopImmureOperator(){
		return this.troopImmureOperator;
	}

	@JSONField(name = "1")
	public void setPid(long _v_){
		this.pid = _v_;
	}

	@JSONField(name = "2")
	public void setName(String _v_){
		this.name = _v_;
	}

	@JSONField(name = "3")
	public void setSex(int _v_){
		this.sex = _v_;
	}

	@JSONField(name = "4")
	public void setNation(int _v_){
		this.nation = _v_;
	}

	@JSONField(name = "5")
	public void setHair(int _v_){
		this.hair = _v_;
	}

	@JSONField(name = "6")
	public void setBodyid(int _v_){
		this.bodyid = _v_;
	}

	@JSONField(name = "7")
	public void setMlv(int _v_){
		this.mlv = _v_;
	}

	@JSONField(name = "8")
	public void setUserId(String _v_){
		this.userId = _v_;
	}

	@JSONField(name = "9")
	public void setGcd(long _v_){
		this.gcd = _v_;
	}

	@JSONField(name = "10")
	public void setContribute(int _v_){
		this.contribute = _v_;
	}

	@JSONField(name = "11")
	public void setDailyContrib(int _v_){
		this.dailyContrib = _v_;
	}

	@JSONField(name = "12")
	public void setDayDepotNum(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.dayDepotNum = _v_;
	}

	@JSONField(name = "13")
	public void setWeeklyReceived(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.weeklyReceived = _v_;
	}

	@JSONField(name = "14")
	public void setWeeklyLastRefresh(long _v_){
		this.weeklyLastRefresh = _v_;
	}

	@JSONField(name = "15")
	public void setDayLastRefresh(long _v_){
		this.dayLastRefresh = _v_;
	}

	@JSONField(name = "16")
	public void setAvatar(int _v_){
		this.avatar = _v_;
	}

	@JSONField(name = "17")
	public void setTakenGensQuestId(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.takenGensQuestId = _v_;
	}

	@JSONField(name = "18")
	public void setDayReceiveSupplyNum(int _v_){
		this.dayReceiveSupplyNum = _v_;
	}

	@JSONField(name = "19")
	public void setMaxTalentType(int _v_){
		this.maxTalentType = _v_;
	}

	@JSONField(name = "20")
	public void setWildRewardTime(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.wildRewardTime = _v_;
	}

	@JSONField(name = "21")
	public void setIsCheckInDaily(boolean _v_){
		this.isCheckInDaily = _v_;
	}

	@JSONField(name = "22")
	public void setTalentActiveType(int _v_){
		this.talentActiveType = _v_;
	}

	@JSONField(name = "23")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "24")
	public void setLastTrumpetTime(long _v_){
		this.lastTrumpetTime = _v_;
	}

	@JSONField(name = "25")
	public void setTrumpetCombo(int _v_){
		this.trumpetCombo = _v_;
	}

	@JSONField(name = "26")
	public void setAchieveNum(int _v_){
		this.achieveNum = _v_;
	}

	@JSONField(name = "28")
	public void setLastFactionSalaryTime(long _v_){
		this.lastFactionSalaryTime = _v_;
	}

	@JSONField(name = "29")
	public void setHonor(long _v_){
		this.honor = _v_;
	}

	@JSONField(name = "30")
	public void setQq(String _v_){
		this.qq = _v_;
	}

	@JSONField(name = "31")
	public void setShowFaction(boolean _v_){
		this.showFaction = _v_;
	}

	@JSONField(name = "32")
	public void setShowFriend(boolean _v_){
		this.showFriend = _v_;
	}

	@JSONField(name = "33")
	public void setShowCompany(boolean _v_){
		this.showCompany = _v_;
	}

	@JSONField(name = "34")
	public void setOffline(long _v_){
		this.offline = _v_;
	}

	@JSONField(name = "35")
	public void setMonthlyCheckInInfo(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.monthlyCheckInInfo = _v_;
	}

	@JSONField(name = "36")
	public void setMonthlyTakenCheckInReward(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.monthlyTakenCheckInReward = _v_;
	}

	@JSONField(name = "37")
	public void setMonthlyLastRefresh(long _v_){
		this.monthlyLastRefresh = _v_;
	}

	@JSONField(name = "38")
	public void setMaxTroopLead(int _v_){
		this.maxTroopLead = _v_;
	}

	@JSONField(name = "39")
	public void setTalentTotalLevel(int _v_){
		this.talentTotalLevel = _v_;
	}

	@JSONField(name = "40")
	public void setHoldSum(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.holdSum = _v_;
	}

	@JSONField(name = "41")
	public void setCarrierTypeNum(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.carrierTypeNum = _v_;
	}

	@JSONField(name = "43")
	public void setOfflineAndLeaveScene(boolean _v_){
		this.offlineAndLeaveScene = _v_;
	}

	@JSONField(name = "44")
	public void setCreateInOrientationAct(boolean _v_){
		this.createInOrientationAct = _v_;
	}

	@JSONField(name = "45")
	public void setCreate(long _v_){
		this.create = _v_;
	}

	@JSONField(name = "46")
	public void setTalentLevelMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.talentLevelMap = _v_;
	}

	@JSONField(name = "47")
	public void setTroopImmureEndTime(long _v_){
		this.troopImmureEndTime = _v_;
	}

	@JSONField(name = "48")
	public void setTroopImmureOperator(String _v_){
		this.troopImmureOperator = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LgPlayerInfo) {
			LgPlayerInfo _o_ = (LgPlayerInfo)_o1_;
			if (this.pid != _o_.pid) return false;
			if (!this.name.equals(_o_.name)) return false;
			if (this.sex != _o_.sex) return false;
			if (this.nation != _o_.nation) return false;
			if (this.hair != _o_.hair) return false;
			if (this.bodyid != _o_.bodyid) return false;
			if (this.mlv != _o_.mlv) return false;
			if (!this.userId.equals(_o_.userId)) return false;
			if (this.gcd != _o_.gcd) return false;
			if (this.contribute != _o_.contribute) return false;
			if (this.dailyContrib != _o_.dailyContrib) return false;
			if (!this.dayDepotNum.equals(_o_.dayDepotNum)) return false;
			if (!this.weeklyReceived.equals(_o_.weeklyReceived)) return false;
			if (this.weeklyLastRefresh != _o_.weeklyLastRefresh) return false;
			if (this.dayLastRefresh != _o_.dayLastRefresh) return false;
			if (this.avatar != _o_.avatar) return false;
			if (!this.takenGensQuestId.equals(_o_.takenGensQuestId)) return false;
			if (this.dayReceiveSupplyNum != _o_.dayReceiveSupplyNum) return false;
			if (this.maxTalentType != _o_.maxTalentType) return false;
			if (!this.wildRewardTime.equals(_o_.wildRewardTime)) return false;
			if (this.isCheckInDaily != _o_.isCheckInDaily) return false;
			if (this.talentActiveType != _o_.talentActiveType) return false;
			if (this.faction != _o_.faction) return false;
			if (this.lastTrumpetTime != _o_.lastTrumpetTime) return false;
			if (this.trumpetCombo != _o_.trumpetCombo) return false;
			if (this.achieveNum != _o_.achieveNum) return false;
			if (this.lastFactionSalaryTime != _o_.lastFactionSalaryTime) return false;
			if (this.honor != _o_.honor) return false;
			if (!this.qq.equals(_o_.qq)) return false;
			if (this.showFaction != _o_.showFaction) return false;
			if (this.showFriend != _o_.showFriend) return false;
			if (this.showCompany != _o_.showCompany) return false;
			if (this.offline != _o_.offline) return false;
			if (!this.monthlyCheckInInfo.equals(_o_.monthlyCheckInInfo)) return false;
			if (!this.monthlyTakenCheckInReward.equals(_o_.monthlyTakenCheckInReward)) return false;
			if (this.monthlyLastRefresh != _o_.monthlyLastRefresh) return false;
			if (this.maxTroopLead != _o_.maxTroopLead) return false;
			if (this.talentTotalLevel != _o_.talentTotalLevel) return false;
			if (!this.holdSum.equals(_o_.holdSum)) return false;
			if (!this.carrierTypeNum.equals(_o_.carrierTypeNum)) return false;
			if (this.offlineAndLeaveScene != _o_.offlineAndLeaveScene) return false;
			if (this.createInOrientationAct != _o_.createInOrientationAct) return false;
			if (this.create != _o_.create) return false;
			if (!this.talentLevelMap.equals(_o_.talentLevelMap)) return false;
			if (this.troopImmureEndTime != _o_.troopImmureEndTime) return false;
			if (!this.troopImmureOperator.equals(_o_.troopImmureOperator)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(pid ^ (pid >>> 32));
		_h_ += _h_ * 31 + this.name.hashCode();
		_h_ += _h_ * 31 + (int)(this.sex);
		_h_ += _h_ * 31 + (int)(this.nation);
		_h_ += _h_ * 31 + (int)(this.hair);
		_h_ += _h_ * 31 + (int)(this.bodyid);
		_h_ += _h_ * 31 + (int)(this.mlv);
		_h_ += _h_ * 31 + this.userId.hashCode();
		_h_ += _h_ * 31 + (int)(gcd ^ (gcd >>> 32));
		_h_ += _h_ * 31 + (int)(this.contribute);
		_h_ += _h_ * 31 + (int)(this.dailyContrib);
		_h_ += _h_ * 31 + this.dayDepotNum.hashCode();
		_h_ += _h_ * 31 + this.weeklyReceived.hashCode();
		_h_ += _h_ * 31 + (int)(weeklyLastRefresh ^ (weeklyLastRefresh >>> 32));
		_h_ += _h_ * 31 + (int)(dayLastRefresh ^ (dayLastRefresh >>> 32));
		_h_ += _h_ * 31 + (int)(this.avatar);
		_h_ += _h_ * 31 + this.takenGensQuestId.hashCode();
		_h_ += _h_ * 31 + (int)(this.dayReceiveSupplyNum);
		_h_ += _h_ * 31 + (int)(this.maxTalentType);
		_h_ += _h_ * 31 + this.wildRewardTime.hashCode();
		_h_ += _h_ * 31 + (this.isCheckInDaily ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.talentActiveType);
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + (int)(lastTrumpetTime ^ (lastTrumpetTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.trumpetCombo);
		_h_ += _h_ * 31 + (int)(this.achieveNum);
		_h_ += _h_ * 31 + (int)(lastFactionSalaryTime ^ (lastFactionSalaryTime >>> 32));
		_h_ += _h_ * 31 + (int)(honor ^ (honor >>> 32));
		_h_ += _h_ * 31 + this.qq.hashCode();
		_h_ += _h_ * 31 + (this.showFaction ? 1231 : 1237);
		_h_ += _h_ * 31 + (this.showFriend ? 1231 : 1237);
		_h_ += _h_ * 31 + (this.showCompany ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(offline ^ (offline >>> 32));
		_h_ += _h_ * 31 + this.monthlyCheckInInfo.hashCode();
		_h_ += _h_ * 31 + this.monthlyTakenCheckInReward.hashCode();
		_h_ += _h_ * 31 + (int)(monthlyLastRefresh ^ (monthlyLastRefresh >>> 32));
		_h_ += _h_ * 31 + (int)(this.maxTroopLead);
		_h_ += _h_ * 31 + (int)(this.talentTotalLevel);
		_h_ += _h_ * 31 + this.holdSum.hashCode();
		_h_ += _h_ * 31 + this.carrierTypeNum.hashCode();
		_h_ += _h_ * 31 + (this.offlineAndLeaveScene ? 1231 : 1237);
		_h_ += _h_ * 31 + (this.createInOrientationAct ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(create ^ (create >>> 32));
		_h_ += _h_ * 31 + this.talentLevelMap.hashCode();
		_h_ += _h_ * 31 + (int)(troopImmureEndTime ^ (troopImmureEndTime >>> 32));
		_h_ += _h_ * 31 + this.troopImmureOperator.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(pid).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(sex).append(",");
		_sb_.append(nation).append(",");
		_sb_.append(hair).append(",");
		_sb_.append(bodyid).append(",");
		_sb_.append(mlv).append(",");
		_sb_.append("T").append(userId.length()).append(",");
		_sb_.append(gcd).append(",");
		_sb_.append(contribute).append(",");
		_sb_.append(dailyContrib).append(",");
		_sb_.append(dayDepotNum).append(",");
		_sb_.append(weeklyReceived).append(",");
		_sb_.append(weeklyLastRefresh).append(",");
		_sb_.append(dayLastRefresh).append(",");
		_sb_.append(avatar).append(",");
		_sb_.append(takenGensQuestId).append(",");
		_sb_.append(dayReceiveSupplyNum).append(",");
		_sb_.append(maxTalentType).append(",");
		_sb_.append(wildRewardTime).append(",");
		_sb_.append(isCheckInDaily).append(",");
		_sb_.append(talentActiveType).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(lastTrumpetTime).append(",");
		_sb_.append(trumpetCombo).append(",");
		_sb_.append(achieveNum).append(",");
		_sb_.append(lastFactionSalaryTime).append(",");
		_sb_.append(honor).append(",");
		_sb_.append("T").append(qq.length()).append(",");
		_sb_.append(showFaction).append(",");
		_sb_.append(showFriend).append(",");
		_sb_.append(showCompany).append(",");
		_sb_.append(offline).append(",");
		_sb_.append(monthlyCheckInInfo).append(",");
		_sb_.append(monthlyTakenCheckInReward).append(",");
		_sb_.append(monthlyLastRefresh).append(",");
		_sb_.append(maxTroopLead).append(",");
		_sb_.append(talentTotalLevel).append(",");
		_sb_.append(holdSum).append(",");
		_sb_.append(carrierTypeNum).append(",");
		_sb_.append(offlineAndLeaveScene).append(",");
		_sb_.append(createInOrientationAct).append(",");
		_sb_.append(create).append(",");
		_sb_.append(talentLevelMap).append(",");
		_sb_.append(troopImmureEndTime).append(",");
		_sb_.append("T").append(troopImmureOperator.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
