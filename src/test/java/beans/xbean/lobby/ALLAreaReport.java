package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ALLAreaReport extends warrior.mdb.XBean {
    private long srl;  // srl
    private long startTime;  // 开始时间
    private long endTime;  // 结束时间

    ALLAreaReport(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public ALLAreaReport() {
		this((warrior.mdb.XBean)null);
	}

	public ALLAreaReport(ALLAreaReport _o_) {
		this(_o_, null);
	}

	ALLAreaReport(ALLAreaReport _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.srl = _o_.srl;
		this.startTime = _o_.startTime;
		this.endTime = _o_.endTime;
	}

	public void copyFrom(ALLAreaReport _o_) {
        this.srl = _o_.srl;
        this.startTime = _o_.startTime;
        this.endTime = _o_.endTime;
	}


	@JSONField(name = "1")
	public long getSrl(){
		return this.srl;
	}

	@JSONField(name = "2")
	public long getStartTime(){
		return this.startTime;
	}

	@JSONField(name = "3")
	public long getEndTime(){
		return this.endTime;
	}

	@JSONField(name = "1")
	public void setSrl(long _v_){
		this.srl = _v_;
	}

	@JSONField(name = "2")
	public void setStartTime(long _v_){
		this.startTime = _v_;
	}

	@JSONField(name = "3")
	public void setEndTime(long _v_){
		this.endTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ALLAreaReport) {
			ALLAreaReport _o_ = (ALLAreaReport)_o1_;
			if (this.srl != _o_.srl) return false;
			if (this.startTime != _o_.startTime) return false;
			if (this.endTime != _o_.endTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(srl ^ (srl >>> 32));
		_h_ += _h_ * 31 + (int)(startTime ^ (startTime >>> 32));
		_h_ += _h_ * 31 + (int)(endTime ^ (endTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(srl).append(",");
		_sb_.append(startTime).append(",");
		_sb_.append(endTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
