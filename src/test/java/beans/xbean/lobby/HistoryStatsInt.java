package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class HistoryStatsInt extends warrior.mdb.XBean {
    private java.util.concurrent.CopyOnWriteArrayList<Integer> sequence; 
    private long lastResetTime; 
    private int maxSize; 

    HistoryStatsInt(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.sequence = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
	}

	public HistoryStatsInt() {
		this((warrior.mdb.XBean)null);
	}

	public HistoryStatsInt(HistoryStatsInt _o_) {
		this(_o_, null);
	}

	HistoryStatsInt(HistoryStatsInt _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.sequence = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.sequence.forEach(_v_ -> this.sequence.add(_v_));
		this.lastResetTime = _o_.lastResetTime;
		this.maxSize = _o_.maxSize;
	}

	public void copyFrom(HistoryStatsInt _o_) {
        this.sequence = new java.util.concurrent.CopyOnWriteArrayList<Integer>();
		_o_.sequence.forEach(_v_ -> this.sequence.add(_v_));
        this.lastResetTime = _o_.lastResetTime;
        this.maxSize = _o_.maxSize;
	}


	@JSONField(name = "1")
	public java.util.concurrent.CopyOnWriteArrayList<Integer> getSequence(){
		return this.sequence;
	}

	@JSONField(name = "2")
	public long getLastResetTime(){
		return this.lastResetTime;
	}

	@JSONField(name = "3")
	public int getMaxSize(){
		return this.maxSize;
	}

	@JSONField(name = "1")
	public void setSequence(java.util.concurrent.CopyOnWriteArrayList<Integer> _v_){
		this.sequence = _v_;
	}

	@JSONField(name = "2")
	public void setLastResetTime(long _v_){
		this.lastResetTime = _v_;
	}

	@JSONField(name = "3")
	public void setMaxSize(int _v_){
		this.maxSize = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof HistoryStatsInt) {
			HistoryStatsInt _o_ = (HistoryStatsInt)_o1_;
			if (!this.sequence.equals(_o_.sequence)) return false;
			if (this.lastResetTime != _o_.lastResetTime) return false;
			if (this.maxSize != _o_.maxSize) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.sequence.hashCode();
		_h_ += _h_ * 31 + (int)(lastResetTime ^ (lastResetTime >>> 32));
		_h_ += _h_ * 31 + (int)(this.maxSize);
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(sequence).append(",");
		_sb_.append(lastResetTime).append(",");
		_sb_.append(maxSize).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
