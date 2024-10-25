package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class CFWBattleGroupLog extends warrior.mdb.XBean {
    private int serverId; 
    private int faction; 
    private int rank; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> dataMap; 
    private int score; 
    private int landId; 
    private boolean offence; 

    CFWBattleGroupLog(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.dataMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public CFWBattleGroupLog() {
		this((warrior.mdb.XBean)null);
	}

	public CFWBattleGroupLog(CFWBattleGroupLog _o_) {
		this(_o_, null);
	}

	CFWBattleGroupLog(CFWBattleGroupLog _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.serverId = _o_.serverId;
		this.faction = _o_.faction;
		this.rank = _o_.rank;
		this.dataMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dataMap.forEach((_k_, _v_) -> this.dataMap.put(_k_, _v_));
		this.score = _o_.score;
		this.landId = _o_.landId;
		this.offence = _o_.offence;
	}

	public void copyFrom(CFWBattleGroupLog _o_) {
        this.serverId = _o_.serverId;
        this.faction = _o_.faction;
        this.rank = _o_.rank;
        this.dataMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dataMap.forEach((_k_, _v_) -> this.dataMap.put(_k_, _v_));
        this.score = _o_.score;
        this.landId = _o_.landId;
        this.offence = _o_.offence;
	}


	@JSONField(name = "1")
	public int getServerId(){
		return this.serverId;
	}

	@JSONField(name = "2")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "3")
	public int getRank(){
		return this.rank;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDataMap(){
		return this.dataMap;
	}

	@JSONField(name = "5")
	public int getScore(){
		return this.score;
	}

	@JSONField(name = "6")
	public int getLandId(){
		return this.landId;
	}

	@JSONField(name = "7")
	public boolean getOffence(){
		return this.offence;
	}

	@JSONField(name = "1")
	public void setServerId(int _v_){
		this.serverId = _v_;
	}

	@JSONField(name = "2")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "3")
	public void setRank(int _v_){
		this.rank = _v_;
	}

	@JSONField(name = "4")
	public void setDataMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.dataMap = _v_;
	}

	@JSONField(name = "5")
	public void setScore(int _v_){
		this.score = _v_;
	}

	@JSONField(name = "6")
	public void setLandId(int _v_){
		this.landId = _v_;
	}

	@JSONField(name = "7")
	public void setOffence(boolean _v_){
		this.offence = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CFWBattleGroupLog) {
			CFWBattleGroupLog _o_ = (CFWBattleGroupLog)_o1_;
			if (this.serverId != _o_.serverId) return false;
			if (this.faction != _o_.faction) return false;
			if (this.rank != _o_.rank) return false;
			if (!this.dataMap.equals(_o_.dataMap)) return false;
			if (this.score != _o_.score) return false;
			if (this.landId != _o_.landId) return false;
			if (this.offence != _o_.offence) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.serverId);
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + (int)(this.rank);
		_h_ += _h_ * 31 + this.dataMap.hashCode();
		_h_ += _h_ * 31 + (int)(this.score);
		_h_ += _h_ * 31 + (int)(this.landId);
		_h_ += _h_ * 31 + (this.offence ? 1231 : 1237);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(serverId).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(rank).append(",");
		_sb_.append(dataMap).append(",");
		_sb_.append(score).append(",");
		_sb_.append(landId).append(",");
		_sb_.append(offence).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
