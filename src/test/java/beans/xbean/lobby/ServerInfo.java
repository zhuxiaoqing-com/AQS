package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ServerInfo extends warrior.mdb.XBean {
    private int serverId;  // 服务器id
    private int playerCount;  // 玩家数量

    ServerInfo(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public ServerInfo() {
		this((warrior.mdb.XBean)null);
	}

	public ServerInfo(ServerInfo _o_) {
		this(_o_, null);
	}

	ServerInfo(ServerInfo _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.serverId = _o_.serverId;
		this.playerCount = _o_.playerCount;
	}

	public void copyFrom(ServerInfo _o_) {
        this.serverId = _o_.serverId;
        this.playerCount = _o_.playerCount;
	}


	@JSONField(name = "1")
	public int getServerId(){
		return this.serverId;
	}

	@JSONField(name = "2")
	public int getPlayerCount(){
		return this.playerCount;
	}

	@JSONField(name = "1")
	public void setServerId(int _v_){
		this.serverId = _v_;
	}

	@JSONField(name = "2")
	public void setPlayerCount(int _v_){
		this.playerCount = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ServerInfo) {
			ServerInfo _o_ = (ServerInfo)_o1_;
			if (this.serverId != _o_.serverId) return false;
			if (this.playerCount != _o_.playerCount) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.serverId);
		_h_ += _h_ * 31 + (int)(this.playerCount);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(serverId).append(",");
		_sb_.append(playerCount).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
