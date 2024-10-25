package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LimitUser extends warrior.mdb.XBean {
    private long limitT;  // 封禁时间
    private String reason;  // 封禁原因

    LimitUser(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.reason = "";
	}

	public LimitUser() {
		this((warrior.mdb.XBean)null);
	}

	public LimitUser(LimitUser _o_) {
		this(_o_, null);
	}

	LimitUser(LimitUser _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.limitT = _o_.limitT;
		this.reason = _o_.reason;
	}

	public void copyFrom(LimitUser _o_) {
        this.limitT = _o_.limitT;
        this.reason = _o_.reason;
	}


	@JSONField(name = "1")
	public long getLimitT(){
		return this.limitT;
	}

	@JSONField(name = "2")
	public String getReason(){
		return this.reason;
	}

	@JSONField(name = "1")
	public void setLimitT(long _v_){
		this.limitT = _v_;
	}

	@JSONField(name = "2")
	public void setReason(String _v_){
		this.reason = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LimitUser) {
			LimitUser _o_ = (LimitUser)_o1_;
			if (this.limitT != _o_.limitT) return false;
			if (!this.reason.equals(_o_.reason)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(limitT ^ (limitT >>> 32));
		_h_ += _h_ * 31 + this.reason.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(limitT).append(",");
		_sb_.append("T").append(reason.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
