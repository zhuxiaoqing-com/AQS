package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class Quest extends warrior.mdb.XBean {
    private int id;  // 任务id
    private int state;  // 任务状态
    private int ct;  // 任务条件值
    private long expireTime;  // 过期时间

    Quest(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public Quest() {
		this((warrior.mdb.XBean)null);
	}

	public Quest(Quest _o_) {
		this(_o_, null);
	}

	Quest(Quest _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.state = _o_.state;
		this.ct = _o_.ct;
		this.expireTime = _o_.expireTime;
	}

	public void copyFrom(Quest _o_) {
        this.id = _o_.id;
        this.state = _o_.state;
        this.ct = _o_.ct;
        this.expireTime = _o_.expireTime;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public int getState(){
		return this.state;
	}

	@JSONField(name = "3")
	public int getCt(){
		return this.ct;
	}

	@JSONField(name = "4")
	public long getExpireTime(){
		return this.expireTime;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setState(int _v_){
		this.state = _v_;
	}

	@JSONField(name = "3")
	public void setCt(int _v_){
		this.ct = _v_;
	}

	@JSONField(name = "4")
	public void setExpireTime(long _v_){
		this.expireTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Quest) {
			Quest _o_ = (Quest)_o1_;
			if (this.id != _o_.id) return false;
			if (this.state != _o_.state) return false;
			if (this.ct != _o_.ct) return false;
			if (this.expireTime != _o_.expireTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + (int)(this.state);
		_h_ += _h_ * 31 + (int)(this.ct);
		_h_ += _h_ * 31 + (int)(expireTime ^ (expireTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(state).append(",");
		_sb_.append(ct).append(",");
		_sb_.append(expireTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
