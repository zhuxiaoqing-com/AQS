package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBCityInfo extends warrior.mdb.XBean {
    public static final int FIGHTING = 1;   // 交战中
    public static final int OCCUPY = 2;   // 占领中

    private int areaId;  // 区域id
    private int state;  // 1：开战中 2：占领中
    private java.util.concurrent.ConcurrentHashMap<Integer, LBCityFlag> cityFlagMap;  // 城市夺旗数据
    private int lastSeasonFaction;  // 上一个领的该区域的阵营
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> factionOccupyTimeMap;  // 旗帜占领时间map k:阵营id v:占领时间
    private long nextCanFightMill;  // 下一次可开战时间
    private boolean isOpenFight;  // 下一次争夺战是否可以开启
    private xbean.lobby.LBGensBaseInfo meritGens;  // 功勋公会
    private int last2SeasonFaction;  // 上上个争夺器占领的该旗帜的阵营
    private java.util.concurrent.ConcurrentHashMap<Integer, LBCityFlagSave> flag2SaveMap;  // (旗帜配置id->LBCityFlagSave)
    private long startTime;  // 赛季开始时间(上个休战期结束时间)
    private long endTime;  // 赛季结束时间(下个休战期开始时间)
    private boolean start;  // 是否开启着
    private long finishTime;  // 升旗是否已经完成
    private long nextStartTime;  // 下一次开启时间
    private int occupyCount;  // 该主城被夺取次数
    private java.util.concurrent.CopyOnWriteArraySet<Integer> unlockTempReviveSet;  // 解锁的临时复活点

    LBCityInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.cityFlagMap = new java.util.concurrent.ConcurrentHashMap<Integer, LBCityFlag>();
		this.factionOccupyTimeMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		this.meritGens = new LBGensBaseInfo(this);
		this.flag2SaveMap = new java.util.concurrent.ConcurrentHashMap<Integer, LBCityFlagSave>();
		this.unlockTempReviveSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
	}

	public LBCityInfo() {
		this((warrior.mdb.XBean)null);
	}

	public LBCityInfo(LBCityInfo _o_) {
		this(_o_, null);
	}

	LBCityInfo(LBCityInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.areaId = _o_.areaId;
		this.state = _o_.state;
		this.cityFlagMap = new java.util.concurrent.ConcurrentHashMap<Integer, LBCityFlag>();
		_o_.cityFlagMap.forEach((_k_, _v_) -> this.cityFlagMap.put(_k_, new LBCityFlag(_v_)));
		this.lastSeasonFaction = _o_.lastSeasonFaction;
		this.factionOccupyTimeMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.factionOccupyTimeMap.forEach((_k_, _v_) -> this.factionOccupyTimeMap.put(_k_, _v_));
		this.nextCanFightMill = _o_.nextCanFightMill;
		this.isOpenFight = _o_.isOpenFight;
		this.meritGens = new LBGensBaseInfo(_o_.meritGens, this);
		this.last2SeasonFaction = _o_.last2SeasonFaction;
		this.flag2SaveMap = new java.util.concurrent.ConcurrentHashMap<Integer, LBCityFlagSave>();
		_o_.flag2SaveMap.forEach((_k_, _v_) -> this.flag2SaveMap.put(_k_, new LBCityFlagSave(_v_)));
		this.startTime = _o_.startTime;
		this.endTime = _o_.endTime;
		this.start = _o_.start;
		this.finishTime = _o_.finishTime;
		this.nextStartTime = _o_.nextStartTime;
		this.occupyCount = _o_.occupyCount;
		this.unlockTempReviveSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.unlockTempReviveSet.forEach(_v_ -> this.unlockTempReviveSet.add(_v_));
	}

	public void copyFrom(LBCityInfo _o_) {
        this.areaId = _o_.areaId;
        this.state = _o_.state;
        this.cityFlagMap = new java.util.concurrent.ConcurrentHashMap<Integer, LBCityFlag>();
		_o_.cityFlagMap.forEach((_k_, _v_) -> this.cityFlagMap.put(_k_, new LBCityFlag(_v_)));
        this.lastSeasonFaction = _o_.lastSeasonFaction;
        this.factionOccupyTimeMap = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.factionOccupyTimeMap.forEach((_k_, _v_) -> this.factionOccupyTimeMap.put(_k_, _v_));
        this.nextCanFightMill = _o_.nextCanFightMill;
        this.isOpenFight = _o_.isOpenFight;
        this.meritGens = new LBGensBaseInfo(_o_.meritGens, this);
        this.last2SeasonFaction = _o_.last2SeasonFaction;
        this.flag2SaveMap = new java.util.concurrent.ConcurrentHashMap<Integer, LBCityFlagSave>();
		_o_.flag2SaveMap.forEach((_k_, _v_) -> this.flag2SaveMap.put(_k_, new LBCityFlagSave(_v_)));
        this.startTime = _o_.startTime;
        this.endTime = _o_.endTime;
        this.start = _o_.start;
        this.finishTime = _o_.finishTime;
        this.nextStartTime = _o_.nextStartTime;
        this.occupyCount = _o_.occupyCount;
        this.unlockTempReviveSet = new java.util.concurrent.CopyOnWriteArraySet<Integer>();
		this.unlockTempReviveSet.forEach(_v_ -> this.unlockTempReviveSet.add(_v_));
	}


	@JSONField(name = "1")
	public int getAreaId(){
		return this.areaId;
	}

	@JSONField(name = "3")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Integer, LBCityFlag> getCityFlagMap(){
		return this.cityFlagMap;
	}

	@JSONField(name = "5")
	public int getLastSeasonFaction(){
		return this.lastSeasonFaction;
	}

	@JSONField(name = "6")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getFactionOccupyTimeMap(){
		return this.factionOccupyTimeMap;
	}

	@JSONField(name = "7")
	public long getNextCanFightMill(){
		return this.nextCanFightMill;
	}

	@JSONField(name = "8")
	public boolean getIsOpenFight(){
		return this.isOpenFight;
	}

	@JSONField(name = "9")
	public xbean.lobby.LBGensBaseInfo getMeritGens(){
		return this.meritGens;
	}

	@JSONField(name = "12")
	public int getLast2SeasonFaction(){
		return this.last2SeasonFaction;
	}

	@JSONField(name = "13")
	public java.util.concurrent.ConcurrentHashMap<Integer, LBCityFlagSave> getFlag2SaveMap(){
		return this.flag2SaveMap;
	}

	@JSONField(name = "14")
	public long getStartTime(){
		return this.startTime;
	}

	@JSONField(name = "15")
	public long getEndTime(){
		return this.endTime;
	}

	@JSONField(name = "17")
	public boolean getStart(){
		return this.start;
	}

	@JSONField(name = "18")
	public long getFinishTime(){
		return this.finishTime;
	}

	@JSONField(name = "16")
	public long getNextStartTime(){
		return this.nextStartTime;
	}

	@JSONField(name = "19")
	public int getOccupyCount(){
		return this.occupyCount;
	}

	@JSONField(name = "20")
	public java.util.concurrent.CopyOnWriteArraySet<Integer> getUnlockTempReviveSet(){
		return this.unlockTempReviveSet;
	}

	@JSONField(name = "1")
	public void setAreaId(int _v_){
		this.areaId = _v_;
	}

	@JSONField(name = "3")
	public void setState(int _v_){
		this.state = _v_;
	}

	@JSONField(name = "4")
	public void setCityFlagMap(java.util.concurrent.ConcurrentHashMap<Integer, LBCityFlag> _v_){
		this.cityFlagMap = _v_;
	}

	@JSONField(name = "5")
	public void setLastSeasonFaction(int _v_){
		this.lastSeasonFaction = _v_;
	}

	@JSONField(name = "6")
	public void setFactionOccupyTimeMap(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.factionOccupyTimeMap = _v_;
	}

	@JSONField(name = "7")
	public void setNextCanFightMill(long _v_){
		this.nextCanFightMill = _v_;
	}

	@JSONField(name = "8")
	public void setIsOpenFight(boolean _v_){
		this.isOpenFight = _v_;
	}

	@JSONField(name = "9")
	public void setMeritGens(xbean.lobby.LBGensBaseInfo _v_){
		this.meritGens = _v_;
	}

	@JSONField(name = "12")
	public void setLast2SeasonFaction(int _v_){
		this.last2SeasonFaction = _v_;
	}

	@JSONField(name = "13")
	public void setFlag2SaveMap(java.util.concurrent.ConcurrentHashMap<Integer, LBCityFlagSave> _v_){
		this.flag2SaveMap = _v_;
	}

	@JSONField(name = "14")
	public void setStartTime(long _v_){
		this.startTime = _v_;
	}

	@JSONField(name = "15")
	public void setEndTime(long _v_){
		this.endTime = _v_;
	}

	@JSONField(name = "17")
	public void setStart(boolean _v_){
		this.start = _v_;
	}

	@JSONField(name = "18")
	public void setFinishTime(long _v_){
		this.finishTime = _v_;
	}

	@JSONField(name = "16")
	public void setNextStartTime(long _v_){
		this.nextStartTime = _v_;
	}

	@JSONField(name = "19")
	public void setOccupyCount(int _v_){
		this.occupyCount = _v_;
	}

	@JSONField(name = "20")
	public void setUnlockTempReviveSet(java.util.concurrent.CopyOnWriteArraySet<Integer> _v_){
		this.unlockTempReviveSet = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBCityInfo) {
			LBCityInfo _o_ = (LBCityInfo)_o1_;
			if (this.areaId != _o_.areaId) return false;
			if (this.state != _o_.state) return false;
			if (!this.cityFlagMap.equals(_o_.cityFlagMap)) return false;
			if (this.lastSeasonFaction != _o_.lastSeasonFaction) return false;
			if (!this.factionOccupyTimeMap.equals(_o_.factionOccupyTimeMap)) return false;
			if (this.nextCanFightMill != _o_.nextCanFightMill) return false;
			if (this.isOpenFight != _o_.isOpenFight) return false;
			if (!this.meritGens.equals(_o_.meritGens)) return false;
			if (this.last2SeasonFaction != _o_.last2SeasonFaction) return false;
			if (!this.flag2SaveMap.equals(_o_.flag2SaveMap)) return false;
			if (this.startTime != _o_.startTime) return false;
			if (this.endTime != _o_.endTime) return false;
			if (this.start != _o_.start) return false;
			if (this.finishTime != _o_.finishTime) return false;
			if (this.nextStartTime != _o_.nextStartTime) return false;
			if (this.occupyCount != _o_.occupyCount) return false;
			if (!this.unlockTempReviveSet.equals(_o_.unlockTempReviveSet)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.areaId);
		_h_ += _h_ * 31 + (int)(this.state);
		_h_ += _h_ * 31 + this.cityFlagMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.lastSeasonFaction);
		_h_ += _h_ * 31 + this.factionOccupyTimeMap.hashCode();
		_h_ += _h_ * 31 + (int)(nextCanFightMill ^ (nextCanFightMill >>> 32));
		_h_ += _h_ * 31 + (this.isOpenFight ? 1231 : 1237);
		_h_ += _h_ * 31 + this.meritGens.hashCode();
		_h_ += _h_ * 31 + (int)(this.last2SeasonFaction);
		_h_ += _h_ * 31 + this.flag2SaveMap.hashCode();
		_h_ += _h_ * 31 + (int)(startTime ^ (startTime >>> 32));
		_h_ += _h_ * 31 + (int)(endTime ^ (endTime >>> 32));
		_h_ += _h_ * 31 + (this.start ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(finishTime ^ (finishTime >>> 32));
		_h_ += _h_ * 31 + (int)(nextStartTime ^ (nextStartTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.occupyCount);
		_h_ += _h_ * 31 + this.unlockTempReviveSet.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(areaId).append(",");
		_sb_.append(state).append(",");
		_sb_.append(cityFlagMap).append(",");
		_sb_.append(lastSeasonFaction).append(",");
		_sb_.append(factionOccupyTimeMap).append(",");
		_sb_.append(nextCanFightMill).append(",");
		_sb_.append(isOpenFight).append(",");
		_sb_.append(meritGens).append(",");
		_sb_.append(last2SeasonFaction).append(",");
		_sb_.append(flag2SaveMap).append(",");
		_sb_.append(startTime).append(",");
		_sb_.append(endTime).append(",");
		_sb_.append(start).append(",");
		_sb_.append(finishTime).append(",");
		_sb_.append(nextStartTime).append(",");
		_sb_.append(occupyCount).append(",");
		_sb_.append(unlockTempReviveSet).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
