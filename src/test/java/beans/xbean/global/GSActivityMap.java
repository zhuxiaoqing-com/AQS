package beans.xbean.global;
import com.alibaba.fastjson.annotation.JSONField;


public final class GSActivityMap extends warrior.mdb.XBean {
    private java.util.concurrent.ConcurrentHashMap<Integer, GSActivityInfo> activities;  // 活动集合
    private java.util.concurrent.ConcurrentHashMap<Integer, GSActivityInfo> historyActivities;  // 上一次活动记录

    GSActivityMap(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.activities = new java.util.concurrent.ConcurrentHashMap<Integer, GSActivityInfo>();
		this.historyActivities = new java.util.concurrent.ConcurrentHashMap<Integer, GSActivityInfo>();
	}

	public GSActivityMap() {
		this((warrior.mdb.XBean)null);
	}

	public GSActivityMap(GSActivityMap _o_) {
		this(_o_, null);
	}

	GSActivityMap(GSActivityMap _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.activities = new java.util.concurrent.ConcurrentHashMap<Integer, GSActivityInfo>();
		_o_.activities.forEach((_k_, _v_) -> this.activities.put(_k_, new GSActivityInfo(_v_)));
		this.historyActivities = new java.util.concurrent.ConcurrentHashMap<Integer, GSActivityInfo>();
		_o_.historyActivities.forEach((_k_, _v_) -> this.historyActivities.put(_k_, new GSActivityInfo(_v_)));
	}

	public void copyFrom(GSActivityMap _o_) {
        this.activities = new java.util.concurrent.ConcurrentHashMap<Integer, GSActivityInfo>();
		_o_.activities.forEach((_k_, _v_) -> this.activities.put(_k_, new GSActivityInfo(_v_)));
        this.historyActivities = new java.util.concurrent.ConcurrentHashMap<Integer, GSActivityInfo>();
		_o_.historyActivities.forEach((_k_, _v_) -> this.historyActivities.put(_k_, new GSActivityInfo(_v_)));
	}


	@JSONField(name = "1")
	public java.util.concurrent.ConcurrentHashMap<Integer, GSActivityInfo> getActivities(){
		return this.activities;
	}

	@JSONField(name = "2")
	public java.util.concurrent.ConcurrentHashMap<Integer, GSActivityInfo> getHistoryActivities(){
		return this.historyActivities;
	}

	@JSONField(name = "1")
	public void setActivities(java.util.concurrent.ConcurrentHashMap<Integer, GSActivityInfo> _v_){
		this.activities = _v_;
	}

	@JSONField(name = "2")
	public void setHistoryActivities(java.util.concurrent.ConcurrentHashMap<Integer, GSActivityInfo> _v_){
		this.historyActivities = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GSActivityMap) {
			GSActivityMap _o_ = (GSActivityMap)_o1_;
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
