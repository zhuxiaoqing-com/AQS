package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class CFWBattleCamp extends warrior.mdb.XBean {
    private int serverId; 
    private int faction; 

    CFWBattleCamp(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public CFWBattleCamp() {
		this((warrior.mdb.XBean)null);
	}

	public CFWBattleCamp(CFWBattleCamp _o_) {
		this(_o_, null);
	}

	CFWBattleCamp(CFWBattleCamp _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.serverId = _o_.serverId;
		this.faction = _o_.faction;
	}

	public void copyFrom(CFWBattleCamp _o_) {
        this.serverId = _o_.serverId;
        this.faction = _o_.faction;
	}


	@JSONField(name = "1")
	public int getServerId(){
		return this.serverId;
	}

	@JSONField(name = "2")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "1")
	public void setServerId(int _v_){
		this.serverId = _v_;
	}

	@JSONField(name = "2")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CFWBattleCamp) {
			CFWBattleCamp _o_ = (CFWBattleCamp)_o1_;
			if (this.serverId != _o_.serverId) return false;
			if (this.faction != _o_.faction) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.serverId);
		_h_ += _h_ * 31 + (int)(this.faction);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(serverId).append(",");
		_sb_.append(faction).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
