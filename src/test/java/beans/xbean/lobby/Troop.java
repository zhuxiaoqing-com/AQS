package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class Troop extends warrior.mdb.XBean {
    private long id; 
    private String name; 
    private java.util.concurrent.CopyOnWriteArrayList<Long> underling;  // 下属部队的id
    private long gid;  // 具体功能实现直接套军团,只有连级才有
    private int type;  // 级别
    private long leader; 
    private int faction; 
    private java.util.concurrent.CopyOnWriteArrayList<Long> candidates;  // 选举候选
    private long lastElectTime;  // 上次选举时间
    private long supior;  // 上级部队的id
    private boolean system;  // 是否系统创建
    private boolean open;  // 是否开启
    private long relateFormId;  // 关联组建id
    private long autoElectTime;  // 自动任职时间
    private xbean.lobby.CampaignInfo campaign;  // 竞选日活动信息
    private java.util.concurrent.ConcurrentHashMap<Long, RankInfo> ranks;  // 部队内排行榜
    private int leaderDeclareCount;  // 首长已宣战次数
    private java.util.concurrent.ConcurrentHashMap<Long, TroopExploitMember> exploitInfo;  // 历史功勋
    private java.util.concurrent.ConcurrentHashMap<Long, MemberExploitInfo> actExploit;  // 单次活动的授勋列表
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> memberHoldSum;  // 成员拥有装备数量
    private java.util.concurrent.ConcurrentHashMap<Integer, TroopStatistics> troopStatisticsMap; 
    private java.util.concurrent.CopyOnWriteArrayList<TroopRecord> records; 
    private long lastDailyFresh; 
    private long lastWeeklyFresh; 
    private java.util.concurrent.CopyOnWriteArraySet<Long> instructors;  // 教官列表
    private int acceptMerge;  // 接收合并次数
    private long relateMerge;  // 关联的
    private int leaderImmureCount;  // 首长监禁他人次数
    private java.util.concurrent.ConcurrentHashMap<Integer, String> positionName;  // 连队内官职名称
    private int level; 
    private int exp; 
    private int nextGroupId; 
    private java.util.concurrent.ConcurrentHashMap<Integer, FactionComposeTech> composeTech;  // 制造科技等级
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> composeMark;  // 制造科技推荐
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> resHpMax;  // 驻地资源显示
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> resHpRemain;  // 驻地资源显示
    private long wildStartTime;  // 野外阵营战开始时间
    private int wildTotalScore;  // 阵营战当前积分
    private java.util.concurrent.ConcurrentHashMap<Long, Integer> wildJoinPlayerMap;  // 阵营战参与玩家map
    private xbean.lobby.TroopStrategy strategy;  // 部队战略战术
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> makeItemCache;  // 玩家制造的道具的缓存(待处理)
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> dailyBuyResMap;  // 今日已购买资源

    Troop(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.name = "";
		this.underling = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		this.candidates = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		this.campaign = new CampaignInfo(this);
		this.ranks = new java.util.concurrent.ConcurrentHashMap<Long, RankInfo>();
		this.exploitInfo = new java.util.concurrent.ConcurrentHashMap<Long, TroopExploitMember>();
		this.actExploit = new java.util.concurrent.ConcurrentHashMap<Long, MemberExploitInfo>();
		this.memberHoldSum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.troopStatisticsMap = new java.util.concurrent.ConcurrentHashMap<Integer, TroopStatistics>();
		this.records = new java.util.concurrent.CopyOnWriteArrayList<TroopRecord>();
		this.instructors = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.positionName = new java.util.concurrent.ConcurrentHashMap<Integer, String>();
		this.composeTech = new java.util.concurrent.ConcurrentHashMap<Integer, FactionComposeTech>();
		this.composeMark = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		this.resHpMax = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.resHpRemain = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.wildJoinPlayerMap = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		this.strategy = new TroopStrategy(this);
		this.makeItemCache = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.dailyBuyResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public Troop() {
		this((warrior.mdb.XBean)null);
	}

	public Troop(Troop _o_) {
		this(_o_, null);
	}

	Troop(Troop _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.name = _o_.name;
		this.underling = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.underling.forEach(_v_ -> this.underling.add(_v_));
		this.gid = _o_.gid;
		this.type = _o_.type;
		this.leader = _o_.leader;
		this.faction = _o_.faction;
		this.candidates = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.candidates.forEach(_v_ -> this.candidates.add(_v_));
		this.lastElectTime = _o_.lastElectTime;
		this.supior = _o_.supior;
		this.system = _o_.system;
		this.open = _o_.open;
		this.relateFormId = _o_.relateFormId;
		this.autoElectTime = _o_.autoElectTime;
		this.campaign = new CampaignInfo(_o_.campaign, this);
		this.ranks = new java.util.concurrent.ConcurrentHashMap<Long, RankInfo>();
		_o_.ranks.forEach((_k_, _v_) -> this.ranks.put(_k_, new RankInfo(_v_)));
		this.leaderDeclareCount = _o_.leaderDeclareCount;
		this.exploitInfo = new java.util.concurrent.ConcurrentHashMap<Long, TroopExploitMember>();
		_o_.exploitInfo.forEach((_k_, _v_) -> this.exploitInfo.put(_k_, new TroopExploitMember(_v_)));
		this.actExploit = new java.util.concurrent.ConcurrentHashMap<Long, MemberExploitInfo>();
		_o_.actExploit.forEach((_k_, _v_) -> this.actExploit.put(_k_, new MemberExploitInfo(_v_)));
		this.memberHoldSum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.memberHoldSum.forEach((_k_, _v_) -> this.memberHoldSum.put(_k_, _v_));
		this.troopStatisticsMap = new java.util.concurrent.ConcurrentHashMap<Integer, TroopStatistics>();
		_o_.troopStatisticsMap.forEach((_k_, _v_) -> this.troopStatisticsMap.put(_k_, new TroopStatistics(_v_)));
		this.records = new java.util.concurrent.CopyOnWriteArrayList<TroopRecord>();
		_o_.records.forEach(_v_ -> this.records.add(new TroopRecord(_v_)));
		this.lastDailyFresh = _o_.lastDailyFresh;
		this.lastWeeklyFresh = _o_.lastWeeklyFresh;
		this.instructors = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.instructors.forEach(_v_ -> this.instructors.add(_v_));
		this.acceptMerge = _o_.acceptMerge;
		this.relateMerge = _o_.relateMerge;
		this.leaderImmureCount = _o_.leaderImmureCount;
		this.positionName = new java.util.concurrent.ConcurrentHashMap<Integer, String>();
		_o_.positionName.forEach((_k_, _v_) -> this.positionName.put(_k_, _v_));
		this.level = _o_.level;
		this.exp = _o_.exp;
		this.nextGroupId = _o_.nextGroupId;
		this.composeTech = new java.util.concurrent.ConcurrentHashMap<Integer, FactionComposeTech>();
		_o_.composeTech.forEach((_k_, _v_) -> this.composeTech.put(_k_, new FactionComposeTech(_v_)));
		this.composeMark = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.composeMark.forEach((_k_, _v_) -> this.composeMark.put(_k_, _v_));
		this.resHpMax = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.resHpMax.forEach((_k_, _v_) -> this.resHpMax.put(_k_, _v_));
		this.resHpRemain = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.resHpRemain.forEach((_k_, _v_) -> this.resHpRemain.put(_k_, _v_));
		this.wildStartTime = _o_.wildStartTime;
		this.wildTotalScore = _o_.wildTotalScore;
		this.wildJoinPlayerMap = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		_o_.wildJoinPlayerMap.forEach((_k_, _v_) -> this.wildJoinPlayerMap.put(_k_, _v_));
		this.strategy = new TroopStrategy(_o_.strategy, this);
		this.makeItemCache = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.makeItemCache.forEach((_k_, _v_) -> this.makeItemCache.put(_k_, _v_));
		this.dailyBuyResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyBuyResMap.forEach((_k_, _v_) -> this.dailyBuyResMap.put(_k_, _v_));
	}

	public void copyFrom(Troop _o_) {
        this.id = _o_.id;
        this.name = _o_.name;
        this.underling = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.underling.forEach(_v_ -> this.underling.add(_v_));
        this.gid = _o_.gid;
        this.type = _o_.type;
        this.leader = _o_.leader;
        this.faction = _o_.faction;
        this.candidates = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.candidates.forEach(_v_ -> this.candidates.add(_v_));
        this.lastElectTime = _o_.lastElectTime;
        this.supior = _o_.supior;
        this.system = _o_.system;
        this.open = _o_.open;
        this.relateFormId = _o_.relateFormId;
        this.autoElectTime = _o_.autoElectTime;
        this.campaign = new CampaignInfo(_o_.campaign, this);
        this.ranks = new java.util.concurrent.ConcurrentHashMap<Long, RankInfo>();
		_o_.ranks.forEach((_k_, _v_) -> this.ranks.put(_k_, new RankInfo(_v_)));
        this.leaderDeclareCount = _o_.leaderDeclareCount;
        this.exploitInfo = new java.util.concurrent.ConcurrentHashMap<Long, TroopExploitMember>();
		_o_.exploitInfo.forEach((_k_, _v_) -> this.exploitInfo.put(_k_, new TroopExploitMember(_v_)));
        this.actExploit = new java.util.concurrent.ConcurrentHashMap<Long, MemberExploitInfo>();
		_o_.actExploit.forEach((_k_, _v_) -> this.actExploit.put(_k_, new MemberExploitInfo(_v_)));
        this.memberHoldSum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.memberHoldSum.forEach((_k_, _v_) -> this.memberHoldSum.put(_k_, _v_));
        this.troopStatisticsMap = new java.util.concurrent.ConcurrentHashMap<Integer, TroopStatistics>();
		_o_.troopStatisticsMap.forEach((_k_, _v_) -> this.troopStatisticsMap.put(_k_, new TroopStatistics(_v_)));
        this.records = new java.util.concurrent.CopyOnWriteArrayList<TroopRecord>();
		_o_.records.forEach(_v_ -> this.records.add(new TroopRecord(_v_)));
        this.lastDailyFresh = _o_.lastDailyFresh;
        this.lastWeeklyFresh = _o_.lastWeeklyFresh;
        this.instructors = new java.util.concurrent.CopyOnWriteArraySet<Long>();
		this.instructors.forEach(_v_ -> this.instructors.add(_v_));
        this.acceptMerge = _o_.acceptMerge;
        this.relateMerge = _o_.relateMerge;
        this.leaderImmureCount = _o_.leaderImmureCount;
        this.positionName = new java.util.concurrent.ConcurrentHashMap<Integer, String>();
		_o_.positionName.forEach((_k_, _v_) -> this.positionName.put(_k_, _v_));
        this.level = _o_.level;
        this.exp = _o_.exp;
        this.nextGroupId = _o_.nextGroupId;
        this.composeTech = new java.util.concurrent.ConcurrentHashMap<Integer, FactionComposeTech>();
		_o_.composeTech.forEach((_k_, _v_) -> this.composeTech.put(_k_, new FactionComposeTech(_v_)));
        this.composeMark = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.composeMark.forEach((_k_, _v_) -> this.composeMark.put(_k_, _v_));
        this.resHpMax = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.resHpMax.forEach((_k_, _v_) -> this.resHpMax.put(_k_, _v_));
        this.resHpRemain = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.resHpRemain.forEach((_k_, _v_) -> this.resHpRemain.put(_k_, _v_));
        this.wildStartTime = _o_.wildStartTime;
        this.wildTotalScore = _o_.wildTotalScore;
        this.wildJoinPlayerMap = new java.util.concurrent.ConcurrentHashMap<Long, Integer>();
		_o_.wildJoinPlayerMap.forEach((_k_, _v_) -> this.wildJoinPlayerMap.put(_k_, _v_));
        this.strategy = new TroopStrategy(_o_.strategy, this);
        this.makeItemCache = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.makeItemCache.forEach((_k_, _v_) -> this.makeItemCache.put(_k_, _v_));
        this.dailyBuyResMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dailyBuyResMap.forEach((_k_, _v_) -> this.dailyBuyResMap.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public long getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public String getName(){
		return this.name;
	}

	@JSONField(name = "3")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getUnderling(){
		return this.underling;
	}

	@JSONField(name = "4")
	public long getGid(){
		return this.gid;
	}

	@JSONField(name = "5")
	public int getType(){
		return this.type;
	}

	@JSONField(name = "6")
	public long getLeader(){
		return this.leader;
	}

	@JSONField(name = "7")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "8")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getCandidates(){
		return this.candidates;
	}

	@JSONField(name = "9")
	public long getLastElectTime(){
		return this.lastElectTime;
	}

	@JSONField(name = "10")
	public long getSupior(){
		return this.supior;
	}

	@JSONField(name = "11")
	public boolean getSystem(){
		return this.system;
	}

	@JSONField(name = "12")
	public boolean getOpen(){
		return this.open;
	}

	@JSONField(name = "13")
	public long getRelateFormId(){
		return this.relateFormId;
	}

	@JSONField(name = "14")
	public long getAutoElectTime(){
		return this.autoElectTime;
	}

	@JSONField(name = "15")
	public xbean.lobby.CampaignInfo getCampaign(){
		return this.campaign;
	}

	@JSONField(name = "16")
	public java.util.concurrent.ConcurrentHashMap<Long, RankInfo> getRanks(){
		return this.ranks;
	}

	@JSONField(name = "17")
	public int getLeaderDeclareCount(){
		return this.leaderDeclareCount;
	}

	@JSONField(name = "18")
	public java.util.concurrent.ConcurrentHashMap<Long, TroopExploitMember> getExploitInfo(){
		return this.exploitInfo;
	}

	@JSONField(name = "19")
	public java.util.concurrent.ConcurrentHashMap<Long, MemberExploitInfo> getActExploit(){
		return this.actExploit;
	}

	@JSONField(name = "20")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getMemberHoldSum(){
		return this.memberHoldSum;
	}

	@JSONField(name = "21")
	public java.util.concurrent.ConcurrentHashMap<Integer, TroopStatistics> getTroopStatisticsMap(){
		return this.troopStatisticsMap;
	}

	@JSONField(name = "22")
	public java.util.concurrent.CopyOnWriteArrayList<TroopRecord> getRecords(){
		return this.records;
	}

	@JSONField(name = "23")
	public long getLastDailyFresh(){
		return this.lastDailyFresh;
	}

	@JSONField(name = "24")
	public long getLastWeeklyFresh(){
		return this.lastWeeklyFresh;
	}

	@JSONField(name = "26")
	public java.util.concurrent.CopyOnWriteArraySet<Long> getInstructors(){
		return this.instructors;
	}

	@JSONField(name = "27")
	public int getAcceptMerge(){
		return this.acceptMerge;
	}

	@JSONField(name = "28")
	public long getRelateMerge(){
		return this.relateMerge;
	}

	@JSONField(name = "29")
	public int getLeaderImmureCount(){
		return this.leaderImmureCount;
	}

	@JSONField(name = "30")
	public java.util.concurrent.ConcurrentHashMap<Integer, String> getPositionName(){
		return this.positionName;
	}

	@JSONField(name = "31")
	public int getLevel(){
		return this.level;
	}

	@JSONField(name = "32")
	public int getExp(){
		return this.exp;
	}

	@JSONField(name = "33")
	public int getNextGroupId(){
		return this.nextGroupId;
	}

	@JSONField(name = "34")
	public java.util.concurrent.ConcurrentHashMap<Integer, FactionComposeTech> getComposeTech(){
		return this.composeTech;
	}

	@JSONField(name = "35")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getComposeMark(){
		return this.composeMark;
	}

	@JSONField(name = "36")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getResHpMax(){
		return this.resHpMax;
	}

	@JSONField(name = "37")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getResHpRemain(){
		return this.resHpRemain;
	}

	@JSONField(name = "38")
	public long getWildStartTime(){
		return this.wildStartTime;
	}

	@JSONField(name = "39")
	public int getWildTotalScore(){
		return this.wildTotalScore;
	}

	@JSONField(name = "40")
	public java.util.concurrent.ConcurrentHashMap<Long, Integer> getWildJoinPlayerMap(){
		return this.wildJoinPlayerMap;
	}

	@JSONField(name = "41")
	public xbean.lobby.TroopStrategy getStrategy(){
		return this.strategy;
	}

	@JSONField(name = "42")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getMakeItemCache(){
		return this.makeItemCache;
	}

	@JSONField(name = "43")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDailyBuyResMap(){
		return this.dailyBuyResMap;
	}

	@JSONField(name = "1")
	public void setId(long _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setName(String _v_){
		this.name = _v_;
	}

	@JSONField(name = "3")
	public void setUnderling(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.underling = _v_;
	}

	@JSONField(name = "4")
	public void setGid(long _v_){
		this.gid = _v_;
	}

	@JSONField(name = "5")
	public void setType(int _v_){
		this.type = _v_;
	}

	@JSONField(name = "6")
	public void setLeader(long _v_){
		this.leader = _v_;
	}

	@JSONField(name = "7")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "8")
	public void setCandidates(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.candidates = _v_;
	}

	@JSONField(name = "9")
	public void setLastElectTime(long _v_){
		this.lastElectTime = _v_;
	}

	@JSONField(name = "10")
	public void setSupior(long _v_){
		this.supior = _v_;
	}

	@JSONField(name = "11")
	public void setSystem(boolean _v_){
		this.system = _v_;
	}

	@JSONField(name = "12")
	public void setOpen(boolean _v_){
		this.open = _v_;
	}

	@JSONField(name = "13")
	public void setRelateFormId(long _v_){
		this.relateFormId = _v_;
	}

	@JSONField(name = "14")
	public void setAutoElectTime(long _v_){
		this.autoElectTime = _v_;
	}

	@JSONField(name = "15")
	public void setCampaign(xbean.lobby.CampaignInfo _v_){
		this.campaign = _v_;
	}

	@JSONField(name = "16")
	public void setRanks(java.util.concurrent.ConcurrentHashMap<Long, RankInfo> _v_){
		this.ranks = _v_;
	}

	@JSONField(name = "17")
	public void setLeaderDeclareCount(int _v_){
		this.leaderDeclareCount = _v_;
	}

	@JSONField(name = "18")
	public void setExploitInfo(java.util.concurrent.ConcurrentHashMap<Long, TroopExploitMember> _v_){
		this.exploitInfo = _v_;
	}

	@JSONField(name = "19")
	public void setActExploit(java.util.concurrent.ConcurrentHashMap<Long, MemberExploitInfo> _v_){
		this.actExploit = _v_;
	}

	@JSONField(name = "20")
	public void setMemberHoldSum(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.memberHoldSum = _v_;
	}

	@JSONField(name = "21")
	public void setTroopStatisticsMap(java.util.concurrent.ConcurrentHashMap<Integer, TroopStatistics> _v_){
		this.troopStatisticsMap = _v_;
	}

	@JSONField(name = "22")
	public void setRecords(java.util.concurrent.CopyOnWriteArrayList<TroopRecord> _v_){
		this.records = _v_;
	}

	@JSONField(name = "23")
	public void setLastDailyFresh(long _v_){
		this.lastDailyFresh = _v_;
	}

	@JSONField(name = "24")
	public void setLastWeeklyFresh(long _v_){
		this.lastWeeklyFresh = _v_;
	}

	@JSONField(name = "26")
	public void setInstructors(java.util.concurrent.CopyOnWriteArraySet<Long> _v_){
		this.instructors = _v_;
	}

	@JSONField(name = "27")
	public void setAcceptMerge(int _v_){
		this.acceptMerge = _v_;
	}

	@JSONField(name = "28")
	public void setRelateMerge(long _v_){
		this.relateMerge = _v_;
	}

	@JSONField(name = "29")
	public void setLeaderImmureCount(int _v_){
		this.leaderImmureCount = _v_;
	}

	@JSONField(name = "30")
	public void setPositionName(java.util.concurrent.ConcurrentHashMap<Integer, String> _v_){
		this.positionName = _v_;
	}

	@JSONField(name = "31")
	public void setLevel(int _v_){
		this.level = _v_;
	}

	@JSONField(name = "32")
	public void setExp(int _v_){
		this.exp = _v_;
	}

	@JSONField(name = "33")
	public void setNextGroupId(int _v_){
		this.nextGroupId = _v_;
	}

	@JSONField(name = "34")
	public void setComposeTech(java.util.concurrent.ConcurrentHashMap<Integer, FactionComposeTech> _v_){
		this.composeTech = _v_;
	}

	@JSONField(name = "35")
	public void setComposeMark(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.composeMark = _v_;
	}

	@JSONField(name = "36")
	public void setResHpMax(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.resHpMax = _v_;
	}

	@JSONField(name = "37")
	public void setResHpRemain(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.resHpRemain = _v_;
	}

	@JSONField(name = "38")
	public void setWildStartTime(long _v_){
		this.wildStartTime = _v_;
	}

	@JSONField(name = "39")
	public void setWildTotalScore(int _v_){
		this.wildTotalScore = _v_;
	}

	@JSONField(name = "40")
	public void setWildJoinPlayerMap(java.util.concurrent.ConcurrentHashMap<Long, Integer> _v_){
		this.wildJoinPlayerMap = _v_;
	}

	@JSONField(name = "41")
	public void setStrategy(xbean.lobby.TroopStrategy _v_){
		this.strategy = _v_;
	}

	@JSONField(name = "42")
	public void setMakeItemCache(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.makeItemCache = _v_;
	}

	@JSONField(name = "43")
	public void setDailyBuyResMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.dailyBuyResMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Troop) {
			Troop _o_ = (Troop)_o1_;
			if (this.id != _o_.id) return false;
			if (!this.name.equals(_o_.name)) return false;
			if (!this.underling.equals(_o_.underling)) return false;
			if (this.gid != _o_.gid) return false;
			if (this.type != _o_.type) return false;
			if (this.leader != _o_.leader) return false;
			if (this.faction != _o_.faction) return false;
			if (!this.candidates.equals(_o_.candidates)) return false;
			if (this.lastElectTime != _o_.lastElectTime) return false;
			if (this.supior != _o_.supior) return false;
			if (this.system != _o_.system) return false;
			if (this.open != _o_.open) return false;
			if (this.relateFormId != _o_.relateFormId) return false;
			if (this.autoElectTime != _o_.autoElectTime) return false;
			if (!this.campaign.equals(_o_.campaign)) return false;
			if (!this.ranks.equals(_o_.ranks)) return false;
			if (this.leaderDeclareCount != _o_.leaderDeclareCount) return false;
			if (!this.exploitInfo.equals(_o_.exploitInfo)) return false;
			if (!this.actExploit.equals(_o_.actExploit)) return false;
			if (!this.memberHoldSum.equals(_o_.memberHoldSum)) return false;
			if (!this.troopStatisticsMap.equals(_o_.troopStatisticsMap)) return false;
			if (!this.records.equals(_o_.records)) return false;
			if (this.lastDailyFresh != _o_.lastDailyFresh) return false;
			if (this.lastWeeklyFresh != _o_.lastWeeklyFresh) return false;
			if (!this.instructors.equals(_o_.instructors)) return false;
			if (this.acceptMerge != _o_.acceptMerge) return false;
			if (this.relateMerge != _o_.relateMerge) return false;
			if (this.leaderImmureCount != _o_.leaderImmureCount) return false;
			if (!this.positionName.equals(_o_.positionName)) return false;
			if (this.level != _o_.level) return false;
			if (this.exp != _o_.exp) return false;
			if (this.nextGroupId != _o_.nextGroupId) return false;
			if (!this.composeTech.equals(_o_.composeTech)) return false;
			if (!this.composeMark.equals(_o_.composeMark)) return false;
			if (!this.resHpMax.equals(_o_.resHpMax)) return false;
			if (!this.resHpRemain.equals(_o_.resHpRemain)) return false;
			if (this.wildStartTime != _o_.wildStartTime) return false;
			if (this.wildTotalScore != _o_.wildTotalScore) return false;
			if (!this.wildJoinPlayerMap.equals(_o_.wildJoinPlayerMap)) return false;
			if (!this.strategy.equals(_o_.strategy)) return false;
			if (!this.makeItemCache.equals(_o_.makeItemCache)) return false;
			if (!this.dailyBuyResMap.equals(_o_.dailyBuyResMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(id ^ (id >>> 32));
		_h_ += _h_ * 31 + this.name.hashCode();
		_h_ += _h_ * 31 + this.underling.hashCode();
		_h_ += _h_ * 31 + (int)(gid ^ (gid >>> 32));
		_h_ += _h_ * 31 + (int)(this.type);
		_h_ += _h_ * 31 + (int)(leader ^ (leader >>> 32));
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + this.candidates.hashCode();
		_h_ += _h_ * 31 + (int)(lastElectTime ^ (lastElectTime >>> 32));
		_h_ += _h_ * 31 + (int)(supior ^ (supior >>> 32));
		_h_ += _h_ * 31 + (this.system ? 1231 : 1237);
		_h_ += _h_ * 31 + (this.open ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(relateFormId ^ (relateFormId >>> 32));
		_h_ += _h_ * 31 + (int)(autoElectTime ^ (autoElectTime >>> 32));
		_h_ += _h_ * 31 + this.campaign.hashCode();
		_h_ += _h_ * 31 + this.ranks.hashCode();
		_h_ += _h_ * 31 + (int)(this.leaderDeclareCount);
		_h_ += _h_ * 31 + this.exploitInfo.hashCode();
		_h_ += _h_ * 31 + this.actExploit.hashCode();
		_h_ += _h_ * 31 + this.memberHoldSum.hashCode();
		_h_ += _h_ * 31 + this.troopStatisticsMap.hashCode();
		_h_ += _h_ * 31 + this.records.hashCode();
		_h_ += _h_ * 31 + (int)(lastDailyFresh ^ (lastDailyFresh >>> 32));
		_h_ += _h_ * 31 + (int)(lastWeeklyFresh ^ (lastWeeklyFresh >>> 32));
		_h_ += _h_ * 31 + this.instructors.hashCode();
		_h_ += _h_ * 31 + (int)(this.acceptMerge);
		_h_ += _h_ * 31 + (int)(relateMerge ^ (relateMerge >>> 32));
		_h_ += _h_ * 31 + (int)(this.leaderImmureCount);
		_h_ += _h_ * 31 + this.positionName.hashCode();
		_h_ += _h_ * 31 + (int)(this.level);
		_h_ += _h_ * 31 + (int)(this.exp);
		_h_ += _h_ * 31 + (int)(this.nextGroupId);
		_h_ += _h_ * 31 + this.composeTech.hashCode();
		_h_ += _h_ * 31 + this.composeMark.hashCode();
		_h_ += _h_ * 31 + this.resHpMax.hashCode();
		_h_ += _h_ * 31 + this.resHpRemain.hashCode();
		_h_ += _h_ * 31 + (int)(wildStartTime ^ (wildStartTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.wildTotalScore);
		_h_ += _h_ * 31 + this.wildJoinPlayerMap.hashCode();
		_h_ += _h_ * 31 + this.strategy.hashCode();
		_h_ += _h_ * 31 + this.makeItemCache.hashCode();
		_h_ += _h_ * 31 + this.dailyBuyResMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append("T").append(name.length()).append(",");
		_sb_.append(underling).append(",");
		_sb_.append(gid).append(",");
		_sb_.append(type).append(",");
		_sb_.append(leader).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(candidates).append(",");
		_sb_.append(lastElectTime).append(",");
		_sb_.append(supior).append(",");
		_sb_.append(system).append(",");
		_sb_.append(open).append(",");
		_sb_.append(relateFormId).append(",");
		_sb_.append(autoElectTime).append(",");
		_sb_.append(campaign).append(",");
		_sb_.append(ranks).append(",");
		_sb_.append(leaderDeclareCount).append(",");
		_sb_.append(exploitInfo).append(",");
		_sb_.append(actExploit).append(",");
		_sb_.append(memberHoldSum).append(",");
		_sb_.append(troopStatisticsMap).append(",");
		_sb_.append(records).append(",");
		_sb_.append(lastDailyFresh).append(",");
		_sb_.append(lastWeeklyFresh).append(",");
		_sb_.append(instructors).append(",");
		_sb_.append(acceptMerge).append(",");
		_sb_.append(relateMerge).append(",");
		_sb_.append(leaderImmureCount).append(",");
		_sb_.append(positionName).append(",");
		_sb_.append(level).append(",");
		_sb_.append(exp).append(",");
		_sb_.append(nextGroupId).append(",");
		_sb_.append(composeTech).append(",");
		_sb_.append(composeMark).append(",");
		_sb_.append(resHpMax).append(",");
		_sb_.append(resHpRemain).append(",");
		_sb_.append(wildStartTime).append(",");
		_sb_.append(wildTotalScore).append(",");
		_sb_.append(wildJoinPlayerMap).append(",");
		_sb_.append(strategy).append(",");
		_sb_.append(makeItemCache).append(",");
		_sb_.append(dailyBuyResMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
