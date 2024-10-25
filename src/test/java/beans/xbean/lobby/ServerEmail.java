package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ServerEmail extends warrior.mdb.XBean {
    private long serverEmailId;  // 服务器邮件自增id
    private java.util.concurrent.ConcurrentHashMap<Long, ServerEmailInfo> emailMap;  // k:唯一id v:全服邮件数据

    ServerEmail(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.emailMap = new java.util.concurrent.ConcurrentHashMap<Long, ServerEmailInfo>();
	}

	public ServerEmail() {
		this((warrior.mdb.XBean)null);
	}

	public ServerEmail(ServerEmail _o_) {
		this(_o_, null);
	}

	ServerEmail(ServerEmail _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.serverEmailId = _o_.serverEmailId;
		this.emailMap = new java.util.concurrent.ConcurrentHashMap<Long, ServerEmailInfo>();
		_o_.emailMap.forEach((_k_, _v_) -> this.emailMap.put(_k_, new ServerEmailInfo(_v_)));
	}

	public void copyFrom(ServerEmail _o_) {
        this.serverEmailId = _o_.serverEmailId;
        this.emailMap = new java.util.concurrent.ConcurrentHashMap<Long, ServerEmailInfo>();
		_o_.emailMap.forEach((_k_, _v_) -> this.emailMap.put(_k_, new ServerEmailInfo(_v_)));
	}


	@JSONField(name = "1")
	public long getServerEmailId(){
		return this.serverEmailId;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Long, ServerEmailInfo> getEmailMap(){
		return this.emailMap;
	}

	@JSONField(name = "1")
	public void setServerEmailId(long _v_){
		this.serverEmailId = _v_;
	}

	@JSONField(name = "2")
	public void setEmailMap(java.util.concurrent.ConcurrentHashMap<Long, ServerEmailInfo> _v_){
		this.emailMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ServerEmail) {
			ServerEmail _o_ = (ServerEmail)_o1_;
			if (this.serverEmailId != _o_.serverEmailId) return false;
			if (!this.emailMap.equals(_o_.emailMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(serverEmailId ^ (serverEmailId >>> 32));
		_h_ += _h_ * 31 + this.emailMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(serverEmailId).append(",");
		_sb_.append(emailMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
