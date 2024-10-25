package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class ServerFactionQualifyInfo extends warrior.mdb.XBean {
    private int faction; 
    private int serverId; 

    ServerFactionQualifyInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public ServerFactionQualifyInfo() {
		this((warrior.mdb.XBean)null);
	}

	public ServerFactionQualifyInfo(ServerFactionQualifyInfo _o_) {
		this(_o_, null);
	}

	ServerFactionQualifyInfo(ServerFactionQualifyInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.faction = _o_.faction;
		this.serverId = _o_.serverId;
	}

	public void copyFrom(ServerFactionQualifyInfo _o_) {
        this.faction = _o_.faction;
        this.serverId = _o_.serverId;
	}


	@JSONField(name = "1")
	public int getFaction(){
		return this.faction;
	}

	@JSONField(name = "2")
	public int getServerId(){
		return this.serverId;
	}

	@JSONField(name = "1")
	public void setFaction(int _v_){
		this.faction = _v_;
	}

	@JSONField(name = "2")
	public void setServerId(int _v_){
		this.serverId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ServerFactionQualifyInfo) {
			ServerFactionQualifyInfo _o_ = (ServerFactionQualifyInfo)_o1_;
			if (this.faction != _o_.faction) return false;
			if (this.serverId != _o_.serverId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.faction);
		_h_ += _h_ * 31 + (int)(this.serverId);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(faction).append(",");
		_sb_.append(serverId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
