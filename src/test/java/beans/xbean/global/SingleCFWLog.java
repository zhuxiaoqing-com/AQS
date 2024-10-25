package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class SingleCFWLog extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, CrossFactionBattleLog> landLogs;  // 所有地块的结算战报
    private java.util.concurrent.ConcurrentHashMap<Long, CFWPlayerLog> playerLogs;  // 所有玩家的结算战报
    private java.util.concurrent.ConcurrentHashMap<Integer, CFWFactionLog> factionLogs; 
    private java.util.concurrent.CopyOnWriteArrayList<CFWBattleGroupLog> battleGroupLogs; 
    private long summaryTime; 

    SingleCFWLog(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.landLogs = new java.util.concurrent.ConcurrentHashMap<Integer, CrossFactionBattleLog>();
		this.playerLogs = new java.util.concurrent.ConcurrentHashMap<Long, CFWPlayerLog>();
		this.factionLogs = new java.util.concurrent.ConcurrentHashMap<Integer, CFWFactionLog>();
		this.battleGroupLogs = new java.util.concurrent.CopyOnWriteArrayList<CFWBattleGroupLog>();
	}

	public SingleCFWLog() {
		this((warrior.mdb.XBean)null);
	}

	public SingleCFWLog(SingleCFWLog _o_) {
		this(_o_, null);
	}

	SingleCFWLog(SingleCFWLog _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.landLogs = new java.util.concurrent.ConcurrentHashMap<Integer, CrossFactionBattleLog>();
		_o_.landLogs.forEach((_k_, _v_) -> this.landLogs.put(_k_, new CrossFactionBattleLog(_v_)));
		this.playerLogs = new java.util.concurrent.ConcurrentHashMap<Long, CFWPlayerLog>();
		_o_.playerLogs.forEach((_k_, _v_) -> this.playerLogs.put(_k_, new CFWPlayerLog(_v_)));
		this.factionLogs = new java.util.concurrent.ConcurrentHashMap<Integer, CFWFactionLog>();
		_o_.factionLogs.forEach((_k_, _v_) -> this.factionLogs.put(_k_, new CFWFactionLog(_v_)));
		this.battleGroupLogs = new java.util.concurrent.CopyOnWriteArrayList<CFWBattleGroupLog>();
		_o_.battleGroupLogs.forEach(_v_ -> this.battleGroupLogs.add(new CFWBattleGroupLog(_v_)));
		this.summaryTime = _o_.summaryTime;
	}

	public void copyFrom(SingleCFWLog _o_) {
        this.landLogs = new java.util.concurrent.ConcurrentHashMap<Integer, CrossFactionBattleLog>();
		_o_.landLogs.forEach((_k_, _v_) -> this.landLogs.put(_k_, new CrossFactionBattleLog(_v_)));
        this.playerLogs = new java.util.concurrent.ConcurrentHashMap<Long, CFWPlayerLog>();
		_o_.playerLogs.forEach((_k_, _v_) -> this.playerLogs.put(_k_, new CFWPlayerLog(_v_)));
        this.factionLogs = new java.util.concurrent.ConcurrentHashMap<Integer, CFWFactionLog>();
		_o_.factionLogs.forEach((_k_, _v_) -> this.factionLogs.put(_k_, new CFWFactionLog(_v_)));
        this.battleGroupLogs = new java.util.concurrent.CopyOnWriteArrayList<CFWBattleGroupLog>();
		_o_.battleGroupLogs.forEach(_v_ -> this.battleGroupLogs.add(new CFWBattleGroupLog(_v_)));
        this.summaryTime = _o_.summaryTime;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, CrossFactionBattleLog> getLandLogs(){
		return this.landLogs;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Long, CFWPlayerLog> getPlayerLogs(){
		return this.playerLogs;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, CFWFactionLog> getFactionLogs(){
		return this.factionLogs;
	}

	@JSONField(name = "4")
	public java.util.concurrent.CopyOnWriteArrayList<CFWBattleGroupLog> getBattleGroupLogs(){
		return this.battleGroupLogs;
	}

	@JSONField(name = "5")
	public long getSummaryTime(){
		return this.summaryTime;
	}

	@JSONField(name = "1")
	public void setLandLogs(java.util.concurrent.ConcurrentHashMap<Integer, CrossFactionBattleLog> _v_){
		this.landLogs = _v_;
	}

	@JSONField(name = "2")
	public void setPlayerLogs(java.util.concurrent.ConcurrentHashMap<Long, CFWPlayerLog> _v_){
		this.playerLogs = _v_;
	}

	@JSONField(name = "3")
	public void setFactionLogs(java.util.concurrent.ConcurrentHashMap<Integer, CFWFactionLog> _v_){
		this.factionLogs = _v_;
	}

	@JSONField(name = "4")
	public void setBattleGroupLogs(java.util.concurrent.CopyOnWriteArrayList<CFWBattleGroupLog> _v_){
		this.battleGroupLogs = _v_;
	}

	@JSONField(name = "5")
	public void setSummaryTime(long _v_){
		this.summaryTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SingleCFWLog) {
			SingleCFWLog _o_ = (SingleCFWLog)_o1_;
			if (!this.landLogs.equals(_o_.landLogs)) return false;
			if (!this.playerLogs.equals(_o_.playerLogs)) return false;
			if (!this.factionLogs.equals(_o_.factionLogs)) return false;
			if (!this.battleGroupLogs.equals(_o_.battleGroupLogs)) return false;
			if (this.summaryTime != _o_.summaryTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.landLogs.hashCode();
		_h_ += _h_ * 31 + this.playerLogs.hashCode();
		_h_ += _h_ * 31 + this.factionLogs.hashCode();
		_h_ += _h_ * 31 + this.battleGroupLogs.hashCode();
		_h_ += _h_ * 31 + (int)(summaryTime ^ (summaryTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(landLogs).append(",");
		_sb_.append(playerLogs).append(",");
		_sb_.append(factionLogs).append(",");
		_sb_.append(battleGroupLogs).append(",");
		_sb_.append(summaryTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
