package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class Faction extends warrior.mdb.XBean {
    private int id; 
    private int totalDonate;  // 累计捐献数量
    private java.util.concurrent.ConcurrentHashMap<Integer, FactionTech> tech;  // 科技等级
    private long lastDailyRefresh;  // 上次每日刷新时间戳
    private int memberCount;  // 人数
    private int dailyCostSupplyPoint;  // 已消耗补给点
    private java.util.concurrent.ConcurrentHashMap<Integer, Float> balanceEffect;  // 平衡buff
    private java.util.concurrent.CopyOnWriteArraySet<Integer> occupyLands;  // 占领据点
    private java.util.concurrent.ConcurrentHashMap<Integer, FactionOccupyFactoryFixBean> damageLandInfo;  // 工厂损坏据点
    private java.util.concurrent.CopyOnWriteArrayList<Long> donateRank;  // 普通捐献排行
    private java.util.concurrent.ConcurrentHashMap<Long, FactionDonateRankBean> dailyDonateAllData;  // 当天所有捐献数据
    private java.util.concurrent.CopyOnWriteArrayList<Long> fixRank;  // 本轮工厂修复排行
    private java.util.concurrent.ConcurrentHashMap<Long, FactionDonateRankBean> fixAllData;  // 本轮工厂修复数据
    private java.util.concurrent.ConcurrentHashMap<Integer, Float> donateEffect;  // 捐献buff
    private java.util.concurrent.ConcurrentHashMap<Integer, Float> occupyEffect;  // 区域占领buff
    private java.util.concurrent.ConcurrentHashMap<Integer, FactionComposeTech> composeTech;  // 制造科技等级
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> composeMark;  // 制造科技推荐
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> carrierNumMap;  // 阵营当前载具数量
    private java.util.concurrent.ConcurrentHashMap<Integer, FactionMobiInfo> mobiBuff;  // 动员buff信息
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> resCollectMobi;  // 资源工单动员buff
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> resCollectMobiDailyCount;  // 资源工单动员buff当天激活次数
    private java.util.concurrent.ConcurrentHashMap<Integer, FactionTarget> targetMap;  // 阵营目标map
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> todayResOutput;  // 今日阵营产出的资源
    private java.util.concurrent.ConcurrentHashMap<Integer, PassiveTech> passiveTech;  // 被动科技
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> todayResOutputLandNum;  // 今日阵营产出的资源地块数量
    private long teleportDeclareTime;  // 已经快速集结过的公会宣战时间

    Faction(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.tech = new java.util.concurrent.ConcurrentHashMap<Integer, FactionTech>();
		this.balanceEffect = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		this.occupyLands = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.damageLandInfo = new java.util.concurrent.ConcurrentHashMap<Integer, FactionOccupyFactoryFixBean>();
		this.donateRank = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		this.dailyDonateAllData = new java.util.concurrent.ConcurrentHashMap<Long, FactionDonateRankBean>();
		this.fixRank = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		this.fixAllData = new java.util.concurrent.ConcurrentHashMap<Long, FactionDonateRankBean>();
		this.donateEffect = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		this.occupyEffect = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		this.composeTech = new java.util.concurrent.ConcurrentHashMap<Integer, FactionComposeTech>();
		this.composeMark = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		this.carrierNumMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.mobiBuff = new java.util.concurrent.ConcurrentHashMap<Integer, FactionMobiInfo>();
		this.resCollectMobi = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		this.resCollectMobiDailyCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.targetMap = new java.util.concurrent.ConcurrentHashMap<Integer, FactionTarget>();
		this.todayResOutput = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		this.passiveTech = new java.util.concurrent.ConcurrentHashMap<Integer, PassiveTech>();
		this.todayResOutputLandNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public Faction() {
		this((warrior.mdb.XBean)null);
	}

	public Faction(Faction _o_) {
		this(_o_, null);
	}

	Faction(Faction _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.totalDonate = _o_.totalDonate;
		this.tech = new java.util.concurrent.ConcurrentHashMap<Integer, FactionTech>();
		_o_.tech.forEach((_k_, _v_) -> this.tech.put(_k_, new FactionTech(_v_)));
		this.lastDailyRefresh = _o_.lastDailyRefresh;
		this.memberCount = _o_.memberCount;
		this.dailyCostSupplyPoint = _o_.dailyCostSupplyPoint;
		this.balanceEffect = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.balanceEffect.forEach((_k_, _v_) -> this.balanceEffect.put(_k_, _v_));
		this.occupyLands = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.occupyLands.forEach(_v_ -> this.occupyLands.add(_v_));
		this.damageLandInfo = new java.util.concurrent.ConcurrentHashMap<Integer, FactionOccupyFactoryFixBean>();
		_o_.damageLandInfo.forEach((_k_, _v_) -> this.damageLandInfo.put(_k_, new FactionOccupyFactoryFixBean(_v_)));
		this.donateRank = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.donateRank.forEach(_v_ -> this.donateRank.add(_v_));
		this.dailyDonateAllData = new java.util.concurrent.ConcurrentHashMap<Long, FactionDonateRankBean>();
		_o_.dailyDonateAllData.forEach((_k_, _v_) -> this.dailyDonateAllData.put(_k_, new FactionDonateRankBean(_v_)));
		this.fixRank = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.fixRank.forEach(_v_ -> this.fixRank.add(_v_));
		this.fixAllData = new java.util.concurrent.ConcurrentHashMap<Long, FactionDonateRankBean>();
		_o_.fixAllData.forEach((_k_, _v_) -> this.fixAllData.put(_k_, new FactionDonateRankBean(_v_)));
		this.donateEffect = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.donateEffect.forEach((_k_, _v_) -> this.donateEffect.put(_k_, _v_));
		this.occupyEffect = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.occupyEffect.forEach((_k_, _v_) -> this.occupyEffect.put(_k_, _v_));
		this.composeTech = new java.util.concurrent.ConcurrentHashMap<Integer, FactionComposeTech>();
		_o_.composeTech.forEach((_k_, _v_) -> this.composeTech.put(_k_, new FactionComposeTech(_v_)));
		this.composeMark = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.composeMark.forEach((_k_, _v_) -> this.composeMark.put(_k_, _v_));
		this.carrierNumMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.carrierNumMap.forEach((_k_, _v_) -> this.carrierNumMap.put(_k_, _v_));
		this.mobiBuff = new java.util.concurrent.ConcurrentHashMap<Integer, FactionMobiInfo>();
		_o_.mobiBuff.forEach((_k_, _v_) -> this.mobiBuff.put(_k_, new FactionMobiInfo(_v_)));
		this.resCollectMobi = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.resCollectMobi.forEach((_k_, _v_) -> this.resCollectMobi.put(_k_, _v_));
		this.resCollectMobiDailyCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.resCollectMobiDailyCount.forEach((_k_, _v_) -> this.resCollectMobiDailyCount.put(_k_, _v_));
		this.targetMap = new java.util.concurrent.ConcurrentHashMap<Integer, FactionTarget>();
		_o_.targetMap.forEach((_k_, _v_) -> this.targetMap.put(_k_, new FactionTarget(_v_)));
		this.todayResOutput = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.todayResOutput.forEach((_k_, _v_) -> this.todayResOutput.put(_k_, _v_));
		this.passiveTech = new java.util.concurrent.ConcurrentHashMap<Integer, PassiveTech>();
		_o_.passiveTech.forEach((_k_, _v_) -> this.passiveTech.put(_k_, new PassiveTech(_v_)));
		this.todayResOutputLandNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.todayResOutputLandNum.forEach((_k_, _v_) -> this.todayResOutputLandNum.put(_k_, _v_));
		this.teleportDeclareTime = _o_.teleportDeclareTime;
	}

	public void copyFrom(Faction _o_) {
        this.id = _o_.id;
        this.totalDonate = _o_.totalDonate;
        this.tech = new java.util.concurrent.ConcurrentHashMap<Integer, FactionTech>();
		_o_.tech.forEach((_k_, _v_) -> this.tech.put(_k_, new FactionTech(_v_)));
        this.lastDailyRefresh = _o_.lastDailyRefresh;
        this.memberCount = _o_.memberCount;
        this.dailyCostSupplyPoint = _o_.dailyCostSupplyPoint;
        this.balanceEffect = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.balanceEffect.forEach((_k_, _v_) -> this.balanceEffect.put(_k_, _v_));
        this.occupyLands = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.occupyLands.forEach(_v_ -> this.occupyLands.add(_v_));
        this.damageLandInfo = new java.util.concurrent.ConcurrentHashMap<Integer, FactionOccupyFactoryFixBean>();
		_o_.damageLandInfo.forEach((_k_, _v_) -> this.damageLandInfo.put(_k_, new FactionOccupyFactoryFixBean(_v_)));
        this.donateRank = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.donateRank.forEach(_v_ -> this.donateRank.add(_v_));
        this.dailyDonateAllData = new java.util.concurrent.ConcurrentHashMap<Long, FactionDonateRankBean>();
		_o_.dailyDonateAllData.forEach((_k_, _v_) -> this.dailyDonateAllData.put(_k_, new FactionDonateRankBean(_v_)));
        this.fixRank = new java.util.concurrent.CopyOnWriteArrayList<Long>();
		_o_.fixRank.forEach(_v_ -> this.fixRank.add(_v_));
        this.fixAllData = new java.util.concurrent.ConcurrentHashMap<Long, FactionDonateRankBean>();
		_o_.fixAllData.forEach((_k_, _v_) -> this.fixAllData.put(_k_, new FactionDonateRankBean(_v_)));
        this.donateEffect = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.donateEffect.forEach((_k_, _v_) -> this.donateEffect.put(_k_, _v_));
        this.occupyEffect = new java.util.concurrent.ConcurrentHashMap<Integer, Float>();
		_o_.occupyEffect.forEach((_k_, _v_) -> this.occupyEffect.put(_k_, _v_));
        this.composeTech = new java.util.concurrent.ConcurrentHashMap<Integer, FactionComposeTech>();
		_o_.composeTech.forEach((_k_, _v_) -> this.composeTech.put(_k_, new FactionComposeTech(_v_)));
        this.composeMark = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.composeMark.forEach((_k_, _v_) -> this.composeMark.put(_k_, _v_));
        this.carrierNumMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.carrierNumMap.forEach((_k_, _v_) -> this.carrierNumMap.put(_k_, _v_));
        this.mobiBuff = new java.util.concurrent.ConcurrentHashMap<Integer, FactionMobiInfo>();
		_o_.mobiBuff.forEach((_k_, _v_) -> this.mobiBuff.put(_k_, new FactionMobiInfo(_v_)));
        this.resCollectMobi = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.resCollectMobi.forEach((_k_, _v_) -> this.resCollectMobi.put(_k_, _v_));
        this.resCollectMobiDailyCount = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.resCollectMobiDailyCount.forEach((_k_, _v_) -> this.resCollectMobiDailyCount.put(_k_, _v_));
        this.targetMap = new java.util.concurrent.ConcurrentHashMap<Integer, FactionTarget>();
		_o_.targetMap.forEach((_k_, _v_) -> this.targetMap.put(_k_, new FactionTarget(_v_)));
        this.todayResOutput = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.todayResOutput.forEach((_k_, _v_) -> this.todayResOutput.put(_k_, _v_));
        this.passiveTech = new java.util.concurrent.ConcurrentHashMap<Integer, PassiveTech>();
		_o_.passiveTech.forEach((_k_, _v_) -> this.passiveTech.put(_k_, new PassiveTech(_v_)));
        this.todayResOutputLandNum = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.todayResOutputLandNum.forEach((_k_, _v_) -> this.todayResOutputLandNum.put(_k_, _v_));
        this.teleportDeclareTime = _o_.teleportDeclareTime;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "3")
	public int getTotalDonate(){
		return this.totalDonate;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Integer, FactionTech> getTech(){
		return this.tech;
	}

	@JSONField(name = "5")
	public long getLastDailyRefresh(){
		return this.lastDailyRefresh;
	}

	@JSONField(name = "6")
	public int getMemberCount(){
		return this.memberCount;
	}

	@JSONField(name = "7")
	public int getDailyCostSupplyPoint(){
		return this.dailyCostSupplyPoint;
	}

	@JSONField(name = "10")
	public java.util.concurrent.ConcurrentHashMap<Integer, Float> getBalanceEffect(){
		return this.balanceEffect;
	}

	@JSONField(name = "11")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getOccupyLands(){
		return this.occupyLands;
	}

	@JSONField(name = "12")
	public java.util.concurrent.ConcurrentHashMap<Integer, FactionOccupyFactoryFixBean> getDamageLandInfo(){
		return this.damageLandInfo;
	}

	@JSONField(name = "13")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getDonateRank(){
		return this.donateRank;
	}

	@JSONField(name = "14")
	public java.util.concurrent.ConcurrentHashMap<Long, FactionDonateRankBean> getDailyDonateAllData(){
		return this.dailyDonateAllData;
	}

	@JSONField(name = "15")
	public java.util.concurrent.CopyOnWriteArrayList<Long> getFixRank(){
		return this.fixRank;
	}

	@JSONField(name = "17")
	public java.util.concurrent.ConcurrentHashMap<Long, FactionDonateRankBean> getFixAllData(){
		return this.fixAllData;
	}

	@JSONField(name = "18")
	public java.util.concurrent.ConcurrentHashMap<Integer, Float> getDonateEffect(){
		return this.donateEffect;
	}

	@JSONField(name = "19")
	public java.util.concurrent.ConcurrentHashMap<Integer, Float> getOccupyEffect(){
		return this.occupyEffect;
	}

	@JSONField(name = "22")
	public java.util.concurrent.ConcurrentHashMap<Integer, FactionComposeTech> getComposeTech(){
		return this.composeTech;
	}

	@JSONField(name = "23")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getComposeMark(){
		return this.composeMark;
	}

	@JSONField(name = "24")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getCarrierNumMap(){
		return this.carrierNumMap;
	}

	@JSONField(name = "25")
	public java.util.concurrent.ConcurrentHashMap<Integer, FactionMobiInfo> getMobiBuff(){
		return this.mobiBuff;
	}

	@JSONField(name = "26")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getResCollectMobi(){
		return this.resCollectMobi;
	}

	@JSONField(name = "27")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getResCollectMobiDailyCount(){
		return this.resCollectMobiDailyCount;
	}

	@JSONField(name = "28")
	public java.util.concurrent.ConcurrentHashMap<Integer, FactionTarget> getTargetMap(){
		return this.targetMap;
	}

	@JSONField(name = "29")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getTodayResOutput(){
		return this.todayResOutput;
	}

	@JSONField(name = "30")
	public java.util.concurrent.ConcurrentHashMap<Integer, PassiveTech> getPassiveTech(){
		return this.passiveTech;
	}

	@JSONField(name = "31")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getTodayResOutputLandNum(){
		return this.todayResOutputLandNum;
	}

	@JSONField(name = "32")
	public long getTeleportDeclareTime(){
		return this.teleportDeclareTime;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "3")
	public void setTotalDonate(int _v_){
		this.totalDonate = _v_;
	}

	@JSONField(name = "4")
	public void setTech(java.util.concurrent.ConcurrentHashMap<Integer, FactionTech> _v_){
		this.tech = _v_;
	}

	@JSONField(name = "5")
	public void setLastDailyRefresh(long _v_){
		this.lastDailyRefresh = _v_;
	}

	@JSONField(name = "6")
	public void setMemberCount(int _v_){
		this.memberCount = _v_;
	}

	@JSONField(name = "7")
	public void setDailyCostSupplyPoint(int _v_){
		this.dailyCostSupplyPoint = _v_;
	}

	@JSONField(name = "10")
	public void setBalanceEffect(java.util.concurrent.ConcurrentHashMap<Integer, Float> _v_){
		this.balanceEffect = _v_;
	}

	@JSONField(name = "11")
	public void setOccupyLands(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.occupyLands = _v_;
	}

	@JSONField(name = "12")
	public void setDamageLandInfo(java.util.concurrent.ConcurrentHashMap<Integer, FactionOccupyFactoryFixBean> _v_){
		this.damageLandInfo = _v_;
	}

	@JSONField(name = "13")
	public void setDonateRank(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.donateRank = _v_;
	}

	@JSONField(name = "14")
	public void setDailyDonateAllData(java.util.concurrent.ConcurrentHashMap<Long, FactionDonateRankBean> _v_){
		this.dailyDonateAllData = _v_;
	}

	@JSONField(name = "15")
	public void setFixRank(java.util.concurrent.CopyOnWriteArrayList<Long> _v_){
		this.fixRank = _v_;
	}

	@JSONField(name = "17")
	public void setFixAllData(java.util.concurrent.ConcurrentHashMap<Long, FactionDonateRankBean> _v_){
		this.fixAllData = _v_;
	}

	@JSONField(name = "18")
	public void setDonateEffect(java.util.concurrent.ConcurrentHashMap<Integer, Float> _v_){
		this.donateEffect = _v_;
	}

	@JSONField(name = "19")
	public void setOccupyEffect(java.util.concurrent.ConcurrentHashMap<Integer, Float> _v_){
		this.occupyEffect = _v_;
	}

	@JSONField(name = "22")
	public void setComposeTech(java.util.concurrent.ConcurrentHashMap<Integer, FactionComposeTech> _v_){
		this.composeTech = _v_;
	}

	@JSONField(name = "23")
	public void setComposeMark(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.composeMark = _v_;
	}

	@JSONField(name = "24")
	public void setCarrierNumMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.carrierNumMap = _v_;
	}

	@JSONField(name = "25")
	public void setMobiBuff(java.util.concurrent.ConcurrentHashMap<Integer, FactionMobiInfo> _v_){
		this.mobiBuff = _v_;
	}

	@JSONField(name = "26")
	public void setResCollectMobi(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.resCollectMobi = _v_;
	}

	@JSONField(name = "27")
	public void setResCollectMobiDailyCount(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.resCollectMobiDailyCount = _v_;
	}

	@JSONField(name = "28")
	public void setTargetMap(java.util.concurrent.ConcurrentHashMap<Integer, FactionTarget> _v_){
		this.targetMap = _v_;
	}

	@JSONField(name = "29")
	public void setTodayResOutput(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.todayResOutput = _v_;
	}

	@JSONField(name = "30")
	public void setPassiveTech(java.util.concurrent.ConcurrentHashMap<Integer, PassiveTech> _v_){
		this.passiveTech = _v_;
	}

	@JSONField(name = "31")
	public void setTodayResOutputLandNum(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.todayResOutputLandNum = _v_;
	}

	@JSONField(name = "32")
	public void setTeleportDeclareTime(long _v_){
		this.teleportDeclareTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Faction) {
			Faction _o_ = (Faction)_o1_;
			if (this.id != _o_.id) return false;
			if (this.totalDonate != _o_.totalDonate) return false;
			if (!this.tech.equals(_o_.tech)) return false;
			if (this.lastDailyRefresh != _o_.lastDailyRefresh) return false;
			if (this.memberCount != _o_.memberCount) return false;
			if (this.dailyCostSupplyPoint != _o_.dailyCostSupplyPoint) return false;
			if (!this.balanceEffect.equals(_o_.balanceEffect)) return false;
			if (!this.occupyLands.equals(_o_.occupyLands)) return false;
			if (!this.damageLandInfo.equals(_o_.damageLandInfo)) return false;
			if (!this.donateRank.equals(_o_.donateRank)) return false;
			if (!this.dailyDonateAllData.equals(_o_.dailyDonateAllData)) return false;
			if (!this.fixRank.equals(_o_.fixRank)) return false;
			if (!this.fixAllData.equals(_o_.fixAllData)) return false;
			if (!this.donateEffect.equals(_o_.donateEffect)) return false;
			if (!this.occupyEffect.equals(_o_.occupyEffect)) return false;
			if (!this.composeTech.equals(_o_.composeTech)) return false;
			if (!this.composeMark.equals(_o_.composeMark)) return false;
			if (!this.carrierNumMap.equals(_o_.carrierNumMap)) return false;
			if (!this.mobiBuff.equals(_o_.mobiBuff)) return false;
			if (!this.resCollectMobi.equals(_o_.resCollectMobi)) return false;
			if (!this.resCollectMobiDailyCount.equals(_o_.resCollectMobiDailyCount)) return false;
			if (!this.targetMap.equals(_o_.targetMap)) return false;
			if (!this.todayResOutput.equals(_o_.todayResOutput)) return false;
			if (!this.passiveTech.equals(_o_.passiveTech)) return false;
			if (!this.todayResOutputLandNum.equals(_o_.todayResOutputLandNum)) return false;
			if (this.teleportDeclareTime != _o_.teleportDeclareTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.totalDonate);
		_h_ += _h_ * 31 + this.tech.hashCode();
		_h_ += _h_ * 31 + (int)(lastDailyRefresh ^ (lastDailyRefresh >>> 32));
		_h_ += _h_ * 31 + (int)(this.memberCount);
		_h_ += _h_ * 31 + (int)(this.dailyCostSupplyPoint);
		_h_ += _h_ * 31 + this.balanceEffect.hashCode();
		_h_ += _h_ * 31 + this.occupyLands.hashCode();
		_h_ += _h_ * 31 + this.damageLandInfo.hashCode();
		_h_ += _h_ * 31 + this.donateRank.hashCode();
		_h_ += _h_ * 31 + this.dailyDonateAllData.hashCode();
		_h_ += _h_ * 31 + this.fixRank.hashCode();
		_h_ += _h_ * 31 + this.fixAllData.hashCode();
		_h_ += _h_ * 31 + this.donateEffect.hashCode();
		_h_ += _h_ * 31 + this.occupyEffect.hashCode();
		_h_ += _h_ * 31 + this.composeTech.hashCode();
		_h_ += _h_ * 31 + this.composeMark.hashCode();
		_h_ += _h_ * 31 + this.carrierNumMap.hashCode();
		_h_ += _h_ * 31 + this.mobiBuff.hashCode();
		_h_ += _h_ * 31 + this.resCollectMobi.hashCode();
		_h_ += _h_ * 31 + this.resCollectMobiDailyCount.hashCode();
		_h_ += _h_ * 31 + this.targetMap.hashCode();
		_h_ += _h_ * 31 + this.todayResOutput.hashCode();
		_h_ += _h_ * 31 + this.passiveTech.hashCode();
		_h_ += _h_ * 31 + this.todayResOutputLandNum.hashCode();
		_h_ += _h_ * 31 + (int)(teleportDeclareTime ^ (teleportDeclareTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(totalDonate).append(",");
		_sb_.append(tech).append(",");
		_sb_.append(lastDailyRefresh).append(",");
		_sb_.append(memberCount).append(",");
		_sb_.append(dailyCostSupplyPoint).append(",");
		_sb_.append(balanceEffect).append(",");
		_sb_.append(occupyLands).append(",");
		_sb_.append(damageLandInfo).append(",");
		_sb_.append(donateRank).append(",");
		_sb_.append(dailyDonateAllData).append(",");
		_sb_.append(fixRank).append(",");
		_sb_.append(fixAllData).append(",");
		_sb_.append(donateEffect).append(",");
		_sb_.append(occupyEffect).append(",");
		_sb_.append(composeTech).append(",");
		_sb_.append(composeMark).append(",");
		_sb_.append(carrierNumMap).append(",");
		_sb_.append(mobiBuff).append(",");
		_sb_.append(resCollectMobi).append(",");
		_sb_.append(resCollectMobiDailyCount).append(",");
		_sb_.append(targetMap).append(",");
		_sb_.append(todayResOutput).append(",");
		_sb_.append(passiveTech).append(",");
		_sb_.append(todayResOutputLandNum).append(",");
		_sb_.append(teleportDeclareTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
