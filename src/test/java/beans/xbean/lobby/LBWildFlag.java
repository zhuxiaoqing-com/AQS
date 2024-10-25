package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBWildFlag extends warrior.mdb.XBean {
    private int flagMarkId;  // 旗帜markId
    private xbean.lobby.LBGensBaseInfo meritGens;  // 功勋公会
    private int flagCfgId;  // 旗帜配置Id
    private long flagOccupyTime;  // 旗帜占领时间
    private long areaOccupyTime;  // 区域占领时间
    private int state;  // 旗帜状态
    private int faction; 
    private int lastSeasonFaction;  // 上一个争夺器占领的该旗帜的阵营
    private int flagFaction;  // 旗帜派系
    private int armament;  // 战备点
    private long lastHoistFlagGensId;  // 上一次升起旗帜的公会id
    private int last2SeasonFaction;  // 上上个争夺器占领的该旗帜的阵营
    private int reviveNum;  // 复活次数
    private int reinforceMonsterNum;  // 当前增员怪数量
    private java.util.concurrent.ConcurrentHashMap<Integer, FlagLvInfo> lvMap;  // 建筑id->建筑等级
    private java.util.concurrent.CopyOnWriteArrayList<RankObj> lvDonateRankList;  // 捐献排行
    private long startTime;  // 赛季开始时间(上个休战期结束时间)
    private long endTime;  // 赛季结束时间(下个休战期开始时间)
    private boolean start;  // 是否开启着
    private long finishTime;  // 升旗是否已经完成
    private long nextStartTime;  // 下一次开启时间
    private int reinforceMonsterPassiveTechNum;  // 阵营被动加成的怪数量
    private long declareSupplyMonsterSrl;  // 宣战系统补给站怪srl
    private long declareSupplyMonsterDeleteTime;  // 补给站怪消失时间戳
    private long protectEndTime;  // 保护时间结束时间戳
    private xbean.lobby.FlagSpecialInfo specialInfo;  // 旗子特殊属性info

    LBWildFlag(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.meritGens = new LBGensBaseInfo(this);
		this.lvMap = new java.util.concurrent.ConcurrentHashMap<Integer, FlagLvInfo>();
		this.lvDonateRankList = new java.util.concurrent.CopyOnWriteArrayList<RankObj>();
		this.specialInfo = new FlagSpecialInfo(this);
	}

	public LBWildFlag() {
		this((warrior.mdb.XBean)null);
	}

	public LBWildFlag(LBWildFlag _o_) {
		this(_o_, null);
	}

	LBWildFlag(LBWildFlag _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.flagMarkId = _o_.flagMarkId;
		this.meritGens = new LBGensBaseInfo(_o_.meritGens, this);
		this.flagCfgId = _o_.flagCfgId;
		this.flagOccupyTime = _o_.flagOccupyTime;
		this.areaOccupyTime = _o_.areaOccupyTime;
		this.state = _o_.state;
		this.faction = _o_.faction;
		this.lastSeasonFaction = _o_.lastSeasonFaction;
		this.flagFaction = _o_.flagFaction;
		this.armament = _o_.armament;
		this.lastHoistFlagGensId = _o_.lastHoistFlagGensId;
		this.last2SeasonFaction = _o_.last2SeasonFaction;
		this.reviveNum = _o_.reviveNum;
		this.reinforceMonsterNum = _o_.reinforceMonsterNum;
		this.lvMap = new java.util.concurrent.ConcurrentHashMap<Integer, FlagLvInfo>();
		_o_.lvMap.forEach((_k_, _v_) -> this.lvMap.put(_k_, new FlagLvInfo(_v_)));
		this.lvDonateRankList = new java.util.concurrent.CopyOnWriteArrayList<RankObj>();
		_o_.lvDonateRankList.forEach(_v_ -> this.lvDonateRankList.add(new RankObj(_v_)));
		this.startTime = _o_.startTime;
		this.endTime = _o_.endTime;
		this.start = _o_.start;
		this.finishTime = _o_.finishTime;
		this.nextStartTime = _o_.nextStartTime;
		this.reinforceMonsterPassiveTechNum = _o_.reinforceMonsterPassiveTechNum;
		this.declareSupplyMonsterSrl = _o_.declareSupplyMonsterSrl;
		this.declareSupplyMonsterDeleteTime = _o_.declareSupplyMonsterDeleteTime;
		this.protectEndTime = _o_.protectEndTime;
		this.specialInfo = new FlagSpecialInfo(_o_.specialInfo, this);
	}

	public void copyFrom(LBWildFlag _o_) {
        this.flagMarkId = _o_.flagMarkId;
        this.meritGens = new LBGensBaseInfo(_o_.meritGens, this);
        this.flagCfgId = _o_.flagCfgId;
        this.flagOccupyTime = _o_.flagOccupyTime;
        this.areaOccupyTime = _o_.areaOccupyTime;
        this.state = _o_.state;
        this.faction = _o_.faction;
        this.lastSeasonFaction = _o_.lastSeasonFaction;
        this.flagFaction = _o_.flagFaction;
        this.armament = _o_.armament;
        this.lastHoistFlagGensId = _o_.lastHoistFlagGensId;
        this.last2SeasonFaction = _o_.last2SeasonFaction;
        this.reviveNum = _o_.reviveNum;
        this.reinforceMonsterNum = _o_.reinforceMonsterNum;
        this.lvMap = new java.util.concurrent.ConcurrentHashMap<Integer, FlagLvInfo>();
		_o_.lvMap.forEach((_k_, _v_) -> this.lvMap.put(_k_, new FlagLvInfo(_v_)));
        this.lvDonateRankList = new java.util.concurrent.CopyOnWriteArrayList<RankObj>();
		_o_.lvDonateRankList.forEach(_v_ -> this.lvDonateRankList.add(new RankObj(_v_)));
        this.startTime = _o_.startTime;
        this.endTime = _o_.endTime;
        this.start = _o_.start;
        this.finishTime = _o_.finishTime;
        this.nextStartTime = _o_.nextStartTime;
        this.reinforceMonsterPassiveTechNum = _o_.reinforceMonsterPassiveTechNum;
        this.declareSupplyMonsterSrl = _o_.declareSupplyMonsterSrl;
        this.declareSupplyMonsterDeleteTime = _o_.declareSupplyMonsterDeleteTime;
        this.protectEndTime = _o_.protectEndTime;
        this.specialInfo = new FlagSpecialInfo(_o_.specialInfo, this);
	}


	@JSONField(name = "1")
	public int getFlagMarkId(){
		return this.flagMarkId;
	}

	@JSONField(name = "2")
	public xbean.lobby.LBGensBaseInfo getMeritGens(){
		return this.meritGens;
	}

	@JSONField(name = "4")
	public int getFlagCfgId(){
		return this.flagCfgId;
	}

	@JSONField(name = "5")
	public long getFlagOccupyTime(){
		return this.flagOccupyTime;
	}

	@JSONField(name = "6")
	public long getAreaOccupyTime(){
		return this.areaOccupyTime;
	}

	@JSONField(name = "7")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "8")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "9")
	public int getLastSeasonFaction(){
		return this.lastSeasonFaction;
	}

	@JSONField(name = "11")
	public int getFlagFaction(){
		return this.flagFaction;
	}

	@JSONField(name = "12")
	public int getArmament(){
		return this.armament;
	}

	@JSONField(name = "15")
	public long getLastHoistFlagGensId(){
		return this.lastHoistFlagGensId;
	}

	@JSONField(name = "16")
	public int getLast2SeasonFaction(){
		return this.last2SeasonFaction;
	}

	@JSONField(name = "17")
	public int getReviveNum(){
		return this.reviveNum;
	}

	@JSONField(name = "18")
	public int getReinforceMonsterNum(){
		return this.reinforceMonsterNum;
	}

	@JSONField(name = "19")
	public java.util.concurrent.ConcurrentHashMap<Integer, FlagLvInfo> getLvMap(){
		return this.lvMap;
	}

	@JSONField(name = "20")
	public java.util.concurrent.CopyOnWriteArrayList<RankObj> getLvDonateRankList(){
		return this.lvDonateRankList;
	}

	@JSONField(name = "21")
	public long getStartTime(){
		return this.startTime;
	}

	@JSONField(name = "22")
	public long getEndTime(){
		return this.endTime;
	}

	@JSONField(name = "24")
	public boolean getStart(){
		return this.start;
	}

	@JSONField(name = "25")
	public long getFinishTime(){
		return this.finishTime;
	}

	@JSONField(name = "23")
	public long getNextStartTime(){
		return this.nextStartTime;
	}

	@JSONField(name = "26")
	public int getReinforceMonsterPassiveTechNum(){
		return this.reinforceMonsterPassiveTechNum;
	}

	@JSONField(name = "27")
	public long getDeclareSupplyMonsterSrl(){
		return this.declareSupplyMonsterSrl;
	}

	@JSONField(name = "28")
	public long getDeclareSupplyMonsterDeleteTime(){
		return this.declareSupplyMonsterDeleteTime;
	}

	@JSONField(name = "29")
	public long getProtectEndTime(){
		return this.protectEndTime;
	}

	@JSONField(name = "30")
	public xbean.lobby.FlagSpecialInfo getSpecialInfo(){
		return this.specialInfo;
	}

	@JSONField(name = "1")
	public void setFlagMarkId(int _v_){
		this.flagMarkId = _v_;
	}

	@JSONField(name = "2")
	public void setMeritGens(xbean.lobby.LBGensBaseInfo _v_){
		this.meritGens = _v_;
	}

	@JSONField(name = "4")
	public void setFlagCfgId(int _v_){
		this.flagCfgId = _v_;
	}

	@JSONField(name = "5")
	public void setFlagOccupyTime(long _v_){
		this.flagOccupyTime = _v_;
	}

	@JSONField(name = "6")
	public void setAreaOccupyTime(long _v_){
		this.areaOccupyTime = _v_;
	}

	@JSONField(name = "7")
	public void setState(int _v_){
		this.state = _v_;
	}

	@JSONField(name = "8")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "9")
	public void setLastSeasonFaction(int _v_){
		this.lastSeasonFaction = _v_;
	}

	@JSONField(name = "11")
	public void setFlagFaction(int _v_){
		this.flagFaction = _v_;
	}

	@JSONField(name = "12")
	public void setArmament(int _v_){
		this.armament = _v_;
	}

	@JSONField(name = "15")
	public void setLastHoistFlagGensId(long _v_){
		this.lastHoistFlagGensId = _v_;
	}

	@JSONField(name = "16")
	public void setLast2SeasonFaction(int _v_){
		this.last2SeasonFaction = _v_;
	}

	@JSONField(name = "17")
	public void setReviveNum(int _v_){
		this.reviveNum = _v_;
	}

	@JSONField(name = "18")
	public void setReinforceMonsterNum(int _v_){
		this.reinforceMonsterNum = _v_;
	}

	@JSONField(name = "19")
	public void setLvMap(java.util.concurrent.ConcurrentHashMap<Integer, FlagLvInfo> _v_){
		this.lvMap = _v_;
	}

	@JSONField(name = "20")
	public void setLvDonateRankList(java.util.concurrent.CopyOnWriteArrayList<RankObj> _v_){
		this.lvDonateRankList = _v_;
	}

	@JSONField(name = "21")
	public void setStartTime(long _v_){
		this.startTime = _v_;
	}

	@JSONField(name = "22")
	public void setEndTime(long _v_){
		this.endTime = _v_;
	}

	@JSONField(name = "24")
	public void setStart(boolean _v_){
		this.start = _v_;
	}

	@JSONField(name = "25")
	public void setFinishTime(long _v_){
		this.finishTime = _v_;
	}

	@JSONField(name = "23")
	public void setNextStartTime(long _v_){
		this.nextStartTime = _v_;
	}

	@JSONField(name = "26")
	public void setReinforceMonsterPassiveTechNum(int _v_){
		this.reinforceMonsterPassiveTechNum = _v_;
	}

	@JSONField(name = "27")
	public void setDeclareSupplyMonsterSrl(long _v_){
		this.declareSupplyMonsterSrl = _v_;
	}

	@JSONField(name = "28")
	public void setDeclareSupplyMonsterDeleteTime(long _v_){
		this.declareSupplyMonsterDeleteTime = _v_;
	}

	@JSONField(name = "29")
	public void setProtectEndTime(long _v_){
		this.protectEndTime = _v_;
	}

	@JSONField(name = "30")
	public void setSpecialInfo(xbean.lobby.FlagSpecialInfo _v_){
		this.specialInfo = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBWildFlag) {
			LBWildFlag _o_ = (LBWildFlag)_o1_;
			if (this.flagMarkId != _o_.flagMarkId) return false;
			if (!this.meritGens.equals(_o_.meritGens)) return false;
			if (this.flagCfgId != _o_.flagCfgId) return false;
			if (this.flagOccupyTime != _o_.flagOccupyTime) return false;
			if (this.areaOccupyTime != _o_.areaOccupyTime) return false;
			if (this.state != _o_.state) return false;
			if (this.faction != _o_.faction) return false;
			if (this.lastSeasonFaction != _o_.lastSeasonFaction) return false;
			if (this.flagFaction != _o_.flagFaction) return false;
			if (this.armament != _o_.armament) return false;
			if (this.lastHoistFlagGensId != _o_.lastHoistFlagGensId) return false;
			if (this.last2SeasonFaction != _o_.last2SeasonFaction) return false;
			if (this.reviveNum != _o_.reviveNum) return false;
			if (this.reinforceMonsterNum != _o_.reinforceMonsterNum) return false;
			if (!this.lvMap.equals(_o_.lvMap)) return false;
			if (!this.lvDonateRankList.equals(_o_.lvDonateRankList)) return false;
			if (this.startTime != _o_.startTime) return false;
			if (this.endTime != _o_.endTime) return false;
			if (this.start != _o_.start) return false;
			if (this.finishTime != _o_.finishTime) return false;
			if (this.nextStartTime != _o_.nextStartTime) return false;
			if (this.reinforceMonsterPassiveTechNum != _o_.reinforceMonsterPassiveTechNum) return false;
			if (this.declareSupplyMonsterSrl != _o_.declareSupplyMonsterSrl) return false;
			if (this.declareSupplyMonsterDeleteTime != _o_.declareSupplyMonsterDeleteTime) return false;
			if (this.protectEndTime != _o_.protectEndTime) return false;
			if (!this.specialInfo.equals(_o_.specialInfo)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.flagMarkId);
		_h_ += _h_ * 31 + this.meritGens.hashCode();
		_h_ += _h_ * 31 + (int)(this.flagCfgId);
		_h_ += _h_ * 31 + (int)(flagOccupyTime ^ (flagOccupyTime >>> 32));
		_h_ += _h_ * 31 + (int)(areaOccupyTime ^ (areaOccupyTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.state);
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + (int)(this.lastSeasonFaction);
		_h_ += _h_ * 31 + (int)(this.flagFaction);
		_h_ += _h_ * 31 + (int)(this.armament);
		_h_ += _h_ * 31 + (int)(lastHoistFlagGensId ^ (lastHoistFlagGensId >>> 32));
		_h_ += _h_ * 31 + (int)(this.last2SeasonFaction);
		_h_ += _h_ * 31 + (int)(this.reviveNum);
		_h_ += _h_ * 31 + (int)(this.reinforceMonsterNum);
		_h_ += _h_ * 31 + this.lvMap.hashCode();
		_h_ += _h_ * 31 + this.lvDonateRankList.hashCode();
		_h_ += _h_ * 31 + (int)(startTime ^ (startTime >>> 32));
		_h_ += _h_ * 31 + (int)(endTime ^ (endTime >>> 32));
		_h_ += _h_ * 31 + (this.start ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(finishTime ^ (finishTime >>> 32));
		_h_ += _h_ * 31 + (int)(nextStartTime ^ (nextStartTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.reinforceMonsterPassiveTechNum);
		_h_ += _h_ * 31 + (int)(declareSupplyMonsterSrl ^ (declareSupplyMonsterSrl >>> 32));
		_h_ += _h_ * 31 + (int)(declareSupplyMonsterDeleteTime ^ (declareSupplyMonsterDeleteTime >>> 32));
		_h_ += _h_ * 31 + (int)(protectEndTime ^ (protectEndTime >>> 32));
		_h_ += _h_ * 31 + this.specialInfo.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(flagMarkId).append(",");
		_sb_.append(meritGens).append(",");
		_sb_.append(flagCfgId).append(",");
		_sb_.append(flagOccupyTime).append(",");
		_sb_.append(areaOccupyTime).append(",");
		_sb_.append(state).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(lastSeasonFaction).append(",");
		_sb_.append(flagFaction).append(",");
		_sb_.append(armament).append(",");
		_sb_.append(lastHoistFlagGensId).append(",");
		_sb_.append(last2SeasonFaction).append(",");
		_sb_.append(reviveNum).append(",");
		_sb_.append(reinforceMonsterNum).append(",");
		_sb_.append(lvMap).append(",");
		_sb_.append(lvDonateRankList).append(",");
		_sb_.append(startTime).append(",");
		_sb_.append(endTime).append(",");
		_sb_.append(start).append(",");
		_sb_.append(finishTime).append(",");
		_sb_.append(nextStartTime).append(",");
		_sb_.append(reinforceMonsterPassiveTechNum).append(",");
		_sb_.append(declareSupplyMonsterSrl).append(",");
		_sb_.append(declareSupplyMonsterDeleteTime).append(",");
		_sb_.append(protectEndTime).append(",");
		_sb_.append(specialInfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
