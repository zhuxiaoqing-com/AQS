package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class CrossBattleAppointment extends warrior.mdb.XBean {
    private int serverId; 
    private int faction; 
    private int landId; 
    private boolean offence; 

    CrossBattleAppointment(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public CrossBattleAppointment() {
		this((warrior.mdb.XBean)null);
	}

	public CrossBattleAppointment(CrossBattleAppointment _o_) {
		this(_o_, null);
	}

	CrossBattleAppointment(CrossBattleAppointment _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.serverId = _o_.serverId;
		this.faction = _o_.faction;
		this.landId = _o_.landId;
		this.offence = _o_.offence;
	}

	public void copyFrom(CrossBattleAppointment _o_) {
        this.serverId = _o_.serverId;
        this.faction = _o_.faction;
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
	public int getLandId(){
		return this.landId;
	}

	@JSONField(name = "4")
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
	public void setLandId(int _v_){
		this.landId = _v_;
	}

	@JSONField(name = "4")
	public void setOffence(boolean _v_){
		this.offence = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CrossBattleAppointment) {
			CrossBattleAppointment _o_ = (CrossBattleAppointment)_o1_;
			if (this.serverId != _o_.serverId) return false;
			if (this.faction != _o_.faction) return false;
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
		_sb_.append(landId).append(",");
		_sb_.append(offence).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
