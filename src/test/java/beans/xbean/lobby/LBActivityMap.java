package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class LBActivityMap extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, LBActivityInfo> activities;  // 活动集合
    private java.util.concurrent.ConcurrentHashMap<Integer, LBActivityInfo> historyActivities;  // 上一次活动记录

    LBActivityMap(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.activities = new java.util.concurrent.ConcurrentHashMap<Integer, LBActivityInfo>();
		this.historyActivities = new java.util.concurrent.ConcurrentHashMap<Integer, LBActivityInfo>();
	}

	public LBActivityMap() {
		this((warrior.mdb.XBean)null);
	}

	public LBActivityMap(LBActivityMap _o_) {
		this(_o_, null);
	}

	LBActivityMap(LBActivityMap _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.activities = new java.util.concurrent.ConcurrentHashMap<Integer, LBActivityInfo>();
		_o_.activities.forEach((_k_, _v_) -> this.activities.put(_k_, new LBActivityInfo(_v_)));
		this.historyActivities = new java.util.concurrent.ConcurrentHashMap<Integer, LBActivityInfo>();
		_o_.historyActivities.forEach((_k_, _v_) -> this.historyActivities.put(_k_, new LBActivityInfo(_v_)));
	}

	public void copyFrom(LBActivityMap _o_) {
        this.activities = new java.util.concurrent.ConcurrentHashMap<Integer, LBActivityInfo>();
		_o_.activities.forEach((_k_, _v_) -> this.activities.put(_k_, new LBActivityInfo(_v_)));
        this.historyActivities = new java.util.concurrent.ConcurrentHashMap<Integer, LBActivityInfo>();
		_o_.historyActivities.forEach((_k_, _v_) -> this.historyActivities.put(_k_, new LBActivityInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, LBActivityInfo> getActivities(){
		return this.activities;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, LBActivityInfo> getHistoryActivities(){
		return this.historyActivities;
	}

	@JSONField(name = "1")
	public void setActivities(java.util.concurrent.ConcurrentHashMap<Integer, LBActivityInfo> _v_){
		this.activities = _v_;
	}

	@JSONField(name = "2")
	public void setHistoryActivities(java.util.concurrent.ConcurrentHashMap<Integer, LBActivityInfo> _v_){
		this.historyActivities = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LBActivityMap) {
			LBActivityMap _o_ = (LBActivityMap)_o1_;
			if (!this.activities.equals(_o_.activities)) return false;
			if (!this.historyActivities.equals(_o_.historyActivities)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + this.activities.hashCode();
		_h_ += _h_ * 31 + this.historyActivities.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(activities).append(",");
		_sb_.append(historyActivities).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
