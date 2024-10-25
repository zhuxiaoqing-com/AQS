package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class TroopMerge extends warrior.mdb.XBean {
    private long id; 
    private long initTroop; 
    private long remTroop; 
    private long expireTime; 

    TroopMerge(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public TroopMerge() {
		this((warrior.mdb.XBean)null);
	}

	public TroopMerge(TroopMerge _o_) {
		this(_o_, null);
	}

	TroopMerge(TroopMerge _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.initTroop = _o_.initTroop;
		this.remTroop = _o_.remTroop;
		this.expireTime = _o_.expireTime;
	}

	public void copyFrom(TroopMerge _o_) {
        this.id = _o_.id;
        this.initTroop = _o_.initTroop;
        this.remTroop = _o_.remTroop;
        this.expireTime = _o_.expireTime;
	}


	@JSONField(name = "1")
	public long getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public long getInitTroop(){
		return this.initTroop;
	}

	@JSONField(name = "3")
	public long getRemTroop(){
		return this.remTroop;
	}

	@JSONField(name = "4")
	public long getExpireTime(){
		return this.expireTime;
	}

	@JSONField(name = "1")
	public void setId(long _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setInitTroop(long _v_){
		this.initTroop = _v_;
	}

	@JSONField(name = "3")
	public void setRemTroop(long _v_){
		this.remTroop = _v_;
	}

	@JSONField(name = "4")
	public void setExpireTime(long _v_){
		this.expireTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TroopMerge) {
			TroopMerge _o_ = (TroopMerge)_o1_;
			if (this.id != _o_.id) return false;
			if (this.initTroop != _o_.initTroop) return false;
			if (this.remTroop != _o_.remTroop) return false;
			if (this.expireTime != _o_.expireTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(id ^ (id >>> 32));
		_h_ += _h_ * 31 + (int)(initTroop ^ (initTroop >>> 32));
		_h_ += _h_ * 31 + (int)(remTroop ^ (remTroop >>> 32));
		_h_ += _h_ * 31 + (int)(expireTime ^ (expireTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(initTroop).append(",");
		_sb_.append(remTroop).append(",");
		_sb_.append(expireTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
