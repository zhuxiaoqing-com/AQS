package beans.xbean.lobby;
import com.alibaba.fastjson.annotation.JSONField;


public final class TroopStatistics extends warrior.mdb.XBean {
    private int refreshType; 
    private long lastFreshTime; 
    private java.util.concurrent.ConcurrentHashMap<Integer, Integer> dataMap; 

    TroopStatistics(warrior.mdb.XBean _xp_) {
        super(_xp_);
		this.dataMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
	}

	public TroopStatistics() {
		this((warrior.mdb.XBean)null);
	}

	public TroopStatistics(TroopStatistics _o_) {
		this(_o_, null);
	}

	TroopStatistics(TroopStatistics _o_, warrior.mdb.XBean _xp_) {
		super(_xp_);
		this.refreshType = _o_.refreshType;
		this.lastFreshTime = _o_.lastFreshTime;
		this.dataMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dataMap.forEach((_k_, _v_) -> this.dataMap.put(_k_, _v_));
	}

	public void copyFrom(TroopStatistics _o_) {
        this.refreshType = _o_.refreshType;
        this.lastFreshTime = _o_.lastFreshTime;
        this.dataMap = new java.util.concurrent.ConcurrentHashMap<Integer, Integer>();
		_o_.dataMap.forEach((_k_, _v_) -> this.dataMap.put(_k_, _v_));
	}


	@JSONField(name = "1")
	public int getRefreshType(){
		return this.refreshType;
	}

	@JSONField(name = "2")
	public long getLastFreshTime(){
		return this.lastFreshTime;
	}

	@JSONField(name = "3")
	public java.util.concurrent.ConcurrentHashMap<Integer, Integer> getDataMap(){
		return this.dataMap;
	}

	@JSONField(name = "1")
	public void setRefreshType(int _v_){
		this.refreshType = _v_;
	}

	@JSONField(name = "2")
	public void setLastFreshTime(long _v_){
		this.lastFreshTime = _v_;
	}

	@JSONField(name = "3")
	public void setDataMap(java.util.concurrent.ConcurrentHashMap<Integer, Integer> _v_){
		this.dataMap = _v_;
	}

	@Override
	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TroopStatistics) {
			TroopStatistics _o_ = (TroopStatistics)_o1_;
			if (this.refreshType != _o_.refreshType) return false;
			if (this.lastFreshTime != _o_.lastFreshTime) return false;
			if (!this.dataMap.equals(_o_.dataMap)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int _h_ = 0;
		_h_ += _h_ * 31 + (int)(this.refreshType);
		_h_ += _h_ * 31 + (int)(lastFreshTime ^ (lastFreshTime >>> 32));
		_h_ += _h_ * 31 + this.dataMap.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		StringBuilder _sb_ = new StringBuilder(super.toString());
		_sb_.append("=(");
		_sb_.append(refreshType).append(",");
		_sb_.append(lastFreshTime).append(",");
		_sb_.append(dataMap).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}
