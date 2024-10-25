package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class OfflinePlayerEmails extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, OfflineEmailInfo> emailinfos;  // 玩家所有邮件

    OfflinePlayerEmails(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.emailinfos = new java.util.concurrent.ConcurrentHashMap<Long, OfflineEmailInfo>();
	}

	public OfflinePlayerEmails() {
		this((warrior.mdb.XBean)null);
	}

	public OfflinePlayerEmails(OfflinePlayerEmails _o_) {
		this(_o_, null);
	}

	OfflinePlayerEmails(OfflinePlayerEmails _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.emailinfos = new java.util.concurrent.ConcurrentHashMap<Long, OfflineEmailInfo>();
		_o_.emailinfos.forEach((_k_, _v_) -> this.emailinfos.put(_k_, new OfflineEmailInfo(_v_)));
	}

	public void copyFrom(OfflinePlayerEmails _o_) {
        this.emailinfos = new java.util.concurrent.ConcurrentHashMap<Long, OfflineEmailInfo>();
		_o_.emailinfos.forEach((_k_, _v_) -> this.emailinfos.put(_k_, new OfflineEmailInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, OfflineEmailInfo> getEmailinfos(){
		return this.emailinfos;
	}

	@JSONField(name = "1")
	public void setEmailinfos(java.util.concurrent.ConcurrentHashMap<Long, OfflineEmailInfo> _v_){
		this.emailinfos = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof OfflinePlayerEmails) {
			OfflinePlayerEmails _o_ = (OfflinePlayerEmails)_o1_;
			if (!this.emailinfos.equals(_o_.emailinfos)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.emailinfos.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(emailinfos).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
