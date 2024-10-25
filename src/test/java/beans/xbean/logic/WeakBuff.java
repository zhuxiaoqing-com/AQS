package beans.xbean.logic;
import com.alibaba.fastjson.annotation.JSONField;


public final class WeakBuff extends warrior.mdb.XBean {
    private long stime;  // buff开始时间
    private long etime;  // buff影响时间

    WeakBuff(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public WeakBuff() {
		this((warrior.mdb.XBean)null);
	}

	public WeakBuff(WeakBuff _o_) {
		this(_o_, null);
	}

	WeakBuff(WeakBuff _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.stime = _o_.stime;
		this.etime = _o_.etime;
	}

	public void copyFrom(WeakBuff _o_) {
        this.stime = _o_.stime;
        this.etime = _o_.etime;
	}


	@JSONField(name = "1")
	public long getStime(){
		return this.stime;
	}

	@JSONField(name = "2")
	public long getEtime(){
		return this.etime;
	}

	@JSONField(name = "1")
	public void setStime(long _v_){
		this.stime = _v_;
	}

	@JSONField(name = "2")
	public void setEtime(long _v_){
		this.etime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof WeakBuff) {
			WeakBuff _o_ = (WeakBuff)_o1_;
			if (this.stime != _o_.stime) return false;
			if (this.etime != _o_.etime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(stime ^ (stime >>> 32));
		_h_ += _h_ * 31 + (int)(etime ^ (etime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(stime).append(",");
		_sb_.append(etime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
