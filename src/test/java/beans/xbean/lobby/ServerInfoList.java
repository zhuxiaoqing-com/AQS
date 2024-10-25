package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ServerInfoList extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, ServerInfo> serverInfoMap;  // key:服务器id value:服务器数据

    ServerInfoList(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.serverInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, ServerInfo>();
	}

	public ServerInfoList() {
		this((warrior.mdb.XBean)null);
	}

	public ServerInfoList(ServerInfoList _o_) {
		this(_o_, null);
	}

	ServerInfoList(ServerInfoList _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.serverInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, ServerInfo>();
		_o_.serverInfoMap.forEach((_k_, _v_) -> this.serverInfoMap.put(_k_, new ServerInfo(_v_)));
	}

	public void copyFrom(ServerInfoList _o_) {
        this.serverInfoMap = new java.util.concurrent.ConcurrentHashMap<Integer, ServerInfo>();
		_o_.serverInfoMap.forEach((_k_, _v_) -> this.serverInfoMap.put(_k_, new ServerInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, ServerInfo> getServerInfoMap(){
		return this.serverInfoMap;
	}

	@JSONField(name = "1")
	public void setServerInfoMap(java.util.concurrent.ConcurrentHashMap<Integer, ServerInfo> _v_){
		this.serverInfoMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ServerInfoList) {
			ServerInfoList _o_ = (ServerInfoList)_o1_;
			if (!this.serverInfoMap.equals(_o_.serverInfoMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.serverInfoMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(serverInfoMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
