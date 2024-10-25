package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBCarrierLog extends warrior.mdb.XBean {
    private int id; 
    private java.util.concurrent.CopyOnWriteArrayList<String> cparams; 
    private long time; 
    private int avatar;  // 头像id 帮助日志需要

    LBCarrierLog(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.cparams = new java.util.concurrent.CopyOnWriteArrayList<String>();
	}

	public LBCarrierLog() {
		this((warrior.mdb.XBean)null);
	}

	public LBCarrierLog(LBCarrierLog _o_) {
		this(_o_, null);
	}

	LBCarrierLog(LBCarrierLog _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.id = _o_.id;
		this.cparams = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.cparams.forEach(_v_ -> this.cparams.add(_v_));
		this.time = _o_.time;
		this.avatar = _o_.avatar;
	}

	public void copyFrom(LBCarrierLog _o_) {
        this.id = _o_.id;
        this.cparams = new java.util.concurrent.CopyOnWriteArrayList<String>();
		_o_.cparams.forEach(_v_ -> this.cparams.add(_v_));
        this.time = _o_.time;
        this.avatar = _o_.avatar;
	}


	@JSONField(name = "1")
	public int getId(){
		return this.id;
	}

	@JSONField(name = "2")
	public java.util.concurrent.CopyOnWriteArrayList<String> getCparams(){
		return this.cparams;
	}

	@JSONField(name = "3")
	public long getTime(){
		return this.time;
	}

	@JSONField(name = "4")
	public int getAvatar(){
		return this.avatar;
	}

	@JSONField(name = "1")
	public void setId(int _v_){
		this.id = _v_;
	}

	@JSONField(name = "2")
	public void setCparams(java.util.concurrent.CopyOnWriteArrayList<String> _v_){
		this.cparams = _v_;
	}

	@JSONField(name = "3")
	public void setTime(long _v_){
		this.time = _v_;
	}

	@JSONField(name = "4")
	public void setAvatar(int _v_){
		this.avatar = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBCarrierLog) {
			LBCarrierLog _o_ = (LBCarrierLog)_o1_;
			if (this.id != _o_.id) return false;
			if (!this.cparams.equals(_o_.cparams)) return false;
			if (this.time != _o_.time) return false;
			if (this.avatar != _o_.avatar) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.id);
		_h_ += _h_ * 31 + this.cparams.hashCode();
		_h_ += _h_ * 31 + (int)(time ^ (time >>> 32));
		_h_ += _h_ * 31 + (int)(this.avatar);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(id).append(",");
		_sb_.append(cparams).append(",");
		_sb_.append(time).append(",");
		_sb_.append(avatar).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
