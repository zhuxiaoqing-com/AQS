package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ServerPlayerInfo extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, Long> infos;  // key=lobbyId value=playerId

    ServerPlayerInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.infos = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
	}

	public ServerPlayerInfo() {
		this((warrior.mdb.XBean)null);
	}

	public ServerPlayerInfo(ServerPlayerInfo _o_) {
		this(_o_, null);
	}

	ServerPlayerInfo(ServerPlayerInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.infos = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.infos.forEach((_k_, _v_) -> this.infos.put(_k_, _v_));
	}

	public void copyFrom(ServerPlayerInfo _o_) {
        this.infos = new java.util.concurrent.ConcurrentHashMap<Integer, Long>();
		_o_.infos.forEach((_k_, _v_) -> this.infos.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, Long> getInfos(){
		return this.infos;
	}

	@JSONField(name = "1")
	public void setInfos(java.util.concurrent.ConcurrentHashMap<Integer, Long> _v_){
		this.infos = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ServerPlayerInfo) {
			ServerPlayerInfo _o_ = (ServerPlayerInfo)_o1_;
			if (!this.infos.equals(_o_.infos)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.infos.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(infos).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
