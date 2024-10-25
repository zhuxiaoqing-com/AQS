package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class Gens extends warrior.mdb.XBean {
    private long gid;  // 公会id
    private String gname;  // 家族名称
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> icon;  // 工会图标
    private int glv;  // 家族等级
    private String notice;  // 家族公告
    private java.util.concurrent.ConcurrentHashMap<Long, Integer> members;  // 成员列表 key:成员id value:成员职位
    private long leader;  // 族长id
    private java.util.concurrent.ConcurrentHashMap<Long, GensJoinApply> apply;  // 申请列表
    private java.util.concurrent.CopyOnWriteArraySet<Long> refuse;  // 已拒绝申请列表
    private int lftime;  // 最后刷新拒绝申请列表时间，一年当中第几天
    private long create;  // 创建时间
    private java.util.concurrent.ConcurrentHashMap<Integer, GensGroup> groups;  // 分组列表
    private long gold;  // 工会资金
    private int winNum;  // 工会战胜利次数
    private int loseNum;  // 工会战失败次数
    private boolean switchs;  // 是否自动同意加入工会
    private int joinGpid;  // 新入会加入分组ID
    private long lastRefresh;  // 上一次刷新时间
    private xbean.lobby.GensImpeach impeach;  // 公会弹劾
    private xbean.lobby.GensMerge sendMerge;  // 发送的公会合并信息
    private java.util.concurrent.ConcurrentHashMap<Long, GensMerge> receiveMerge;  // 收到的公会合并信息 key:公会id; value:合并信息
    private long lastCall;  // 上一次号召时间
    private long declareNum;  // 今日宣战次数
    private long bossTime;  // 每日军团boss开始时间
    private int bossState;  // 0:未开启 1：开启中 2：已结束
    private long currBossOpenTime;  // 当日军团boss开启的时间
    private long weeklyActivePoint;  // 每周军团活跃点
    private java.util.concurrent.ConcurrentHashMap<Integer, GensPositionInfo> positionInfo;  // 公会职位数据
    private long createrId;  // 创建者id
    private int OccupyPoint;  // 占据积分
    private long armPoint;  // 武装度
    private int cityOccupySum;  // 历史城市占领总数
    private int wildOccupySum;  // 历史野外占领总数
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> supply;  // 军团每日可领取补给
    private java.util.concurrent.ConcurrentHashMap<Long, SupplyApply> receiveApplySupply;  // 收到的补给申请
    private String qqGroup;  // qq群号
    private String qqContact;  // 联络人qq号
    private String innerNotice;  // 内部公告
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> guardInfo;  // 军团守卫设置
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> commanderSkill;  // k:指挥官技能id v:下次可用时间
    private long lastSendADTime;  // 上次发送招募广告的时间
    private int dailyRecommendRefreshCount;  // 今日刷新招募推荐列表次数
    private int dailyFreeRecommendRefreshCount;  // 今日免费刷新招募推荐列表次数
    private xbean.lobby.GensCheckInInfo checkInInfo;  // 签到信息
    private long weeklyLastRefresh;  // 上一次周刷新时间
    private java.util.concurrent.CopyOnWriteArraySet<Long> recommendList;  // 推荐列表
    private xbean.lobby.GensAllianceInfo gensAllianceInfo; 
    private java.util.concurrent.CopyOnWriteArrayList<SupplyApply> approvedSupplyApply;  // 已被批准的补给申请
    private int dailyNormalSupplyReceiveCount;  // 每天可领取制式补给的次数
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> dailyUseCommanderSkillCount;  // 每天使用指挥官技能的次数,k->技能id,v->使用次数
    private java.util.concurrent.CopyOnWriteArraySet<Integer> receiveQuest;  // 已被领取过的军团任务
    private int faction;  // 阵营
    private long lastAssembleTime;  // 上次操作集结时间
    private int assembleArea;  // 区域地图ID
    private int assembleLine;  // 线id
    private int assemblePosX;  // posX
    private int assemblePosZ;  // posZ
    private long lastMonthRefresh;  // 上次月刷新时间
    private long dailySeasonGensScore;  // 当日阵营战军团积分
    private long weekSeasonGensScore;  // 当周阵营战军团积分
    private long monthSeasonGensScore;  // 当月阵营战军团积分
    private long totalSeasonGensScore;  // 累计阵营战军团积分
    private int lastContestLevel;  // 上周争霸赛档次
    private long troopId;  // 所属部队id
    private java.util.concurrent.ConcurrentHashMap<Integer, GensSupplyInfo> talentSupply;  // 职业补给信息

    Gens(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.gname = "";
		this.icon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.notice = "";
		this.members = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		this.apply = new java.util.concurrent.ConcurrentHashMap<Long, GensJoinApply>();
		this.refuse = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.groups = new java.util.concurrent.ConcurrentHashMap<Integer, GensGroup>();
		this.impeach = new GensImpeach(this);
		this.sendMerge = new GensMerge(this);
		this.receiveMerge = new java.util.concurrent.ConcurrentHashMap<Long, GensMerge>();
		this.positionInfo = new java.util.concurrent.ConcurrentHashMap<Integer, GensPositionInfo>();
		this.supply = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.receiveApplySupply = new java.util.concurrent.ConcurrentHashMap<Long, SupplyApply>();
		this.qqGroup = "";
		this.qqContact = "";
		this.innerNotice = "";
		this.guardInfo = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.commanderSkill = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		this.checkInInfo = new GensCheckInInfo(this);
		this.recommendList = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.gensAllianceInfo = new GensAllianceInfo(this);
		this.approvedSupplyApply = new java.util.concurrent.CopyOnWriteArrayList<SupplyApply>();
		this.dailyUseCommanderSkillCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.receiveQuest = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.talentSupply = new java.util.concurrent.ConcurrentHashMap<Integer, GensSupplyInfo>();
	}

	public Gens() {
		this((warrior.mdb.XBean)null);
	}

	public Gens(Gens _o_) {
		this(_o_, null);
	}

	Gens(Gens _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.gid = _o_.gid;
		this.gname = _o_.gname;
		this.icon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.icon.forEach((_k_, _v_) -> this.icon.put(_k_, _v_));
		this.glv = _o_.glv;
		this.notice = _o_.notice;
		this.members = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		_o_.members.forEach((_k_, _v_) -> this.members.put(_k_, _v_));
		this.leader = _o_.leader;
		this.apply = new java.util.concurrent.ConcurrentHashMap<Long, GensJoinApply>();
		_o_.apply.forEach((_k_, _v_) -> this.apply.put(_k_, new GensJoinApply(_v_)));
		this.refuse = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.refuse.forEach(_v_ -> this.refuse.add(_v_));
		this.lftime = _o_.lftime;
		this.create = _o_.create;
		this.groups = new java.util.concurrent.ConcurrentHashMap<Integer, GensGroup>();
		_o_.groups.forEach((_k_, _v_) -> this.groups.put(_k_, new GensGroup(_v_)));
		this.gold = _o_.gold;
		this.winNum = _o_.winNum;
		this.loseNum = _o_.loseNum;
		this.switchs = _o_.switchs;
		this.joinGpid = _o_.joinGpid;
		this.lastRefresh = _o_.lastRefresh;
		this.impeach = new GensImpeach(_o_.impeach, this);
		this.sendMerge = new GensMerge(_o_.sendMerge, this);
		this.receiveMerge = new java.util.concurrent.ConcurrentHashMap<Long, GensMerge>();
		_o_.receiveMerge.forEach((_k_, _v_) -> this.receiveMerge.put(_k_, new GensMerge(_v_)));
		this.lastCall = _o_.lastCall;
		this.declareNum = _o_.declareNum;
		this.bossTime = _o_.bossTime;
		this.bossState = _o_.bossState;
		this.currBossOpenTime = _o_.currBossOpenTime;
		this.weeklyActivePoint = _o_.weeklyActivePoint;
		this.positionInfo = new java.util.concurrent.ConcurrentHashMap<Integer, GensPositionInfo>();
		_o_.positionInfo.forEach((_k_, _v_) -> this.positionInfo.put(_k_, new GensPositionInfo(_v_)));
		this.createrId = _o_.createrId;
		this.OccupyPoint = _o_.OccupyPoint;
		this.armPoint = _o_.armPoint;
		this.cityOccupySum = _o_.cityOccupySum;
		this.wildOccupySum = _o_.wildOccupySum;
		this.supply = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.supply.forEach((_k_, _v_) -> this.supply.put(_k_, _v_));
		this.receiveApplySupply = new java.util.concurrent.ConcurrentHashMap<Long, SupplyApply>();
		_o_.receiveApplySupply.forEach((_k_, _v_) -> this.receiveApplySupply.put(_k_, new SupplyApply(_v_)));
		this.qqGroup = _o_.qqGroup;
		this.qqContact = _o_.qqContact;
		this.innerNotice = _o_.innerNotice;
		this.guardInfo = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.guardInfo.forEach((_k_, _v_) -> this.guardInfo.put(_k_, _v_));
		this.commanderSkill = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.commanderSkill.forEach((_k_, _v_) -> this.commanderSkill.put(_k_, _v_));
		this.lastSendADTime = _o_.lastSendADTime;
		this.dailyRecommendRefreshCount = _o_.dailyRecommendRefreshCount;
		this.dailyFreeRecommendRefreshCount = _o_.dailyFreeRecommendRefreshCount;
		this.checkInInfo = new GensCheckInInfo(_o_.checkInInfo, this);
		this.weeklyLastRefresh = _o_.weeklyLastRefresh;
		this.recommendList = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.recommendList.forEach(_v_ -> this.recommendList.add(_v_));
		this.gensAllianceInfo = new GensAllianceInfo(_o_.gensAllianceInfo, this);
		this.approvedSupplyApply = new java.util.concurrent.CopyOnWriteArrayList<SupplyApply>();
		_o_.approvedSupplyApply.forEach(_v_ -> this.approvedSupplyApply.add(new SupplyApply(_v_)));
		this.dailyNormalSupplyReceiveCount = _o_.dailyNormalSupplyReceiveCount;
		this.dailyUseCommanderSkillCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyUseCommanderSkillCount.forEach((_k_, _v_) -> this.dailyUseCommanderSkillCount.put(_k_, _v_));
		this.receiveQuest = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.receiveQuest.forEach(_v_ -> this.receiveQuest.add(_v_));
		this.faction = _o_.faction;
		this.lastAssembleTime = _o_.lastAssembleTime;
		this.assembleArea = _o_.assembleArea;
		this.assembleLine = _o_.assembleLine;
		this.assemblePosX = _o_.assemblePosX;
		this.assemblePosZ = _o_.assemblePosZ;
		this.lastMonthRefresh = _o_.lastMonthRefresh;
		this.dailySeasonGensScore = _o_.dailySeasonGensScore;
		this.weekSeasonGensScore = _o_.weekSeasonGensScore;
		this.monthSeasonGensScore = _o_.monthSeasonGensScore;
		this.totalSeasonGensScore = _o_.totalSeasonGensScore;
		this.lastContestLevel = _o_.lastContestLevel;
		this.troopId = _o_.troopId;
		this.talentSupply = new java.util.concurrent.ConcurrentHashMap<Integer, GensSupplyInfo>();
		_o_.talentSupply.forEach((_k_, _v_) -> this.talentSupply.put(_k_, new GensSupplyInfo(_v_)));
	}

	public void copyFrom(Gens _o_) {
        this.gid = _o_.gid;
        this.gname = _o_.gname;
        this.icon = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.icon.forEach((_k_, _v_) -> this.icon.put(_k_, _v_));
        this.glv = _o_.glv;
        this.notice = _o_.notice;
        this.members = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		_o_.members.forEach((_k_, _v_) -> this.members.put(_k_, _v_));
        this.leader = _o_.leader;
        this.apply = new java.util.concurrent.ConcurrentHashMap<Long, GensJoinApply>();
		_o_.apply.forEach((_k_, _v_) -> this.apply.put(_k_, new GensJoinApply(_v_)));
        this.refuse = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.refuse.forEach(_v_ -> this.refuse.add(_v_));
        this.lftime = _o_.lftime;
        this.create = _o_.create;
        this.groups = new java.util.concurrent.ConcurrentHashMap<Integer, GensGroup>();
		_o_.groups.forEach((_k_, _v_) -> this.groups.put(_k_, new GensGroup(_v_)));
        this.gold = _o_.gold;
        this.winNum = _o_.winNum;
        this.loseNum = _o_.loseNum;
        this.switchs = _o_.switchs;
        this.joinGpid = _o_.joinGpid;
        this.lastRefresh = _o_.lastRefresh;
        this.impeach = new GensImpeach(_o_.impeach, this);
        this.sendMerge = new GensMerge(_o_.sendMerge, this);
        this.receiveMerge = new java.util.concurrent.ConcurrentHashMap<Long, GensMerge>();
		_o_.receiveMerge.forEach((_k_, _v_) -> this.receiveMerge.put(_k_, new GensMerge(_v_)));
        this.lastCall = _o_.lastCall;
        this.declareNum = _o_.declareNum;
        this.bossTime = _o_.bossTime;
        this.bossState = _o_.bossState;
        this.currBossOpenTime = _o_.currBossOpenTime;
        this.weeklyActivePoint = _o_.weeklyActivePoint;
        this.positionInfo = new java.util.concurrent.ConcurrentHashMap<Integer, GensPositionInfo>();
		_o_.positionInfo.forEach((_k_, _v_) -> this.positionInfo.put(_k_, new GensPositionInfo(_v_)));
        this.createrId = _o_.createrId;
        this.OccupyPoint = _o_.OccupyPoint;
        this.armPoint = _o_.armPoint;
        this.cityOccupySum = _o_.cityOccupySum;
        this.wildOccupySum = _o_.wildOccupySum;
        this.supply = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.supply.forEach((_k_, _v_) -> this.supply.put(_k_, _v_));
        this.receiveApplySupply = new java.util.concurrent.ConcurrentHashMap<Long, SupplyApply>();
		_o_.receiveApplySupply.forEach((_k_, _v_) -> this.receiveApplySupply.put(_k_, new SupplyApply(_v_)));
        this.qqGroup = _o_.qqGroup;
        this.qqContact = _o_.qqContact;
        this.innerNotice = _o_.innerNotice;
        this.guardInfo = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.guardInfo.forEach((_k_, _v_) -> this.guardInfo.put(_k_, _v_));
        this.commanderSkill = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.commanderSkill.forEach((_k_, _v_) -> this.commanderSkill.put(_k_, _v_));
        this.lastSendADTime = _o_.lastSendADTime;
        this.dailyRecommendRefreshCount = _o_.dailyRecommendRefreshCount;
        this.dailyFreeRecommendRefreshCount = _o_.dailyFreeRecommendRefreshCount;
        this.checkInInfo = new GensCheckInInfo(_o_.checkInInfo, this);
        this.weeklyLastRefresh = _o_.weeklyLastRefresh;
        this.recommendList = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.recommendList.forEach(_v_ -> this.recommendList.add(_v_));
        this.gensAllianceInfo = new GensAllianceInfo(_o_.gensAllianceInfo, this);
        this.approvedSupplyApply = new java.util.concurrent.CopyOnWriteArrayList<SupplyApply>();
		_o_.approvedSupplyApply.forEach(_v_ -> this.approvedSupplyApply.add(new SupplyApply(_v_)));
        this.dailyNormalSupplyReceiveCount = _o_.dailyNormalSupplyReceiveCount;
        this.dailyUseCommanderSkillCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyUseCommanderSkillCount.forEach((_k_, _v_) -> this.dailyUseCommanderSkillCount.put(_k_, _v_));
        this.receiveQuest = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.receiveQuest.forEach(_v_ -> this.receiveQuest.add(_v_));
        this.faction = _o_.faction;
        this.lastAssembleTime = _o_.lastAssembleTime;
        this.assembleArea = _o_.assembleArea;
        this.assembleLine = _o_.assembleLine;
        this.assemblePosX = _o_.assemblePosX;
        this.assemblePosZ = _o_.assemblePosZ;
        this.lastMonthRefresh = _o_.lastMonthRefresh;
        this.dailySeasonGensScore = _o_.dailySeasonGensScore;
        this.weekSeasonGensScore = _o_.weekSeasonGensScore;
        this.monthSeasonGensScore = _o_.monthSeasonGensScore;
        this.totalSeasonGensScore = _o_.totalSeasonGensScore;
        this.lastContestLevel = _o_.lastContestLevel;
        this.troopId = _o_.troopId;
        this.talentSupply = new java.util.concurrent.ConcurrentHashMap<Integer, GensSupplyInfo>();
		_o_.talentSupply.forEach((_k_, _v_) -> this.talentSupply.put(_k_, new GensSupplyInfo(_v_)));
	}


	@JSONField(name = "1")
	public long getGid(){
		return this.gid;
	}

	@JSONField(name = "2")
	public String getGname(){
		return this.gname;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getIcon(){
		return this.icon;
	}

	@JSONField(name = "4")
	public int getGlv(){
		return this.glv;
	}

	@JSONField(name = "5")
	public String getNotice(){
		return this.notice;
	}

	@JSONField(name = "6")
	public java.util.concurrent.ConcurrentHashMap<Long, Integer> getMembers(){
		return this.members;
	}

	@JSONField(name = "7")
	public long getLeader(){
		return this.leader;
	}

	@JSONField(name = "8")
	public java.util.concurrent.ConcurrentHashMap<Long, GensJoinApply> getApply(){
		return this.apply;
	}

	@JSONField(name = "9")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getRefuse(){
		return this.refuse;
	}

	@JSONField(name = "10")
	public int getLftime(){
		return this.lftime;
	}

	@JSONField(name = "11")
	public long getCreate(){
		return this.create;
	}

	@JSONField(name = "13")
	public java.util.concurrent.ConcurrentHashMap<Integer, GensGroup> getGroups(){
		return this.groups;
	}

	@JSONField(name = "14")
	public long getGold(){
		return this.gold;
	}

	@JSONField(name = "15")
	public int getWinNum(){
		return this.winNum;
	}

	@JSONField(name = "16")
	public int getLoseNum(){
		return this.loseNum;
	}

	@JSONField(name = "17")
	public boolean getSwitchs(){
		return this.switchs;
	}

	@JSONField(name = "18")
	public int getJoinGpid(){
		return this.joinGpid;
	}

	@JSONField(name = "20")
	public long getLastRefresh(){
		return this.lastRefresh;
	}

	@JSONField(name = "21")
	public xbean.lobby.GensImpeach getImpeach(){
		return this.impeach;
	}

	@JSONField(name = "22")
	public xbean.lobby.GensMerge getSendMerge(){
		return this.sendMerge;
	}

	@JSONField(name = "23")
	public java.util.concurrent.ConcurrentHashMap<Long, GensMerge> getReceiveMerge(){
		return this.receiveMerge;
	}

	@JSONField(name = "24")
	public long getLastCall(){
		return this.lastCall;
	}

	@JSONField(name = "25")
	public long getDeclareNum(){
		return this.declareNum;
	}

	@JSONField(name = "26")
	public long getBossTime(){
		return this.bossTime;
	}

	@JSONField(name = "27")
	public int getBossState(){
		return this.bossState;
	}

	@JSONField(name = "28")
	public long getCurrBossOpenTime(){
		return this.currBossOpenTime;
	}

	@JSONField(name = "29")
	public long getWeeklyActivePoint(){
		return this.weeklyActivePoint;
	}

	@JSONField(name = "30")
	public java.util.concurrent.ConcurrentHashMap<Integer, GensPositionInfo> getPositionInfo(){
		return this.positionInfo;
	}

	@JSONField(name = "31")
	public long getCreaterId(){
		return this.createrId;
	}

	@JSONField(name = "32")
	public int getOccupyPoint(){
		return this.OccupyPoint;
	}

	@JSONField(name = "33")
	public long getArmPoint(){
		return this.armPoint;
	}

	@JSONField(name = "34")
	public int getCityOccupySum(){
		return this.cityOccupySum;
	}

	@JSONField(name = "35")
	public int getWildOccupySum(){
		return this.wildOccupySum;
	}

	@JSONField(name = "36")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getSupply(){
		return this.supply;
	}

	@JSONField(name = "37")
	public java.util.concurrent.ConcurrentHashMap<Long, SupplyApply> getReceiveApplySupply(){
		return this.receiveApplySupply;
	}

	@JSONField(name = "38")
	public String getQqGroup(){
		return this.qqGroup;
	}

	@JSONField(name = "39")
	public String getQqContact(){
		return this.qqContact;
	}

	@JSONField(name = "40")
	public String getInnerNotice(){
		return this.innerNotice;
	}

	@JSONField(name = "41")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getGuardInfo(){
		return this.guardInfo;
	}

	@JSONField(name = "42")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getCommanderSkill(){
		return this.commanderSkill;
	}

	@JSONField(name = "43")
	public long getLastSendADTime(){
		return this.lastSendADTime;
	}

	@JSONField(name = "44")
	public int getDailyRecommendRefreshCount(){
		return this.dailyRecommendRefreshCount;
	}

	@JSONField(name = "45")
	public int getDailyFreeRecommendRefreshCount(){
		return this.dailyFreeRecommendRefreshCount;
	}

	@JSONField(name = "47")
	public xbean.lobby.GensCheckInInfo getCheckInInfo(){
		return this.checkInInfo;
	}

	@JSONField(name = "48")
	public long getWeeklyLastRefresh(){
		return this.weeklyLastRefresh;
	}

	@JSONField(name = "49")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getRecommendList(){
		return this.recommendList;
	}

	@JSONField(name = "50")
	public xbean.lobby.GensAllianceInfo getGensAllianceInfo(){
		return this.gensAllianceInfo;
	}

	@JSONField(name = "51")
	public java.util.concurrent.CopyOnWriteArrayList<SupplyApply> getApprovedSupplyApply(){
		return this.approvedSupplyApply;
	}

	@JSONField(name = "52")
	public int getDailyNormalSupplyReceiveCount(){
		return this.dailyNormalSupplyReceiveCount;
	}

	@JSONField(name = "53")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDailyUseCommanderSkillCount(){
		return this.dailyUseCommanderSkillCount;
	}

	@JSONField(name = "54")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getReceiveQuest(){
		return this.receiveQuest;
	}

	@JSONField(name = "55")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "56")
	public long getLastAssembleTime(){
		return this.lastAssembleTime;
	}

	@JSONField(name = "57")
	public int getAssembleArea(){
		return this.assembleArea;
	}

	@JSONField(name = "58")
	public int getAssembleLine(){
		return this.assembleLine;
	}

	@JSONField(name = "59")
	public int getAssemblePosX(){
		return this.assemblePosX;
	}

	@JSONField(name = "60")
	public int getAssemblePosZ(){
		return this.assemblePosZ;
	}

	@JSONField(name = "61")
	public long getLastMonthRefresh(){
		return this.lastMonthRefresh;
	}

	@JSONField(name = "62")
	public long getDailySeasonGensScore(){
		return this.dailySeasonGensScore;
	}

	@JSONField(name = "63")
	public long getWeekSeasonGensScore(){
		return this.weekSeasonGensScore;
	}

	@JSONField(name = "64")
	public long getMonthSeasonGensScore(){
		return this.monthSeasonGensScore;
	}

	@JSONField(name = "65")
	public long getTotalSeasonGensScore(){
		return this.totalSeasonGensScore;
	}

	@JSONField(name = "66")
	public int getLastContestLevel(){
		return this.lastContestLevel;
	}

	@JSONField(name = "67")
	public long getTroopId(){
		return this.troopId;
	}

	@JSONField(name = "68")
	public java.util.concurrent.ConcurrentHashMap<Integer, GensSupplyInfo> getTalentSupply(){
		return this.talentSupply;
	}

	@JSONField(name = "1")
	public void setGid(long _v_){
		this.gid = _v_;
	}

	@JSONField(name = "2")
	public void setGname(String _v_){
		this.gname = _v_;
	}

	@JSONField(name = "3")
	public void setIcon(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.icon = _v_;
	}

	@JSONField(name = "4")
	public void setGlv(int _v_){
		this.glv = _v_;
	}

	@JSONField(name = "5")
	public void setNotice(String _v_){
		this.notice = _v_;
	}

	@JSONField(name = "6")
	public void setMembers(java.util.concurrent.ConcurrentHashMap<Long, Integer> _v_){
		this.members = _v_;
	}

	@JSONField(name = "7")
	public void setLeader(long _v_){
		this.leader = _v_;
	}

	@JSONField(name = "8")
	public void setApply(java.util.concurrent.ConcurrentHashMap<Long, GensJoinApply> _v_){
		this.apply = _v_;
	}

	@JSONField(name = "9")
	public void setRefuse(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.refuse = _v_;
	}

	@JSONField(name = "10")
	public void setLftime(int _v_){
		this.lftime = _v_;
	}

	@JSONField(name = "11")
	public void setCreate(long _v_){
		this.create = _v_;
	}

	@JSONField(name = "13")
	public void setGroups(java.util.concurrent.ConcurrentHashMap<Integer, GensGroup> _v_){
		this.groups = _v_;
	}

	@JSONField(name = "14")
	public void setGold(long _v_){
		this.gold = _v_;
	}

	@JSONField(name = "15")
	public void setWinNum(int _v_){
		this.winNum = _v_;
	}

	@JSONField(name = "16")
	public void setLoseNum(int _v_){
		this.loseNum = _v_;
	}

	@JSONField(name = "17")
	public void setSwitchs(boolean _v_){
		this.switchs = _v_;
	}

	@JSONField(name = "18")
	public void setJoinGpid(int _v_){
		this.joinGpid = _v_;
	}

	@JSONField(name = "20")
	public void setLastRefresh(long _v_){
		this.lastRefresh = _v_;
	}

	@JSONField(name = "21")
	public void setImpeach(xbean.lobby.GensImpeach _v_){
		this.impeach = _v_;
	}

	@JSONField(name = "22")
	public void setSendMerge(xbean.lobby.GensMerge _v_){
		this.sendMerge = _v_;
	}

	@JSONField(name = "23")
	public void setReceiveMerge(java.util.concurrent.ConcurrentHashMap<Long, GensMerge> _v_){
		this.receiveMerge = _v_;
	}

	@JSONField(name = "24")
	public void setLastCall(long _v_){
		this.lastCall = _v_;
	}

	@JSONField(name = "25")
	public void setDeclareNum(long _v_){
		this.declareNum = _v_;
	}

	@JSONField(name = "26")
	public void setBossTime(long _v_){
		this.bossTime = _v_;
	}

	@JSONField(name = "27")
	public void setBossState(int _v_){
		this.bossState = _v_;
	}

	@JSONField(name = "28")
	public void setCurrBossOpenTime(long _v_){
		this.currBossOpenTime = _v_;
	}

	@JSONField(name = "29")
	public void setWeeklyActivePoint(long _v_){
		this.weeklyActivePoint = _v_;
	}

	@JSONField(name = "30")
	public void setPositionInfo(java.util.concurrent.ConcurrentHashMap<Integer, GensPositionInfo> _v_){
		this.positionInfo = _v_;
	}

	@JSONField(name = "31")
	public void setCreaterId(long _v_){
		this.createrId = _v_;
	}

	@JSONField(name = "32")
	public void setOccupyPoint(int _v_){
		this.OccupyPoint = _v_;
	}

	@JSONField(name = "33")
	public void setArmPoint(long _v_){
		this.armPoint = _v_;
	}

	@JSONField(name = "34")
	public void setCityOccupySum(int _v_){
		this.cityOccupySum = _v_;
	}

	@JSONField(name = "35")
	public void setWildOccupySum(int _v_){
		this.wildOccupySum = _v_;
	}

	@JSONField(name = "36")
	public void setSupply(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.supply = _v_;
	}

	@JSONField(name = "37")
	public void setReceiveApplySupply(java.util.concurrent.ConcurrentHashMap<Long, SupplyApply> _v_){
		this.receiveApplySupply = _v_;
	}

	@JSONField(name = "38")
	public void setQqGroup(String _v_){
		this.qqGroup = _v_;
	}

	@JSONField(name = "39")
	public void setQqContact(String _v_){
		this.qqContact = _v_;
	}

	@JSONField(name = "40")
	public void setInnerNotice(String _v_){
		this.innerNotice = _v_;
	}

	@JSONField(name = "41")
	public void setGuardInfo(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.guardInfo = _v_;
	}

	@JSONField(name = "42")
	public void setCommanderSkill(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.commanderSkill = _v_;
	}

	@JSONField(name = "43")
	public void setLastSendADTime(long _v_){
		this.lastSendADTime = _v_;
	}

	@JSONField(name = "44")
	public void setDailyRecommendRefreshCount(int _v_){
		this.dailyRecommendRefreshCount = _v_;
	}

	@JSONField(name = "45")
	public void setDailyFreeRecommendRefreshCount(int _v_){
		this.dailyFreeRecommendRefreshCount = _v_;
	}

	@JSONField(name = "47")
	public void setCheckInInfo(xbean.lobby.GensCheckInInfo _v_){
		this.checkInInfo = _v_;
	}

	@JSONField(name = "48")
	public void setWeeklyLastRefresh(long _v_){
		this.weeklyLastRefresh = _v_;
	}

	@JSONField(name = "49")
	public void setRecommendList(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.recommendList = _v_;
	}

	@JSONField(name = "50")
	public void setGensAllianceInfo(xbean.lobby.GensAllianceInfo _v_){
		this.gensAllianceInfo = _v_;
	}

	@JSONField(name = "51")
	public void setApprovedSupplyApply(java.util.concurrent.CopyOnWriteArrayList<SupplyApply> _v_){
		this.approvedSupplyApply = _v_;
	}

	@JSONField(name = "52")
	public void setDailyNormalSupplyReceiveCount(int _v_){
		this.dailyNormalSupplyReceiveCount = _v_;
	}

	@JSONField(name = "53")
	public void setDailyUseCommanderSkillCount(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.dailyUseCommanderSkillCount = _v_;
	}

	@JSONField(name = "54")
	public void setReceiveQuest(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.receiveQuest = _v_;
	}

	@JSONField(name = "55")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "56")
	public void setLastAssembleTime(long _v_){
		this.lastAssembleTime = _v_;
	}

	@JSONField(name = "57")
	public void setAssembleArea(int _v_){
		this.assembleArea = _v_;
	}

	@JSONField(name = "58")
	public void setAssembleLine(int _v_){
		this.assembleLine = _v_;
	}

	@JSONField(name = "59")
	public void setAssemblePosX(int _v_){
		this.assemblePosX = _v_;
	}

	@JSONField(name = "60")
	public void setAssemblePosZ(int _v_){
		this.assemblePosZ = _v_;
	}

	@JSONField(name = "61")
	public void setLastMonthRefresh(long _v_){
		this.lastMonthRefresh = _v_;
	}

	@JSONField(name = "62")
	public void setDailySeasonGensScore(long _v_){
		this.dailySeasonGensScore = _v_;
	}

	@JSONField(name = "63")
	public void setWeekSeasonGensScore(long _v_){
		this.weekSeasonGensScore = _v_;
	}

	@JSONField(name = "64")
	public void setMonthSeasonGensScore(long _v_){
		this.monthSeasonGensScore = _v_;
	}

	@JSONField(name = "65")
	public void setTotalSeasonGensScore(long _v_){
		this.totalSeasonGensScore = _v_;
	}

	@JSONField(name = "66")
	public void setLastContestLevel(int _v_){
		this.lastContestLevel = _v_;
	}

	@JSONField(name = "67")
	public void setTroopId(long _v_){
		this.troopId = _v_;
	}

	@JSONField(name = "68")
	public void setTalentSupply(java.util.concurrent.ConcurrentHashMap<Integer, GensSupplyInfo> _v_){
		this.talentSupply = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Gens) {
			Gens _o_ = (Gens)_o1_;
			if (this.gid != _o_.gid) return false;
			if (!this.gname.equals(_o_.gname)) return false;
			if (!this.icon.equals(_o_.icon)) return false;
			if (this.glv != _o_.glv) return false;
			if (!this.notice.equals(_o_.notice)) return false;
			if (!this.members.equals(_o_.members)) return false;
			if (this.leader != _o_.leader) return false;
			if (!this.apply.equals(_o_.apply)) return false;
			if (!this.refuse.equals(_o_.refuse)) return false;
			if (this.lftime != _o_.lftime) return false;
			if (this.create != _o_.create) return false;
			if (!this.groups.equals(_o_.groups)) return false;
			if (this.gold != _o_.gold) return false;
			if (this.winNum != _o_.winNum) return false;
			if (this.loseNum != _o_.loseNum) return false;
			if (this.switchs != _o_.switchs) return false;
			if (this.joinGpid != _o_.joinGpid) return false;
			if (this.lastRefresh != _o_.lastRefresh) return false;
			if (!this.impeach.equals(_o_.impeach)) return false;
			if (!this.sendMerge.equals(_o_.sendMerge)) return false;
			if (!this.receiveMerge.equals(_o_.receiveMerge)) return false;
			if (this.lastCall != _o_.lastCall) return false;
			if (this.declareNum != _o_.declareNum) return false;
			if (this.bossTime != _o_.bossTime) return false;
			if (this.bossState != _o_.bossState) return false;
			if (this.currBossOpenTime != _o_.currBossOpenTime) return false;
			if (this.weeklyActivePoint != _o_.weeklyActivePoint) return false;
			if (!this.positionInfo.equals(_o_.positionInfo)) return false;
			if (this.createrId != _o_.createrId) return false;
			if (this.OccupyPoint != _o_.OccupyPoint) return false;
			if (this.armPoint != _o_.armPoint) return false;
			if (this.cityOccupySum != _o_.cityOccupySum) return false;
			if (this.wildOccupySum != _o_.wildOccupySum) return false;
			if (!this.supply.equals(_o_.supply)) return false;
			if (!this.receiveApplySupply.equals(_o_.receiveApplySupply)) return false;
			if (!this.qqGroup.equals(_o_.qqGroup)) return false;
			if (!this.qqContact.equals(_o_.qqContact)) return false;
			if (!this.innerNotice.equals(_o_.innerNotice)) return false;
			if (!this.guardInfo.equals(_o_.guardInfo)) return false;
			if (!this.commanderSkill.equals(_o_.commanderSkill)) return false;
			if (this.lastSendADTime != _o_.lastSendADTime) return false;
			if (this.dailyRecommendRefreshCount != _o_.dailyRecommendRefreshCount) return false;
			if (this.dailyFreeRecommendRefreshCount != _o_.dailyFreeRecommendRefreshCount) return false;
			if (!this.checkInInfo.equals(_o_.checkInInfo)) return false;
			if (this.weeklyLastRefresh != _o_.weeklyLastRefresh) return false;
			if (!this.recommendList.equals(_o_.recommendList)) return false;
			if (!this.gensAllianceInfo.equals(_o_.gensAllianceInfo)) return false;
			if (!this.approvedSupplyApply.equals(_o_.approvedSupplyApply)) return false;
			if (this.dailyNormalSupplyReceiveCount != _o_.dailyNormalSupplyReceiveCount) return false;
			if (!this.dailyUseCommanderSkillCount.equals(_o_.dailyUseCommanderSkillCount)) return false;
			if (!this.receiveQuest.equals(_o_.receiveQuest)) return false;
			if (this.faction != _o_.faction) return false;
			if (this.lastAssembleTime != _o_.lastAssembleTime) return false;
			if (this.assembleArea != _o_.assembleArea) return false;
			if (this.assembleLine != _o_.assembleLine) return false;
			if (this.assemblePosX != _o_.assemblePosX) return false;
			if (this.assemblePosZ != _o_.assemblePosZ) return false;
			if (this.lastMonthRefresh != _o_.lastMonthRefresh) return false;
			if (this.dailySeasonGensScore != _o_.dailySeasonGensScore) return false;
			if (this.weekSeasonGensScore != _o_.weekSeasonGensScore) return false;
			if (this.monthSeasonGensScore != _o_.monthSeasonGensScore) return false;
			if (this.totalSeasonGensScore != _o_.totalSeasonGensScore) return false;
			if (this.lastContestLevel != _o_.lastContestLevel) return false;
			if (this.troopId != _o_.troopId) return false;
			if (!this.talentSupply.equals(_o_.talentSupply)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(gid ^ (gid >>> 32));
		_h_ += _h_ * 31 + this.gname.hashCode();
		_h_ += _h_ * 31 + this.icon.hashCode();
		_h_ += _h_ * 31 + (int)(this.glv);
		_h_ += _h_ * 31 + this.notice.hashCode();
		_h_ += _h_ * 31 + this.members.hashCode();
		_h_ += _h_ * 31 + (int)(leader ^ (leader >>> 32));
		_h_ += _h_ * 31 + this.apply.hashCode();
		_h_ += _h_ * 31 + this.refuse.hashCode();
		_h_ += _h_ * 31 + (int)(this.lftime);
		_h_ += _h_ * 31 + (int)(create ^ (create >>> 32));
		_h_ += _h_ * 31 + this.groups.hashCode();
		_h_ += _h_ * 31 + (int)(gold ^ (gold >>> 32));
		_h_ += _h_ * 31 + (int)(this.winNum);
		_h_ += _h_ * 31 + (int)(this.loseNum);
		_h_ += _h_ * 31 + (this.switchs ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(this.joinGpid);
		_h_ += _h_ * 31 + (int)(lastRefresh ^ (lastRefresh >>> 32));
		_h_ += _h_ * 31 + this.impeach.hashCode();
		_h_ += _h_ * 31 + this.sendMerge.hashCode();
		_h_ += _h_ * 31 + this.receiveMerge.hashCode();
		_h_ += _h_ * 31 + (int)(lastCall ^ (lastCall >>> 32));
		_h_ += _h_ * 31 + (int)(declareNum ^ (declareNum >>> 32));
		_h_ += _h_ * 31 + (int)(bossTime ^ (bossTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.bossState);
		_h_ += _h_ * 31 + (int)(currBossOpenTime ^ (currBossOpenTime >>> 32));
		_h_ += _h_ * 31 + (int)(weeklyActivePoint ^ (weeklyActivePoint >>> 32));
		_h_ += _h_ * 31 + this.positionInfo.hashCode();
		_h_ += _h_ * 31 + (int)(createrId ^ (createrId >>> 32));
		_h_ += _h_ * 31 + (int)(this.OccupyPoint);
		_h_ += _h_ * 31 + (int)(armPoint ^ (armPoint >>> 32));
		_h_ += _h_ * 31 + (int)(this.cityOccupySum);
		_h_ += _h_ * 31 + (int)(this.wildOccupySum);
		_h_ += _h_ * 31 + this.supply.hashCode();
		_h_ += _h_ * 31 + this.receiveApplySupply.hashCode();
		_h_ += _h_ * 31 + this.qqGroup.hashCode();
		_h_ += _h_ * 31 + this.qqContact.hashCode();
		_h_ += _h_ * 31 + this.innerNotice.hashCode();
		_h_ += _h_ * 31 + this.guardInfo.hashCode();
		_h_ += _h_ * 31 + this.commanderSkill.hashCode();
		_h_ += _h_ * 31 + (int)(lastSendADTime ^ (lastSendADTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.dailyRecommendRefreshCount);
		_h_ += _h_ * 31 + (int)(this.dailyFreeRecommendRefreshCount);
		_h_ += _h_ * 31 + this.checkInInfo.hashCode();
		_h_ += _h_ * 31 + (int)(weeklyLastRefresh ^ (weeklyLastRefresh >>> 32));
		_h_ += _h_ * 31 + this.recommendList.hashCode();
		_h_ += _h_ * 31 + this.gensAllianceInfo.hashCode();
		_h_ += _h_ * 31 + this.approvedSupplyApply.hashCode();
		_h_ += _h_ * 31 + (int)(this.dailyNormalSupplyReceiveCount);
		_h_ += _h_ * 31 + this.dailyUseCommanderSkillCount.hashCode();
		_h_ += _h_ * 31 + this.receiveQuest.hashCode();
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + (int)(lastAssembleTime ^ (lastAssembleTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.assembleArea);
		_h_ += _h_ * 31 + (int)(this.assembleLine);
		_h_ += _h_ * 31 + (int)(this.assemblePosX);
		_h_ += _h_ * 31 + (int)(this.assemblePosZ);
		_h_ += _h_ * 31 + (int)(lastMonthRefresh ^ (lastMonthRefresh >>> 32));
		_h_ += _h_ * 31 + (int)(dailySeasonGensScore ^ (dailySeasonGensScore >>> 32));
		_h_ += _h_ * 31 + (int)(weekSeasonGensScore ^ (weekSeasonGensScore >>> 32));
		_h_ += _h_ * 31 + (int)(monthSeasonGensScore ^ (monthSeasonGensScore >>> 32));
		_h_ += _h_ * 31 + (int)(totalSeasonGensScore ^ (totalSeasonGensScore >>> 32));
		_h_ += _h_ * 31 + (int)(this.lastContestLevel);
		_h_ += _h_ * 31 + (int)(troopId ^ (troopId >>> 32));
		_h_ += _h_ * 31 + this.talentSupply.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(gid).append(",");
		_sb_.append("T").append(gname.length()).append(",");
		_sb_.append(icon).append(",");
		_sb_.append(glv).append(",");
		_sb_.append("T").append(notice.length()).append(",");
		_sb_.append(members).append(",");
		_sb_.append(leader).append(",");
		_sb_.append(apply).append(",");
		_sb_.append(refuse).append(",");
		_sb_.append(lftime).append(",");
		_sb_.append(create).append(",");
		_sb_.append(groups).append(",");
		_sb_.append(gold).append(",");
		_sb_.append(winNum).append(",");
		_sb_.append(loseNum).append(",");
		_sb_.append(switchs).append(",");
		_sb_.append(joinGpid).append(",");
		_sb_.append(lastRefresh).append(",");
		_sb_.append(impeach).append(",");
		_sb_.append(sendMerge).append(",");
		_sb_.append(receiveMerge).append(",");
		_sb_.append(lastCall).append(",");
		_sb_.append(declareNum).append(",");
		_sb_.append(bossTime).append(",");
		_sb_.append(bossState).append(",");
		_sb_.append(currBossOpenTime).append(",");
		_sb_.append(weeklyActivePoint).append(",");
		_sb_.append(positionInfo).append(",");
		_sb_.append(createrId).append(",");
		_sb_.append(OccupyPoint).append(",");
		_sb_.append(armPoint).append(",");
		_sb_.append(cityOccupySum).append(",");
		_sb_.append(wildOccupySum).append(",");
		_sb_.append(supply).append(",");
		_sb_.append(receiveApplySupply).append(",");
		_sb_.append("T").append(qqGroup.length()).append(",");
		_sb_.append("T").append(qqContact.length()).append(",");
		_sb_.append("T").append(innerNotice.length()).append(",");
		_sb_.append(guardInfo).append(",");
		_sb_.append(commanderSkill).append(",");
		_sb_.append(lastSendADTime).append(",");
		_sb_.append(dailyRecommendRefreshCount).append(",");
		_sb_.append(dailyFreeRecommendRefreshCount).append(",");
		_sb_.append(checkInInfo).append(",");
		_sb_.append(weeklyLastRefresh).append(",");
		_sb_.append(recommendList).append(",");
		_sb_.append(gensAllianceInfo).append(",");
		_sb_.append(approvedSupplyApply).append(",");
		_sb_.append(dailyNormalSupplyReceiveCount).append(",");
		_sb_.append(dailyUseCommanderSkillCount).append(",");
		_sb_.append(receiveQuest).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(lastAssembleTime).append(",");
		_sb_.append(assembleArea).append(",");
		_sb_.append(assembleLine).append(",");
		_sb_.append(assemblePosX).append(",");
		_sb_.append(assemblePosZ).append(",");
		_sb_.append(lastMonthRefresh).append(",");
		_sb_.append(dailySeasonGensScore).append(",");
		_sb_.append(weekSeasonGensScore).append(",");
		_sb_.append(monthSeasonGensScore).append(",");
		_sb_.append(totalSeasonGensScore).append(",");
		_sb_.append(lastContestLevel).append(",");
		_sb_.append(troopId).append(",");
		_sb_.append(talentSupply).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
