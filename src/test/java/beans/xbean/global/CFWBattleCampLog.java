package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class CFWBattleCampLog extends warrior.mdb.XBean {
    private int serverId; 
    private int faction; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> dataMap; 

    CFWBattleCampLog(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.dataMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public CFWBattleCampLog() {
		this((warrior.mdb.XBean)null);
	}

	public CFWBattleCampLog(CFWBattleCampLog _o_) {
		this(_o_, null);
	}

	CFWBattleCampLog(CFWBattleCampLog _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.serverId = _o_.serverId;
		this.faction = _o_.faction;
		this.dataMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dataMap.forEach((_k_, _v_) -> this.dataMap.put(_k_, _v_));
	}

	public void copyFrom(CFWBattleCampLog _o_) {
        this.serverId = _o_.serverId;
        this.faction = _o_.faction;
        this.dataMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dataMap.forEach((_k_, _v_) -> this.dataMap.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public int getServerId(){
		return this.serverId;
	}

	@JSONField(name = "2")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "4")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDataMap(){
		return this.dataMap;
	}

	@JSONField(name = "1")
	public void setServerId(int _v_){
		this.serverId = _v_;
	}

	@JSONField(name = "2")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "4")
	public void setDataMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.dataMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CFWBattleCampLog) {
			CFWBattleCampLog _o_ = (CFWBattleCampLog)_o1_;
			if (this.serverId != _o_.serverId) return false;
			if (this.faction != _o_.faction) return false;
			if (!this.dataMap.equals(_o_.dataMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.serverId);
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + this.dataMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(serverId).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(dataMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
