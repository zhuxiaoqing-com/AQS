package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class ActTime extends warrior.mdb.XBean {
    private long startTime; 
    private long endTime; 
    private boolean open; 
    private long nextStartTime; 
    private long nextEndTime; 
    private long sessionStartTime;  // 场次结束时设置下一场的开始时间,场次结束时清零 用来做当前是哪一场的判断

    ActTime(warrior.mdb.XBean _xp_) {
        super(_xp_);
	}

	public ActTime() {
		this((warrior.mdb.XBean)null);
	}

	public ActTime(ActTime _o_) {
		this(_o_, null);
	}

	ActTime(ActTime _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.startTime = _o_.startTime;
		this.endTime = _o_.endTime;
		this.open = _o_.open;
		this.nextStartTime = _o_.nextStartTime;
		this.nextEndTime = _o_.nextEndTime;
		this.sessionStartTime = _o_.sessionStartTime;
	}

	public void copyFrom(ActTime _o_) {
        this.startTime = _o_.startTime;
        this.endTime = _o_.endTime;
        this.open = _o_.open;
        this.nextStartTime = _o_.nextStartTime;
        this.nextEndTime = _o_.nextEndTime;
        this.sessionStartTime = _o_.sessionStartTime;
	}


	@JSONField(name = "1")
	public long getStartTime(){
		return this.startTime;
	}

	@JSONField(name = "2")
	public long getEndTime(){
		return this.endTime;
	}

	@JSONField(name = "3")
	public boolean getOpen(){
		return this.open;
	}

	@JSONField(name = "4")
	public long getNextStartTime(){
		return this.nextStartTime;
	}

	@JSONField(name = "5")
	public long getNextEndTime(){
		return this.nextEndTime;
	}

	@JSONField(name = "6")
	public long getSessionStartTime(){
		return this.sessionStartTime;
	}

	@JSONField(name = "1")
	public void setStartTime(long _v_){
		this.startTime = _v_;
	}

	@JSONField(name = "2")
	public void setEndTime(long _v_){
		this.endTime = _v_;
	}

	@JSONField(name = "3")
	public void setOpen(boolean _v_){
		this.open = _v_;
	}

	@JSONField(name = "4")
	public void setNextStartTime(long _v_){
		this.nextStartTime = _v_;
	}

	@JSONField(name = "5")
	public void setNextEndTime(long _v_){
		this.nextEndTime = _v_;
	}

	@JSONField(name = "6")
	public void setSessionStartTime(long _v_){
		this.sessionStartTime = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ActTime) {
			ActTime _o_ = (ActTime)_o1_;
			if (this.startTime != _o_.startTime) return false;
			if (this.endTime != _o_.endTime) return false;
			if (this.open != _o_.open) return false;
			if (this.nextStartTime != _o_.nextStartTime) return false;
			if (this.nextEndTime != _o_.nextEndTime) return false;
			if (this.sessionStartTime != _o_.sessionStartTime) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(startTime ^ (startTime >>> 32));
		_h_ += _h_ * 31 + (int)(endTime ^ (endTime >>> 32));
		_h_ += _h_ * 31 + (this.open ? 1231 : 1237);
		_h_ += _h_ * 31 + (int)(nextStartTime ^ (nextStartTime >>> 32));
		_h_ += _h_ * 31 + (int)(nextEndTime ^ (nextEndTime >>> 32));
		_h_ += _h_ * 31 + (int)(sessionStartTime ^ (sessionStartTime >>> 32));
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(startTime).append(",");
		_sb_.append(endTime).append(",");
		_sb_.append(open).append(",");
		_sb_.append(nextStartTime).append(",");
		_sb_.append(nextEndTime).append(",");
		_sb_.append(sessionStartTime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
