package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LimitUidIp extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<String, LimitUser> limitIps;  // ip对应的封禁信息
    private java.util.concurrent.ConcurrentHashMap<String, LimitUser> limitUids;  // uuid对应的封禁信息

    LimitUidIp(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.limitIps = new java.util.concurrent.ConcurrentHashMap<String, LimitUser>();
		this.limitUids = new java.util.concurrent.ConcurrentHashMap<String, LimitUser>();
	}

	public LimitUidIp() {
		this((warrior.mdb.XBean)null);
	}

	public LimitUidIp(LimitUidIp _o_) {
		this(_o_, null);
	}

	LimitUidIp(LimitUidIp _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.limitIps = new java.util.concurrent.ConcurrentHashMap<String, LimitUser>();
		_o_.limitIps.forEach((_k_, _v_) -> this.limitIps.put(_k_, new LimitUser(_v_)));
		this.limitUids = new java.util.concurrent.ConcurrentHashMap<String, LimitUser>();
		_o_.limitUids.forEach((_k_, _v_) -> this.limitUids.put(_k_, new LimitUser(_v_)));
	}

	public void copyFrom(LimitUidIp _o_) {
        this.limitIps = new java.util.concurrent.ConcurrentHashMap<String, LimitUser>();
		_o_.limitIps.forEach((_k_, _v_) -> this.limitIps.put(_k_, new LimitUser(_v_)));
        this.limitUids = new java.util.concurrent.ConcurrentHashMap<String, LimitUser>();
		_o_.limitUids.forEach((_k_, _v_) -> this.limitUids.put(_k_, new LimitUser(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<String, LimitUser> getLimitIps(){
		return this.limitIps;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<String, LimitUser> getLimitUids(){
		return this.limitUids;
	}

	@JSONField(name = "1")
	public void setLimitIps(java.util.concurrent.ConcurrentHashMap<String, LimitUser> _v_){
		this.limitIps = _v_;
	}

	@JSONField(name = "2")
	public void setLimitUids(java.util.concurrent.ConcurrentHashMap<String, LimitUser> _v_){
		this.limitUids = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LimitUidIp) {
			LimitUidIp _o_ = (LimitUidIp)_o1_;
			if (!this.limitIps.equals(_o_.limitIps)) return false;
			if (!this.limitUids.equals(_o_.limitUids)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.limitIps.hashCode();
		_h_ += _h_ * 31 + this.limitUids.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(limitIps).append(",");
		_sb_.append(limitUids).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
