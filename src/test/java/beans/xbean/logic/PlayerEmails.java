package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class PlayerEmails extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Long, EmailInfo> emailinfos;  // 玩家所有邮件
    private long receiveServerEmailId;  // 已经领取过的全服邮件id

    PlayerEmails(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.emailinfos = new java.util.concurrent.ConcurrentHashMap<Long, EmailInfo>();
	}

	public PlayerEmails() {
		this((warrior.mdb.XBean)null);
	}

	public PlayerEmails(PlayerEmails _o_) {
		this(_o_, null);
	}

	PlayerEmails(PlayerEmails _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.emailinfos = new java.util.concurrent.ConcurrentHashMap<Long, EmailInfo>();
		_o_.emailinfos.forEach((_k_, _v_) -> this.emailinfos.put(_k_, new EmailInfo(_v_)));
		this.receiveServerEmailId = _o_.receiveServerEmailId;
	}

	public void copyFrom(PlayerEmails _o_) {
        this.emailinfos = new java.util.concurrent.ConcurrentHashMap<Long, EmailInfo>();
		_o_.emailinfos.forEach((_k_, _v_) -> this.emailinfos.put(_k_, new EmailInfo(_v_)));
        this.receiveServerEmailId = _o_.receiveServerEmailId;
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Long, EmailInfo> getEmailinfos(){
		return this.emailinfos;
	}

	@JSONField(name = "2")
	public long getReceiveServerEmailId(){
		return this.receiveServerEmailId;
	}

	@JSONField(name = "1")
	public void setEmailinfos(java.util.concurrent.ConcurrentHashMap<Long, EmailInfo> _v_){
		this.emailinfos = _v_;
	}

	@JSONField(name = "2")
	public void setReceiveServerEmailId(long _v_){
		this.receiveServerEmailId = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PlayerEmails) {
			PlayerEmails _o_ = (PlayerEmails)_o1_;
			if (!this.emailinfos.equals(_o_.emailinfos)) return false;
			if (this.receiveServerEmailId != _o_.receiveServerEmailId) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.emailinfos.hashCode();
		_h_ += _h_ * 31 + (int)(receiveServerEmailId ^ (receiveServerEmailId >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(emailinfos).append(",");
		_sb_.append(receiveServerEmailId).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
