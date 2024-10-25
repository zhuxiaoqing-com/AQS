package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class Buff extends warrior.mdb.XBean {
    private int bid;  // buffId
    private long stime;  // buff开始时间
    private int etime;  // buff影响时间
    private long ctime;  // 对于周期性buff

    Buff(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public Buff() {
		this((warrior.mdb.XBean)null);
	}

	public Buff(Buff _o_) {
		this(_o_, null);
	}

	Buff(Buff _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.bid = _o_.bid;
		this.stime = _o_.stime;
		this.etime = _o_.etime;
		this.ctime = _o_.ctime;
	}

	public void copyFrom(Buff _o_) {
        this.bid = _o_.bid;
        this.stime = _o_.stime;
        this.etime = _o_.etime;
        this.ctime = _o_.ctime;
	}


	@JSONField(name = "1")
	public int getBid(){
		return this.bid;
	}

	@JSONField(name = "2")
	public long getStime(){
		return this.stime;
	}

	@JSONField(name = "3")
	public int getEtime(){
		return this.etime;
	}

	@JSONField(name = "4")
	public long getCtime(){
		return this.ctime;
	}

	@JSONField(name = "1")
	public void setBid(int _v_){
		this.bid = _v_;
	}

	@JSONField(name = "2")
	public void setStime(long _v_){
		this.stime = _v_;
	}

	@JSONField(name = "3")
	public void setEtime(int _v_){
		this.etime = _v_;
	}

	@JSONField(name = "4")
	public void setCtime(long _v_){
		this.ctime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Buff) {
			Buff _o_ = (Buff)_o1_;
			if (this.bid != _o_.bid) return false;
			if (this.stime != _o_.stime) return false;
			if (this.etime != _o_.etime) return false;
			if (this.ctime != _o_.ctime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.bid);
		_h_ += _h_ * 31 + (int)(stime ^ (stime >>> 32));
		_h_ += _h_ * 31 + (int)(this.etime);
		_h_ += _h_ * 31 + (int)(ctime ^ (ctime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(bid).append(",");
		_sb_.append(stime).append(",");
		_sb_.append(etime).append(",");
		_sb_.append(ctime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
